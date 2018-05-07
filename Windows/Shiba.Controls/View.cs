using System.Collections.Generic;
using Shiba.Controls.Common;

namespace Shiba.Controls
{
    public class SbEvent
    {
        public SbEvent(string target)
        {
            Target = target;
        }

        public string Target { get; }
    }

    public class View
    {
        public View(string viewName, Dictionary<string, object> attribute)
        {
            ViewName = viewName;
            Properties = attribute;
        }

        public string ViewName { get; }
        public List<View> Children { get; } = new List<View>();
        public Dictionary<string, object> Properties { get; }

        public bool TryGet(string key, out object value)
        {
            if (Properties.ContainsKey(key))
            {
                value = Properties[key];
                return true;
            }

            value = null;
            return false;
        }
    }

//    public class Comput
//    {
//        public string FuncName { get; set; }
//        public object Value { get; set; }
//        public Comput[] Paramter { get; set; }
//    }

    internal interface IStaticValue
    {
        object Value { get; }
    }

    public struct Token
    {
        public Token(object value)
        {
            Value = value;
        }

        public object Value { get; }
    }

    public class Calculate
    {
        
    }
    
    public struct Binding : IStaticValue
    {
        public Binding(object value)
        {
            Value = value;
        }

        public object Value { get; }
    }

    public struct JsonPath : IStaticValue
    {
        public JsonPath(object value)
        {
            Value = value;
        }

        public object Value { get; }
    }

    public struct NativeResource : IStaticValue
    {
        public NativeResource(object value)
        {
            Value = value;
        }

        public object Value { get; }
    }
}