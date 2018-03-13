using System;
using System.IO;
using Sprache;
using Xunit;

namespace Shiba.Test
{
    public class UnitTest1
    {
        [Fact]
        public void Test1()
        {
            var text = "<body>\n    <p name=\"sadasdas\" aaa=\"aaa\">\n        hello,<br aaa=\"aaa\"/> <!--\n      This is a comment\n    --><i>world!</i>\n    </p>\n</body>";
            var doc = SbParser.Node.Parse(text);
            Assert.NotNull(doc);
        }
    }
}