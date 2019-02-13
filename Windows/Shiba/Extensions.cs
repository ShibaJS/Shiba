using System;
using System.Linq;
using System.Reflection;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Markup;
using Shiba.Controls;
using Shiba.Visitors;
using ShibaView = Shiba.Controls.View;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;

namespace Shiba
{
    public static class ViewExtension
    {
        public static DataTemplate ToDataTemplate(this ShibaView view)
        {
            var layoutString = view.ToString();
            var templateString =
                $"<DataTemplate xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\"><views:{typeof(ShibaHost).Name} xmlns:views=\"using:{typeof(ShibaHost).Namespace}\" DataContext=\"{{Binding}}\" Layout='{layoutString}'/></DataTemplate>";
            var template = XamlReader.Load(templateString) as DataTemplate;


            return template;
        }
    }

    public static class ShibaMapExtension
    {
        public static T Get<T>(this ShibaMap map, string name)
        {
            var value = map.Properties.FirstOrDefault(it => it.Key == name).Value;
            if (value == null) return default;

            var result = ShibaValueVisitor.GetValue(value, null);
            if (result is T targetResult) return targetResult;

            if (result.TryChangeType(typeof(T), out var castResult))
            {
                return (T) castResult;
            }

            return default;
        }

        public static NativeThickness ToNativeThickness(this ShibaMap shibaObject)
        {
            var left = shibaObject?.Get<double>("left") ?? default;
            var right = shibaObject?.Get<double>("right") ?? default;
            var top = shibaObject?.Get<double>("top") ?? default;
            var bottom = shibaObject?.Get<double>("bottom") ?? default;
            var vertical = shibaObject?.Get<double>("vertical") ?? default;
            var horizon = shibaObject?.Get<double>("horizon") ?? default;

            if (vertical != default || horizon != default)
                return new NativeThickness(horizon, vertical, horizon, vertical);


            return new NativeThickness(left, top, right, bottom);
        }
    }
}