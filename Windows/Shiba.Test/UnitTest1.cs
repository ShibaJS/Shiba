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
            var input = "grid {aaa: true, bbb: 4312412, input{aaa:false}}";
            var tree = new ShibaParserWrapper().Parse(input);

//            var view = ShibaParserWrapper.BuildViewTree(tree);

            Assert.NotNull(tree);
        }
    }
}