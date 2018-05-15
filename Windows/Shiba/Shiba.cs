using System;
using System.Linq;
using System.Reflection;
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
    }

    public class ShibaConfiguration
    {
        public IJsonValueResolver JsonValueResolver { get; set; } = new DefaultJsonValueResolver();
        public IValueResolver ResourceValueResolver { get; set; } = new DefaultResourceValueResolver();
        public IBindingValueResolver BindingValueResolver { get; set; } = new DefaultBindingValueResolver();
        public IConverterExecutor ConverterExecutor { get; set; } = new DefaultConverterExecutor();
        public string PlatformType { get; set; } = "Windows";
    }

    public interface IConverterExecutor
    {
        object Execute(string functionName, params object[] paramters);
    }
    
    public class DefaultConverterExecutor : IConverterExecutor
    {
        public object Execute(string functionName, params object[] paramters)
        {
            throw new NotImplementedException();
        }
    }

    public class DefaultJsonValueResolver : IJsonValueResolver
    {
        public object GetValue(object dataContext, string path)
        {
            throw new NotImplementedException();
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

    public class DefaultResourceValueResolver : IValueResolver
    {
        public object GetValue(object value)
        {
            throw new NotImplementedException();
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