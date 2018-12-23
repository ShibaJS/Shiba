using System;
using System.ComponentModel;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using ChakraCore.NET;
using ChakraCore.NET.API;

namespace Shiba.Scripting.Conversion
{
    public class PromiseConversion : ITypeConversion
    {
        public PromiseConversion()
        {
            ToJsValue = value =>
            {
                if (!(value is Task task))
                {
                    return JavaScriptValue.Undefined;
                }
                if (!(AbstractShiba.Instance.Configuration.ScriptRuntime is DefaultScriptRuntime runtime))
                {
                    return JavaScriptValue.Undefined;
                }
                JavaScriptValue.CreatePromise(out var result, out var resolve, out var reject);
                Task.Factory.StartNew(async () =>
                {
                    try
                    {
                        if (value.GetType().IsGenericType) //Task<>
                        {
                            dynamic dtask = task;
                            var taskResult = await dtask as object;
                            runtime.Context?.ServiceNode.WithContext(() => resolve.CallFunction(result, taskResult.ToJavaScriptValue()));
                        }
                        else //Task
                        {
                            await task;
                            runtime.Context?.ServiceNode.WithContext(() => resolve.CallFunction(result, JavaScriptValue.Invalid));
                        }
                    }
                    catch (Exception ex)
                    {
                        runtime.Context?.ServiceNode.WithContext(() => reject.CallFunction(result, JavaScriptValue.FromString(ex.Message)));
                    }
                });

                return result;
            };

            FromJsValue = value =>
            {
                return Task.Factory.FromAsync((callback, state) =>
                {

                    var result = new AsyncResult();

                    JavaScriptValue FulfilledCallback(JavaScriptValue callee, bool call, JavaScriptValue[] arguments,
                        ushort count, IntPtr data)
                    {
                        result.SetResult(arguments.Skip(1).FirstOrDefault().ToNative());
                        callback?.Invoke(result);
                        return JavaScriptValue.Invalid;
                    }

                    JavaScriptValue RejectCallback(JavaScriptValue callee, bool call, JavaScriptValue[] arguments,
                        ushort count, IntPtr data)
                    {
                        result.SetError(arguments.Skip(1).FirstOrDefault().ValueType == JavaScriptValueType.String
                            ? arguments.FirstOrDefault().ToString()
                            : string.Empty);
                        callback?.Invoke(result);
                        return JavaScriptValue.Invalid;
                    }


                    var thenProperty = value.GetProperty(JavaScriptPropertyId.FromString("then"));
                    thenProperty.CallFunction(value, JavaScriptValue.CreateFunction(FulfilledCallback, IntPtr.Zero),
                        JavaScriptValue.CreateFunction(RejectCallback, IntPtr.Zero));
                    return result;
                }, result =>
                {
                    var asyncResult = result as AsyncResult ?? throw new ArgumentException("Result is of wrong type.");
                    if (asyncResult.HasError)
                    {
                        throw new Exception(asyncResult.Error.ToString());
                    }
                    return asyncResult.Result;
                }, null);
            };
        }

        public Type ObjectType { get; } = typeof(Task);
        public Func<object, JavaScriptValue> ToJsValue { get; }
        public Func<JavaScriptValue, object> FromJsValue { get; }
        public bool CanConvert(JavaScriptValue value)
        {
            if (!value.HasProperty(JavaScriptPropertyId.FromString("constructor")))
            {
                return false;
            }
            var constructor = value.GetProperty(JavaScriptPropertyId.FromString("constructor"));
            if (!constructor.HasProperty(JavaScriptPropertyId.FromString("name")))
            {
                return false;
            }

            var name = constructor.GetProperty(JavaScriptPropertyId.FromString("name"));
            return name.ValueType == JavaScriptValueType.String && name.ToString() == "Promise";
        }
    }
    
    public class AsyncResult : IAsyncResult
    {
        private readonly ManualResetEvent _mre = new ManualResetEvent(false);

        public WaitHandle AsyncWaitHandle => _mre;

        public bool CompletedSynchronously { get; private set; } = false;

        public bool IsCompleted { get; private set; }

        public object AsyncState => throw new NotImplementedException();

        public string Error { get; private set; }
        public bool HasError { get; private set; } = false;

        private void Release()
        {
            IsCompleted = true;
            _mre.Set();
        }

        public void SetError(string error)
        {
            Error = error;
            HasError = true;
            Release();
        }
        public object Result { get; private set; }

        public void SetResult(object value)
        {
            Result = value;
            Release();
        }
    }
}