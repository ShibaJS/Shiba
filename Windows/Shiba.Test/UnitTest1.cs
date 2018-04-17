using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Shiba.Parser;
using Xunit;

namespace Shiba.Test
{
    public class UnitTest1
    {
        [Fact]
        public void Test1()
        {
            var input = "xxx {aaa: true, bbb: 4312412, ccc{aaa:false}}";
            var stream = CharStreams.fromstring(input);
            var lexer = new ShibaLexer(stream);
            var tokens = new CommonTokenStream(lexer);
            var parser = new ShibaParser(tokens) {BuildParseTree = true};
            var tree = parser.root();
            var obj = tree.GetChild<ShibaParser.ObjContext>(0);
            var child = obj.GetChild(1);
            
            Assert.NotNull(tree);
        }
    }
}