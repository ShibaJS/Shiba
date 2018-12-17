using System;
using System.Linq;
using System.Reflection;
using ChakraCore.NET;
using ChakraCore.NET.API;
using Shiba.Scripting.Conversion;
using Shiba.Scripting.Runtime;

namespace Shiba.Scripting
{
    public class DefaultScriptRuntime : IScriptRuntime, IDisposable
    {
        private JavaScriptValue[] _prefix;

        public DefaultScriptRuntime(ChakraContext context = null)
        {
            Context = context ?? ChakraRuntime.Create().CreateContext(false);
            Context.ServiceNode.WithContext(() => { _prefix = new[] {JavaScriptValue.FromBoolean(false)}; });
            InitConversion();
            InitRuntimeObject();
        }

        public ChakraContext Context { get; private set; }

        public void Dispose()
        {
            Context?.Dispose();
            Context = null;
        }

        public object Execute(string functionName, params object[] parameters)
        {
            return Context.ServiceNode.WithContext(() =>
            {
                var func = Context.GlobalObject.ReferenceValue.GetProperty(
                    JavaScriptPropertyId.FromString(functionName));

                switch (func.ValueType)
                {
                    case JavaScriptValueType.Function:
                    {
                        var param = _prefix.Concat(parameters.Select(it => it.ToJavaScriptValue())).ToArray();

                        var result = func.CallFunction(param);

                        return result.ToNative();
                    }
                    default:
                        return null;
                }
            });
        }

        public object Execute(string script)
        {
            return Context.ServiceNode.WithContext(() =>
            {
                var debugService = Context.ServiceNode.GetService<IRuntimeDebuggingService>();
                var result = JavaScriptContext.RunScript(script, debugService.GetScriptContext("Script", script));
                return result.ToNative();
            });
        }

        public void AddObject(string name, object value)
        {
            if (value == null || string.IsNullOrEmpty(name)) throw new ArgumentException();

            Context.ServiceNode.WithContext(() =>
            {
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
                        Context.GlobalObject.ReferenceValue.SetProperty(objPropertyId, value.ToJavaScriptValue(),
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
                                        if (count >= parameter.Length)
                                            param = Enumerable.Range(0, parameter.Length)
                                                .Select(index => args[index].ToNative()).ToArray();
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
                        Context.GlobalObject.ReferenceValue.SetProperty(objPropertyId, obj, true);
                        break;
                }
            });
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
        }
    }

    [AttributeUsage(AttributeTargets.Method | AttributeTargets.Property)]
    public sealed class JsExportAttribute : Attribute
    {
        public string Name { get; set; }
    }
}