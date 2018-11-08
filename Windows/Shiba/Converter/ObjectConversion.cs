using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using ChakraCore.NET.API;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace Shiba.Converter
{
    public class ObjectConversion : ITypeConversion
    {
        public ObjectConversion()
        {
            ToJsValue = value =>
            {
                if (value is IEnumerable list)
                {
                    return JsonToJsValue.Convert(JArray.FromObject(list));
                }

                return JsonToJsValue.Convert(JObject.FromObject(value));
            };

            FromJsValue = value =>
            {
                throw new NotImplementedException();
            };
        }

        public Type ObjectType { get; } = typeof(object);
        public JavaScriptValueType[] JsType { get; } = {JavaScriptValueType.Object, JavaScriptValueType.Array};
        public Func<object, JavaScriptValue> ToJsValue { get; }
        public Func<JavaScriptValue, object> FromJsValue { get; }
    }
}
