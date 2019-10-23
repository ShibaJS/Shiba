using System.Collections.Generic;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Media;
using Shiba.Controls;
using Shiba.ViewMappers;
using NativeView = Windows.UI.Xaml.Controls.TextBlock;

[assembly: ExportMapper("text", typeof(TextMapper))]

namespace Shiba.ViewMappers
{
    public class TextMapper : ViewMapper<NativeView>
    {
        protected override NativeView CreateNativeView(IShibaContext context)
        {
            return new NativeView()
            {
                TextWrapping = TextWrapping.Wrap,
            };
        }

        protected override bool HasDefaultProperty { get; } = true;

        protected override PropertyMap DefaultPropertyMap { get; } =
            new PropertyMap("text", NativeView.TextProperty, typeof(string));

        protected  override IEnumerable<IValueMap> PropertyMaps()
        {
            foreach (var propertyMap in base.PropertyMaps()) yield return propertyMap;
            yield return new PropertyMap("size", NativeView.FontSizeProperty, typeof(double));
            yield return new PropertyMap("color", NativeView.ForegroundProperty, typeof(string),
                typeof(SolidColorBrush), ColorConverter);
        }
    }
}