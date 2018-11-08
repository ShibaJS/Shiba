using System;
using System.Diagnostics;
using System.Linq;
using Shiba.Controls;
using Shiba.Internal;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Data;
#elif WPF
using System.Windows;
using System.Windows.Data;
using System.Globalization;
#elif FORMS
using Xamarin.Forms;
using System.Globalization;
#endif

namespace Shiba
{
    internal class ShibaFunctionExecutor
    {
        public object Execute(ShibaFunction function, object dataContext, Type targetType)
        {
            return AbstractShiba.Instance.Configuration.ConverterExecutor.Execute(function.Name, targetType, 
                function.Parameters.Select(it => GetParameterValue(it, dataContext, targetType)).ToArray());                            
        }
        
        private object GetParameterValue(object it, object dataContext, Type targetType)
        {
            switch (it)
            {
                case ShibaFunction function:
                    return Execute(function, dataContext, targetType);
                case ShibaExtension extension:
                    return GetValueFromDataContext(extension, dataContext);
                default:
                    return it;
            }
        }

        protected virtual object GetValueFromDataContext(ShibaExtension extension, object dataContext)
        {
            return AbstractShiba.Instance.Configuration.ExtensionExecutors
                .FirstOrDefault(it => it.Name == extension.Type)?.ProvideValue(null, extension);
        }
    }

    internal class SingleBindingShibaFunctionExecutor : ShibaFunctionExecutor
    {
        protected override object GetValueFromDataContext(ShibaExtension binding, object dataContext)
        {
            return dataContext;
        }
    }
    
    internal class SingleBindingFunctionConverter : FunctionConverter
    {
        protected override ShibaFunctionExecutor Executor => Singleton<SingleBindingShibaFunctionExecutor>.Instance;
    }

    internal class FunctionConverter : ShibaConverter
    {    
        protected override object Convert(object value, Type targetType, object parameter)
        {
            switch (parameter)
            {
                case ShibaFunction function:
                    return Executor.Execute(function, value, targetType);
                case ShibaConverterParameter converterParameter:
                    if (converterParameter.InnerConverter != null)
                    {
#if WINDOWS_UWP
                        var innerResult = converterParameter.InnerConverter.Convert(value, targetType,
                            converterParameter.InnerParameter, string.Empty);
#elif WPF || FORMS
                        var innerResult = converterParameter.InnerConverter.Convert(value, targetType,
                            converterParameter.InnerParameter, CultureInfo.CurrentCulture);
#endif
                        return Executor.Execute(converterParameter.Function, innerResult, targetType);
                    }
                    else
                    {
                        return Executor.Execute(converterParameter.Function, value, targetType);
                    }
                default:
                    throw new NotImplementedException();
            }
        }

        protected virtual ShibaFunctionExecutor Executor => Singleton<ShibaFunctionExecutor>.Instance;
        

        protected override object ConvertBack(object value, Type targetType, object parameter)
        {
            throw new NotImplementedException();
        }
    }
    
    
    internal sealed class RawDataConverter : ShibaConverter
    {
        protected override object Convert(object value, Type targetType, object parameter)
        {
            Debug.WriteLine("Binding a raw data is not recommended");
            return parameter;
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
#if !FORMS
            if (targetType == typeof(Visibility) && value is bool boolValue)
            {
                return boolValue ? Visibility.Visible : Visibility.Collapsed;
            }
#endif

            return value;
        }

        public static object CheckIfIsVisibility(this object value, Type targetType)
        {
#if !FORMS
            if (targetType == typeof(bool) && value is Visibility visibility)
            {
                return visibility == Visibility.Visible;
            } 
#endif

            return value;
        }
    }

    internal class ShibaConverterParameter
    {
        public IValueConverter InnerConverter { get; set; }
        public object InnerParameter { get; set; }
        public ShibaFunction Function { get; set; }
    }

    internal abstract class ShibaConverter : IValueConverter
    {
#if WINDOWS_UWP
        public object Convert(object value, Type targetType, object parameter, string language)
        {
            return Convert(value, targetType, parameter).CheckIfIsBoolean(targetType);
        }

        public object ConvertBack(object value, Type targetType, object parameter, string language)
        {
            return ConvertBack(value, targetType, parameter).CheckIfIsVisibility(targetType);
        }

#elif WPF || FORMS
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