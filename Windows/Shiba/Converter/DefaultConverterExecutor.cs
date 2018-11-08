using System;
using System.Collections.Generic;
using System.Linq;
using ChakraCore.NET;
using ChakraCore.NET.API;

namespace Shiba.Converter
{
    public class DefaultConverterExecutor : IConverterExecutor
    {
        private readonly ChakraContext _context;
        private JavaScriptValue[] _prefix;

        private readonly List<ITypeConversion> _conversions = new List<ITypeConversion>();

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
        
        private void InitConversion()
        {
            AddConversion(JavaScriptValueType.String, JavaScriptValue.FromString, it => it.ToString());
            AddConversion(JavaScriptValueType.Number, JavaScriptValue.FromDouble, it => it.ToDouble());
            AddConversion(JavaScriptValueType.Number, JavaScriptValue.FromInt32, it => it.ToInt32());
            AddConversion(JavaScriptValueType.Boolean, JavaScriptValue.FromBoolean, it => it.ToBoolean());
            AddConversion(JavaScriptValueType.Number, it => JavaScriptValue.FromDouble(it), it => Convert.ToSingle(it.ToDouble()));
            AddConversion(JavaScriptValueType.Number, it => JavaScriptValue.FromDouble(Convert.ToDouble(it)), it => Convert.ToDecimal(it.ToDouble()));
            AddConversion(new JTokenConversion());
            AddConversion(new ObjectConversion());
        }
        
        public DefaultConverterExecutor(ChakraContext context = null)
        {
            _context = context ?? ChakraRuntime.Create().CreateContext(false);
            _context.ServiceNode.WithContext(() =>
            {
                _prefix = new[] {JavaScriptValue.FromBoolean(false)};
            });
            InitConversion();
        }

        public object Execute(string functionName, Type targetType, params object[] parameters)
        {
            return  _context.ServiceNode.WithContext(() =>
            {
                var func = _context.GlobalObject.ReferenceValue.GetProperty(JavaScriptPropertyId.FromString(functionName));

                if (func.ValueType == JavaScriptValueType.Function)
                {
                    var param = _prefix.Concat(parameters.Select(it =>
                    {
                        if (it == null)
                        {
                            return JavaScriptValue.Null;
                        }

                        ITypeConversion converter = null;
                        var type = it.GetType();
                        foreach (var item in _conversions)
                        {
                            if (item.ObjectType == type)
                            {
                                converter = item;
                                break;
                            }

                            if (item.ObjectType.IsAssignableFrom(type))
                            {
                                converter = item;
                            }
                        }

                        return converter?.ToJsValue?.Invoke(it) ?? JavaScriptValue.Undefined;
                    })).ToArray();

                    var result = func.CallFunction(param);

                    ITypeConversion resultConverter = null;

                    foreach (var item in _conversions)
                    {
                        if (item.JsType.Contains(result.ValueType))
                        {
                            if (item.ObjectType == targetType)
                            {
                                resultConverter = item;
                                break;
                            }

                            if (targetType.IsAssignableFrom(item.ObjectType))
                            {
                                resultConverter = item;
                            }
                        }
                    }
                    return resultConverter?.FromJsValue(result);
                }

                return null;
            });   
        }

        public void Register(string converter)
        {
            _context.RunScript(converter);
        }

        public void Register(string name, Delegate @delegate)
        {
        }
    }
}