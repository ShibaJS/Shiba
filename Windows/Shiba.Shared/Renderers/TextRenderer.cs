using System;
using System.Collections.Generic;
using System.Linq;
using Shiba.Controls;
using Shiba.Core;
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

                            if (!(resource.Value is Token token))
                            {
                                throw new NotSupportedException(
                                    $"Not support native resource at line {resource.Value.Line}, colunm {resource.Value.Column}");
                            }
#if !WINDOWS_UWP
                            frameworkElement.SetResourceReference(FrameworkElement.WidthProperty,
                                Initialization.Configuration.ResourceValueResolver.GetValue(token.Value));
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
                    if (!(binding.Value is Token token))
                    {
                        throw new NotSupportedException(
                            $"Not support binding at line {binding.Value.Line}, colunm {binding.Value.Column}");
                    }

                    return new NativeBinding
                    {
                        Path = new PropertyPath(token.Value),
                        Source = dataContext,
                        Converter = Singleton<BindingConverter>.Instance,
                        Mode = BindingMode.OneWay
                    };
                }
                case JsonPath json:
                {
                    if (!(json.Value is Token token))
                    {
                        throw new NotSupportedException(
                            $"Not support json path at line {json.Value.Line}, colunm {json.Value.Column}");
                    }

                    return new NativeBinding
                    {
                        Source = dataContext,
                        ConverterParameter = token.Value,
                        Converter = Singleton<JsonConverter>.Instance
                    };
                }
                case Function function:
                {
                    var bindings = GetFunctionBindings(function).ToList();
                    if (bindings.Count == 1)
                    {
                        if (!(bindings.FirstOrDefault().Value is Token token))
                        {
                            throw new NotSupportedException(
                                $"Not support binding at line {bindings.FirstOrDefault().Value.Line}, colunm {bindings.FirstOrDefault().Value.Column}");
                        }

                        return new NativeBinding
                        {
                            Path = new PropertyPath(token.Value),
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

        private IEnumerable<Binding> GetFunctionBindings(IParamter paramter)
        {
            while (true)
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
                            case FunctionToken functionToken:
                                paramter = functionToken.Value;
                                continue;
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

    internal class SingleBindingFunctionConverter : ShibaConverter
    {
        protected override object Convert(object value, Type targetType, object parameter)
        {
            if (!(parameter is Function function))
            {
                throw new ArgumentException();
            }

            throw new NotImplementedException();
        }

        protected override object ConvertBack(object value, Type targetType, object parameter)
        {
            throw new NotImplementedException();
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

            throw new NotImplementedException();
        }

        protected override object ConvertBack(object value, Type targetType, object parameter)
        {
            throw new NotImplementedException();
        }
    }

    internal class JsonConverter : ShibaConverter
    {
        protected override object Convert(object value, Type targetType, object parameter)
        {
            return Initialization.Configuration.JsonValueResolver.GetValue(value, parameter + "");
        }

        protected override object ConvertBack(object value, Type targetType, object parameter)
        {
            throw new NotImplementedException();
        }
    }

    internal class BindingConverter : ShibaConverter
    {
        protected override object Convert(object value, Type targetType, object parameter)
        {
            if (targetType == typeof(Visibility) && value is bool boolValue)
            {
                return boolValue ? Visibility.Visible : Visibility.Collapsed;
            }

            return value;
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