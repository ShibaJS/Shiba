using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Reflection;

namespace Shiba.Controls
{
    public class ViewMapping
    {
        public ReadOnlyCollection<ExportRendererAttribute> Renderers { get; private set; }

        public void Init()
        {
            var assemblies = AppDomain.CurrentDomain.GetAssemblies().ToList();
            Renderers = assemblies
                .Where(item => item.GetCustomAttributes<ExportRendererAttribute>()?.Any() == true)
                .SelectMany(item => item.GetCustomAttributes<ExportRendererAttribute>()).ToList().AsReadOnly();
        }
    }
}