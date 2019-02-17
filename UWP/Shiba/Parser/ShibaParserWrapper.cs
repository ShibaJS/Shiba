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
            if (visitor == null) return default;
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

            if (!tree.HasElements && !string.IsNullOrEmpty(tree.Value))
            {
                view.DefaultValue = Singleton<ValueParser>.Instance.Parse(tree.Value);
            }

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

    internal sealed class ValueParser
    {
        private const char OpenCurly = '{';
        private const char CloseCurly = '}';
        private const char OpenBracket = '[';
        private const char CloseBracket = ']';
        private const char ExtensionStart = '$';
        public object Parse(string value)
        {
            value = value.Trim();
            if (value.StartsWith(OpenCurly) && value.EndsWith(CloseCurly))
            {
                var subValue = value.Substring(1, value.Length - 2);
                var function = ShibaVisitor.GetValue<ShibaFunction>(subValue);
                if (function != null)
                {
                    return function;
                }
            }
            else if (value.StartsWith(OpenBracket) && value.EndsWith(CloseBracket))
            {
                var subValue = value.Substring(1, value.Length - 2);
                var map = ShibaVisitor.GetValue<ShibaMap>(subValue);
                if (map != null)
                {
                    return map;
                }
            }
            else if (value.StartsWith(ExtensionStart))
            {
                var extension = ShibaVisitor.GetValue<ShibaExtension>(value);
                if (extension != null)
                {
                    return extension;
                }
            }

            if (value.TryChangeType(typeof(bool), out var boolValue))
            {
                return boolValue;
            }
            if (value.TryChangeType(typeof(decimal), out var numberValue))
            {
                return numberValue;
            }
            return value;
        }
    }

    internal abstract class PropertyBaseVisitor<T> : GenericVisitor<T, Property>
    {

        protected object GetPropertyValue(string value)
        {
            return Singleton<ValueParser>.Instance.Parse(value);
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
        private const char Comma = ',';
        protected override ShibaFunction Parse(string value)
        {
            var index = value.IndexOf('(');
            var name = value.Substring(0, index);
            var function = new ShibaFunction(name.Trim());
            value = value.Substring(index + 1, value.Length - index - 2);
            var param = value.Split(Comma).Select(it => Singleton<ValueParser>.Instance.Parse(it.Trim()));
            function.Parameters.AddRange(param);
            return function;
        }
    }

    internal sealed class ShibaExtensionVisitor : GenericVisitor<string, ShibaExtension>
    {
        private const char ScriptStart = '{';
        private const char ExtensionStart = '$';

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
            index = value.IndexOf(ScriptStart);
            if (index == -1)// value only contains binding like `$bind path`
            {
                return new ShibaExtension(name.Trim(), value.Trim(), string.Empty);
            }

            var bindingValue = value.Substring(0, index).Trim();
            // or value contains script like `$bind path, {return hello}`
            value = value.Substring(index + 1, value.Length - index - 1).Trim();
            var script = value.Substring(1, value.Length - 2);
            return new ShibaExtension(name.Trim(), bindingValue, script);
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