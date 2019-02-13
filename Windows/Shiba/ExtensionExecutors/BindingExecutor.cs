using System;
using System.Linq;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Data;
using Newtonsoft.Json.Linq;
using Shiba.Controls;
using Shiba.Internal;
using ShibaView = Shiba.Controls.View;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;


namespace Shiba.ExtensionExecutors
{
    public class BindingExecutor : IBindingExtensionExecutor
    {
        public virtual string Name { get; } = "bind";

        public object ProvideValue(IShibaContext context, ShibaExtension value)
        {
            return ProvideNativeBinding(context, value);
        }

        public virtual NativeBinding ProvideNativeBinding(IShibaContext context, ShibaExtension value)
        {
            var dataContextPath =
                "DataContext";
            var targetPath = value.Value;
            var path = string.IsNullOrEmpty(targetPath)
                ? dataContextPath
                : $"{dataContextPath}.{targetPath}";

            return new NativeBinding
            {
                Source = context.ShibaHost,
                Path = new PropertyPath(path),
                UpdateSourceTrigger = UpdateSourceTrigger.PropertyChanged
            };
        }
    }

    public class JsonExecutor : BindingExecutor
    {
        public override string Name { get; } = "json";

        public override NativeBinding ProvideNativeBinding(IShibaContext context, ShibaExtension value)
        {
            var dataContextPath =
                "DataContext";
            var targetPath = value.Value;
            var path = dataContextPath;

            return new NativeBinding
            {
                Converter = Singleton<JsonConverter>.Instance,
                ConverterParameter = targetPath,
                Source = context.ShibaHost,
                Path = new PropertyPath(path),
                UpdateSourceTrigger = UpdateSourceTrigger.PropertyChanged
            };
        }
    }

    internal class JsonConverter : ShibaConverter
    {
        protected override object Convert(object value, Type targetType, object parameter)
        {
            if (!(value is JToken token)) return null;

            var targetPath = parameter + "";

            if (string.IsNullOrEmpty(targetPath)) return ParseValue(token, targetType);

            token = targetPath.Split('.').Aggregate(token, (current, path) => current?[path]);
            return ParseValue(token, targetType);
        }

        protected override object ConvertBack(object value, Type targetType, object parameter)
        {
            throw new NotImplementedException();
        }

        private object ParseValue(JToken token, Type targetType)
        {
            if (token == null) return null;

            if (!token.HasValues)
            {
                if (token is JValue value) return value.Value;
                return token;
            }

            if (targetType == typeof(string)) return token.Value<string>();

            if (targetType == typeof(int)) return token.Value<int>();

            if (targetType == typeof(double)) return token.Value<double>();

            return token.Value<object>();
        }
    }
}