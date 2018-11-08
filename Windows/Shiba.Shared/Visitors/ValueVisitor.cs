using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Security.Cryptography;
using System.Text;
using Shiba.Controls;
using Shiba.Internal;
using ShibaView = Shiba.Controls.View;
using View = Shiba.Controls.View;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Data;
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
using System.Windows.Data;
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
            if (item == null)
            {
                return null;
            }

            var visitor = Visitors.FirstOrDefault(it => it.HandleType == item.GetType());

            if (visitor == null)
            {
                return item;
            }

            return visitor.Visit(item, context);
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
            var attribute =
                AbstractShiba.Instance.ViewMapping.Mappers.LastOrDefault(it =>
                    view.ViewName.IsCurrentPlatform(it.ViewName));
            if (attribute == null)
            {
                return null;
            }

            var renderer = Renderer.GetOrAdd(attribute.ViewName, type => CreateRenderer(attribute));
            if (context == null)
            {
                throw new ArgumentNullException($"{nameof(context)} can not be null");
            }

            var target = renderer.Map(view, context) as NativeView;
            if (view.Children.Any() && target is NativeViewGroup panel)
            {
                var commonprops = new List<(ShibaView view, NativeView native, List<Property> properties)>();
                view.Children.ForEach(it =>
                {
                    var child = Parse(it, context);
                    panel.Children.Add(child);
                    var commonprop = it.Properties.Where(prop =>
                            prop.Name.IsCurrentPlatform() &&
                            AbstractShiba.Instance.Configuration.CommonProperties.Any(cp => cp.Name == prop.Name.Value))
                        .ToList();
                    if (commonprop.Any())
                    {
                        commonprops.Add((it, child, commonprop));
                    }
                });

                commonprops.ForEach(it =>
                {
                    it.properties.ForEach(prop =>
                    {
                        AbstractShiba.Instance.Configuration.CommonProperties
                            .Where(cp => cp.Name == prop.Name.Value).ToList()
                            .ForEach(cp => cp.Handle(prop.Value, it.native, target));
                    });
                });
            }

            return target;
        }

        private static IViewMapper CreateRenderer(ExportMapperAttribute arg)
        {
            return Activator.CreateInstance(arg.MapperType) as IViewMapper;
        }
    }

    internal sealed class ShibaExtensionVisitor : GenericVisitor<ShibaExtension, object>
    {
        public ConcurrentDictionary<string, string> ScriptsCache { get; } = new ConcurrentDictionary<string, string>();

        private string Hash(string input)
        {
            var hash = (new SHA1Managed()).ComputeHash(Encoding.UTF8.GetBytes(input));
            return string.Join("", hash.Select(b => b.ToString("x2")).ToArray());
        }


        protected override object Parse(ShibaExtension item, IShibaContext context)
        {
            var executor =
                AbstractShiba.Instance.Configuration.ExtensionExecutors.FirstOrDefault(it => it.Name == item.Type);
            if (executor != null)
            {
                var value = executor.ProvideValue(context, item);
                if (string.IsNullOrEmpty(item.Script))
                {
                    return value;
                }

                var funcName = ScriptsCache.GetOrAdd(item.Script, script =>
                {
                    var hash = $"_{Hash(script)}";
                    //TODO: function parameter name
                    AbstractShiba.Instance.AddConverter($"function {hash}(it){{ {script.TrimStart('$').TrimStart('{').TrimEnd('}')} }}");
                    return hash;
                });
                var func = new ShibaFunction(funcName) {Parameters = {item}};
                if (value is NativeBinding binding)
                {
                    
                    if (binding.Converter != null)
                    {
                        binding.ConverterParameter = new ShibaConverterParameter
                        {
                            InnerConverter = binding.Converter,
                            InnerParameter = binding.ConverterParameter,
                            Function = func
                        };
                    }
                    else
                    {
                        binding.ConverterParameter = func;
                    }
                    binding.Converter = Singleton<SingleBindingFunctionConverter>.Instance;
                    return binding;
                }
                else
                {
                    return new NativeBinding
                    {
                        ConverterParameter =
                            Singleton<SingleBindingShibaFunctionExecutor>.Instance.Execute(func, item,
                                typeof(object)),
                        Converter = Singleton<RawDataConverter>.Instance
                    };
                }
                
            }

            throw new NotImplementedException();
        }
    }

    internal sealed class ShibaFunctionVisitor : GenericVisitor<ShibaFunction, NativeBinding>
    {
        protected override NativeBinding Parse(ShibaFunction item, IShibaContext context)
        {
            var function = ParseFunction(item, context);
            var extensions = GetExtensions(function)?.ToList();
            if (extensions == null || !extensions.Any())
            {
                return new NativeBinding
                {
                    ConverterParameter = Singleton<ShibaFunctionExecutor>.Instance.Execute(function, null, typeof(object)),
                    Converter = Singleton<RawDataConverter>.Instance
                };
            }

            if (extensions.Count == 1)
            {
                var extension = extensions.FirstOrDefault();

                var extensionValue = GetValue(extension, context);

                switch (extensionValue)
                {
                    case NativeBinding binding:
                        if (binding.Converter != null)
                        {
                            binding.ConverterParameter = new ShibaConverterParameter
                            {
                                InnerConverter = binding.Converter,
                                InnerParameter = binding.ConverterParameter,
                                Function = function
                            };
                        }
                        else
                        {
                            binding.ConverterParameter = function;
                        }
                        binding.Converter = Singleton<SingleBindingFunctionConverter>.Instance;
                        return binding;
                    default:
                        return new NativeBinding
                        {
                            ConverterParameter = Singleton<SingleBindingShibaFunctionExecutor>.Instance.Execute(function, extensionValue, typeof(object)),
                            Converter = Singleton<RawDataConverter>.Instance
                        };
                }
            }

            if (extensions.Count > 1)
            {
                throw new ArgumentOutOfRangeException($"Currently only support single ShibaExtension");
            }

            throw new ArgumentOutOfRangeException($"Can not handle function call ${item}");
        }

        private IEnumerable<ShibaExtension> GetExtensions(ShibaFunction function)
        {
            foreach (var item in function.Parameters)
            {
                switch (item)
                {
                    case ShibaExtension extension:
                    {
                        var executor = AbstractShiba.Instance.Configuration.ExtensionExecutors
                            .FirstOrDefault(it => it.Name == extension.Type);
                        if (!(executor is IMutableExtensionExecutor))
                        {
                            yield return extension;
                        }
                    }
                        break;
                    case ShibaFunction shibaFunction:
                        foreach (var extension in GetExtensions(shibaFunction))
                        {
                            yield return extension;
                        }
                        break;
                }
            }
        }

        private ShibaFunction ParseFunction(ShibaFunction item, IShibaContext context)
        {
            for (var i = 0; i < item.Parameters.Count; i++)
            {
                var parameter = item.Parameters[i];
                switch (parameter)
                {
                    case ShibaFunction function:
                        item.Parameters[i] = ParseFunction(function, context);
                        break;
                    case ShibaExtension extension:
                        var executor = AbstractShiba.Instance.Configuration.ExtensionExecutors
                            .FirstOrDefault(it => it.Name == extension.Type);
                        if (executor is IMutableExtensionExecutor)
                        {
                            item.Parameters[i] = executor.ProvideValue(context, extension);
                        }
                        break;
                    default:
                        item.Parameters[i] = GetValue(parameter, context);
                        break;
                }
            }

            return item;
        }
    }

//    internal sealed class ShibaMapVisitor : GenericVisitor<ShibaMap, Dictionary<string, object>>
//    {
//        protected override Dictionary<string, object> Parse(ShibaMap item, IShibaContext context)
//        {
//            return item..ToDictionary(it => it.Key, it => GetValue(it.Value, context));
//        }
//    }

    internal sealed class ShibaArrayVisitor : GenericVisitor<ShibaArray, IEnumerable<object>>
    {
        protected override IEnumerable<object> Parse(ShibaArray item, IShibaContext context)
        {
            var shibaArray = new ShibaArray();
            shibaArray.AddRange(item.Select(it => GetValue(it, context)));
            return shibaArray;
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