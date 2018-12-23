using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;

namespace Shiba.CommonProperty
{
    public abstract class AbsCommonProperty<TValue> : ICommonProperty
    {
        public abstract string Name { get; }

        public void Handle(object targetValue, object targetNativeView, object parentNativeView)
        {
            if (targetValue is TValue value && targetNativeView is NativeView nativeView &&
                parentNativeView is NativeViewGroup parent) SetValue(value, nativeView, parent);
        }

        public abstract void SetValue(TValue targetValue, NativeView nativeView, NativeViewGroup parent);
    }
}