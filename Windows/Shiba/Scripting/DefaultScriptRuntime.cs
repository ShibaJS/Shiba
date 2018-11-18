using System;
using System.Collections.Generic;
using System.Linq;
using ChakraCore.NET;
using ChakraCore.NET.API;

namespace Shiba.Scripting
{
    public class DefaultScriptRuntime : IScriptRuntime
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

                    //ITypeConversion resultConverter = null;

                    //foreach (var item in _conversions)
                    //{
                    //    if (item.JsType.Contains(result.ValueType))
                    //    {
                    //        if (item.ObjectType == targetType)
                    //        {
                    //            resultConverter = item;
                    //            break;
                    //        }

                    //        if (targetType.IsAssignableFrom(item.ObjectType))
                    //        {
                    //            resultConverter = item;
                    //        }
                    //    }
                    //}
                    //return resultConverter?.FromJsValue(result);
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
                case bool bvalue:
                    return JavaScriptValue.FromBoolean(bvalue);
                case string svalue:
                    return JavaScriptValue.FromString(svalue);
                case int ivalue:
                    return JavaScriptValue.FromInt32(ivalue);
                case double dvalue:
                    return JavaScriptValue.FromDouble(dvalue);
                case float fvalue:
                    return JavaScriptValue.FromDouble(fvalue);
                case decimal dvalue:
                    return JavaScriptValue.FromDouble(Convert.ToDouble(dvalue));
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

                    return converter?.ToJsValue?.Invoke(it) ?? JavaScriptValue.Undefined;
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
                    return value.ToDouble();
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
    }
}