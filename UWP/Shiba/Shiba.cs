using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;
using Windows.ApplicationModel.Core;
using Windows.UI.Core;
using Shiba.CommonProperty;
using Shiba.Controls;
using Shiba.ExtensionExecutors;
using Shiba.Scripting;
using Shiba.Scripting.Runtime;
using Console = Shiba.Scripting.Runtime.Console;

namespace Shiba
{
    public class ShibaApp
    {
        private ShibaApp(Action<ShibaConfiguration> action)
        {
            action?.Invoke(Configuration);
            var assemblies = AppDomain.CurrentDomain.GetAssemblies().ToList();
            ViewMappings = assemblies
                .Where(item => item.GetCustomAttributes<ExportMapperAttribute>()?.Any() == true)
                .SelectMany(item => item.GetCustomAttributes<ExportMapperAttribute>()).ToList().AsReadOnly();
        }

        public ReadOnlyCollection<ExportMapperAttribute> ViewMappings { get; }
        internal Dictionary<string, View> Components { get; } = new Dictionary<string, View>();
        public ShibaConfiguration Configuration { get; } = new ShibaConfiguration();
        public static ShibaApp Instance { get; protected set; }
        internal View AppComponent { get; set; }

        public void AddConverter(string name, Func<List<object>, object> converter)
        {
            Configuration.NativeConverters.Add(name, converter);
        }

        public static void Init(Action<ShibaConfiguration> action = null)
        {
            Instance = new ShibaApp(c =>
            {
                c.PlatformType = "UWP";
                c.ScriptRuntime.AddObject("shibaStorage", new Storage());
                c.ScriptRuntime.AddObject("console", new Console());
                c.CommonProperties.Add(new GridProperty());
                c.CommonProperties.Add(new RelativeProperty());
                c.ExtensionExecutors.Add(new BindingExecutor());
                c.ExtensionExecutors.Add(new JsonExecutor());
                action?.Invoke(c);
            }); 
        }
    }

    public class ShibaConfiguration
    {
        public IScriptRuntime ScriptRuntime { get; set; } = new DefaultScriptRuntime();
        public string PlatformType { get; set; } = "Windows";

        public Dictionary<string, Func<List<object>, object>> NativeConverters { get; set; } =
            new Dictionary<string, Func<List<object>, object>>();

        public List<IExtensionExecutor> ExtensionExecutors { get; } = new List<IExtensionExecutor>();

        public List<ICommonProperty> CommonProperties { get; } = new List<ICommonProperty>();
    }
}