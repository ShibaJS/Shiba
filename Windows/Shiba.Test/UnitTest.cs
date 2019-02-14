
using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shiba.Parser;

namespace Shiba.Test
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public void TestMethod1()
        {
            var tree = new ShibaParserWrapper().Parse(
                "<stack xmlns:uwp=\"UWP\">\r\n    <text text=\"{awesome($bind someText, true, 1)}\" uwp:size=\"20\" color=\"$bind someColor, {return true}\" empty=\"$bind ,{return false}\" normal=\"somenormal\" />\r\n</stack>");
            Assert.IsNotNull(tree);
        }
    }
}
