using Shiba.Controls;

#if WINDOWS_UWP
using Windows.UI.Xaml.Controls;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;
#elif FORMS
using Xamarin.Forms;
using NativeView = Xamarin.Forms.View;
using NativeBinding = Xamarin.Forms.Binding;
using NativeProperty = Xamarin.Forms.BindableProperty;
using NativeThickness = Xamarin.Forms.Thickness;
using NativeViewGroup = Xamarin.Forms.Layout<Xamarin.Forms.View>;
#elif WPF
using System.Windows;
using System.Windows.Controls;
using NativeView = System.Windows.FrameworkElement;
using NativeBinding = System.Windows.Data.Binding;
using NativeProperty = System.Windows.DependencyProperty;
using NativeThickness = System.Windows.Thickness;
using System.Windows.Media;
using NativeViewGroup = System.Windows.Controls.Panel;
#endif

namespace Shiba.CommonProperty
{
    public class RelativeProperty: AbsCommonProperty<ShibaMap>
    {
        public override string Name { get; } = "relative";
        public override void SetValue(ShibaMap map, NativeView element, NativeViewGroup parent)
        {
            
        }
    }
}