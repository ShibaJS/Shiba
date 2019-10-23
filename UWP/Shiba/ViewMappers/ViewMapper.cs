using System;
using System.Collections.Generic;
using System.Linq;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using ChakraHosting;
using Shiba.Controls;
using Shiba.Internal;
using Shiba.Visitors;
using ShibaView = Shiba.Controls.View;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;

namespace Shiba.ViewMappers
{
    public interface IEventMap
    {
        string Name { get; }
        Action<NativeView, string, IShibaContext> Setter { get; }
    }

    public class EventMap : IEventMap
    {
        public EventMap(string name, Action<NativeView, string, IShibaContext> setter)
        {
            Name = name;
            Setter = setter;
        }

        public string Name { get; }
        public Action<NativeView, string, IShibaContext> Setter { get; }
    }

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

        public Action<NativeView, object> Action { get; protected set; }

        public string Name { get; }
        public Type ValueType { get; }

        public virtual void SetValue(NativeView view, object value)
        {
            value.TryChangeType(ValueType, out value);
            Action?.Invoke(view, value);
        }
    }

    public sealed class PropertyMap : ManuallyValueMap
    {
        public PropertyMap(string name, NativeProperty dependencyProperty, Type valueType, Type propertyType,
            Func<object, object> converter = null, bool isTwoWay = false) :
            this(name, dependencyProperty, valueType, converter, isTwoWay)
        {
            PropertyType = propertyType;
        }

        public PropertyMap(string name, NativeProperty dependencyProperty, Type valueType,
            Func<object, object> converter = null, bool isTwoWay = false) : base(name, valueType, null)
        {
            DependencyProperty = dependencyProperty;
            Converter = converter;
            IsTwoWay = isTwoWay;
            PropertyType = valueType;
            Action = SetValue;
        }

        public NativeProperty DependencyProperty { get; }
        public Type PropertyType { get; }
        public Func<object, object> Converter { get; }
        public bool IsTwoWay { get; }

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
                    if (!ValueType.IsEnum)
                    {
                        value.TryChangeType(ValueType, out value);
                    }
                    value = Converter == null ? value : Converter.Invoke(value);
                    if (value.GetType() == PropertyType) view.SetValue(DependencyProperty, value);
                    break;
            }
        }
    }

    public class ViewMapper<TNativeView> : IViewMapper
        where TNativeView : NativeView, new()
    {
        protected internal readonly Lazy<List<IValueMap>> _propertyCache;
        protected internal readonly Lazy<List<IEventMap>> _eventCache;

        protected ViewMapper()
        {
            _propertyCache = new Lazy<List<IValueMap>>(() => PropertyMaps().ToList());
            _eventCache = new Lazy<List<IEventMap>>(() => EventMaps().ToList());
        }

        protected virtual bool HasDefaultProperty { get; } = false;

        protected virtual PropertyMap DefaultPropertyMap { get; }

        object IViewMapper.Map(ShibaView view, IShibaContext context)
        {
            return Map(view, context);
        }

        protected virtual TNativeView Map(ShibaView view, IShibaContext context)
        {
            var target = CreateNativeView(context);

            if (view.DefaultValue != null && DefaultPropertyMap != null && HasDefaultProperty)
                SetValue(context, view.DefaultValue, DefaultPropertyMap, target);

            foreach (var property in view.Properties)
            {
                var propertyCache = _propertyCache.Value.LastOrDefault(it => it.Name == property.Name);
                if (propertyCache != null)
                {
                    SetValue(context, property.Value, propertyCache, target);
                }
                else
                {
                    var eventCache = _eventCache.Value.LastOrDefault(it => it.Name == property.Name);
                    if (eventCache != null && property.Value is string name)
                    {
                        eventCache.Setter.Invoke(target, name, context);
                    }
                }
            }

            return target;
        }

        protected virtual TNativeView CreateNativeView(IShibaContext context)
        {
            return new TNativeView();
        }

        protected virtual IEnumerable<IEventMap> EventMaps()
        {
            yield return new EventMap("click", (element, name, context) =>
            {
                element.Tapped += delegate { context.EventCallback(name); };
            });
        }

        protected virtual IEnumerable<IValueMap> PropertyMaps()
        {
            yield return new PropertyMap("enable",
                Control.IsEnabledProperty,
                typeof(bool));
            yield return new ManuallyValueMap("width", typeof(object), (element, o) =>
            {
                if (o.TryChangeType<double>(out var numberResult))
                {
                    element.Width = numberResult;
                } 
                else if (o is string str)
                {
                    if (str == "fill")
                    {
                        element.HorizontalAlignment = HorizontalAlignment.Stretch;
                    }
                }
            });
            yield return new ManuallyValueMap("height", typeof(object), (element, o) =>
            {
                if (o.TryChangeType<double>(out var numberResult))
                {
                    element.Width = numberResult;
                } 
                else if (o is string str)
                {
                    if (str == "fill")
                    {
                        element.VerticalAlignment = VerticalAlignment.Stretch;
                    }
                }
            });
            yield return new PropertyMap("maxHeight", NativeView.MaxHeightProperty, typeof(double));
            yield return new PropertyMap("minHeight", NativeView.MinHeightProperty, typeof(double));
            yield return new PropertyMap("maxWidth", NativeView.MaxWidthProperty, typeof(double));
            yield return new PropertyMap("minWidth", NativeView.MinWidthProperty, typeof(double));
            yield return new PropertyMap("name", NativeView.NameProperty, typeof(string));
            yield return new PropertyMap("visible", UIElement.VisibilityProperty, typeof(bool),
                value => (bool) value ? Visibility.Visible : Visibility.Collapsed);
            yield return new ManuallyValueMap("background", typeof(string), (element, o) =>
            {
                var brush = ColorConverter(o) as Brush;
                switch (element)
                {
                    case Panel panel:
                        panel.Background = brush;
                        break;
                    case Control control:
                        control.Background = brush;
                        break;
                }
            });
            yield return new ManuallyValueMap("padding", typeof(object),
                (element, value) =>
                {
                    NativeThickness thickness;
                    switch (value)
                    {
                        case ShibaObject shibaMap:
                            thickness = shibaMap.ToNativeThickness();
                            break;
                        default:
                            thickness = value.TryChangeType<int>(out var ivalue) ? new NativeThickness(ivalue, ivalue, ivalue, ivalue) : new NativeThickness();
                            break;
                    }

                    
                    switch (element)
                    {
                        case Control control:
                            control.Padding = thickness;
                            break;
                    }
                });
            yield return new ManuallyValueMap("margin", typeof(object),
                (element, value) =>
                {
                    NativeThickness thickness;
                    switch (value)
                    {
                        case ShibaObject shibaMap:
                            thickness = shibaMap.ToNativeThickness();
                            break;
                        default:
                            thickness = value.TryChangeType<int>(out var ivalue) ? new NativeThickness(ivalue, ivalue, ivalue, ivalue) : new NativeThickness();

                            break;
                    }

                    element.Margin = thickness;
                });
            yield return new PropertyMap("alpha", UIElement.OpacityProperty, typeof(double));
            yield return new PropertyMap("style", NativeView.StyleProperty, typeof(string), typeof(Style));
            if (DefaultPropertyMap != null) yield return DefaultPropertyMap;
            var exprop = ExtendPropertyMap();
            if (exprop != null)
                foreach (var item in exprop)
                    yield return item;
        }

        protected object ColorConverter(object arg)
        {
            if (arg is Brush)
            {
                return arg;
            }
            if (!(arg is string value)) throw new ArgumentException("background value should be string");

            return new SolidColorBrush(value.ToNativeColor());
        }

        protected virtual IEnumerable<IValueMap> ExtendPropertyMap()
        {
            return null;
        }

        protected internal void SetValue(IShibaContext context, object value, IValueMap valueMap, TNativeView target)
        {
            var targetValue = valueMap.ValueType == value?.GetType()
                ? value
                : Singleton<ValueVisitor>.Instance.DynamicVisit(value, context);
            valueMap.SetValue(target, targetValue);
        }
    }

    public class AllowChildViewMapper<TNativeView> : ViewMapper<TNativeView>, IAllowChildViewMapper 
        where TNativeView: Panel, new()
    {
        public virtual void AddChild(NativeView parent, NativeView child)
        {
            if (parent is Panel panel)
            {
                panel.Children.Add(child);
            }
        }
    }
}