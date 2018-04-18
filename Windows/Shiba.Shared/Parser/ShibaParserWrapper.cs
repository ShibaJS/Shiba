using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Reflection;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Shiba.Common;
using Shiba.Controls;

namespace Shiba.Parser
{
    public class ShibaParserWrapper
    {
        public List<string> NameSpaces { get; } = new List<string>
        {
            "Shiba.Controls"
        };

        public static IParseTree ParseGrammarTree(string input)
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


        public View BuildViewTree(IParseTree tree)
        {
            switch (tree)
            {
                case ShibaParser.RootContext root:
                    NameSpaces.AddRange(root.namespaces()?.NAMESPACE()
                        ?.Select(item => item.Symbol.Text.Trim('@').ToWindowsNameSpace()));
                    return BuildViewTree(root.obj());
                case ShibaParser.ObjContext obj:
                    var view = FindTypes(obj.Start.Text)?.FirstOrDefault()?.CreateInstance() as View;
                    InitPair(ref view, obj.pair());
                    if (obj.obj() != null && obj.obj().Any())
                    {
                        if (!(view is ViewGroup viewGroup))
                            throw new InvalidOperationException(
                                $"{obj.Start.Text} must be {nameof(ViewGroup)} to have child view");
                        viewGroup.Children.AddRange(obj.obj().Select(BuildViewTree));
                    }
                    return view;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        private void InitPair(ref View view, params ShibaParser.PairContext[] pairs)
        {
            var properties = view.GetType().GetRuntimeProperties().ToArray();
            foreach (var pair in pairs)
            {
                var property = properties.FirstOrDefault(item =>
                    string.Equals(item.Name, pair.Start.Text, StringComparison.OrdinalIgnoreCase));
                if (property == null)
                {
                    throw new MissingMemberException();
                }
                if (property.CanWrite)
                {
                    object targetValue = null;
                    try
                    {
                        targetValue = Convert.ChangeType(pair.value().GetText(), property.PropertyType);
                    }
                    catch (Exception e)
                    {
                        Debug.WriteLine(e.Message);
                        Debug.WriteLine(e.StackTrace);
                    }
                    
                }
            }
        }

        private IEnumerable<Type> FindTypes(string name)
        {
            return NameSpaces.Select(item => Type.GetType($"{item}.{name}"));
        }
    }
}