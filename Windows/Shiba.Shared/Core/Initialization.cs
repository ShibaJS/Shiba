using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;

namespace Shiba.Core
{
    public partial class Initialization
    {
        public static Initialization Instance { get; } = new Initialization();

        public List<ExportRendererAttribute> Renderers { get; private set; }

        public static void Init()
        {
            Instance.Renderers = Instance.GetAssemblies()
                .SelectMany(item => item.GetCustomAttributes<ExportRendererAttribute>()).ToList();
        }
    }
}