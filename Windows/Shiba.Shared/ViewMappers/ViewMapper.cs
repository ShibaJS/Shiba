using System;
using System.Collections.Generic;
using System.Dynamic;
using System.Linq;
using System.Reflection;
using System.Runtime.InteropServices;
using Shiba;
using Shiba.Common;
using Shiba.Controls;
using Shiba.Internal;
using Shiba.Visitors;
using ShibaView = Shiba.Controls.View;
using View = Shiba.Controls.View;
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
            if (value == null)
            {
                view.SetValue(DependencyProperty, null);
                return;
            }

            value.TryChangeType(ValueType, out value);

            value = Converter == null ? value : Converter.Invoke(value);
            
            if (value.GetType() == PropertyType)
            {
                view.SetValue(DependencyProperty, value);
            }
            else if (value is NativeBinding binding)
            {
                if (IsTwoWay)
                {
                    binding.Mode = BindingMode.TwoWay;
                }
                view.SetBinding(DependencyProperty, binding);
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
            yield return new ManuallyValueMap("grid", typeof(ShibaMap), (element, value) =>
            {
                if (value is ShibaMap map)
                {
                    var row = map.GetValue<BasicValue>("row").To<int>();
                    var colunm = map.GetValue<BasicValue>("colunm").To<int>();
                    var rowSpan = map.GetValue<BasicValue>("rowSpan").To<int>();
                    var colunmSpan = map.GetValue<BasicValue>("colunmSpan").To<int>();
                    if (row != default)
                    {
                        Grid.SetRow(element, row);
                    }

                    if (colunm != default)
                    {
                        Grid.SetColumn(element, colunm);
                    }

                    if (rowSpan != default)
                    {
                        Grid.SetRowSpan(element, rowSpan);
                    }

                    if (colunmSpan != default)
                    {
                        Grid.SetColumnSpan(element, colunmSpan);
                    }
                }
            });
            
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

        public virtual string ViewName { get; } = "view";

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
            var targetValue = ShibaValueVisitor.GetValue(value, context);
            switch (targetValue)
            {
                case ShibaMultiBinding multiBinding:
                    if (context.ShibaHost.DataContext != null)
                    {
#if WINDOWS_UWP 
                        targetValue = multiBinding.Converter.Convert(context.ShibaHost.DataContext, valueMap.ValueType,
                            multiBinding.Parameter, string.Empty);
#elif FORMS || WPF
                        targetValue = multiBinding.Converter.Convert(context.ShibaHost.DataContext, valueMap.ValueType,
                            multiBinding.Parameter, null);
#endif
                    }
                    else
                    {
                        targetValue = null;
                    }
                    break;
                case ShibaBinding binding:
                    targetValue = new NativeBinding
                    {
                        Source = context.ShibaHost,
#if FORMS
                        Path = "BindingContext." + binding.Path,
#elif WINDOWS_UWP || WPF
                        Path = new PropertyPath("DataContext." + binding.Path),
                        UpdateSourceTrigger = UpdateSourceTrigger.PropertyChanged,
#endif
                        Converter = binding.Converter,
                        ConverterParameter = binding.Parameter,
                        
                    };
                    break;
                default:
                    break;
            }

            
            valueMap.SetValue(target, targetValue);

        }

        public virtual TNativeView CreateNativeView()
        {
            return new TNativeView();
        }
    }
    
    
    
    
    
    
}