using System;
using System.Collections.Generic;
using System.Text;
using Shiba.Controls;
using Shiba.ViewMappers;
#if WINDOWS_UWP
using Windows.UI.Xaml.Media;
using NativeView = Windows.UI.Xaml.Controls.TextBlock;
#elif WPF
using System.Windows.Media;
using NativeView = System.Windows.Controls.TextBlock;
#elif FORMS
using NativeView = Xamarin.Forms.Label;
#endif

[assembly: ExportMapper("text", typeof(TextMapper))]
namespace Shiba.ViewMappers
{
    public class TextMapper : ViewMapper<NativeView>
    {
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

        protected override bool HasDefaultProperty { get; } = true;

        protected override PropertyMap DefaultPropertyMap { get; } = new PropertyMap("text", NativeView.TextProperty, typeof(string));
    }
}
