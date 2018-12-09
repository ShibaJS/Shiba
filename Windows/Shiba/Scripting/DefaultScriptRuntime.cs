using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using ChakraCore.NET;
using ChakraCore.NET.API;

namespace Shiba.Scripting
{
    public class DefaultScriptRuntime : IScriptRuntime, IDisposable
    {
        private readonly ChakraContext _context;

        private readonly List<ITypeConversion> _conversions = new List<ITypeConversion>();
        private JavaScriptValue[] _prefix;

        public DefaultScriptRuntime(ChakraContext context = null)
        {
            _context = context ?? ChakraRuntime.Create().CreateContext(false);
            _context.ServiceNode.WithContext(() => { _prefix = new[] {JavaScriptValue.FromBoolean(false)}; });
            InitConversion();
        }

        public object Execute(string functionName, params object[] parameters)
        {
            return _context.ServiceNode.WithContext(() =>
            {
                var func = _context.GlobalObject.ReferenceValue.GetProperty(
                    JavaScriptPropertyId.FromString(functionName));

                if (func.ValueType == JavaScriptValueType.Function)
                {
                    var param = _prefix.Concat(parameters.Select(FromNative)).ToArray();

                    var result = func.CallFunction(param);

                    return ToNative(result);
                }

                return null;
            });
        }

        public object Execute(string script)
        {
            return _context.ServiceNode.WithContext(() =>
            {
                var debugService = _context.ServiceNode.GetService<IRuntimeDebuggingService>();
                var result = JavaScriptContext.RunScript(script, debugService.GetScriptContext("Script", script));
                return ToNative(result);
            });
        }

        public void AddObject(string name, object value)
        {
            if (value == null || string.IsNullOrEmpty(name))
            {
                throw new ArgumentException();
            }

            _context.ServiceNode.WithContext(() =>
            {
                var obj = JavaScriptValue.CreateObject();
                var type = value.GetType().GetTypeInfo();
                var members = type.GetMembers()
                    .Where(it => it.GetCustomAttribute<JsExportAttribute>() != null);

                foreach (var item in members)
                {
                    switch (item)
                    {
                        case MethodInfo method:
                            var functionId =
                                JavaScriptPropertyId.FromString(method.GetCustomAttribute<JsExportAttribute>().Name);
                            var parameter = method.GetParameters();

                            JavaScriptValue Function(JavaScriptValue callee, bool call, JavaScriptValue[] arguments,
                                ushort count, IntPtr data)
                            {
                                object[] param;
                                var args = arguments.Skip(1).ToArray();
                                if (count >= parameter.Length)
                                {
                                    param = Enumerable.Range(0, parameter.Length)
                                        .Select(index => ToNative(args[index])).ToArray();
                                }
                                else
                                {
                                    param = args.Select(ToNative).Concat(Enumerable
                                        .Range(count + 1, parameter.Length - count)
                                        .Select(
                                            index =>
                                            {
                                                var paramType = parameter[index].ParameterType;
                                                return paramType.IsValueType
                                                    ? Activator.CreateInstance(paramType)
                                                    : null;
                                            })).ToArray();
                                }

                                var result = method.Invoke(value, param);
                                return FromNative(result);
                            }

                            var function = JavaScriptValue.CreateFunction(Function, IntPtr.Zero);
                            obj.SetProperty(functionId, function, true);
                            break;
                        default:
                            break;
                    }
                }
                var objPropertyId = JavaScriptPropertyId.FromString(name);
                _context.GlobalObject.ReferenceValue.SetProperty(objPropertyId, obj, true);
            });
        }

        public void AddConversion(ITypeConversion conversion)
        {
            _conversions.Add(conversion);
        }


        public void AddConversion<T>(JavaScriptValueType jsType, Func<T, JavaScriptValue> toJsValue,
            Func<JavaScriptValue, T> fromJsValue)
        {
            AddConversion(new TypeConversion(typeof(T), jsType,
                it => it is T target ? toJsValue.Invoke(target) : JavaScriptValue.Undefined,
                it => fromJsValue.Invoke(it)));
        }

        private JavaScriptValue FromNative(object it)
        {
            switch (it)
            {
                case bool value:
                    return JavaScriptValue.FromBoolean(value);
                case string value:
                    return JavaScriptValue.FromString(value);
                case int value:
                    return JavaScriptValue.FromInt32(value);
                case double value:
                    return JavaScriptValue.FromDouble(value);
                case float value:
                    return JavaScriptValue.FromDouble(value);
                case decimal value:
                    return JavaScriptValue.FromDouble(Convert.ToDouble(value));
                case null:
                    return JavaScriptValue.Null;
                default:
                    ITypeConversion converter = null;
                    var type = it.GetType();
                    foreach (var item in _conversions)
                    {
                        if (item.ObjectType == type)
                        {
                            converter = item;
                            break;
                        }

                        if (item.ObjectType.IsAssignableFrom(type)) converter = item;
                    }

                    return converter?.ToJsValue?.Invoke(it) ?? JavaScriptValue.Invalid;
            }
        }

        private void InitConversion()
        {
            AddConversion(new JTokenConversion());
        }

        private object ToNative(JavaScriptValue value)
        {
            switch (value.ValueType)
            {
                case JavaScriptValueType.Undefined:
                case JavaScriptValueType.Null:
                    return null;
                case JavaScriptValueType.Number:
                {
                    var target = value.ToDouble();
                    if (Math.Abs(target % 1) <= double.Epsilon * 100)
                    {
                        return Convert.ToInt32(target);
                    }

                    return target;
                }
                case JavaScriptValueType.String:
                    return value.ToString();
                case JavaScriptValueType.Boolean:
                    return value.ToBoolean();
                case JavaScriptValueType.Object:
                case JavaScriptValueType.Function:
                case JavaScriptValueType.Array:
                case JavaScriptValueType.DataView:
                case JavaScriptValueType.TypedArray:
                case JavaScriptValueType.ArrayBuffer:
                case JavaScriptValueType.Symbol:
                case JavaScriptValueType.Error:
                    return value;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        public void Dispose()
        {
            _context?.Dispose();
        }
    }

    [AttributeUsage(AttributeTargets.Method)]
    public sealed class JsExportAttribute : Attribute
    {
        public string Name { get; set; }
    }
}