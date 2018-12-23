using System;
using ChakraHosting;

namespace Shiba.Scripting.Conversion
{
    public interface ITypeConversion
    {
        Type ObjectType { get; }
        Func<object, JavaScriptValue> ToJsValue { get; }
        Func<JavaScriptValue, object> FromJsValue { get; }
        bool CanConvert(JavaScriptValue value);
    }

    public class TypeConversion : ITypeConversion
    {
        public TypeConversion(Type objectType, Func<object, JavaScriptValue> toJsValue,
            Func<JavaScriptValue, object> fromJsValue)
        {
            ObjectType = objectType;
            ToJsValue = toJsValue;
            FromJsValue = fromJsValue;
        }

        public Type ObjectType { get; }
        public Func<object, JavaScriptValue> ToJsValue { get; }
        public Func<JavaScriptValue, object> FromJsValue { get; }

        public bool CanConvert(JavaScriptValue value)
        {
            return false;
        }
    }
}