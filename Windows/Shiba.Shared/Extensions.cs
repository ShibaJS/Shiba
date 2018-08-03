using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using Shiba;
using Shiba.Controls;
using ShibaView = Shiba.Controls.View;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Media;
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
        public static Dictionary<string, object> ToDictionary(this ShibaMap shibaObject)
        {
            return shibaObject.Properties.Where(it => string.IsNullOrEmpty(it.Name.Prefix) ||
                                                      !string.IsNullOrEmpty(it.Name.Prefix) && it.Name.Prefix ==
                                                      AbstractShiba.Instance.Configuration.PlatformType)
                .ToDictionary(it => it.Name.Value, it => it.Value);
        }
        
        public static NativeThickness ToNativeThickness(this ShibaMap shibaObject)
        {
            var left = shibaObject?.GetValue<BasicValue>("left")?.Get<double>() ?? default;
            var right = shibaObject?.GetValue<BasicValue>("right")?.Get<double>() ?? default;
            var top = shibaObject?.GetValue<BasicValue>("top")?.Get<double>() ?? default;
            var bottom = shibaObject?.GetValue<BasicValue>("bottom")?.Get<double>() ?? default;
            var vertical = shibaObject?.GetValue<BasicValue>("vertical")?.Get<double>() ?? default;
            var horizon = shibaObject?.GetValue<BasicValue>("horizon")?.Get<double>() ?? default;

            if (vertical != default || horizon != default)
            {
                return new NativeThickness(left: horizon, top: vertical, right: horizon, bottom: vertical);
            }


            return new NativeThickness(left: left, top: top, right: right, bottom: bottom);
        }

        public static T GetValue<T>(this ShibaMap shibaObject, string name) where T : class 
        {
            return shibaObject.Properties.FirstOrDefault(it =>
                it.Name.Value == name && (string.IsNullOrEmpty(it.Name.Prefix) ||
                                          !string.IsNullOrEmpty(it.Name.Prefix) && it.Name.Prefix ==
                                          AbstractShiba.Instance.Configuration.PlatformType))?.GetValue<T>();
        }
    }

    public static class PropertyExtension
    {
        public static T GetValue<T>(this Property property) where T : class 
        {
            if (!string.IsNullOrEmpty(property.Name.Prefix) && property.Name.Prefix != AbstractShiba.Instance.Configuration.PlatformType)
            {
                return null;
            }

            return property.Value as T;
        }
    }

    public static class BasicValueExtension
    {
        public static T Get<T>(this BasicValue basicValue) 
        {
            switch (basicValue.TypeCode)
            {
                case ShibaValueType.Boolean:
                case ShibaValueType.Number:
                case ShibaValueType.String:
                case ShibaValueType.Token:
                    if (typeof(IConvertible).GetTypeInfo().IsAssignableFrom(typeof(T).GetTypeInfo()))
                    {
                        return (T) Convert.ChangeType(basicValue.Value, typeof(T));
                    }
                    else
                    {
                        return default;
                    }
                case ShibaValueType.Null:
                    return default;
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }
    }
}