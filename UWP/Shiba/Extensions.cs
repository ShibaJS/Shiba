using System;
using System.Linq;
using System.Reflection;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Markup;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shiba.Controls;
using Shiba.Internal;
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
            // TODO:
            throw new NotImplementedException();
            //var layoutString = view.ToString();
            //var templateString =
            //    $"<DataTemplate xmlns=\"http://schemas.microsoft.com/winfx/2006/xaml/presentation\"><views:{typeof(ShibaHost).Name} xmlns:views=\"using:{typeof(ShibaHost).Namespace}\" DataContext=\"{{Binding}}\" Layout={JsonConvert.SerializeObject(layoutString)}/></DataTemplate>";
            //var template = XamlReader.Load(templateString) as DataTemplate;


            //return template;
        }
    }

    public static class ShibaMapExtension
    {
        public static T Get<T>(this ShibaObject shibaObject, string name)
        {
            var value = shibaObject.FirstOrDefault(it => it.Key == name).Value;
            switch (value)
            {
                case null:
                    return default;
                case JToken token:
                    
                    break;
                default:
                {
                    var result = Singleton<ValueVisitor>.Instance.DynamicVisit(value, null);
                    if (result is T targetResult) return targetResult;

                    if (result.TryChangeType(typeof(T), out var castResult))
                    {
                        return (T) castResult;
                    }

                    break;
                }
            }


            return default;
        }

        public static NativeThickness ToNativeThickness(this ShibaObject shibaObject)
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