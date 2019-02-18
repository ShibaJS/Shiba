using System;
using System.Diagnostics;
using System.Linq;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Data;
using Shiba.Controls;
using Shiba.Internal;

namespace Shiba
{
    internal class ShibaFunctionExecutor
    {
        public object Execute(ShibaFunction function, object dataContext)
        {
            return ShibaApp.Instance.Configuration.ScriptRuntime.Execute(function.Name,
                function.Parameters.Select(it => GetParameterValue(it, dataContext)).ToArray());
        }

        protected virtual object GetValueFromDataContext(ShibaExtension extension, object dataContext)
        {
            return ShibaApp.Instance.Configuration.ExtensionExecutors
                .FirstOrDefault(it => it.Name == extension.Type)?.ProvideValue(null, extension);
        }

        private object GetParameterValue(object it, object dataContext)
        {
            switch (it)
            {
                case ShibaFunction function:
                    return Execute(function, dataContext);
                case ShibaExtension extension:
                    return GetValueFromDataContext(extension, dataContext);
                default:
                    return it;
            }
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
        protected virtual ShibaFunctionExecutor Executor => Singleton<ShibaFunctionExecutor>.Instance;

        protected override object Convert(object value, Type targetType, object parameter)
        {
            switch (parameter)
            {
                case ShibaFunction function:
                    return Executor.Execute(function, value);
                case ShibaConverterParameter converterParameter:
                    if (converterParameter.InnerConverter != null)
                    {
                        var innerResult = converterParameter.InnerConverter.Convert(value, targetType,
                            converterParameter.InnerParameter, string.Empty);
                        return Executor.Execute(converterParameter.Function, innerResult);
                    }
                    else
                    {
                        return Executor.Execute(converterParameter.Function, value);
                    }
                default:
                    throw new NotImplementedException();
            }
        }


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
            if (targetType == typeof(Visibility) && value is bool boolValue)
                return boolValue ? Visibility.Visible : Visibility.Collapsed;

            return value;
        }

        public static object CheckIfIsVisibility(this object value, Type targetType)
        {
            if (targetType == typeof(bool) && value is Visibility visibility) return visibility == Visibility.Visible;

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
        public object Convert(object value, Type targetType, object parameter, string language)
        {
            return Convert(value, targetType, parameter).CheckIfIsBoolean(targetType);
        }

        public object ConvertBack(object value, Type targetType, object parameter, string language)
        {
            return ConvertBack(value, targetType, parameter).CheckIfIsVisibility(targetType);
        }

        protected abstract object Convert(object value, Type targetType, object parameter);

        protected abstract object ConvertBack(object value, Type targetType, object parameter);
    }
}