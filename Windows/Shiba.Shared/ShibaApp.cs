using System;

#if WINDOWS_UWP
using Windows.UI.Xaml;
#elif WPF
using System.Windows;
#elif FORMS
using Xamarin.Forms;
#endif

#if WINDOWS_UWP
namespace Shiba.UWP
#elif WPF 
namespace Shiba.WPF
#elif FORMS 
namespace Shiba.Forms
#endif
{
    public class DefaultResourceValueResolver : IValueResolver
    {
        public object GetValue(object value)
        {
#if WINDOWS_UWP
            return Application.Current.Resources[value];
#elif WPF
            return Application.Current.Resources[value];
#elif FORMS
            return Application.Current.Resources[value + ""];
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
#elif WPF
                    "WPF"
#elif FORMS
                    "Forms"
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
