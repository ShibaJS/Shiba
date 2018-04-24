using System;
using System.Collections.Generic;
using System.IO;
using System.Reflection;
using Windows.ApplicationModel;
using Windows.Storage;
using Windows.Storage.Search;

namespace Shiba.Core
{
    public partial class Initialization
    {
        private IEnumerable<Assembly> GetAssemblies()
        {
            var options = new QueryOptions { FileTypeFilter = { ".exe", ".dll" } };

            var query = Package.Current.InstalledLocation.CreateFileQueryWithOptions(options);
            var files = query.GetFilesAsync().AsTask().Result;

            var assemblies = new List<Assembly>(files.Count);
            foreach (var file in files)
            {
                try
                {
                    var assembly = Assembly.Load(new AssemblyName { Name = Path.GetFileNameWithoutExtension(file.Name) });
                    assemblies.Add(assembly);
                }
                catch (IOException)
                {
                }
                catch (BadImageFormatException)
                {
                }
            }

            var thisAssembly = GetType().GetTypeInfo().Assembly;
            // this happens with .NET Native
            if (!assemblies.Contains(thisAssembly))
                assemblies.Add(thisAssembly);

            return assemblies.ToArray();

        }
    }
}