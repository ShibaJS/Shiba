using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Shiba.Controls;

namespace Shiba.Parser
{
    internal interface IShibaVisitor
    {
        Type HandleType { get; }
        object Visit(IParseTree tree);
    }

    internal abstract class ShibaVisitor : IShibaVisitor
    {
        private static readonly List<IShibaVisitor> Visitors;

        static ShibaVisitor()
        {
            Visitors = typeof(IShibaVisitor)
                .Assembly
                .DefinedTypes
                .Where(it =>
                    it.IsClass && !it.IsAbstract &&
                    typeof(IShibaVisitor).GetTypeInfo().IsAssignableFrom(it.GetTypeInfo()))
                .Select(it => Activator.CreateInstance(it) as IShibaVisitor)
                .ToList();
        }

        public abstract Type HandleType { get; }
        public abstract object Visit(IParseTree tree);

        protected static T GetValue<T>(IParseTree tree)
        {
            var value = GetValue(tree);
            if (value is T result) return result;

            return default;
        }

        protected internal static object GetValue(IParseTree tree)
        {
            return tree == null ? null : Visitors.FirstOrDefault(it => it.HandleType == tree.GetType())?.Visit(tree);
        }
    }


    internal abstract class GenericVisitor<T, K> : ShibaVisitor
        where T : class, IParseTree
    {
        public override Type HandleType { get; } = typeof(T);

        public override object Visit(IParseTree tree)
        {
            return Parse(tree as T);
        }

        protected abstract K Parse(T tree);
    }

    internal sealed class ViewVisitor : GenericVisitor<ShibaParser.ViewContext, View>
    {
        protected override View Parse(ShibaParser.ViewContext tree)
        {
            var viewName = GetValue<ShibaToken>(tree.identifier());
            var view = new View(viewName, GetValue(tree.value()));
            
            if (tree.property() != null) view.Properties.AddRange(tree.property().Select(GetValue<Property>));

            if (tree.view() != null) view.Children.AddRange(tree.view().Select(GetValue<View>));

            return view;
        }
    }

    internal sealed class PropertyVisitor : GenericVisitor<ShibaParser.PropertyContext, Property>
    {
        protected override Property Parse(ShibaParser.PropertyContext tree)
        {
            var tokenName = GetValue<ShibaToken>(tree.identifier());
            var value = GetValue(tree.value());
            return new Property(tokenName, value);
        }
    }

    internal sealed class ValueVisitor : GenericVisitor<ShibaParser.ValueContext, object>
    {
        protected override object Parse(ShibaParser.ValueContext tree)
        {
            return GetValue(tree.GetChild(0));
        }
    }

    internal sealed class ShibaTokenVisitor : GenericVisitor<ShibaParser.IdentifierContext, ShibaToken>
    {
        protected override ShibaToken Parse(ShibaParser.IdentifierContext tree)
        {
            switch (tree.Identifier()?.Length)
            {
                case 1:
                    return new ShibaToken(tree.Identifier().FirstOrDefault()?.GetText());
                case 2:
                    return new ShibaToken(tree.Identifier().FirstOrDefault()?.GetText(),
                        tree.Identifier().Skip(1).FirstOrDefault()?.GetText());
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }
    }

    internal sealed class ShibaMapVisitor : GenericVisitor<ShibaParser.MapContext, ShibaMap>
    {
        protected override ShibaMap Parse(ShibaParser.MapContext tree)
        {
            var obj = new ShibaMap();
            if (tree.property() != null) obj.Properties.AddRange(tree.property().Select(GetValue<Property>));

            return obj;
        }
    }

    internal sealed class BasicValueVisitor : GenericVisitor<ShibaParser.BasicValueContext, BasicValue>
    {
        protected override BasicValue Parse(ShibaParser.BasicValueContext tree)
        {
            ShibaValueType type;
            object targetValue;
            var token = tree.children.FirstOrDefault() as ITerminalNode;
            switch (token?.Symbol.Type)
            {
                case ShibaParser.String:
                    type = ShibaValueType.String;
                    targetValue = token.GetText().Trim('"');
                    break;
                case ShibaParser.Number:
                    type = ShibaValueType.Number;
                    targetValue = Convert.ToDecimal(token.GetText());
                    break;
                case ShibaParser.Boolean:
                    type = ShibaValueType.Boolean;
                    targetValue = Convert.ToBoolean(token.GetText());
                    break;
                case ShibaParser.Null:
                    type = ShibaValueType.Null;
                    targetValue = null;
                    break;
                case ShibaParser.Identifier:
                    type = ShibaValueType.Token;
                    targetValue = token.GetText();
                    break;
                default:
                    throw new ArgumentOutOfRangeException();
            }

            var value = new BasicValue(type, targetValue);
            return value;
        }
    }

    internal sealed class FunctionVisitor : GenericVisitor<ShibaParser.FunctionContext, ShibaFunction>
    {
        protected override ShibaFunction Parse(ShibaParser.FunctionContext tree)
        {
            var name = tree.Identifier().GetText();
            var function = new ShibaFunction(name);
            if (tree.value() != null) function.Paramters.AddRange(tree.value().Select(GetValue));

            return function;
        }
    }

    internal sealed class ShibaExtensionVisitor : GenericVisitor<ShibaParser.ShibaExtensionContext, ShibaExtension>
    {
        protected override ShibaExtension Parse(ShibaParser.ShibaExtensionContext tree)
        {
            var name = tree.Identifier().GetText();
            var value = GetValue<BasicValue>(tree.basicValue());
            var extension = new ShibaExtension(name, value);
            return extension;
        }
    }

    internal sealed class ArrayVisitor : GenericVisitor<ShibaParser.ArrayContext, ShibaArray>
    {
        protected override ShibaArray Parse(ShibaParser.ArrayContext tree)
        {
            var value = new ShibaArray();
            if (tree.value() != null) value.AddRange(tree.value().Select(GetValue));

            return value;
        }
    }

    public class ShibaParserWrapper
    {
        private IParseTree ParseGrammarTree(string input)
        {
            var stream = CharStreams.fromstring(input);
            var lexer = new ShibaLexer(stream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new ShibaParser(tokens) {BuildParseTree = true};

            return parser.view();
        }

        public object Parse(string input)
        {
            var tree = ParseGrammarTree(input);
            return ShibaVisitor.GetValue(tree);
        }
    }
}