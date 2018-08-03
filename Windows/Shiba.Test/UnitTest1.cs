using System;
using System.Collections.Generic;
using System.Linq;
using Jint;
using Jint.Native.Function;
using Shiba.Parser;
using Xunit;

namespace Shiba.Test
{
    class ShibaApp : AbstractShiba
    {


        public static void Init(Action<ShibaConfiguration> action = null)
        {
            Instance = new ShibaApp(c =>
            {
                action?.Invoke(c);
            });
        }

        private ShibaApp(Action<ShibaConfiguration> action = null) : base(action)
        {
        }
    }
    
    public class UnitTest1
    {
        [Fact]
        public void Test1()
        {
            ShibaApp.Init();
            var wrapper = new ShibaParserWrapper();
            
//            const string input =
//                "stackLayout {orientation: horizontal, padding: [8, 0], alpha: 50%, etgsa: $bind aaa, input{content: fdsafds(dsafs($bind dasfd, dfsa), dfsafs)}}";
            const string input =
                "stackLayout {orientation= dsads(dsafs($bind aaa, $bind bbb), dsafs) fdsafd = [ fdsaf = 1121 fdsafd = false ] aaa -> 123 ccc -> fjsoi = cdsa jiodsa { aa = dasf }}";
            var tree = wrapper.Parse(input);
//            var view = ShibaParserWrapper.BuildViewTree(tree);

            Assert.NotNull(tree);
            var str = tree.ToString();
            var tree2 = wrapper.Parse(str);
            Assert.Equal(tree, tree2);
        }

    }
}