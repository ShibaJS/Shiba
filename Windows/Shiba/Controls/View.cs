using System.Collections.Generic;

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
        public View(string viewName, Dictionary<string, IToken> attribute)
        {
            ViewName = viewName;
            Properties = attribute;
        }

        public string ViewName { get; }
        public List<View> Children { get; } = new List<View>();
        public Dictionary<string, IToken> Properties { get; }

        public bool TryGet(string key, out IToken value)
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

    public interface IBindingValue
    {
        IToken Value { get; }
    }

    public interface IToken
    {
        int Line { get; }
        int Column { get; }
        object GetValue();
    }

    public interface IToken<out T> : IToken
    {
        T Value { get; }
    }
    
    public abstract class TokenBase<T> : IToken<T>
    {
        protected TokenBase(T value, int column, int line)
        {
            Line = line;
            Value = value;
            Column = column;
        }

        public int Line { get; }
        public int Column { get; }

        public object GetValue()
        {
            return Value;
        }

        public T Value { get; }
    }

    public sealed class BindingToken : TokenBase<Binding>
    {
        public BindingToken(Binding value, int column, int line) : base(value, column, line)
        {
        }
    }

    public sealed class NativeResourceToken : TokenBase<NativeResource>
    {
        public NativeResourceToken(NativeResource value, int column, int line) : base(value, column, line)
        {
        }
    }

    public sealed class JsonPathToken : TokenBase<JsonPath>
    {
        public JsonPathToken(JsonPath value, int column, int line) : base(value, column, line)
        {
        }
    }

    public sealed class FunctionToken : TokenBase<Function>
    {
        public FunctionToken(Function value, int column, int line) : base(value, column, line)
        {
        }
    }

    public sealed class ThicknessToken : TokenBase<Thickness>
    {
        public ThicknessToken(Thickness value, int column, int line) : base(value, column, line)
        {
        }
    }

    public sealed class PercentToken : TokenBase<Percent>
    {
        public PercentToken(Percent value, int column, int line) : base(value, column, line)
        {
        }
    }

    public sealed class NullToken : IToken
    {
        public NullToken(int column, int line)
        {
            Column = column;
            Line = line;
        }

        public int Column { get; }
        public object GetValue()
        {
            return null;
        }

        public int Line { get; }
    }

    public sealed class StringToken : TokenBase<string>
    {
        public StringToken(string value, int column, int line) : base(value, column, line)
        {
        }
        
    }

    public sealed class NumberToken : TokenBase<decimal>
    {
        public NumberToken(decimal value, int column, int line) : base(value, column, line)
        {
        }
    }

    public sealed class BoolToken : TokenBase<bool>
    {
        public BoolToken(bool value, int column, int line) : base(value, column, line)
        {
        }
    }

    public sealed class Token : TokenBase<string>
    {
        public Token(string value, int column, int line) : base(value, column, line)
        {
        }
    }


    public class Function : IParamter
    {
        public Function(string name, params IParamter[] paramters)
        {
            Name = name;
            Paramters = paramters;
        }

        public string Name { get; }
        public IParamter[] Paramters { get; }

        //public object GetValue(object dataContext)
        //{
        //    throw new NotImplementedException();
        //}
    }

    public interface IParamter
    {
        //object GetValue(object dataContext);
    }

    public class ValueParamter : IParamter
    {
        public ValueParamter(IToken value)
        {
            Value = value;
        }

        public IToken Value { get; }

        //public object GetValue(object dataContext)
        //{
        //    return Value;
        //}
    }

    public class Binding : IBindingValue
    {
        public Binding(IToken value)
        {
            Value = value;
        }

        public IToken Value { get; }
    }

    public class JsonPath : IBindingValue
    {
        public JsonPath(IToken value)
        {
            Value = value;
        }

        public IToken Value { get; }
    }

    public class NativeResource : IBindingValue
    {
        public NativeResource(IToken value)
        {
            Value = value;
        }

        public IToken Value { get; }
    }
}