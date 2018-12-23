using System.Collections.Generic;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Media;
using Shiba.Controls;
using Shiba.ViewMappers;
using NativeView = Windows.UI.Xaml.Controls.TextBox;

[assembly: ExportMapper("input", typeof(InputMapper))]

namespace Shiba.ViewMappers
{
    public class InputMapper : ViewMapper<NativeView>
    {
        protected override bool HasDefaultProperty { get; } = true;

        protected override PropertyMap DefaultPropertyMap { get; } =
            new PropertyMap("text", NativeView.TextProperty, typeof(string), isTwoWay: true);

        public override IEnumerable<IValueMap> PropertyMaps()
        {
            foreach (var propertyMap in base.PropertyMaps()) yield return propertyMap;
            yield return new PropertyMap("size", Control.FontSizeProperty, typeof(double));
            yield return new PropertyMap("color", Control.ForegroundProperty, typeof(string), typeof(SolidColorBrush),
                ColorConverter);
        }
    }
}