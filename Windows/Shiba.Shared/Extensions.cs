using System;
using System.Linq;
using System.Reflection;
using Shiba.Controls;
using Shiba.Visitors;
using ShibaView = Shiba.Controls.View;
#if WINDOWS_UWP
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
#elif FORMS
using Xamarin.Forms;
using NativeView = Xamarin.Forms.VisualElement;
using XFView = Xamarin.Forms.View;
using NativeBinding = Xamarin.Forms.Binding;
using NativeProperty = Xamarin.Forms.BindableProperty;
using NativeThickness = Xamarin.Forms.Thickness;
#elif WPF
using System.Windows;
using System.Windows.Controls;
using NativeView = System.Windows.FrameworkElement;
using NativeBinding = System.Windows.Data.Binding;
using NativeProperty = System.Windows.DependencyProperty;
using NativeThickness = System.Windows.Thickness;
using System.Windows.Media;
#endif

namespace Shiba
{
    public static class ShibaMapExtension
    {
        
        public static NativeThickness ToNativeThickness(this ShibaMap shibaObject)
        {
            var left = shibaObject?.Get<double>("left") ?? default;
            var right = shibaObject?.Get<double>("right") ?? default;
            var top = shibaObject?.Get<double>("top") ?? default;
            var bottom = shibaObject?.Get<double>("bottom") ?? default;
            var vertical = shibaObject?.Get<double>("vertical") ?? default;
            var horizon = shibaObject?.Get<double>("horizon") ?? default;

            if (vertical != default || horizon != default)
            {
                return new NativeThickness(left: horizon, top: vertical, right: horizon, bottom: vertical);
            }


            return new NativeThickness(left: left, top: top, right: right, bottom: bottom);
        }
        
        public static T Get<T>(this ShibaMap map, string name)
        {
            var value = map.Properties.FirstOrDefault(it => it.Name.IsCurrentPlatform(name))?.Value;
            if (value == null)
            {
                return default;
            }

            var result = ShibaValueVisitor.GetValue(value, null);
            if (result is T targetResult)
            {
                return targetResult;
            }

            if (typeof(IConvertible).GetTypeInfo().IsAssignableFrom(typeof(T).GetTypeInfo()))
            {
                return (T)Convert.ChangeType(result, typeof(T));
            }

            return default;
        }
    }
    
}