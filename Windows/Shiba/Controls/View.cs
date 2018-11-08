using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

namespace Shiba.Controls
{
    public sealed class Property
    {
        public Property(ShibaToken name, object value)
        {
            Name = name;
            Value = value;
        }

        public ShibaToken Name { get; }
        public object Value { get; }

        public override string ToString()
        {
            return $"{Name} = {Value}";
        }

        private bool Equals(Property other)
        {
            return Equals(Name, other.Name) && Equals(Value, other.Value);
        }

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
    }

    public sealed class ShibaExtension
    {
        public ShibaExtension(string type, BasicValue value, string script)
        {
            Type = type;
            Value = value;
            Script = script;
        }

        public string Type { get; }
        public BasicValue Value { get; }
        public string Script { get; }

        public override string ToString()
        {
            return $"${Type} {Value}";
        }

        private bool Equals(ShibaExtension other)
        {
            return string.Equals(Type, other.Type) && Equals(Value, other.Value);
        }

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
    }

    public sealed class View
    {
        public View(ShibaToken viewName, object defaultValue = null)
        {
            ViewName = viewName;
            DefaultValue = defaultValue;
        }

        public ShibaToken ViewName { get; }
        public object DefaultValue { get; }
        public List<View> Children { get; } = new List<View>();
        public List<Property> Properties { get; } = new List<Property>();
        public View Parent { get; internal set; }
        

        public override string ToString()
        {
            if (DefaultValue != null)
            {
                return $"{ViewName} -> {DefaultValue}";
            }
            
            return
                $"{ViewName} {{ {string.Join(" ", Properties.Select(it => it.ToString()))} {string.Join(" ", Children.Select(it => it.ToString()))} }}";
        }

        private bool Equals(View other)
        {
            return Equals(ViewName, other.ViewName) && Equals(DefaultValue, other.DefaultValue) && Children.SequenceEqual(other.Children) && Properties.SequenceEqual(other.Properties);
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
                var hashCode = (ViewName != null ? ViewName.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ (DefaultValue != null ? DefaultValue.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ (Children != null ? Children.GetHashCode() : 0);
                hashCode = (hashCode * 397) ^ (Properties != null ? Properties.GetHashCode() : 0);
                return hashCode;
            }
        }
    }

    public sealed class ShibaArray : List<object>
    {
        public override string ToString()
        {
            return $"[{string.Join(",", this.Select(it => it.ToString()))}]";
        }

        private bool Equals(ShibaArray other)
        {
            return this.SequenceEqual(other);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj is ShibaArray array && Equals(array);
        }
    }

    public enum ShibaValueType
    {
        String,
        Token,
        Number,
        Null,
        Boolean,
    }
    
    public sealed class BasicValue
    {
        public BasicValue(ShibaValueType typeCode, object value)
        {
            TypeCode = typeCode;
            Value = value;
        }

        public ShibaValueType TypeCode { get; }
        public object Value { get; }
        public override string ToString()
        {
            switch (TypeCode)
            {
                case ShibaValueType.String:
                    return $"\"{Value}\"";
                case ShibaValueType.Token:
                case ShibaValueType.Number:
                    return $"{Value}";
                case ShibaValueType.Null:
                    return $"null";
                case ShibaValueType.Boolean:
                    return $"{Value.ToString().ToLower()}";
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        private bool Equals(BasicValue other)
        {
            return TypeCode == other.TypeCode && Equals(Value, other.Value);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj is BasicValue value && Equals(value);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((int) TypeCode * 397) ^ (Value != null ? Value.GetHashCode() : 0);
            }
        }
    }
    
    public sealed class ShibaMap
    {
        public List<Property> Properties { get; } = new List<Property>();
        public override string ToString()
        {
            return $"[ {string.Join(" ", Properties.Select(it => it.ToString()))} ]";
        }

        private bool Equals(ShibaMap other)
        {
            return Properties.SequenceEqual(other.Properties);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj is ShibaMap o && Equals(o);
        }

        public override int GetHashCode()
        {
            return Properties != null ? Properties.GetHashCode() : 0;
        }

        //public object this[string name]
        //{
        //    get
        //    {
        //        var value = Properties.FirstOrDefault(it => it.Name.IsCurrentPlatform(name))?.Value;
                
        //    }
        //}
        
    }
    
    public sealed class ShibaToken
    {
        public bool IsCurrentPlatform(string value)
        {
            if (ReferenceEquals(value, null)) return false;
            return IsCurrentPlatform() && string.Equals(value, Value);
        }

        public bool IsCurrentPlatform()
        {
            return Prefix == AbstractShiba.Instance.Configuration.PlatformType || string.IsNullOrEmpty(Prefix);
        }
        
//        public static bool operator ==(string value, ShibaToken token)
//        {
//            if (ReferenceEquals(value, null)) return false;
//            if (ReferenceEquals(token, null)) return false;
//            return (token.Prefix == AbstractShiba.Instance.Configuration.PlatformType || string.IsNullOrEmpty(token.Prefix)) && string.Equals(value, token.Value);
//        }
//
//        public static bool operator !=(string name, ShibaToken token)
//        {
//            return !(name == token);
//        }

        public static bool operator ==(ShibaToken x, ShibaToken y)
        {
            if (ReferenceEquals(x, y)) return true;
            if (ReferenceEquals(x, null)) return false;
            if (ReferenceEquals(y, null)) return false;
            return x.Equals(y);
        }

        public static bool operator !=(ShibaToken c1, ShibaToken c2)
        {
            return !(c1 == c2);
        }


        public ShibaToken(string value) : this(prefix: string.Empty, value: value)
        {
            
        }
        
        public ShibaToken(string prefix, string value)
        {
            Value = value;
            Prefix = prefix;
        }

        public string Prefix { get; }
        public string Value { get; }

        public override string ToString()
        {
            return string.IsNullOrEmpty(Prefix) ? Value : $"{Prefix}:{Value}";
        }

        private bool Equals(ShibaToken other)
        {
            return string.Equals(Prefix, other.Prefix) && string.Equals(Value, other.Value);
        }

        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj is ShibaToken token && Equals(token);
        }

        public override int GetHashCode()
        {
            unchecked
            {
                return ((Prefix != null ? Prefix.GetHashCode() : 0) * 397) ^ (Value != null ? Value.GetHashCode() : 0);
            }
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

        public override string ToString()
        {
            return $"{Name}({string.Join(",", Parameters.Select(it => it.ToString()))})";
        }

        private bool Equals(ShibaFunction other)
        {
            return string.Equals(Name, other.Name) && Parameters.SequenceEqual(other.Parameters);
        }

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
                return ((Name != null ? Name.GetHashCode() : 0) * 397) ^ (Parameters != null ? Parameters.GetHashCode() : 0);
            }
        }
    }

    
}