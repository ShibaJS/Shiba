using System;
using ChakraCore.NET.API;

namespace Shiba.Converter
{
    public interface ITypeConversion
    {
        Type ObjectType { get; }
        JavaScriptValueType[] JsType { get; }
        Func<object, JavaScriptValue> ToJsValue { get; }
        Func<JavaScriptValue, object> FromJsValue { get; }
    }

    public class TypeConversion : ITypeConversion
    {
        public TypeConversion(Type objectType, JavaScriptValueType jsType, Func<object, JavaScriptValue> toJsValue, Func<JavaScriptValue, object> fromJsValue)
        {
            ObjectType = objectType;
            JsType = new[] {jsType};
            ToJsValue = toJsValue;
            FromJsValue = fromJsValue;
        }

        public Type ObjectType { get; }
        public JavaScriptValueType[] JsType { get; }
        public Func<object, JavaScriptValue> ToJsValue { get; }
        public Func<JavaScriptValue, object> FromJsValue { get; }
    }
}