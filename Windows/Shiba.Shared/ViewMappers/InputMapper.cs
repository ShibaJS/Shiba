using System.Collections.Generic;
using Shiba.Controls;
using Shiba.ViewMappers;
#if WINDOWS_UWP
using Windows.UI.Xaml.Media;
using NativeView = Windows.UI.Xaml.Controls.TextBox;
#elif WPF
using System.Windows.Media;
using NativeView = System.Windows.Controls.TextBox;
#elif FORMS
using NativeView = Xamarin.Forms.Entry;
#endif

[assembly: ExportMapper("input", typeof(InputMapper))]
namespace Shiba.ViewMappers
{
    public class InputMapper : ViewMapper<NativeView>
    {
        protected override bool HasDefaultProperty { get; } = true;
        protected override PropertyMap DefaultPropertyMap { get; } = new PropertyMap("text", NativeView.TextProperty, typeof(string), isTwoWay: true);

        public override IEnumerable<IValueMap> PropertyMaps()
        {
            foreach (var propertyMap in base.PropertyMaps())
            {
                yield return propertyMap;
            }
            yield return new PropertyMap("size", NativeView.FontSizeProperty, typeof(double));
#if FORMS
            yield return new PropertyMap("color", NativeView.TextColorProperty, typeof(string), converter: ColorConverter);
#elif WINDOWS_UWP || WPF
            yield return new PropertyMap("color", NativeView.ForegroundProperty, typeof(string), typeof(SolidColorBrush), converter: ColorConverter);
#endif

        }
    }
}
