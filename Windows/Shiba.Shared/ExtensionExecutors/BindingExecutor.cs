using System;
using System.Collections.Generic;
using System.Linq;
using Shiba.Controls;
using Shiba.Visitors;
using ShibaView = Shiba.Controls.View;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Media;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using Windows.UI.Xaml.Data;

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
using System.Windows.Data;
#endif


namespace Shiba.ExtensionExecutors
{
    public class BindingExecutor : IBindingExtensionExecutor
    {
        public string Name { get; } = "bind";

        public object ProvideValue(IShibaContext context, ShibaExtension value)
        {
            return ProvideNativeBinding(context, value);
        }

        public NativeBinding ProvideNativeBinding(IShibaContext context, ShibaExtension value)
        {
            var dataContextPath =
#if FORMS
                "BindingContext";
#elif WINDOWS_UWP || WPF
                "DataContext";
#endif
            var targetPath = value.Value != null && value.Value.TypeCode == ShibaValueType.Token ? value.Value.Value as string : null;
            var path = string.IsNullOrEmpty(targetPath)
                ? dataContextPath
                : $"{dataContextPath}.{targetPath}";

            return new NativeBinding
            {
                Source = context.ShibaHost,
#if FORMS
                Path = path,
#elif WINDOWS_UWP || WPF
                Path = new PropertyPath(path),
                UpdateSourceTrigger = UpdateSourceTrigger.PropertyChanged,
#endif
            };
        }
    }
}