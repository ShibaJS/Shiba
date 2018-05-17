using System;
using System.Collections.Generic;
using System.Text;
#if WINDOWS_UWP
using Windows.UI.Xaml;
#else
using System.Windows;
#endif

#if WINDOWS_UWP
namespace Shiba.UWP
#else 
namespace Shiba.WPF
#endif
{
    public class DefaultResourceValueResolver : IValueResolver
    {
        public object GetValue(object value)
        {
#if WINDOWS_UWP
            return Application.Current.Resources[value];
#else
            return Application.Current.Resources[value];
#endif
        }
    }

    public class ShibaApp : AbstractShiba
    {
        public static void Init(Action<ShibaConfiguration> action = null)
        {
            Instance = new ShibaApp(c =>
            {
                c.ResourceValueResolver = new DefaultResourceValueResolver();
                c.PlatformType =
#if WINDOWS_UWP
                    "UWP"
#else
                    "WPF"
#endif
                    ;
                action?.Invoke(c);
            });
        }

        private ShibaApp(Action<ShibaConfiguration> action = null) : base(action)
        {
        }
    }
}
