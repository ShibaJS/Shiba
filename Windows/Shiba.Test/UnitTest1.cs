using System;
using System.Collections.Generic;
using System.Linq;
using Jint;
using Jint.Native.Function;
using Shiba.Parser;
using Xunit;

namespace Shiba.Test
{
    class Shiba : AbstractShiba
    {

        static Shiba()
        {
            Instance = new Shiba();
        }

        public Shiba(Action<ShibaConfiguration> action = null) : base(action)
        {
        }
    }
    
    public class UnitTest1
    {
        [Fact]
        public void Test1()
        {
            //Shiba.Init();
//            const string input =
////                "stackLayout {orientation: horizontal, padding: [8, 0], alpha: 50%, etgsa: $bind aaa, input{content: fdsafds(dsafs($bind dasfd, dfsa), dfsafs)}}";
//            const string input =
//                "stackLayout {orientation: dsads(dsafs($bind aaa, $bind bbb), dsafs)}";
//            var tree = new ShibaParserWrapper().Parse(input);
////            var view = ShibaParserWrapper.BuildViewTree(tree);

//            Assert.NotNull(tree);
        }

    }
}