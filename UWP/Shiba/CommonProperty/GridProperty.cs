using Windows.UI.Xaml.Controls;
using Shiba.Controls;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;


namespace Shiba.CommonProperty
{
    public class GridProperty : AbsCommonProperty<ShibaObject>
    {
        public override string Name { get; } = "grid";

        public override void SetValue(ShibaObject shibaObject, View targetShibaView, NativeView element, NativeViewGroup parent)
        {
            var row = shibaObject.Get<int>("row");
            var column = shibaObject.Get<int>("column");
            var rowSpan = shibaObject.Get<int>("rowSpan");
            var columnSpan = shibaObject.Get<int>("columnSpan");
            if (row != default) Grid.SetRow(element, row);

            if (column != default) Grid.SetColumn(element, column);

            if (rowSpan != default) Grid.SetRowSpan(element, rowSpan);

            if (columnSpan != default) Grid.SetColumnSpan(element, columnSpan);
        }
    }
}