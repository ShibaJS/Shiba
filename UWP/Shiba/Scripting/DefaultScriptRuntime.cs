using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;
using Windows.ApplicationModel.Core;
using Windows.Foundation.Diagnostics;
using Windows.UI.Core;
using ChakraHosting;
using Newtonsoft.Json;
using Shiba.Controls;
using Shiba.Internal;
using Shiba.Scripting.Conversion;
using Shiba.Scripting.Runtime;
using Shiba.Scripting.Visitors;
using Console = System.Console;

namespace Shiba.Scripting
{
    public class DefaultScriptRuntime : IScriptRuntime, IDisposable
    {
        private readonly List<JavaScriptNativeFunction> _functions = new List<JavaScriptNativeFunction>();

        public DefaultScriptRuntime()
        {
            ChakraHost = new ChakraHost();
            ChakraHost.EnterContext();
            InitConversion();
            InitRuntimeObject();
            // Since the hole application will run with shiba, maybe we should not leave context until application is exit?
            //ChakraHost.LeaveContext();
        }

        public ChakraHost ChakraHost { get; private set; }

        public void Dispose()
        {
            ChakraHost?.LeaveContext();
            ChakraHost?.Dispose();
            ChakraHost = null;
        }

        public bool HasFunction(string name)
        {
            return HasFunction(ChakraHost.GlobalObject, name);
        }

        public bool HasFunction(object instance, string name)
        {
            if (!(instance is JavaScriptValue javaScriptValue))
            {
                return false;
            }

            return javaScriptValue.GetProperty(name.ToJavaScriptPropertyId()).ValueType == JavaScriptValueType.Function;
        }

        public object CallFunctionWithCustomThis(object thiz, string functionName, params object[] parameters)
        {
            //ChakraHost.EnterContext();
            if (!(thiz is JavaScriptValue javaScriptValue))
            {
                return null;
            }
            var func = ChakraHost.GlobalObject.GetProperty(
                JavaScriptPropertyId.FromString(functionName));
            object result = null;
            switch (func.ValueType)
            {
                case JavaScriptValueType.Function:
                {
                    var param = new []{javaScriptValue}.Concat(parameters.Select(it => it.ToJavaScriptValue())).ToArray();
                    
                    result = func.CallFunction(param).ToNative();
                }
                    break;
            }

            //ChakraHost.LeaveContext();
            return result;
        }

        public object CallFunction(string functionName, params object[] parameters)
        {
            return CallFunction(ChakraHost.GlobalObject, functionName, parameters);
        }

        public object CallFunction(object instance, string functionName, params object[] parameters)
        {
            //ChakraHost.EnterContext();
            if (!(instance is JavaScriptValue javaScriptValue))
            {
                return null;
            }
            var func = javaScriptValue.GetProperty(
                JavaScriptPropertyId.FromString(functionName));
            object result = null;
            switch (func.ValueType)
            {
                case JavaScriptValueType.Function:
                {
                    var param = new []{javaScriptValue}.Concat(parameters.Select(it => it.ToJavaScriptValue())).ToArray();
                    result = func.CallFunction(param).ToNative();
                }
                    break;
            }

            //ChakraHost.LeaveContext();
            return result;
        }

        public object GetProperty(object instance, string propertyName)
        {
            if (!(instance is JavaScriptValue javaScriptValue))
            {
                return null;
            }

            if (!javaScriptValue.HasProperty(propertyName.ToJavaScriptPropertyId()))
            {
                return null;
            }

            return javaScriptValue.GetProperty(propertyName.ToJavaScriptPropertyId()).ToNative();
        }

        public object GetAtIndex(object instance, int index)
        {
            if (!(instance is JavaScriptValue javaScriptValue))
            {
                return null;
            }

            if (javaScriptValue.ValueType != JavaScriptValueType.Array)
            {
                return null;
            }

            return javaScriptValue.GetIndexedProperty(JavaScriptValue.FromInt32(index)).ToNative();
        }

        public void AddRef(object instance)
        {
            if (!(instance is JavaScriptValue javaScriptValue))
            {
                return;
            }

            javaScriptValue.AddRef();
        }

        public void ReleaseObj(object instance)
        {
            if (!(instance is JavaScriptValue javaScriptValue))
            {
                return;
            }

            javaScriptValue.Release();
        }

        public bool IsArray(object instance)
        {
            if (!(instance is JavaScriptValue javaScriptValue))
            {
                return false;
            }

            return javaScriptValue.ValueType == JavaScriptValueType.Array;
        }

        public IEnumerable ToArray(object instance)
        {
            if (IsArray(instance))
            {
                var javascriptValue = (JavaScriptValue)instance;
                var result = new List<object>();
                var currentIndex = 0;
                while (javascriptValue.HasIndexedProperty(currentIndex.ToJavaScriptValue()))
                {
                    result.Add(javascriptValue.GetIndexedProperty(currentIndex.ToJavaScriptValue()).ToNative());
                    currentIndex++;
                }

                return result;
            }
            else
            {
                return Enumerable.Empty<object>();
            }
        }

        public object Execute(string script)
        {
            //ChakraHost.EnterContext();
            var result = ChakraHost.RunScript(script).ToNative();
            //ChakraHost.LeaveContext();
            return result;
        }

        public void AddObject(string name, object value)
        {
            if (value == null || string.IsNullOrEmpty(name)) throw new ArgumentException();

            //ChakraHost.EnterContext();
            var objPropertyId = JavaScriptPropertyId.FromString(name);
            switch (value)
            {
                case string _:
                case bool _:
                case int _:
                case decimal _:
                case float _:
                case double _:
                case null:
                    ChakraHost.GlobalObject.SetProperty(objPropertyId, value.ToJavaScriptValue(),
                        true);
                    break;
                default:
                    var obj = JavaScriptValue.CreateObject();
                    var type = value.GetType().GetTypeInfo();
                    var members = type.GetMembers()
                        .Where(it => it.GetCustomAttribute<JsExportAttribute>() != null);

                    foreach (var item in members)
                        switch (item)
                        {
                            case MethodInfo method:
                                var functionId =
                                    JavaScriptPropertyId.FromString(method.GetCustomAttribute<JsExportAttribute>()
                                        .Name);
                                var parameter = method.GetParameters();

                                JavaScriptValue Function(JavaScriptValue callee, bool call,
                                    JavaScriptValue[] arguments,
                                    ushort count, IntPtr data)
                                {
                                    object[] param;
                                    var args = arguments.Skip(1).ToArray();
                                    if (!args.Any() && parameter.Length > 0) return JavaScriptValue.Invalid;
                                    if (count >= parameter.Length)
                                        param = Enumerable.Range(0, parameter.Length)
                                            .Select(index => args.ElementAtOrDefault(index).ToNative()).ToArray();
                                    else
                                        param = args.Select(it => it.ToNative()).Concat(Enumerable
                                            .Range(count + 1, parameter.Length - count)
                                            .Select(
                                                index =>
                                                {
                                                    var paramType = parameter[index].ParameterType;
                                                    return paramType.IsValueType
                                                        ? Activator.CreateInstance(paramType)
                                                        : null;
                                                })).ToArray();

                                    var result = method.Invoke(value, param);
                                    return result.ToJavaScriptValue();
                                }

                                _functions.Add(Function);
                                var function = JavaScriptValue.CreateFunction(Function, IntPtr.Zero);
                                obj.SetProperty(functionId, function, true);
                                break;
                            case PropertyInfo property:
                                var propertyId =
                                    JavaScriptPropertyId.FromString(property.GetCustomAttribute<JsExportAttribute>()
                                        .Name);

                                obj.SetProperty(propertyId, property.GetValue(value).ToJavaScriptValue(), false);
                                break;
                        }

                    ChakraHost.GlobalObject.SetProperty(objPropertyId, obj, true);
                    break;
            }

            //ChakraHost.LeaveContext();
        }

        private void CreateRunShibaAppFunction()
        {
            var id = "runShibaApp".ToJavaScriptPropertyId();
            var function = JavaScriptValue.CreateFunction(RunShibaApp, IntPtr.Zero);
            ChakraHost.GlobalObject.SetProperty(id, function, true);
        }

        private void CreateRegisterComponentFunction()
        {
            var id = "registerComponent".ToJavaScriptPropertyId();
            var function = JavaScriptValue.CreateFunction(RegisterComponent, IntPtr.Zero);
            ChakraHost.GlobalObject.SetProperty(id, function, true);
        }

        private static JavaScriptValue RunShibaApp(JavaScriptValue callee, bool call,
            JavaScriptValue[] arguments,
            ushort count, IntPtr data)
        {
            var args = arguments.Skip(1).ToArray();
            if (count < 1)
            {
                return false.ToJavaScriptValue();
            }

            var view = args[0];
            var component = Singleton<JSViewVisitor>.Instance.Visit(view);
            if (component is View shibaView)
            {
                ShibaApp.Instance.AppComponent = shibaView;
                return true.ToJavaScriptValue();
            }

            return false.ToJavaScriptValue();
        }

        private static JavaScriptValue RegisterComponent(JavaScriptValue callee, bool call,
            JavaScriptValue[] arguments,
            ushort count, IntPtr data)
        {
            var args = arguments.Skip(1).ToArray();
            if (count < 2)
            {
                return false.ToJavaScriptValue();
            }

            var name = args[0].ToNative<string>();
            var view = args[1];
            

            if (string.IsNullOrEmpty(name))
            {
                return false.ToJavaScriptValue();
            }

            var component = Singleton<JSViewVisitor>.Instance.Visit(view);
            if (component is View shibaView)
            {
                if (ShibaApp.Instance.Components.ContainsKey(name))
                {
                    ShibaApp.Instance.Components[name] = shibaView;
                }
                else
                {
                    ShibaApp.Instance.Components.TryAdd(name, shibaView);
                }
                return true.ToJavaScriptValue();
            }
            return false.ToJavaScriptValue();
        }

        public void AddConversion(ITypeConversion conversion)
        {
            JavaScriptValueExtension.Conversions.Add(conversion);
        }


        public void AddConversion<T>(Func<T, JavaScriptValue> toJsValue,
            Func<JavaScriptValue, T> fromJsValue)
        {
            AddConversion(new TypeConversion(typeof(T),
                it => it is T target ? toJsValue.Invoke(target) : JavaScriptValue.Undefined,
                it => fromJsValue.Invoke(it)));
        }

        private void InitConversion()
        {
            AddConversion(new JTokenConversion());
            AddConversion(new PromiseConversion());
        }

        private void InitRuntimeObject()
        {
            AddObject("http", new Http());
            CreateRegisterComponentFunction();
            CreateRunShibaAppFunction();
        }
    }

    [AttributeUsage(AttributeTargets.Method | AttributeTargets.Property)]
    public sealed class JsExportAttribute : Attribute
    {
        public string Name { get; set; }
    }
}