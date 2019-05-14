using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Reflection;
using Shiba.CommonProperty;
using Shiba.Controls;
using Shiba.ExtensionExecutors;
using Shiba.Scripting;
using Shiba.Scripting.Runtime;

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

        public void AddConverter(string converter)
        {
            Configuration.ScriptRuntime.Execute(converter);
        }

        public static void Init(Action<ShibaConfiguration> action = null)
        {
            Instance = new ShibaApp(c =>
            {
                c.PlatformType = "UWP";
                c.ScriptRuntime.AddObject("storage", new Storage());
                action?.Invoke(c);
            });
        }
    }

    public class ShibaConfiguration
    {
        public IScriptRuntime ScriptRuntime { get; set; } = new DefaultScriptRuntime();
        public string PlatformType { get; set; } = "Windows";

        public List<IExtensionExecutor> ExtensionExecutors { get; } =
            AppDomain.CurrentDomain.GetAssemblies()
                .Where(it => !it.IsDynamic)
                .SelectMany(it => it.ExportedTypes)
                .Where(it => it.IsClass && !it.IsAbstract && typeof(IExtensionExecutor).IsAssignableFrom(it))
                .Select(it => Activator.CreateInstance(it) as IExtensionExecutor).ToList();

        public List<ICommonProperty> CommonProperties { get; } =
            AppDomain.CurrentDomain.GetAssemblies()
                .Where(it => !it.IsDynamic)
                .SelectMany(it => it.ExportedTypes)
                .Where(it => it.IsClass && !it.IsAbstract && typeof(ICommonProperty).IsAssignableFrom(it))
                .Select(it => Activator.CreateInstance(it) as ICommonProperty).ToList();
    }
}