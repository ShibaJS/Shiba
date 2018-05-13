using Antlr4.Runtime;
using Shiba.Core;
using Shiba.Parser;
using Xunit;

namespace Shiba.Test
{
    public class UnitTest1
    {
        [Fact]  
        public void Test1()
        {
            Initialization.Init();
            const string input = "stackLayout {orientation: horizontal, padding: [8, 0], alpha: 50%, etgsa: $bind aaa, input{content: fdsafds(dsafs($bind dasfd, dfsa), dfsafs)}}";
            var tree = new ShibaParserWrapper().Parse(input);

//            var view = ShibaParserWrapper.BuildViewTree(tree);

            Assert.NotNull(tree);
        }
    }
}