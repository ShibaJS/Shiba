using System;
using System.Collections.Generic;
using System.Linq;
using Shiba.Controls;
using Shiba.Visitors;
using ShibaView = Shiba.Controls.View;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Media;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using Windows.UI.Xaml.Data;
#elif FORMS
using Xamarin.Forms;
using NativeView = Xamarin.Forms.VisualElement;
using XFView = Xamarin.Forms.View;
using NativeBinding = Xamarin.Forms.Binding;
using NativeProperty = Xamarin.Forms.BindableProperty;
using NativeThickness = Xamarin.Forms.Thickness;

#elif WPF
using System.Windows;
using System.Windows.Controls;
using NativeView = System.Windows.FrameworkElement;
using NativeBinding = System.Windows.Data.Binding;
using NativeProperty = System.Windows.DependencyProperty;
using NativeThickness = System.Windows.Thickness;
using System.Windows.Media;
using System.Windows.Data;
#endif

namespace Shiba.ViewMappers
{

    public interface IValueMap
    {
        string Name { get; }
        Type ValueType { get; }
        void SetValue(NativeView view, object value);
    }

    public class ManuallyValueMap : IValueMap
    {
        public ManuallyValueMap(string name, Type valueType, Action<NativeView, object> action)
        {
            Name = name;
            ValueType = valueType;
            Action = action;
        }

        public string Name { get; }
        public Type ValueType { get; }
        public virtual void SetValue(NativeView view, object value)
        {
            value.TryChangeType(ValueType, out value);
            Action?.Invoke(view, value);
        }

        public Action<NativeView, object> Action { get; protected set; }
    }

    public sealed class PropertyMap : ManuallyValueMap
    {
        public PropertyMap(string name, NativeProperty dependencyProperty, Type valueType, Type propertyType, Func<object, object> converter = null, bool isTwoWay = false) : 
            this(name, dependencyProperty, valueType, converter, isTwoWay)
        {
            PropertyType = propertyType;
        }

        public PropertyMap(string name, NativeProperty dependencyProperty, Type valueType, Func<object, object> converter = null, bool isTwoWay = false) : base(name, valueType, null)
        {
            DependencyProperty = dependencyProperty;
            Converter = converter;
            IsTwoWay = isTwoWay;
            PropertyType = valueType;
            Action = SetValue;
        }

        public override void SetValue(NativeView view, object value)
        {
            switch (value)
            {
                case null:
                    view.SetValue(DependencyProperty, null);
                    break;
                case NativeBinding binding:
                    binding.Mode = IsTwoWay ? BindingMode.TwoWay : BindingMode.OneWay;
                    view.SetBinding(DependencyProperty, binding);
                    break;
                default:
                    value.TryChangeType(ValueType, out value);
                    value = Converter == null ? value : Converter.Invoke(value);
                    if (value.GetType() == PropertyType)
                    {
                        view.SetValue(DependencyProperty, value);
                    }
                    break;
            }
        }

        public NativeProperty DependencyProperty { get; }
        public Type PropertyType { get; }
        public Func<object, object> Converter { get; }
        public bool IsTwoWay { get; }
    }

    public class ViewMapper<TNativeView> : IViewMapper<TNativeView>
        where TNativeView : NativeView, new()
    {
        private List<IValueMap> _propertyCache;

        protected virtual IEnumerable<IValueMap> ExtendPropertyMap()
        {
            return null;
        }

        public virtual IEnumerable<IValueMap> PropertyMaps()
        {
            yield return new PropertyMap("enable",
#if WINDOWS_UWP || WPF
                Control.IsEnabledProperty,
#elif FORMS
                NativeView.IsEnabledProperty,
#endif
                typeof(bool));
            yield return new PropertyMap("width", NativeView.WidthProperty, typeof(double));
            yield return new PropertyMap("height", NativeView.HeightProperty, typeof(double));
#if WINDOWS_UWP || WPF
            yield return new PropertyMap("maxHeight", NativeView.MaxHeightProperty, typeof(double));
            yield return new PropertyMap("minHeight", NativeView.MinHeightProperty, typeof(double));
            yield return new PropertyMap("maxWidth", NativeView.MaxWidthProperty, typeof(double));
            yield return new PropertyMap("minWidth", NativeView.MinWidthProperty, typeof(double));
            yield return new PropertyMap("name", NativeView.NameProperty, typeof(string));
            yield return new PropertyMap("visible", UIElement.VisibilityProperty, typeof(bool),
                value => (bool) value ? Visibility.Visible : Visibility.Collapsed);
            yield return new PropertyMap("background", Control.BackgroundProperty, typeof(string),
                ColorConverter);
            yield return new PropertyMap("padding", Control.PaddingProperty, typeof(ShibaMap), typeof(NativeThickness),
                value => ((ShibaMap) value).ToNativeThickness());
            yield return new PropertyMap("margin", NativeView.MarginProperty, typeof(ShibaMap), typeof(NativeThickness),
                value => ((ShibaMap) value).ToNativeThickness());
#elif FORMS
            yield return new PropertyMap("padding", Layout.PaddingProperty, typeof(ShibaMap), typeof(NativeThickness),
                value => ((ShibaMap) value).ToNativeThickness());
            yield return new PropertyMap("margin", XFView.MarginProperty, typeof(ShibaMap), typeof(NativeThickness),
                value => ((ShibaMap) value).ToNativeThickness());
            yield return new PropertyMap("background", NativeView.BackgroundColorProperty, typeof(string),
                ColorConverter);
#endif
            yield return new PropertyMap("alpha", NativeView.OpacityProperty, typeof(double));
            yield return new PropertyMap("style", NativeView.StyleProperty, typeof(string), typeof(Style));
            if (DefaultPropertyMap != null)
            {
                yield return DefaultPropertyMap;
            }
            var exprop = ExtendPropertyMap();
            if (exprop != null)
            {
                foreach (var item in exprop) yield return item;
            }
        }

        protected object ColorConverter(object arg)
        {
            if (!(arg is string value))
            {
                throw new ArgumentException("background value should be string");
            }

#if WINDOWS_UWP || WPF
            return new SolidColorBrush(value.ToNativeColor());
#elif FORMS
            return value.ToNativeColor();
#endif
        }

        protected virtual bool HasDefaultProperty { get; } = false;

        protected virtual PropertyMap DefaultPropertyMap { get; }

        object IViewMapper.Map(ShibaView view, IShibaContext context)
        {
            return Map(view, context);
        }

        public TNativeView Map(ShibaView view, IShibaContext context)
        {
            var target = CreateNativeView();
            
            if (_propertyCache == null)
            {
                _propertyCache = PropertyMaps().ToList();
            }

            if (view.DefaultValue != null && DefaultPropertyMap != null && HasDefaultProperty)
            {
                SetValue(context, view.DefaultValue, DefaultPropertyMap, target);
            }

            foreach (var property in view.Properties)
            {
                if (!string.IsNullOrEmpty(property.Name.Prefix) &&
                    property.Name.Prefix != AbstractShiba.Instance.Configuration.PlatformType)
                {
                    continue;
                }

                var cache = _propertyCache.FirstOrDefault(it => it.Name == property.Name.Value);
                if (cache != null)
                {
                    SetValue(context, property.Value, cache, target);
                }
            }

            return target;
        }

        private void SetValue(IShibaContext context, object value, IValueMap valueMap, TNativeView target)
        {
            var targetValue = valueMap.ValueType == value?.GetType() ? value : ShibaValueVisitor.GetValue(value, context);
            valueMap.SetValue(target, targetValue);
        }

        public virtual TNativeView CreateNativeView()
        {
            return new TNativeView();
        }
    }
}