using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using JavaScriptEngineSwitcher.ChakraCore;
using JavaScriptEngineSwitcher.Core;
using JavaScriptEngineSwitcher.Jint;
using Shiba.Controls;

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
            if (Configuration?.ConverterExecutor is DefaultConverterExecutor executor) executor.Register(converter);
        }
    }

    public class ShibaConfiguration
    {
        public IConverterExecutor ConverterExecutor { get; set; } = new DefaultConverterExecutor();
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

    public interface IConverterExecutor
    {
        object Execute(string functionName, params object[] parameters);
    }

    public class DefaultConverterExecutor : IConverterExecutor
    {
        private readonly IJsEngine _engine;

        public DefaultConverterExecutor()
        {
            var engineSwitcher = JsEngineSwitcher.Current;
            engineSwitcher.EngineFactories
                .AddJint()
                .AddChakraCore();

            
            switch (Environment.OSVersion.Platform)
            {
                case PlatformID.Win32NT:
                case PlatformID.Win32S:
                case PlatformID.WinCE:
                case PlatformID.Xbox:
                case PlatformID.Win32Windows:
                    engineSwitcher.DefaultEngineName = ChakraCoreJsEngine.EngineName;
                    _engine = JsEngineSwitcher.Current.CreateEngine(ChakraCoreJsEngine.EngineName);
                    break;
                default:
                    engineSwitcher.DefaultEngineName = JintJsEngine.EngineName;
                    _engine = JsEngineSwitcher.Current.CreateEngine(JintJsEngine.EngineName);
                    break;
            }
        }

        public void Register(string converter)
        {
            _engine.Execute(converter);
        }

        public void Register(string name, Delegate @delegate)
        {
        }

        public object Execute(string functionName, params object[] parameters)
        {
            var result = _engine.CallFunction(functionName, parameters);
            return result;
        }
    }
}