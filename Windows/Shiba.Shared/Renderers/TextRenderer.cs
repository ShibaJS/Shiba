using System;
using System.Collections.Generic;
using System.Linq;
using Shiba.Controls;
using Shiba.Internal;
using Shiba.Renderers;
using Binding = Shiba.Controls.Binding;
#if WINDOWS_UWP
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml;
using NativeBinding = Windows.UI.Xaml.Data.Binding;

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
    public class ViewRenderer<TNativeView> : IViewRenderer
        where TNativeView : UIElement, new()
    {
        public object Render(View view, object dataContext)
        {
            var target = new TNativeView();

            if (view.TryGet("visible", out var visible))
            {
                if (visible is bool boolValue)
                {
                    target.Visibility = boolValue ? Visibility.Visible : Visibility.Collapsed;
                }
            }

            if (target is FrameworkElement frameworkElement)
            {
                if (view.TryGet("width", out var width))
                {
                    switch (width)
                    {
                        case double doubleValue:
                            frameworkElement.Width = doubleValue;
                            break;
                        case NativeResource resource:
#if !WINDOWS_UWP
                            frameworkElement.SetResourceReference(FrameworkElement.WidthProperty,
                                AbstractShiba.Instance.Configuration.ResourceValueResolver.GetValue(resource.Value.GetTokenValue()));
#else
                            //TODO:
#endif
                            break;
                        default:
                            frameworkElement.SetBinding(FrameworkElement.WidthProperty, GetBinding(dataContext, width));
                            break;
                    }
                }
            }

            if (target is Control control)
            {
                //control.IsEnabled = view.Enable;
            }

            Render(view, ref target);
            return target;
        }

        private BindingBase GetBinding(object dataContext, object value)
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

    internal class SingleBindingFunctionConverter : FunctionConverter
    {
        protected override object GetValueFromDataContext(object dataContext, IBindingValue value)
        {
            return dataContext;
        }
    }

    internal class FunctionConverter : ShibaConverter
    {
        protected override object Convert(object value, Type targetType, object parameter)
        {
            if (!(parameter is Function function))
            {
                throw new ArgumentException();
            }

            return Execute(function, value).CheckIfIsBoolean(targetType);
        }
        
        private object Execute(Function function, object bindingValue)
        {
            return AbstractShiba.Instance.Configuration.ConverterExecutor.Execute(function.Name,
                function.Paramters.Select(it => GetParamterValue(it, bindingValue)));
        }

        private object GetParamterValue(IParamter it, object bindingValue)
        {
            switch (it)
            {
                case Function function:
                    return Execute(function, bindingValue);
                case ValueParamter valueParamter:
                    if (valueParamter.Value.GetValue() is IBindingValue value)
                    {
                        return GetValueFromDataContext(bindingValue, value);
                    }
                    else
                    {
                        return valueParamter.Value.GetValue();
                   }
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        protected virtual object GetValueFromDataContext(object dataContext, IBindingValue value)
        {
            switch (value)
            {
                case Binding binding:
                    return AbstractShiba.Instance.Configuration.BindingValueResolver.GetValue(dataContext, value.Value.GetTokenValue());
                case JsonPath jsonPath:
                    return AbstractShiba.Instance.Configuration.JsonValueResolver.GetValue(dataContext,
                        jsonPath.Value.GetTokenValue());
                case NativeResource resource:
                    return AbstractShiba.Instance.Configuration.ResourceValueResolver.GetValue(resource.Value.GetTokenValue());
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        protected override object ConvertBack(object value, Type targetType, object parameter)
        {
            throw new NotImplementedException();
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
    
    internal class JsonConverter : ShibaConverter
    {
        protected override object Convert(object value, Type targetType, object parameter)
        {
            return AbstractShiba.Instance.Configuration.JsonValueResolver.GetValue(value, parameter + "").CheckIfIsBoolean(targetType);
        }

        protected override object ConvertBack(object value, Type targetType, object parameter)
        {
            throw new NotImplementedException();
        }
    }

    internal static class ConverterExtensions
    {
        public static object CheckIfIsBoolean(this object value, Type targetType)
        {
            if (targetType == typeof(Visibility) && value is bool boolValue)
            {
                return boolValue ? Visibility.Visible : Visibility.Collapsed;
            }

            return value;
        }
    }

    internal class BindingConverter : ShibaConverter
    {
        protected override object Convert(object value, Type targetType, object parameter)
        {
            return value.CheckIfIsBoolean(targetType);
        }

        protected override object ConvertBack(object value, Type targetType, object parameter)
        {
            throw new NotImplementedException();
        }
    }

    internal abstract class ShibaConverter : IValueConverter
    {
#if WINDOWS_UWP
        public object Convert(object value, Type targetType, object parameter, string language)
        {
            return Convert(value, targetType, parameter);
        }

        public object ConvertBack(object value, Type targetType, object parameter, string language)
        {
            return ConvertBack(value, targetType, parameter);
        }
#else
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            return Convert(value, targetType, parameter);
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            return ConvertBack(value, targetType, parameter);
        }
#endif

        protected abstract object Convert(object value, Type targetType, object parameter);

        protected abstract object ConvertBack(object value, Type targetType, object parameter);
    }

    public class TextRenderer : ViewRenderer<TextBlock>
    {
        protected override void Render(View view, ref TextBlock target)
        {
        }
    }
}