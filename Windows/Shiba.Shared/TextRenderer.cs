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
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeThickness = Windows.UI.Xaml.Thickness;


#else
using System.Globalization;
using System.Windows.Data;
using System.Windows.Controls;
using System.Windows;
using NativeBinding = System.Windows.Data.Binding;

#endif


[assembly: ExportRenderer("text", typeof(TextRenderer))]
//[assembly: ExportRenderer("switch", typeof(Switch))]
//[assembly: ExportRenderer("check", typeof(Check))]
//[assembly: ExportRenderer("stackLayout", typeof(StackPanel))]
//[assembly: ExportRenderer("grid", typeof(Grid))]
//[assembly: ExportRenderer("input", typeof(Input))]
//[assembly: ExportRenderer("img", typeof(Image))]


namespace Shiba.Renderers
{
    public class PropertyMap
    {
        public PropertyMap(string name, DependencyProperty dependencyProperty, Type type, Func<object, object> converter = null)
        {
            Name = name;
            DependencyProperty = dependencyProperty;
            Type = type;
            Converter = converter;
        }

        public string Name { get; }
        public DependencyProperty DependencyProperty { get; }
        public Type Type { get; }
        public Func<object, object> Converter { get; }
    }
    
    public class ViewRenderer<TNativeView> : IViewRenderer
        where TNativeView : UIElement, new()
    {

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

            return value.ToNativeColor();
        }


        public object Render(View view, object dataContext)
        {
            var target = new TNativeView();
            foreach (var item in PropertyMaps())
            {
                SetValue(item.Name, view, target, item.DependencyProperty, dataContext, item.Type, item.Converter);
            }
            Render(view, ref target);
            return target;
        }


        protected void SetValue(string name, View view, DependencyObject dependencyObject, DependencyProperty property,
            object dataContext, Type type, Func<object, object> converter = null)
        {
            if (view.TryGet(name, out var value))
            {
                SetValue(dependencyObject, property, value, dataContext, type, converter);
            }
        }
        
        protected void SetValue(DependencyObject dependencyObject, DependencyProperty property, IToken token, object dataContext, Type type, Func<object, object> converter = null)
        {
            if (token is NullToken)
            {
                return;
            }

            var value = token.GetValue();

            if (value == null)
            {
                return;
            }

            if (value.GetType() == type)
            {
                dependencyObject.SetValue(property, converter?.Invoke(value) ?? value);
                return;
            }

            if (value.CanChangeType(type))
            {
                var targetValue = Convert.ChangeType(value, type);
                dependencyObject.SetValue(property, converter?.Invoke(targetValue) ?? targetValue);
            }
            
            switch (value)
            {
                case NativeResource resource:
                {
#if !WINDOWS_UWP
                    if (dependencyObject is FrameworkElement frameworkElement)
                    {
                        frameworkElement.SetResourceReference(property, resource.Value.GetTokenValue());                            
                    }
                    else
                    {
#endif
                        dependencyObject.SetValue(property, AbstractShiba.Instance.Configuration.ResourceValueResolver.GetValue(resource.Value.GetTokenValue()));
#if !WINDOWS_UWP
                    }
#endif
                }
                    break;

                case IBindingValue bindingValue:
                {
                    if (dependencyObject is FrameworkElement frameworkElement)
                    {
                        frameworkElement.SetBinding(property, GetBinding(dataContext, bindingValue));
                    }
                }
                    break;
            }
        }

        private BindingBase GetBinding(object dataContext, IBindingValue value)
        {
            switch (value)
            {
                case Binding binding:
                {
                    return new NativeBinding
                    {
                        Path = new PropertyPath(binding.Value.GetTokenValue()),
                        Source = dataContext,
                        Converter = Singleton<BindingConverter>.Instance,
                        Mode = BindingMode.OneWay
                    };
                }
                case JsonPath json:
                {
                    return new NativeBinding
                    {
                        Source = dataContext,
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
                            Path = new PropertyPath(binding.Value.GetTokenValue()),
                            Source = dataContext,
                            Converter = Singleton<SingleBindingFunctionConverter>.Instance,
                            ConverterParameter = function
                        };
                    }

                    return new NativeBinding
                    {
                        Source = dataContext,
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

    internal static class TokenExtensions
    {
        public static string GetTokenValue(this IToken value)
        {
            if (value is Token token)
            {
                return token.Value;
            }
            throw new ArgumentOutOfRangeException($"Line {value.Line}, colunm {value.Column} should be token");
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
}