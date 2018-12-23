using Windows.UI.Xaml.Controls;
using Shiba.Controls;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;


namespace Shiba.CommonProperty
{
    public class GridProperty : AbsCommonProperty<ShibaMap>
    {
        public override string Name { get; } = "grid";

        public override void SetValue(ShibaMap map, NativeView element, NativeViewGroup parent)
        {
            var row = map.Get<int>("row");
            var column = map.Get<int>("column");
            var rowSpan = map.Get<int>("rowSpan");
            var columnSpan = map.Get<int>("columnSpan");
            if (row != default) Grid.SetRow(element, row);

            if (column != default) Grid.SetColumn(element, column);

            if (rowSpan != default) Grid.SetRowSpan(element, rowSpan);

            if (columnSpan != default) Grid.SetColumnSpan(element, columnSpan);
        }
    }
}