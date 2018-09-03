using System;
using System.Collections.Generic;
using System.Linq;
using Shiba.Controls;
using Shiba.ViewMappers;
#if WINDOWS_UWP
using NativeView = Windows.UI.Xaml.Controls.StackPanel;
using Windows.UI.Xaml.Controls;
#elif WPF
using System.Windows.Controls;
using NativeView = System.Windows.Controls.StackPanel;
#elif FORMS
using NativeView = Xamarin.Forms.StackLayout;
using Orientation = Xamarin.Forms.StackOrientation;
#endif

[assembly: ExportMapper("stack", typeof(StackMapper))]
namespace Shiba.ViewMappers
{
    public class StackMapper : ViewMapper<NativeView>
    {
        
        public override IEnumerable<IValueMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetProperties());
        }

        private IEnumerable<PropertyMap> GetProperties()
        {
            yield return new PropertyMap("orientation", NativeView.OrientationProperty, typeof(string), OrientationConverter);
        }

        private object OrientationConverter(object arg)
        {
            if (!(arg is string value))
            {
                throw new ArgumentException();
            }
            
            if (Enum.TryParse(value, true, out Orientation result))
            {
                return result;
            }

            throw new ArgumentOutOfRangeException();
        }
    }
}
