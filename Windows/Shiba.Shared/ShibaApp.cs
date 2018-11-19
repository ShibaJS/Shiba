using System;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Shiba.Scripting.Runtime;
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

    public class ShibaApp : AbstractShiba
    {
        public static void Init(Action<ShibaConfiguration> action = null)
        {
            Instance = new ShibaApp(c =>
            {
                c.PlatformType =
#if WINDOWS_UWP
                    "UWP"
#elif WPF
                    "WPF"
#elif FORMS
                    "Forms"
#endif
                    ;
#if WINDOWS_UWP
                c.ScriptRuntime.AddObject("storage", new Storage());
#endif
                action?.Invoke(c);
            });
        }

        private ShibaApp(Action<ShibaConfiguration> action = null) : base(action)
        {
        }
    }
}
