using System;
using System.Collections.Generic;
using System.Globalization;
using System.Text;
using Shiba.Controls;
using Shiba.Core;
using Shiba.Renderers;
using Binding = Shiba.Controls.Binding;
#if WINDOWS_UWP
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
#else
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
        where TNativeView: UIElement, new()
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
                        default:
                            frameworkElement.SetBinding(FrameworkElement.WidthProperty, GetBinding(dataContext, width));
                            break;
                    }
                }

                //if (view.TryGet<double>("height", out var height))
                //{
                //    frameworkElement.Height = height;
                //}

                //if (view.TryGet<double>("minWidth", out var minWidth))
                //{
                //    frameworkElement.MinWidth = minWidth;
                //}

                //if (view.TryGet<double>("minHeight", out var minHeight))
                //{
                //    frameworkElement.MinHeight = minHeight;
                //}

                //if (view.TryGet<double>("maxWidth", out var maxWidth))
                //{
                //    frameworkElement.MaxWidth = maxWidth;
                //}

                //if (view.TryGet<double>("maxHeight", out var maxHeight))
                //{
                //    frameworkElement.MaxHeight = maxHeight;
                //}
                
            }

            if (target is Control control)
            {   
                //control.IsEnabled = view.Enable;
            }
            Render(view, ref target);
            return target;
        }

        private NativeBinding GetBinding(object dataContext, object value)
        {
            switch (value)
            {
                case Binding binding:
                    return new NativeBinding
                    {
                        Source = dataContext,
                        Converter = new BindingConverter(binding)
                    };
                    break;
                case JsonPath json:
                    return new NativeBinding
                    {
                        Source = dataContext,
                        Converter = new JsonConverter(json)
                    };
                    break;
                case NativeResource native:
                    break;
                default:
                    break;
            }
            return new NativeBinding
            {
                Source = dataContext,
            };
        }

        protected virtual void Render(View view, ref TNativeView target)
        {

        }
    }

    internal class JsonConverter : ShibaConverter
    {
        private JsonPath _json;

        public JsonConverter(JsonPath json)
        {
            _json = json;
        }

        protected override object Convert(object value, Type targetType)
        {
            throw new NotImplementedException();
        }

        protected override object ConvertBack(object value, Type targetType)
        {
            throw new NotImplementedException();
        }
    }

    internal class BindingConverter : ShibaConverter
    {
        private Binding _binding;

        public BindingConverter(Binding binding)
        {
            _binding = binding;
        }

        protected override object Convert(object value, Type targetType)
        {
            throw new NotImplementedException();
        }

        protected override object ConvertBack(object value, Type targetType)
        {
            throw new NotImplementedException();
        }
    }

    internal abstract class ShibaConverter : IValueConverter
    {
#if WINDOWS_UWP
        public object Convert(object value, Type targetType, object parameter, string language)
        {
            return Convert(value, targetType);
        }

        public object ConvertBack(object value, Type targetType, object parameter, string language)
        {
            return ConvertBack(value, targetType);
        }
#else
        public object Convert(object value, Type targetType, object parameter, CultureInfo culture)
        {
            return Convert(value, targetType);
        }

        public object ConvertBack(object value, Type targetType, object parameter, CultureInfo culture)
        {
            return ConvertBack(value, targetType);
        }
#endif

        protected abstract object Convert(object value, Type targetType);

        protected abstract object ConvertBack(object value, Type targetType);
    }

    public class TextRenderer : ViewRenderer<TextBlock>
    {
        protected override void Render(View view, ref TextBlock target)
        {

        }
    }
}
