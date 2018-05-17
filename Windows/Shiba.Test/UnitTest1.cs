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
            var engine = new Engine();
            engine.Execute("function add(a, b) { return a + b; }")
                .Execute("function add(a, b, c) { return a + b + c; }");
            var c = engine.GetValue("add");
            var d = engine.GetValue("edd");
            var e = c.Is<FunctionInstance>();
            var s = d.Is<FunctionInstance>();
            var a = engine.Invoke("add", 1, 2);
            var b = engine.Invoke("add", 1, 3, 4);

            Assert.NotNull(engine);

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