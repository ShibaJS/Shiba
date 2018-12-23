using System;
using System.Collections.Concurrent;
using System.Reflection.Metadata.Ecma335;
using System.Threading;
using System.Threading.Tasks;
using Windows.ApplicationModel.Appointments;

namespace ChakraHosting
{
    public class ChakraHost : IDisposable
    {
        private static JavaScriptSourceContext _currentSourceContext = JavaScriptSourceContext.FromIntPtr(IntPtr.Zero);
        private static JavaScriptRuntime _runtime;

        private static readonly BlockingCollection<JavaScriptValue> TaskQueue =
            new BlockingCollection<JavaScriptValue>();

        private static readonly JavaScriptPromiseContinuationCallback PromiseContinuationDelegate =
            PromiseContinuationCallback;

        private readonly CancellationTokenSource _shutdownCts = new CancellationTokenSource();
        private JavaScriptContext _context;

        public JavaScriptValue GlobalObject { get; private set; }

        public void Dispose()
        {
            _runtime.Dispose();
            _shutdownCts.Cancel();
        }

        public ChakraHost()
        {
            Init();
        }

        public void Init()
        {
            Native.ThrowIfError(Native.JsCreateRuntime(JavaScriptRuntimeAttributes.None, null, out _runtime));
            Native.ThrowIfError(Native.JsCreateContext(_runtime, out _context));
            WithContext(() =>
            {
                Native.ThrowIfError(Native.JsSetPromiseContinuationCallback(PromiseContinuationDelegate, IntPtr.Zero));
                StartPromiseTaskLoop(_shutdownCts.Token);
                Native.ThrowIfError(Native.JsProjectWinRTNamespace("Windows"));
                Native.ThrowIfError(Native.JsGetGlobalObject(out var global));
                GlobalObject = global;
            });
            //Native.ThrowIfError(Native.JsStartDebugging());
        }

        public void WithContext(Action action)
        {
            Native.ThrowIfError(Native.JsSetCurrentContext(_context));
            action.Invoke();
            Native.ThrowIfError(Native.JsSetCurrentContext(JavaScriptContext.Invalid));
        }

        public T WithContext<T>(Func<T> action)
        {
            Native.ThrowIfError(Native.JsSetCurrentContext(_context));
            var result = action.Invoke();
            Native.ThrowIfError(Native.JsSetCurrentContext(JavaScriptContext.Invalid));
            return result;
        }

        public JavaScriptValue RunScript(string script)
        {
            if (Native.JsRunScript(script, _currentSourceContext++, "", out var result) != JavaScriptErrorCode.NoError)
            {
                Native.ThrowIfError(Native.JsGetAndClearException(out var exception));
                Native.ThrowIfError(Native.JsGetPropertyIdFromName("message", out var messageName));
                Native.ThrowIfError(Native.JsGetProperty(exception, messageName, out var messageValue));
                return messageValue;
            }

            return result;
        }

        private static void PromiseContinuationCallback(JavaScriptValue task, IntPtr callbackState)
        {
            TaskQueue.Add(task);
            //task.AddRef();
        }

        private void StartPromiseTaskLoop(CancellationToken token)
        {
            Task.Factory.StartNew(() =>
                {
                    while (true)
                    {
                        try
                        {
                            if (TaskQueue.Count == 0) continue;
                            var task = TaskQueue.Take(token);
                            Native.ThrowIfError(Native.JsSetCurrentContext(_context));
                            task.CallFunction(GlobalObject);
                            task.Release();
                            Native.ThrowIfError(Native.JsSetCurrentContext(JavaScriptContext.Invalid));
                        }
                        catch (OperationCanceledException e)
                        {
                            return;
                        }
                    }
                }
                , token
            );
        }
    }
}