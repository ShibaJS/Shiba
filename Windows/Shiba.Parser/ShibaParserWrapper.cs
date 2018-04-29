using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Linq.Expressions;
using System.Reflection;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Shiba.Controls;

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
                    var view = FindTypes(obj.Start.Text)?.FirstOrDefault()?.CreateInstance<View>(PairToDictionary(obj.pair()));
                    //InitPair(ref view, obj.pair());
                    if (obj.obj() != null && obj.obj().Any())
                    {
                        if (!(view is ViewGroup viewGroup))
                            throw new InvalidOperationException(
                                $"{view?.Name ?? obj.Start.Text} must be {nameof(ViewGroup)} to have child view");
                        viewGroup.Children.AddRange(obj.obj().Select(BuildViewTree));
                    }
                    return view;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        private Dictionary<string, object> PairToDictionary(IEnumerable<ShibaParser.PairContext> pair)
        {
            return pair?.ToDictionary(x => x.Start.Text, x => GetValue(x.value())) ?? new Dictionary<string, object>();
        }

        private object GetValue(ShibaParser.ValueContext context)
        {
            if (context == null)
            {
                return null;
            }
            if (context.BOOLEAN() != null)
            {
                return bool.Parse(context.BOOLEAN().GetText());
            }

            if (context.NUMBER() != null)
            {
                return decimal.Parse(context.NUMBER().GetText());
            }

            if (context.percent() != null)
            {
                return new Percent(float.Parse(context.percent().Start.Text));
            }

            if (context.thickness() != null)
            {
                return new Thickness(context.thickness().GetText());
            }

            if (context.binding() != null)
            {
                return new Binding(CreateComput(context.binding().comput()));
            }

            if (context.native() != null)
            {
                return new NativeResource(CreateComput(context.native().comput()));
            }

            if (context.jsonpath() != null)
            {
                return new JsonPath(CreateComput(context.jsonpath().comput()));
            }

            if (context.dic() != null)
            {
                var pair = context.dic().pair().FirstOrDefault(it => it.TOKEN().Symbol.Text == "Windows");
                return GetValue(pair?.value());
            }

            return context.STRING()?.GetText()?.Trim('"') ?? context.GetText();
        }

        private Comput CreateComput(ShibaParser.ComputContext comput)
        {
            if (comput.comput() != null)
            {
                return new Comput
                {
                    FuncName = comput.comput().TOKEN().Symbol.Text,
                    InnerComput = CreateComput(comput.comput())
                };
            }

            return new Comput
            {
                Value = comput.TOKEN().Symbol.Text
            };
        }

        //private void InitPair(ref View view, params ShibaParser.PairContext[] pairs)
        //{
        //    var properties = view.GetType().GetRuntimeProperties().ToArray();
        //    foreach (var pair in pairs)
        //    {
        //        var property = properties.FirstOrDefault(item =>
        //            string.Equals(item.Name, pair.Start.Text, StringComparison.OrdinalIgnoreCase));
        //        if (property == null)
        //        {
        //            throw new MissingMemberException();
        //        }
        //        if (property.CanWrite)
        //        {
        //            object targetValue = null;
        //            try
        //            {
        //                targetValue = Convert.ChangeType(pair.value().GetText(), property.PropertyType);
        //            }
        //            catch (Exception e)
        //            {
        //                Debug.WriteLine(e.Message);
        //                Debug.WriteLine(e.StackTrace);
        //            }
                    
        //        }
        //    }
        //}

        private IEnumerable<Type> FindTypes(string name)
        {
            return ViewMapping.Instance.Views.Where(item => item.ViewName == name).Select(item => item.ViewType);
        }
    }
}