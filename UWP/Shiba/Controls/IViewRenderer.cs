
using ChakraHosting;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;

namespace Shiba.Controls
{
    public interface IShibaContext
    {
        object DataContext { get; }
        void EventCallback(string name);
    }

    internal interface IViewMapper
    {
        object Map(View view, IShibaContext context);
    }

    internal interface IAllowChildViewMapper : IViewMapper
    {
        void AddChild(NativeView parent, NativeView child);
    }
}