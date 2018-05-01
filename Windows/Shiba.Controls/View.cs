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

    public interface IValueConverter
    {
        object Convert(object value, object paramter);
        object ConvertBack(object value, object paramter);
    }

    public class Comput
    {
        public Comput InnerComput { get; set; }
        public string FuncName { get; set; }
        public object Value { get; set; }
        public object Paramter { get; set; }
    }

    internal interface IComputValue
    {
        Comput Value { get; }
    }

    public struct Binding : IComputValue
    {
        public Binding(Comput value)
        {
            Value = value;
        }

        public Comput Value { get; }
    }

    public struct JsonPath : IComputValue
    {
        public JsonPath(Comput value)
        {
            Value = value;
        }

        public Comput Value { get; }
    }

    public struct NativeResource : IComputValue
    {
        public NativeResource(Comput value)
        {
            Value = value;
        }

        public Comput Value { get; }
    }
}