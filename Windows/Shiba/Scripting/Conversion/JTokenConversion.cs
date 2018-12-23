using System;
using ChakraHosting;
using Newtonsoft.Json.Linq;

namespace Shiba.Scripting.Conversion
{
    internal class JTokenConversion : ITypeConversion
    {
        public JTokenConversion()
        {
            ToJsValue = value =>
            {
                if (value is JToken token) return JsonToJsValue.Convert(token);

                return JavaScriptValue.Undefined;
            };
            FromJsValue = JsValueToJson.Convert;
        }

        public Type ObjectType { get; } = typeof(JToken);


        public Func<object, JavaScriptValue> ToJsValue { get; }
        public Func<JavaScriptValue, object> FromJsValue { get; }

        public bool CanConvert(JavaScriptValue value)
        {
            return false;
        }
    }

    internal sealed class JsValueToJson
    {
        private static readonly JToken TrueValue = new JValue(true);
        private static readonly JToken FalseValue = new JValue(false);
        private static readonly JToken NullValue = JValue.CreateNull();
        private static readonly JToken UndefinedValue = JValue.CreateUndefined();

        private static readonly JsValueToJson Instance =
            new JsValueToJson();

        private JsValueToJson()
        {
        }

        public static JToken Convert(JavaScriptValue value)
        {
            return Instance.Visit(value);
        }

        private JToken Visit(JavaScriptValue value)
        {
            switch (value.ValueType)
            {
                case JavaScriptValueType.Array:
                    return VisitArray(value);
                case JavaScriptValueType.Boolean:
                    return VisitBoolean(value);
                case JavaScriptValueType.Error:
                    return VisitError(value);
                case JavaScriptValueType.Null:
                    return VisitNull(value);
                case JavaScriptValueType.Number:
                    return VisitNumber(value);
                case JavaScriptValueType.Object:
                    return VisitObject(value);
                case JavaScriptValueType.String:
                    return VisitString(value);
                case JavaScriptValueType.Undefined:
                    return VisitUndefined(value);
                case JavaScriptValueType.Function:
                default:
                    throw new NotSupportedException();
            }
        }

        private JToken VisitArray(JavaScriptValue value)
        {
            var array = new JArray();
            var propertyId = JavaScriptPropertyId.FromString("length");
            var length = (int) value.GetProperty(propertyId).ToDouble();
            for (var i = 0; i < length; ++i)
            {
                var index = JavaScriptValue.FromInt32(i);
                var element = value.GetIndexedProperty(index);
                array.Add(Visit(element));
            }

            return array;
        }

        private JToken VisitBoolean(JavaScriptValue value)
        {
            return value.ToBoolean() ? TrueValue : FalseValue;
        }

        private JToken VisitError(JavaScriptValue value)
        {
            return new JObject
            {
                {"message", Visit(value.GetProperty(JavaScriptPropertyId.FromString("message")))},
                {"description", Visit(value.GetProperty(JavaScriptPropertyId.FromString("description")))},
                {"stack", Visit(value.GetProperty(JavaScriptPropertyId.FromString("stack")))}
            };
        }

        private JToken VisitNull(JavaScriptValue value)
        {
            return NullValue;
        }

        private JToken VisitNumber(JavaScriptValue value)
        {
            var number = value.ToDouble();

            return number % 1 == 0
                ? new JValue((long) number)
                : new JValue(number);
        }

        private JToken VisitObject(JavaScriptValue value)
        {
            var jsonObject = new JObject();
            var properties = Visit(value.GetOwnPropertyNames()).ToObject<string[]>();
            foreach (var property in properties)
            {
                var propertyId = JavaScriptPropertyId.FromString(property);
                var propertyValue = value.GetProperty(propertyId);
                jsonObject.Add(property, Visit(propertyValue));
            }

            return jsonObject;
        }

        private JToken VisitString(JavaScriptValue value)
        {
            return JValue.CreateString(value.ToString());
        }

        private JToken VisitUndefined(JavaScriptValue value)
        {
            return UndefinedValue;
        }
    }

    internal sealed class JsonToJsValue
    {
        private static readonly JsonToJsValue Instance =
            new JsonToJsValue();

        private JsonToJsValue()
        {
        }

        public static JavaScriptValue Convert(JToken token)
        {
            return Instance.Visit(token);
        }

        private JavaScriptValue Visit(JToken token)
        {
            if (token == null)
                throw new ArgumentNullException(nameof(token));

            switch (token.Type)
            {
                case JTokenType.Array:
                    return VisitArray((JArray) token);
                case JTokenType.Boolean:
                    return VisitBoolean((JValue) token);
                case JTokenType.Float:
                    return VisitFloat((JValue) token);
                case JTokenType.Integer:
                    return VisitInteger((JValue) token);
                case JTokenType.Null:
                    return VisitNull(token);
                case JTokenType.Object:
                    return VisitObject((JObject) token);
                case JTokenType.String:
                    return VisitString((JValue) token);
                case JTokenType.Undefined:
                    return VisitUndefined(token);
                case JTokenType.Constructor:
                case JTokenType.Property:
                case JTokenType.Comment:
                case JTokenType.Date:
                case JTokenType.Raw:
                case JTokenType.Bytes:
                case JTokenType.Guid:
                case JTokenType.Uri:
                case JTokenType.TimeSpan:
                case JTokenType.None:
                default:
                    throw new NotSupportedException();
            }
        }

        private JavaScriptValue VisitArray(JArray token)
        {
            var n = token.Count;
            var array = JavaScriptValue.CreateArray((uint) n);
            for (var i = 0; i < n; ++i)
            {
                var element = Visit(token[i]);
                array.SetIndexedProperty(JavaScriptValue.FromInt32(i), element);
            }

            return array;
        }

        private JavaScriptValue VisitBoolean(JValue token)
        {
            return JavaScriptValue.FromBoolean(token.Value<bool>());
        }

        private JavaScriptValue VisitFloat(JValue token)
        {
            return JavaScriptValue.FromDouble(token.Value<double>());
        }

        private JavaScriptValue VisitInteger(JValue token)
        {
            return JavaScriptValue.FromDouble(token.Value<double>());
        }

        private JavaScriptValue VisitNull(JToken token)
        {
            return JavaScriptValue.Null;
        }

        private JavaScriptValue VisitObject(JObject token)
        {
            var jsonObject = JavaScriptValue.CreateObject();
            foreach (var entry in token)
            {
                var value = Visit(entry.Value);
                var propertyId = JavaScriptPropertyId.FromString(entry.Key);
                jsonObject.SetProperty(propertyId, value, true);
            }

            return jsonObject;
        }

        private JavaScriptValue VisitString(JValue token)
        {
            return JavaScriptValue.FromString(token.Value<string>());
        }

        private JavaScriptValue VisitUndefined(JToken token)
        {
            return JavaScriptValue.Undefined;
        }
    }
}