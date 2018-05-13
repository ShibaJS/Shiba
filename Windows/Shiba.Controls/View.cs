using System;
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

    public interface IToken
    {
        int Line { get; }
        int Column { get; }
    }

    public interface IToken<out T> : IToken
    {
        T Value { get; }
    }

    public struct BindingToken : IToken<Binding>
    {
        public BindingToken(Binding value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Line { get; }
        public int Column { get; }
        public Binding Value { get; }
    }

    public struct NativeResourceToken : IToken<NativeResource>
    {
        public NativeResourceToken(NativeResource value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Line { get; }
        public int Column { get; }
        public NativeResource Value { get; }
    }

    public struct JsonPathToken : IToken<JsonPath>
    {
        public JsonPathToken(JsonPath value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Line { get; }
        public int Column { get; }
        public JsonPath Value { get; }
    }

    public struct FunctionToken : IToken<Function>
    {
        public FunctionToken(Function value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Line { get; }
        public int Column { get; }
        public Function Value { get; }
    }

    public struct ThicknessToken : IToken<Thickness>
    {
        public ThicknessToken(Thickness value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Column { get; }
        public int Line { get; }
        public Thickness Value { get; }
    }

    public struct PercentToken : IToken<Percent>
    {
        public PercentToken(Percent value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Column { get; }
        public int Line { get; }
        public Percent Value { get; }
    }

    public struct NullToken : IToken
    {
        public NullToken(int column, int line)
        {
            Column = column;
            Line = line;
        }

        public int Column { get; }
        public int Line { get; }
    }

    public struct StringToken : IToken<string>
    {
        public StringToken(string value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Column { get; }
        public int Line { get; }
        public string Value { get; }
    }

    public struct NumberToken : IToken<decimal>
    {
        public NumberToken(decimal value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Column { get; }
        public int Line { get; }
        public decimal Value { get; }
    }

    public struct BoolToken : IToken<bool>
    {
        public BoolToken(bool value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Column { get; }
        public int Line { get; }
        public bool Value { get; }
    }

    public struct Token : IToken<string>
    {
        public Token(string value, int column, int line)
        {
            Value = value;
            Column = column;
            Line = line;
        }

        public int Column { get; }
        public int Line { get; }
        public string Value { get; }
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