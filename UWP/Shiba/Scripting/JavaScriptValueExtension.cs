using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using ChakraHosting;
using Shiba.Scripting.Conversion;

namespace Shiba.Scripting
{
    internal static class JavaScriptValueExtension
    {
        internal static readonly List<ITypeConversion> Conversions = new List<ITypeConversion>();

        public static JavaScriptPropertyId ToJavaScriptPropertyId(this string id)
        {
            return JavaScriptPropertyId.FromString(id);
        }

        public static JavaScriptValue ToJavaScriptValue(this object it)
        {
            switch (it)
            {
                case JavaScriptValue javaScriptValue:
                    return javaScriptValue;
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
                    foreach (var item in Conversions)
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

        public static T ToNative<T>(this JavaScriptValue value)
        {
            return value.ToNative() is T ? (T) value.ToNative() : default;
        }

        public static object ToNative(this JavaScriptValue value)
        {
            switch (value.ValueType)
            {
                case JavaScriptValueType.Undefined:
                case JavaScriptValueType.Null:
                    return null;
                case JavaScriptValueType.Number:
                {
                    var target = value.ToDouble();
                    if (Math.Abs(target % 1) <= double.Epsilon * 100) return Convert.ToInt32(target);

                    return target;
                }
                case JavaScriptValueType.String:
                    return value.ToString();
                case JavaScriptValueType.Boolean:
                    return value.ToBoolean();
                default:
                    var converter = Conversions.FirstOrDefault(it => it.CanConvert(value));
                    return converter?.FromJsValue?.Invoke(value) ?? value;
            }
        }
    }
}