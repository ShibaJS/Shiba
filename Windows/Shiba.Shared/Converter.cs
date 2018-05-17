using System;
using System.Linq;
using Shiba.Controls;
using Shiba.Renderers;
using Binding = Shiba.Controls.Binding;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Data;
#else
using System.Windows;
using System.Windows.Data;
using System.Globalization;

#endif

namespace Shiba
{
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
                function.Paramters.Select(it => GetParamterValue(it, bindingValue)).ToArray());
        }

        private object GetParamterValue(IParamter it, object bindingValue)
        {
            switch (it)
            {
                case Function function:
                    return Execute(function, bindingValue);
                case ValueParamter valueParamter:
                    switch (valueParamter.Value.GetValue())
                    {
                        case IBindingValue value:
                            return GetValueFromDataContext(bindingValue, value);
                        case Function function:
                            return Execute(function, bindingValue);
                        default:
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
                    return AbstractShiba.Instance.Configuration.BindingValueResolver.GetValue(dataContext,
                        binding.Value.GetTokenValue());
                case JsonPath jsonPath:
                    return AbstractShiba.Instance.Configuration.JsonValueResolver.GetValue(dataContext,
                        jsonPath.Value.GetTokenValue());
                case NativeResource resource:
                    return AbstractShiba.Instance.Configuration.ResourceValueResolver.GetValue(
                        resource.Value.GetTokenValue());
                default:
                    throw new ArgumentOutOfRangeException();
            }
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
            return AbstractShiba.Instance.Configuration.JsonValueResolver.GetValue(value, parameter + "")
                .CheckIfIsBoolean(targetType);
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

        public static object CheckIfIsVisibility(this object value, Type targetType)
        {
            if (targetType == typeof(bool) && value is Visibility visibility)
            {
                return visibility == Visibility.Visible;
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
            return value.CheckIfIsVisibility(targetType);
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
}