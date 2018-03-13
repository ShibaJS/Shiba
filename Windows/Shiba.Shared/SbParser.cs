using System.Collections.Generic;
using System.Linq;
using Sprache;

namespace Shiba
{
    internal class Item
    {
    }

    internal class Content : Item
    {
        public string Text { get; set; }

        public override string ToString()
        {
            return Text;
        }
    }

    internal class Node : Item
    {
        public string Name { get; set; }
        public IDictionary<string, string> Attributes { get; set; }
        public IEnumerable<Item> Children { get; set; }

        public override string ToString()
        {
            if (Children != null)
                return $"<{Name} {string.Join(" ", Attributes.Select(x => $"{x.Key}=\"{x.Value}\""))}>" +
                       Children.Aggregate("", (s, c) => s + c) +
                       $"</{Name}>";
            return $"<{Name} {string.Join(" ", Attributes.Select(x => $"{x.Key}=\"{x.Value}\""))}/>";
        }
    }

    internal static class SbParser
    {
        private static readonly CommentParser Comment = new CommentParser("<!--", "-->", "\r\n");

        private static readonly Parser<string> Identifier =
            from first in Parse.Letter.Once()
            from rest in Parse.LetterOrDigit.XOr(Parse.Char('-')).XOr(Parse.Char('_')).Many()
            select new string(first.Concat(rest).ToArray());

        private static readonly Parser<(string name, IOption<IEnumerable<KeyValuePair<string, string>>> attribute)>
            BeginTag = Tag(
                from name in Identifier
                from attribute in Attribute.Many().Optional()
                select (name, attribute));

        private static readonly Parser<Content> Content =
            from chars in Parse.CharExcept('<').Many()
            select new Content {Text = new string(chars.ToArray())};

        private static readonly Parser<KeyValuePair<string, string>> Attribute =
            from name in Parse.Letter.Many().Text().Token()
            from eq in Parse.Char('=').Token()
            from begin in Parse.Char('"').Token()
            from value in Parse.AnyChar.Except(Parse.Char('"')).Many().Text().Token()
            from end in Parse.Char('"').Token()
            select new KeyValuePair<string, string>(name, value);


        private static readonly Parser<Node> FullNode =
            from tag in BeginTag
            from nodes in Parse.Ref(() => Item).Many()
            from end in EndTag(tag.name)
            select new Node
            {
                Name = tag.name,
                Attributes = tag.attribute.GetOrDefault()?.ToDictionary(x => x.Key, x => x.Value),
                Children = nodes
            };

        private static readonly Parser<Node> ShortNode = Tag(
            from id in Identifier
            from attributes in Attribute.Many().Optional()
            from slash in Parse.Char('/')
            select new Node
            {
                Name = id,
                Attributes = attributes.GetOrDefault()?.ToDictionary(x => x.Key, x => x.Value)
            });

        public static readonly Parser<Node> Node = ShortNode.Or(FullNode);

        private static readonly Parser<Item> Item =
            from leading in Comment.MultiLineComment.Many()
            from item in Node.Select(n => (Item) n).XOr(Content)
            from trailing in Comment.MultiLineComment.Many()
            select item;


        private static Parser<T> Tag<T>(Parser<T> content)
        {
            return from lt in Parse.Char('<')
                from t in content
                from attribute in Attribute.Many()
                from gt in Parse.Char('>').Token()
                select t;
        }

        private static Parser<string> EndTag(string name)
        {
            return Tag(from slash in Parse.Char('/')
                from id in Identifier
                where id == name
                select id).Named("closing tag for " + name);
        }
    }
}