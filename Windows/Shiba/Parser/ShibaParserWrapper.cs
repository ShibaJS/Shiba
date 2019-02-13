using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Xml.Linq;
using Shiba.Controls;
using Shiba.Internal;

namespace Shiba.Parser
{
    internal interface IShibaVisitor
    {
        Type HandleType { get; }
        Type ReturnType { get; }
        object Visit(object tree);
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
        public abstract Type ReturnType { get; }
        public abstract object Visit(object tree);

        //protected internal static object GetValue(object tree)
        //{
        //    if (tree == null) return null;
        //    var visitor = Visitors.FirstOrDefault(it => it.HandleType == tree.GetType());
            
        //    return visitor.Visit(tree);
        //}

        protected internal static T GetValue<T>(object tree)
        {
            if (tree == null) return default;
            var visitor = Visitors.FirstOrDefault(it => it.HandleType == tree.GetType() && it.ReturnType == typeof(T));

            var value = visitor.Visit(tree);

            if (value is T result) return result;

            return default;
        }
    }

    internal abstract class GenericVisitor<T, K> : ShibaVisitor
    {
        public override Type HandleType { get; } = typeof(T);
        public override Type ReturnType { get; } = typeof(K);

        public override object Visit(object tree)
        {
            if (tree is T t)
            {
                return Parse(t);
            }

            return null;
        }

        protected abstract K Parse(T tree);
    }

    internal sealed class ViewVisitor : GenericVisitor<XElement, View>
    {
        protected override View Parse(XElement tree)
        {
            var viewName = GetValue<ShibaToken>(tree.Name);
            var view = new View(viewName, tree.ToString());

            view.Properties.AddRange(tree.Attributes().Select(GetValue<Property>));
            var propertyElement = tree.Elements()
                .Where(it =>
                    it.Name.LocalName.Contains(".") &&
                    it.Name.LocalName.Split('.').FirstOrDefault() == viewName.Value &&
                    it.Name.NamespaceName == viewName.Prefix).ToList();
            view.Properties.AddRange(propertyElement
                .Select(GetValue<Property>));

            view.Children.AddRange(tree.Elements().Except(propertyElement).Select(it =>
            {
                var child = GetValue<View>(it);
                child.Parent = view;
                return child;
            }));

            return view;
        }
    }

    internal sealed class PropertyElementVisitor : PropertyBaseVisitor<XElement>
    {
        protected override Property Parse(XElement tree)
        {
            var name = GetValue<ShibaToken>(tree.Name);
            if (tree.HasElements)
            {
                return new Property(name, GetValue<View>(tree.Elements().FirstOrDefault()));
            }
            else
            {
                return new Property(name, GetPropertyValue(tree.Value));
            }
        }
    }

    internal abstract class PropertyBaseVisitor<T> : GenericVisitor<T, Property>
    {
        private const char OpenCurly = '{';
        private const char CloseCurly = '}';
        private const char OpenBracket = '[';
        private const char CloseBracket = ']';

        protected object GetPropertyValue(string value)
        {
            value = value.Trim();
            var subValue = value.Substring(1, value.Length - 2);
            if (value.StartsWith(OpenCurly) && value.EndsWith(CloseCurly))
            {
                // TODO: Find a better way
                var function = GetValue<ShibaFunction>(subValue);
                if (function != null)
                {
                    return function;
                }
                var extension = GetValue<ShibaExtension>(subValue);
                if (extension != null)
                {
                    return extension;
                }
            }
            else if (value.StartsWith(OpenBracket) && value.EndsWith(CloseBracket))
            {
                return GetValue<ShibaMap>(subValue);
            }

            return value;
        }
    }

    internal sealed class PropertyVisitor : PropertyBaseVisitor<XAttribute>
    {
        protected override Property Parse(XAttribute tree)
        {
            var tokenName = GetValue<ShibaToken>(tree.Name);
            return new Property(tokenName, GetPropertyValue(tree.Value));
        }
    }

    internal sealed class ShibaTokenVisitor : GenericVisitor<XName, ShibaToken>
    {
        protected override ShibaToken Parse(XName tree)
        {
            if (tree.LocalName.Contains('.'))
            {
                return new ShibaToken(tree.LocalName.Split('.').Skip(1).FirstOrDefault());
            }
            if (string.IsNullOrEmpty(tree.NamespaceName))
            {
                return new ShibaToken(tree.LocalName);
            }
            return new ShibaToken(tree.NamespaceName, tree.LocalName);
        }
    }

    internal sealed class ShibaMapVisitor : GenericVisitor<string, ShibaMap>
    {
        private const char EqualSign = '=';
        private const char Comma = ',';
        protected override ShibaMap Parse(string tree)
        {
            return new ShibaMap(tree
                    .Split(Comma)
                    .Select(it => it.Trim())
                    .Select(it => it.Split(EqualSign))
                    .ToDictionary(it => it.FirstOrDefault(), it => it.Skip(1).FirstOrDefault()));
        }
    }

    internal sealed class FunctionVisitor : GenericVisitor<string, ShibaFunction>
    {
        protected override ShibaFunction Parse(string tree)
        {
            var name = tree.Identifier().GetText();
            var function = new ShibaFunction(name);
            if (tree.value() != null) function.Parameters.AddRange(tree.value().Select(GetValue));

            return function;
        }
    }

    internal sealed class ShibaExtensionVisitor : GenericVisitor<string, ShibaExtension>
    {
        private const char ExtensionStart = '$';
        private const char Comma = ',';
        private const char EqualSign = '=';

        protected override ShibaExtension Parse(string tree)
        {
            if (!tree.StartsWith(ExtensionStart))
            {
                return null;
            }

            // Checking for extension name
            var value = tree.TrimStart(ExtensionStart);
            var index = value.IndexOf(' ');
            var name = value.Substring(0, index);
            // Checking for extension value
            value = value.Substring(index + 1, value.Length - index - 1);
            index = value.IndexOf(Comma);
            if (index == -1)// value only contains binding like {$bind path}
            {
                return new ShibaExtension(name.Trim(), value.Trim(), string.Empty);
            }
            // or value contains some other stuff link {$bind path, script={hello}}
            value = value.Substring(index + 1, value.Length - index - 1).Trim();
            var attrs = value.Split(Comma).Select(it => it.Split(EqualSign))
        }
    }

    public class ShibaParserWrapper
    {
        public View Parse(string input)
        {
            var tree = ParseGrammarTree(input);
            return ShibaVisitor.GetValue<View>(tree);
        }

        private XElement ParseGrammarTree(string input)
        {
            return XDocument.Parse(input).Root;
        }
    }
}