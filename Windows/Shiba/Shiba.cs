using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Runtime.InteropServices;
using Jint;
using Jint.Native;
using Jint.Native.Function;
using Newtonsoft.Json.Linq;
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
            if (Configuration?.ConverterExecutor is DefaultConverterExecutor executor)
            {
                executor.Register(converter);
            }
        }
    }

    public class ShibaConfiguration
    {
        public IJsonValueResolver JsonValueResolver { get; set; } = new DefaultJsonValueResolver();
        public IValueResolver ResourceValueResolver { get; set; }
        public IBindingValueResolver BindingValueResolver { get; set; } = new DefaultBindingValueResolver();
        public IConverterExecutor ConverterExecutor { get; set; } = new DefaultConverterExecutor();
        public string PlatformType { get; set; } = "Windows";

        public List<ICommonProperty> CommonProperties { get; } =
            AppDomain.CurrentDomain.GetAssemblies()
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
        private readonly Engine _engine;

        public DefaultConverterExecutor(Engine engine = null)
        {
            _engine = engine ?? new Engine();
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
            var jsConverter = _engine.GetValue(functionName);
            if (jsConverter != null && jsConverter.Is<FunctionInstance>())
            {
                return jsConverter.Invoke(parameters.Select(it => JsValue.FromObject(_engine, it)).ToArray()).ToObject();
            }
            

            throw new EntryPointNotFoundException();
        }
    }

    public class DefaultJsonValueResolver : IJsonValueResolver
    {
        public object GetValue(object dataContext, string path)
        {
            if (!(dataContext is JObject jObject) || string.IsNullOrEmpty(path))
            {
                return null;
            }

            return jObject[path].Value<object>();
        }
    }
    
    public class DefaultBindingValueResolver : IBindingValueResolver
    {
        public object GetValue(object dataContext, string name)
        {
            if (dataContext == null || string.IsNullOrEmpty(name))
            {
                return null;
            }

            var propertyInfo = dataContext.GetType().GetTypeInfo().DeclaredProperties.FirstOrDefault(it =>
                string.Equals(it.Name, name, StringComparison.OrdinalIgnoreCase));
            
            if (propertyInfo == null)
            {
                return null;
            }

            return propertyInfo.GetValue(dataContext);
        }
    }



    public interface IBindingValueResolver
    {
        object GetValue(object dataContext, string name);
    }

    public interface IJsonValueResolver
    {
        object GetValue(object dataContext, string path);
    }

    public interface IValueResolver
    {
        object GetValue(object value);
    }
}