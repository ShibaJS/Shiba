using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Dynamic;
using System.Linq;
using System.Reflection;
using System.Runtime.InteropServices;
using Shiba;
using Shiba.Common;
using Shiba.Controls;
using Shiba.Internal;
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
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;
#elif FORMS
using Xamarin.Forms;
using NativeView = Xamarin.Forms.View;
using NativeBinding = Xamarin.Forms.Binding;
using NativeProperty = Xamarin.Forms.BindableProperty;
using NativeThickness = Xamarin.Forms.Thickness;
using NativeViewGroup = Xamarin.Forms.Layout<Xamarin.Forms.View>;

#elif WPF
using System.Windows;
using System.Windows.Controls;
using NativeView = System.Windows.FrameworkElement;
using NativeBinding = System.Windows.Data.Binding;
using NativeProperty = System.Windows.DependencyProperty;
using NativeThickness = System.Windows.Thickness;
using System.Windows.Media;
using NativeViewGroup = System.Windows.Controls.Panel;
#endif

namespace Shiba.Visitors
{
    internal interface IValueVisitor
    {
        Type HandleType { get; }
        object Visit(object item, IShibaContext context);
    }

    internal abstract class ShibaValueVisitor : IValueVisitor
    {
        private static readonly List<IValueVisitor> Visitors;

        static ShibaValueVisitor()
        {
            Visitors = typeof(IValueVisitor)
                .Assembly
                .DefinedTypes
                .Where(it =>
                    it.IsClass && !it.IsAbstract &&
                    typeof(IValueVisitor).GetTypeInfo().IsAssignableFrom(it.GetTypeInfo()))
                .Select(it => Activator.CreateInstance(it) as IValueVisitor)
                .ToList();
        }

        public abstract Type HandleType { get; }
        public abstract object Visit(object item, IShibaContext context);

        protected internal static object GetValue(object item, IShibaContext context)
        {
            return item == null ? null : Visitors.FirstOrDefault(it => it.HandleType == item.GetType())?.Visit(item, context) ?? item;
        }
    }

    internal abstract class GenericVisitor<T, K> : ShibaValueVisitor
        where T : class
    {
        public override Type HandleType { get; } = typeof(T);

        public override object Visit(object item, IShibaContext context)
        {
            return Parse(item as T, context);
        }

        protected abstract K Parse(T item, IShibaContext context);
    }

    internal sealed class ViewVisitor : GenericVisitor<ShibaView, NativeView>
    {
        //TODO: Dictionary means it does not support multiple renderer for one view (like Xamarin.Forms custom renderer)
        private static readonly ConcurrentDictionary<string, IViewMapper> Renderer =
            new ConcurrentDictionary<string, IViewMapper>();
        protected override NativeView Parse(View view, IShibaContext context)
        {
            var attribute = AbstractShiba.Instance.ViewMapping.Renderers.LastOrDefault(it => it.ViewName == view.ViewName);
            if (attribute == null)
            {
                return null;
            }
            var renderer = Renderer.GetOrAdd(attribute.ViewName, type => CreateRenderer(attribute));
            var target = renderer.Map(view, context) as NativeView;
            if (view.Children.Any() && target is NativeViewGroup panel)
            {
                view.Children.ForEach(it => panel.Children.Add(Parse(it, context)));
            }

            return target;
        }

        private static IViewMapper CreateRenderer(ExportMapperAttribute arg)
        {
            return Activator.CreateInstance(arg.MapperType) as IViewMapper;
        }
    }

    internal sealed class ShibaExtensionVisitor : GenericVisitor<ShibaExtension, ShibaBinding>
    {
        protected override ShibaBinding Parse(ShibaExtension item, IShibaContext context)
        {
            switch (item.Type)
            {
                case "bind":
                {
                    switch (item.Value.TypeCode)
                    {
                        case ShibaValueType.Token:
                            return new ShibaBinding
                            {
                                Path = item.Value.Value + ""
                            };
                        case ShibaValueType.Number:
                        case ShibaValueType.Null:
                        case ShibaValueType.Boolean:
                        case ShibaValueType.String:
                            return new ShibaBinding
                            {
                                Converter = Singleton<RawDataConverter>.Instance,
                                Parameter = item.Value.Value,
                            };
                        default:
                            throw new ArgumentOutOfRangeException();
                    }
                }
            }

            throw new NotImplementedException();
        }
    }

    internal sealed class ShibaFunctionVisitor : GenericVisitor<ShibaFunction, ShibaBinding>
    {
        protected override ShibaBinding Parse(ShibaFunction item, IShibaContext context)
        {
            var function = ParseFunction(item);
            var bindings = GetBindings(function)?.ToList();

            if (bindings == null || !bindings.Any())
            {
                return new ShibaBinding
                {
                    Parameter = Singleton<ShibaFunctionExecutor>.Instance.Execute(function, null),
                    Converter = Singleton<RawDataConverter>.Instance
                };
            }

            if (bindings.Count == 1)
            {
                return new ShibaBinding
                {
                    Path = bindings.FirstOrDefault().Path,
                    Parameter = function,
                    Converter = Singleton<SingleBindingFunctionConverter>.Instance
                };
            }

            if (bindings.Count > 1)
            {
                return new ShibaMultiBinding
                {
                    Parameter = function,
                    Converter = Singleton<FunctionConverter>.Instance,
                    Paths = bindings.Select(it => it.Path).ToArray()
                };
            }

            throw new ArgumentOutOfRangeException();
        }

        private IEnumerable<ShibaBinding> GetBindings(ShibaFunction function)
        {
            foreach (var item in function.Paramters)
            {
                switch (item)
                {
                    case ShibaBinding binding:
                        yield return binding;
                        break;
                    case ShibaFunction shibaFunction:
                        foreach (var shibaBinding in GetBindings(shibaFunction))
                        {
                            yield return shibaBinding;
                        }

                        break;
                }
            }
        }

        private ShibaFunction ParseFunction(ShibaFunction item)
        {
            for (var i = 0; i < item.Paramters.Count; i++)
            {
                var paramter = item.Paramters[i];
                if (paramter is ShibaFunction function)
                {
                    item.Paramters[i] = ParseFunction(function);
                }
                else
                {
                    var result = GetValue(paramter, null);// currently not support view item in function
                    if (result is ShibaBinding shibaBinding && shibaBinding.Converter is RawDataConverter)
                    {
                        result = shibaBinding.Parameter;
                    }

                    item.Paramters[i] = result;
                }
            }

            return item;
        }
    }

    //internal sealed class ShibaMapVisitor : GenericVisitor<ShibaMap, Dictionary<string, object>>
    //{
    //    protected override Dictionary<string, object> Parse(ShibaMap item, IShibaContext context)
    //    {
    //        return item.ToDictionary().ToDictionary(it => it.Key, it => GetValue(it.Value, context));
    //    }
    //}

    internal sealed class ShibaArrayVisitor : GenericVisitor<ShibaArray, List<object>>
    {
        protected override List<object> Parse(ShibaArray item, IShibaContext context)
        {
            return item.Select(it => GetValue(it, context)).ToList();
        }
    }

    internal sealed class BacisValueVisitor : GenericVisitor<BasicValue, object>
    {
        protected override object Parse(BasicValue item, IShibaContext context)
        {
            return item.Value;
        }
    }
}