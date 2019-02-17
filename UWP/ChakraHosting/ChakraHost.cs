using System;
using System.Collections.Concurrent;
using System.Threading;
using System.Threading.Tasks;

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

        public ChakraHost()
        {
            Init();
        }

        public JavaScriptValue GlobalObject { get; private set; }

        public void Dispose()
        {
            _runtime.Dispose();
            _shutdownCts.Cancel();
        }

        public void Init()
        {
            Native.ThrowIfError(Native.JsCreateRuntime(JavaScriptRuntimeAttributes.EnableExperimentalFeatures, null, out _runtime));
            Native.ThrowIfError(Native.JsCreateContext(_runtime, out _context));
            EnterContext();
            Native.ThrowIfError(Native.JsSetPromiseContinuationCallback(PromiseContinuationDelegate, IntPtr.Zero));
            StartPromiseTaskLoop(_shutdownCts.Token);
            //Native.ThrowIfError(Native.JsProjectWinRTNamespace("Windows"));
            Native.ThrowIfError(Native.JsGetGlobalObject(out var global));
            GlobalObject = global;
            //Native.ThrowIfError(Native.JsStartDebugging());
            LeaveContext();
        }

        public void EnterContext()
        {
            Native.ThrowIfError(Native.JsSetCurrentContext(_context));
        }

        public void LeaveContext()
        {
            Native.ThrowIfError(Native.JsSetCurrentContext(JavaScriptContext.Invalid));
        }

        //public void WithContext(Action action)
        //{
        //    EnterContext();
        //    action.Invoke();
        //    LeaveContext();
        //}

        //public T WithContext<T>(Func<T> action)
        //{
        //    EnterContext();
        //    var result = action.Invoke();
        //    LeaveContext();
        //    return result;
        //}

        public JavaScriptValue RunScript(string script)
        {
            Native.ThrowIfError(Native.JsRunScript(script, _currentSourceContext++, "", out var result));
            return result;
        }

        private static void PromiseContinuationCallback(JavaScriptValue task, IntPtr callbackState)
        {
            TaskQueue.Add(task);
            task.AddRef();
        }

        private void StartPromiseTaskLoop(CancellationToken token)
        {
            Task.Factory.StartNew(() =>
                {
                    while (true)
                        try
                        {
                            if (TaskQueue.Count == 0) continue;
                            var task = TaskQueue.Take(token);
                            EnterContext();
                            task.CallFunction(GlobalObject);
                            task.Release();
                            LeaveContext();
                        }
                        catch (OperationCanceledException e)
                        {
                            return;
                        }
                }
                , token
            );
        }
    }
}