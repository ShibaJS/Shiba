using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Collections.ObjectModel;
using System.Linq;
using Shiba.Internal;
using Shiba.Visitors;

namespace Shiba.Controls
{
    public sealed class Property
    {
        public Property(string name, object value)
        {
            Name = name;
            Value = value;
        }

        public string Name { get; }
        public object Value { get; }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj is Property property && Equals(property);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((Name != null ? Name.GetHashCode() : 0) * 397) ^ (Value != null ? Value.GetHashCode() : 0);
            }
        }

        private bool Equals(Property other)
        {
            return Equals(Name, other.Name) && Equals(Value, other.Value);
        }
    }

    public sealed class ShibaExtension
    {
        public ShibaExtension(string type, string value, string scriptName)
        {
            Type = type;
            Value = value;
            ScriptName = scriptName;
        }

        public string Type { get; }
        public string Value { get; }
        public string ScriptName { get; }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj is ShibaExtension extension && Equals(extension);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((Type != null ? Type.GetHashCode() : 0) * 397) ^ (Value != null ? Value.GetHashCode() : 0);
            }
        }

        private bool Equals(ShibaExtension other)
        {
            return string.Equals(Type, other.Type) && Equals(Value, other.Value);
        }
    }

    public sealed class View
    {
        public View(string viewName, object defaultValue = null)
        {
            ViewName = viewName;
            DefaultValue = defaultValue;
        }

        public string ViewName { get; }
        public object DefaultValue { get; internal set; }
        public List<View> Children { get; } = new List<View>();
        public List<Property> Properties { get; } = new List<Property>();
        public View Parent { get; internal set; }

        public object GetProperty(string name)
        {
            return Singleton<ValueVisitor>.Instance.DynamicVisit(Properties.FirstOrDefault(it => it.Name == name)?.Value, null);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj is View view && Equals(view);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                var hashCode = ViewName != null ? ViewName.GetHashCode() : 0;
                hashCode = (hashCode * 397) ^ (DefaultValue != null ? DefaultValue.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ (Children != null ? Children.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ (Properties != null ? Properties.GetHashCode() : 0);
                return hashCode;
            }
        }

        private bool Equals(View other)
        {
            return Equals(ViewName, other.ViewName) && Equals(DefaultValue, other.DefaultValue) &&
                   Children.SequenceEqual(other.Children) && Properties.SequenceEqual(other.Properties);
        }
    }


    public sealed class ShibaObject : Dictionary<string, object>
    {
        public ShibaObject()
        {
        }
    }

    public sealed class ShibaFunction
    {
        public ShibaFunction(string name)
        {
            Name = name;
        }

        public string Name { get; }
        public List<object> Parameters { get; } = new List<object>();

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj is ShibaFunction function && Equals(function);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((Name != null ? Name.GetHashCode() : 0) * 397) ^
                       (Parameters != null ? Parameters.GetHashCode() : 0);
            }
        }

        private bool Equals(ShibaFunction other)
        {
            return string.Equals(Name, other.Name) && Parameters.SequenceEqual(other.Parameters);
        }
    }
}