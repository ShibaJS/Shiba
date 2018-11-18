using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using ChakraCore.NET.API;
using Shiba.Controls;
using Shiba.Scripting;

namespace Shiba
{
    public abstract class AbstractShiba
    {
        protected AbstractShiba(Action<ShibaConfiguration> action)
        {
            action?.Invoke(Configuration);
            ViewMapping.Init();
        }

        public ViewMapping ViewMapping { get; } = new ViewMapping();

        public ShibaConfiguration Configuration { get; } = new ShibaConfiguration();

        public static AbstractShiba Instance { get; protected set; }

        public void AddConverter(string converter)
        {
            Configuration.ScriptRuntime.Execute(converter);
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