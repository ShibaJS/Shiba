using System;
using System.Collections.Generic;
using System.Linq;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Shiba.Controls;
using IToken = Shiba.Controls.IToken;

namespace Shiba.Parser
{
    public class ShibaParserWrapper
    {
        private IParseTree ParseGrammarTree(string input)
        {
            var stream = CharStreams.fromstring(input);
            var lexer = new ShibaLexer(stream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new ShibaParser(tokens) {BuildParseTree = true};
            return parser.root();
        }

        public View Parse(string input)
        {
            var tree = ParseGrammarTree(input);
            return BuildViewTree(tree);
        }

        private View BuildViewTree(IParseTree tree)
        {
            switch (tree)
            {
                case ShibaParser.RootContext root:
                    return BuildViewTree(root.obj());
                case ShibaParser.ObjContext obj:
                {
                    //var view = FindTypes(obj.Start.Text)?.FirstOrDefault()?.CreateInstance<View>(PairToDictionary(obj.pair()));
                    var view = new View(obj.Start.Text, PairToDictionary(obj.pair()));
                    //InitPair(ref view, obj.pair());
                    if (obj.children != null && obj.children.Any())
                    {
                        view.Children.AddRange(obj.children.Where(it => it is ShibaParser.ObjContext || it is ShibaParser.ShortobjContext).Select(BuildViewTree));
                    }
                    
                    return view;
                }
                case ShibaParser.ShortobjContext shortObj:
                {
                    return new View(shortObj.Start.Text, GetValue(shortObj.value()));
                }
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        private Dictionary<string, IToken> PairToDictionary(IEnumerable<ShibaParser.PairContext> pair)
        {
            return pair?.ToDictionary(x => x.Start.Text, x => GetValue(x.value())) ??
                   new Dictionary<string, IToken>();
        }

        private IToken GetStaticValue(ShibaParser.StaticvalueContext context)
        {
            if (context == null)
            {
                return null;
            }

            if (context.NULL() != null)
            {
                return new NullToken(context.NULL().Symbol.Column, context.NULL().Symbol.Line);
            }

            if (context.BOOLEAN() != null)
            {
                return new BoolToken(bool.Parse(context.BOOLEAN().GetText()), context.BOOLEAN().Symbol.Column,
                    context.BOOLEAN().Symbol.Line);
            }

            if (context.NUMBER() != null)
            {
                return new NumberToken(decimal.Parse(context.NUMBER().GetText()), context.NUMBER().Symbol.Column,
                    context.NUMBER().Symbol.Line);
            }

            if (context.percent() != null)
            {
                return new PercentToken(new Percent(float.Parse(context.percent().Start.Text)),
                    context.percent().Start.Column, context.percent().Start.Line);
            }

            if (context.thickness() != null)
            {
                return new ThicknessToken(new Thickness(context.thickness().GetText()),
                    context.thickness().Start.Column, context.thickness().Start.Line);
            }

            if (context.STRING() != null)
            {
                return new StringToken(context.STRING().GetText().Trim('"'), context.STRING().Symbol.Column,
                    context.STRING().Symbol.Line);
            }

            if (context.TOKEN() != null)
            {
                return new Token(context.TOKEN().GetText(), context.TOKEN().Symbol.Column, context.TOKEN().Symbol.Line);
            }

            throw new NotSupportedException(
                $"Not support context at line {context.Start.Line}, column {context.Start.Column}");
        }


        private IToken GetValue(ShibaParser.ValueContext context)
        {
            if (context == null)
            {
                return null;
            }

            var staticValue = GetStaticValue(context.staticvalue());
            if (staticValue != null)
            {
                return staticValue;
            }

            if (context.binding() != null)
            {
                return new BindingToken(new Binding(GetStaticValue(context.binding().staticvalue())),
                    context.binding().Start.Column, context.binding().Start.Line);
            }

            if (context.resource() != null)
            {
                return new NativeResourceToken(new NativeResource(GetStaticValue(context.resource().staticvalue())),
                    context.resource().Start.Column, context.resource().Start.Line);
            }

            if (context.jsonpath() != null)
            {
                return new JsonPathToken(new JsonPath(GetStaticValue(context.resource().staticvalue())),
                    context.resource().Start.Column, context.resource().Start.Line);
            }

            if (context.dic() != null)
            {
                var pair = context.dic().pair().FirstOrDefault(it => it.TOKEN().Symbol.Text == AbstractShiba.Instance.Configuration.PlatformType);
                return GetValue(pair?.value());
            }

            if (context.func() != null)
            {
                return new FunctionToken(GetFunc(context.func()), context.func().Start.Column,
                    context.func().Start.Line);
            }

            throw new NotSupportedException(
                $"Not support context at line {context.Start.Line}, column {context.Start.Column}");
        }

        private Function GetFunc(ShibaParser.FuncContext func)
        {
            return new Function(func.TOKEN().Symbol.Text,
                func.paramter().Select(item =>
                    item.func() != null
                        ? GetFunc(item.func()) as IParamter
                        : GetValueParamter(item.value()) as IParamter).ToArray());
        }

        private ValueParamter GetValueParamter(ShibaParser.ValueContext value)
        {
            return new ValueParamter(GetValue(value));
        }
    }
}