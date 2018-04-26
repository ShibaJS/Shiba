using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Reflection;
using Shiba.Controls;

namespace Shiba.Core
{
    internal class ViewMapping
    {
        private ViewMapping()
        {
            
        }

        public static ViewMapping Instance { get; } = new ViewMapping();

        public ReadOnlyCollection<ExportRendererAttribute> Renderers { get; private set; }
        
        public ReadOnlyCollection<ExportViewAttribute> Views { get; private set; }

        public void Init()
        {
            var assemblies = Device.Instance.GetAssemblies().ToList();
            Renderers = assemblies
                .Where(item => item.GetCustomAttributes<ExportRendererAttribute>()?.Any() == true)
                .SelectMany(item => item.GetCustomAttributes<ExportRendererAttribute>()).ToList().AsReadOnly();
            Views = assemblies
                .Where(item => item.GetCustomAttributes<ExportViewAttribute>()?.Any() == true)
                .SelectMany(item => item.GetCustomAttributes<ExportViewAttribute>()).ToList().AsReadOnly();
        }

    }

    public partial class Device
    {
        private Device()
        {
            
        }
        public static Device Instance { get; } = new Device();
    }

    public partial class Initialization
    {
        public static void Init()
        {
            ViewMapping.Instance.Init();
        }
    }
}