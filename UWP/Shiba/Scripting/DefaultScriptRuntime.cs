using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using ChakraHosting;
using Shiba.Controls;
using Shiba.Internal;
using Shiba.Scripting.Conversion;
using Shiba.Scripting.Runtime;
using Shiba.Scripting.Visitors;

namespace Shiba.Scripting
{
    public class DefaultScriptRuntime : IScriptRuntime, IDisposable
    {
        private readonly List<JavaScriptNativeFunction> _functions = new List<JavaScriptNativeFunction>();
        private readonly JavaScriptValue[] _prefix;

        public DefaultScriptRuntime()
        {
            ChakraHost = new ChakraHost();
            ChakraHost.EnterContext();
            _prefix = new[] {JavaScriptValue.FromBoolean(false)};
            InitConversion();
            InitRuntimeObject();
            ChakraHost.LeaveContext();
        }

        public ChakraHost ChakraHost { get; private set; }

        public void Dispose()
        {
            ChakraHost?.Dispose();
            ChakraHost = null;
        }

        public object Execute(string functionName, params object[] parameters)
        {
            ChakraHost.EnterContext();
            var func = ChakraHost.GlobalObject.GetProperty(
                JavaScriptPropertyId.FromString(functionName));
            object result = null;
            switch (func.ValueType)
            {
                case JavaScriptValueType.Function:
                {
                    var param = _prefix.Concat(parameters.Select(it => it.ToJavaScriptValue())).ToArray();
                    result = func.CallFunction(param).ToNative();
                }
                    break;
            }

            ChakraHost.LeaveContext();
            return result;
        }

        public object Execute(string script)
        {
            ChakraHost.EnterContext();
            var result = ChakraHost.RunScript(script).ToNative();
            ChakraHost.LeaveContext();
            return result;
        }

        public void AddObject(string name, object value)
        {
            if (value == null || string.IsNullOrEmpty(name)) throw new ArgumentException();

            ChakraHost.EnterContext();
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

            ChakraHost.LeaveContext();
        }

        private void CreateRegisterComponentFunction()
        {
            var id = JavaScriptPropertyId.FromString("registerComponent");
            var function = JavaScriptValue.CreateFunction(RegisterComponent, IntPtr.Zero);
            ChakraHost.GlobalObject.SetProperty(id, function, true);
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
            //AddObject("http", new Http());
            CreateRegisterComponentFunction();
        }
    }

    [AttributeUsage(AttributeTargets.Method | AttributeTargets.Property)]
    public sealed class JsExportAttribute : Attribute
    {
        public string Name { get; set; }
    }
}