using Shiba.Controls;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;

namespace Shiba.CommonProperty
{
    public class RelativeProperty : AbsCommonProperty<ShibaObject>
    {
        public override string Name { get; } = "relative";

        public override void SetValue(ShibaObject shibaObject, View targetShibaView, NativeView element, NativeViewGroup parent)
        {
        }
    }
}