#if WINDOWS_UWP || WPF
using System;
using System.Collections.Generic;
using System.Linq;
using Shiba.Controls;
using Shiba.Internal;
using Shiba.Renderers;

using Binding = Shiba.Controls.Binding;
using Thickness = Shiba.Controls.Thickness;
#if WINDOWS_UWP
using Windows.UI;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Controls.Primitives;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeThickness = Windows.UI.Xaml.Thickness;
using Windows.UI.Xaml.Media.Imaging;
#elif WPF
using System.Globalization;
using System.Windows.Data;
using System.Windows.Controls;
using System.Windows;
using NativeBinding = System.Windows.Data.Binding;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Controls.Primitives;
#endif


    
[assembly: ExportRenderer("text", typeof(TextRenderer))]
[assembly: ExportRenderer("stack", typeof(StackRenderer))]
[assembly: ExportRenderer("input", typeof(InputRenderer))]
[assembly: ExportRenderer("img", typeof(ImageRenderer))]
[assembly: ExportRenderer("switch", typeof(SwitchRenderer))]
[assembly: ExportRenderer("check", typeof(CheckRenderer))]

namespace Shiba.Renderers
{
    public sealed class PropertyMap
    {
        public PropertyMap(string name, DependencyProperty dependencyProperty, Type type, Func<object, object> converter = null, bool isTwoWay = false)
        {
            Name = name;
            DependencyProperty = dependencyProperty;
            Type = type;
            Converter = converter;
            IsTwoWay = isTwoWay;
        }

        public string Name { get; }
        public DependencyProperty DependencyProperty { get; }
        public Type Type { get; }
        public Func<object, object> Converter { get; }
        public bool IsTwoWay { get; }

    }
    
    public class ViewRenderer<TNativeView> : IViewRenderer
        where TNativeView : UIElement, new()
    {
        private List<PropertyMap> _propertyCache;

        public virtual IEnumerable<PropertyMap> PropertyMaps()
        {
            yield return new PropertyMap("visible", UIElement.VisibilityProperty, typeof(bool),
                value => (bool) value ? Visibility.Visible : Visibility.Collapsed);
            yield return new PropertyMap("enable", Control.IsEnabledProperty, typeof(bool));
            yield return new PropertyMap("width", FrameworkElement.WidthProperty, typeof(double));
            yield return new PropertyMap("height", FrameworkElement.HeightProperty, typeof(double));
            yield return new PropertyMap("maxHeight", FrameworkElement.MaxHeightProperty, typeof(double));
            yield return new PropertyMap("minHeight", FrameworkElement.MinHeightProperty, typeof(double));
            yield return new PropertyMap("maxWidth", FrameworkElement.MaxWidthProperty, typeof(double));
            yield return new PropertyMap("minWidth", FrameworkElement.MinWidthProperty, typeof(double));
            yield return new PropertyMap("margin", FrameworkElement.MarginProperty, typeof(Thickness),
                value => ((Thickness) value).ToNative());
            yield return new PropertyMap("padding", Control.PaddingProperty, typeof(Thickness),
                value => ((Thickness) value).ToNative());
            yield return new PropertyMap("alpha", UIElement.OpacityProperty, typeof(double));
            yield return new PropertyMap("name", FrameworkElement.NameProperty, typeof(string));
            yield return new PropertyMap("background", Control.BackgroundProperty, typeof(string), ColorConverter);
        }

        protected object ColorConverter(object arg)
        {
            if (!(arg is string value))
            {
                throw new ArgumentException("background value should be string");
            }

            return new SolidColorBrush(value.ToNativeColor());
        }


        public object Render(View view, IShibaHost rootHost)
        {
            var target = new TNativeView();
            if (_propertyCache == null)
            {
                _propertyCache = PropertyMaps().ToList();
            }
            foreach (var item in view.Properties)
            {
                var property = _propertyCache.FirstOrDefault(it => it.Name == item.Key);
                if (property != null)
                {
                    SetValue(property, view, target, rootHost);
                }
            }
            Render(view, ref target);
            return target;
        }

        protected void SetValue(PropertyMap propertyMap, View view, TNativeView target, IShibaHost rootHost)
        {
            if (!view.TryGet(propertyMap.Name, out var token) || token is NullToken)
            {
                return;
            }
            
            var value = token.GetValue();

            if (value == null)
            {
                return;
            }

            if (value.GetType() == propertyMap.Type)
            {
                target.SetValue(propertyMap.DependencyProperty, propertyMap.Converter == null ? value : propertyMap.Converter.Invoke(value));
                return;
            }

            if (value.CanChangeType(propertyMap.Type))
            {
                var targetValue = Convert.ChangeType(value, propertyMap.Type);
                target.SetValue(propertyMap.DependencyProperty, propertyMap.Converter == null ? targetValue : propertyMap.Converter.Invoke(targetValue));
            }
            
            switch (value)
            {
                case NativeResource resource:
                {
#if !WINDOWS_UWP
                    if (target is FrameworkElement frameworkElement)
                    {
                        frameworkElement.SetResourceReference(propertyMap.DependencyProperty, resource.Value.GetTokenValue());                            
                    }
                    else
                    {
#endif
                        target.SetValue(propertyMap.DependencyProperty, AbstractShiba.Instance.Configuration.ResourceValueResolver.GetValue(resource.Value.GetTokenValue()));
#if !WINDOWS_UWP
                    }
#endif
                }
                    break;
                case Function _:
                case IBindingValue _:
                {
                    if (target is FrameworkElement frameworkElement)
                    {
                        frameworkElement.SetBinding(propertyMap.DependencyProperty, GetBinding(rootHost, value, propertyMap.IsTwoWay));
                    }
                }
                    break;
            }            
        }

        protected BindingBase GetBinding(IShibaHost rootHost, object value, bool isTwoWay)
        {
            switch (value)
            {
                case Binding binding:
                {
                    return new NativeBinding
                    {
                        Path = new PropertyPath("DataContext." + binding.Value.GetTokenValue()),
                        Source = rootHost,
                        Converter = Singleton<BindingConverter>.Instance,
                        Mode = isTwoWay ? BindingMode.TwoWay : BindingMode.OneWay,
                        UpdateSourceTrigger = UpdateSourceTrigger.PropertyChanged
                    };
                }
                case JsonPath json:
                {
                    return new NativeBinding
                    {
                        Path = new PropertyPath("DataContext"),
                        Source = rootHost,
                        ConverterParameter = json.Value.GetTokenValue(),
                        Converter = Singleton<JsonConverter>.Instance
                    };
                }
                case Function function:
                {
                    var bindings = GetFunctionBindings(function).ToList();
                    if (bindings.Count == 1 && bindings.FirstOrDefault() is Binding binding)
                    {
                        return new NativeBinding
                        {
                            Path = new PropertyPath("DataContext." + binding.Value.GetTokenValue()),
                            Source = rootHost,
                            Converter = Singleton<SingleBindingFunctionConverter>.Instance,
                            ConverterParameter = function
                        };
                    }

                    return new NativeBinding
                    {
                        Path = new PropertyPath("DataContext"),
                        Source = rootHost,
                        Converter = Singleton<FunctionConverter>.Instance,
                        ConverterParameter = function
                    };
                }
                default:
                    break;
            }

            throw new NotSupportedException();
        }

        private IEnumerable<IBindingValue> GetFunctionBindings(IParamter paramter)
        {

            switch (paramter)
            {
                case Function func:
                    foreach (var binding in func.Paramters.SelectMany(GetFunctionBindings))
                    {
                        yield return binding;
                    }

                    break;
                case ValueParamter valueParamter:
                    switch (valueParamter.Value)
                    {
                        case BindingToken token:
                            yield return token.Value;
                            break;
                        case JsonPathToken jsonPathToken:
                            yield return jsonPathToken.Value;
                            break;
                        case NativeResourceToken resourceToken:
                            yield return resourceToken.Value;
                            break;
                        case FunctionToken functionToken:
                            foreach (var functionBinding in GetFunctionBindings(functionToken.Value))
                            {
                                yield return functionBinding;
                            }
                            break;
                    }
                    break;
            }
        }

        protected virtual void Render(View view, ref TNativeView target)
        {
        }
    }


    public class InputRenderer : ViewRenderer<TextBox>
    {
        public override IEnumerable<PropertyMap> PropertyMaps()
        {
            foreach (var propertyMap in base.PropertyMaps())
            {
                yield return propertyMap;
            }
            yield return new PropertyMap("text", TextBox.TextProperty, typeof(string), isTwoWay: true);
            yield return new PropertyMap("textSize", TextBox.FontSizeProperty, typeof(double));
            yield return new PropertyMap("textColor", TextBox.ForegroundProperty, typeof(string), ColorConverter);

        }
    }

    public class StackRenderer : ViewRenderer<StackPanel>
    {
        public override IEnumerable<PropertyMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetPropertys());
        }

        private IEnumerable<PropertyMap> GetPropertys()
        {
            yield return new PropertyMap("orientation", StackPanel.OrientationProperty, typeof(string), OrientationConverter);
        }

        private object OrientationConverter(object arg)
        {
            if (!(arg is string value))
            {
                throw new ArgumentException();
            }
            
            if (Enum.TryParse(value, true, out Orientation result))
            {
                return result;
            }

            throw new ArgumentOutOfRangeException();
        }
    }
    
    public class TextRenderer : ViewRenderer<TextBlock>
    {
        public override IEnumerable<PropertyMap> PropertyMaps()
        {
            foreach (var propertyMap in base.PropertyMaps())
            {
                yield return propertyMap;
            }
            yield return new PropertyMap("text", TextBlock.TextProperty, typeof(string));
            yield return new PropertyMap("textSize", TextBlock.FontSizeProperty, typeof(double));
            yield return new PropertyMap("textColor", TextBlock.ForegroundProperty, typeof(string), ColorConverter);

        }

        protected override void Render(View view, ref TextBlock target)
        {
        }
    }

    public class ImageRenderer : ViewRenderer<Image>
    {
        public override IEnumerable<PropertyMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetPropertys());
        }

        private IEnumerable<PropertyMap> GetPropertys()
        {
            yield return new PropertyMap("src", Image.SourceProperty, typeof(string), SourceConverter);
            yield return new PropertyMap("stretch", Image.StretchProperty, typeof(string), StretchConverter);
        }

        private object SourceConverter(object arg)
        {
            if (arg is string value && !string.IsNullOrEmpty(value))
            {
                return new BitmapImage(new Uri(value, UriKind.RelativeOrAbsolute));
            }

            return arg;
        }

        private object StretchConverter(object arg)
        {
            if (Enum.TryParse<Stretch>(arg + "", true, out var result))
            {
                return result;
            }

            return arg;
        }
    }
#if WINDOWS_UWP
    public class SwitchRenderer : ViewRenderer<ToggleSwitch>
#elif WPF
    public class SwitchRenderer : ViewRenderer<ToggleButton>
#endif
    {
        public override IEnumerable<PropertyMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetPropertys());
        }

        private IEnumerable<PropertyMap> GetPropertys()
        {
#if WINDOWS_UWP
            yield return new PropertyMap("isOn", ToggleSwitch.IsOnProperty, typeof(bool), isTwoWay: true);
            yield return new PropertyMap("text", ToggleSwitch.HeaderProperty, typeof(string));
#elif WPF
            yield return new PropertyMap("isOn", ToggleButton.IsCheckedProperty, typeof(bool), isTwoWay: true);
            yield return new PropertyMap("text", ContentControl.ContentProperty, typeof(string));
#endif
        }

    }
    
    public class CheckRenderer : ViewRenderer<CheckBox>
    {
        public override IEnumerable<PropertyMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetPropertys());
        }

        private IEnumerable<PropertyMap> GetPropertys()
        {
            yield return new PropertyMap("checked", ToggleButton.IsCheckedProperty, typeof(bool), isTwoWay: true);
            yield return new PropertyMap("text", ContentControl.ContentProperty, typeof(string));
        }
    }
    
}

#endif
