using System;
using System.Collections.Generic;
using System.Text;

#if WINDOWS_UWP
namespace Shiba.UWP
#else 
namespace Shiba.WPF
#endif
{
    public class Shiba : AbstractShiba
    {
        static Shiba()
        {
            Instance = new Shiba(c =>
            {
                c.PlatformType =
#if WINDOWS_UWP
                    "UWP"
#else
                    "WPF"
#endif
                    ;
            });
        }

        public Shiba(Action<ShibaConfiguration> action = null) : base(action)
        {
        }
    }
}
