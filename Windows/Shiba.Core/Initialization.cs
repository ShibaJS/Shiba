using System;
using Shiba.Controls;
using Shiba.Internal;

namespace Shiba.Core
{
    public class Initialization
    {
        public static ShibaConfiguration Configuration => Singleton<ShibaConfiguration>.Instance;

        public static void Init(Action<ShibaConfiguration> action = null)
        {
            action?.Invoke(Configuration);
            ViewMapping.Instance.Init();
        }
    }

    public class ShibaConfiguration
    {
        public IJsonValueResolver JsonValueResolver { get; set; } = new DefaultJsonValueResolver();
        public IValueResolver ResourceValueResolver { get; set; } = new DefaultResourceValueResolver();
    }

    public class DefaultJsonValueResolver : IJsonValueResolver
    {
        public object GetValue(object dataContext, string path)
        {
            throw new NotImplementedException();
        }
    }

    public class DefaultResourceValueResolver : IValueResolver
    {
        public object GetValue(object value)
        {
            throw new NotImplementedException();
        }
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