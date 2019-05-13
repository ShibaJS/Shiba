
using System;
using System.IO;
using System.Threading.Tasks;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using Shiba;
using Windows.Storage;

namespace ShibaTest
{
    [TestClass]
    public class UnitTest1
    {
        [TestMethod]
        public async Task TestMethod1()
        {
            ShibaApp.Init();
            var fname = @"Assets\bundle.js";
            var InstallationFolder = Windows.ApplicationModel.Package.Current.InstalledLocation;
            var file = await InstallationFolder.GetFileAsync(fname);
            var contents = await File.ReadAllTextAsync(file.Path);
            ShibaApp.Instance.Configuration.ScriptRuntime.Execute(contents);
        }
    }
}
