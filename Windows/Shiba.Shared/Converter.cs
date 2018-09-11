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
        public object Execute(ShibaFunction function, object dataContext)
        {
            return AbstractShiba.Instance.Configuration.ConverterExecutor.Execute(function.Name,
                function.Parameters.Select(it => GetParameterValue(it, dataContext)).ToArray());
        }
        
        private object GetParameterValue(object it, object dataContext)
        {
            switch (it)
            {
                case ShibaFunction function:
                    return Execute(function, dataContext);
                case ShibaBinding binding:
                    return GetValueFromDataContext(binding, dataContext);
                default:
                    return it;
            }
        }

        protected virtual object GetValueFromDataContext(ShibaBinding binding, object dataContext)
        {
            return AbstractShiba.Instance.Configuration.BindingValueResolver.GetValue(dataContext,
                binding.Path);
        }
    }

    internal class SingleBindingShibaFunctionExecutor : ShibaFunctionExecutor
    {
        protected override object GetValueFromDataContext(ShibaBinding binding, object dataContext)
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
            if (!(parameter is ShibaFunction function))
            {
                throw new ArgumentException();
            }

            return Executor.Execute(function, value);
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