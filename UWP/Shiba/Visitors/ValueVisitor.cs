using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Reflection;
using System.Security.Cryptography;
using System.Text;
using Windows.UI.Xaml;
using ChakraHosting;
using Newtonsoft.Json.Linq;
using Shiba.Controls;
using Shiba.ExtensionExecutors;
using Shiba.Internal;
using Shiba.Scripting;
using Shiba.ViewMappers;
using ShibaView = Shiba.Controls.View;
using NativeView = Windows.UI.Xaml.FrameworkElement;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeProperty = Windows.UI.Xaml.DependencyProperty;
using NativeThickness = Windows.UI.Xaml.Thickness;
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;

namespace Shiba.Visitors
{
    internal class ValueVisitor
    {
        public object DynamicVisit(object obj, IShibaContext context)
        {
            if (obj == null)
            {
                return null;
            }
            return Visit((dynamic) obj, context);
        }

        private object Visit(object obj, IShibaContext context)
        {
            return obj;
        }

        //TODO: Dictionary means it does not support multiple renderer for one view (like Xamarin.Forms custom renderer)
        private static readonly ConcurrentDictionary<string, IViewMapper> Renderer =
            new ConcurrentDictionary<string, IViewMapper>();
        private static readonly IViewMapper ShibaHostMapper = new ComponentMapper();

        private NativeView Visit(View view, IShibaContext context)
        {
            var attribute =
                ShibaApp.Instance.ViewMappings.LastOrDefault(it =>
                    view.ViewName == it.ViewName);
            if (attribute == null)
            {
                if (ShibaApp.Instance.Components.ContainsKey(view.ViewName))
                {
                    view.Properties.Add(new Property("componentName", view.ViewName));
                    return ShibaHostMapper.Map(view, context) as ShibaHost;
                }

                return null;
            }

            var renderer = Renderer.GetOrAdd(attribute.ViewName, type => CreateRenderer(attribute));
            if (context == null) throw new ArgumentNullException($"{nameof(context)} can not be null");

            var target = renderer.Map(view, context) as NativeView;
            if (view.Children.Any() && target is NativeViewGroup panel)
            {
                var commonprops = new List<(ShibaView view, NativeView native, List<Property> properties)>();
                view.Children.ForEach(it =>
                {
                    var child = Visit(it, context);
                    if (renderer is IAllowChildViewMapper viewGroupMapper)
                    {
                        viewGroupMapper.AddChild(panel, child);
                    }
                    else
                    {
                        panel.Children.Add(child);
                    }
                    var commonprop = it.Properties.Where(prop =>
                            ShibaApp.Instance.Configuration.CommonProperties.Any(cp => cp.Name == prop.Name))
                        .ToList();
                    if (commonprop.Any()) commonprops.Add((it, child, commonprop));
                });

                commonprops.ForEach(it =>
                {
                    var (view1, native, properties) = it;
                    properties.ForEach(prop =>
                    {
                        ShibaApp.Instance.Configuration.CommonProperties
                            .Where(cp => cp.Name == prop.Name).ToList()
                            .ForEach(cp => cp.Handle(prop.Value, view1, native, target));
                    });
                });
            }

            return target;
        }

        private static IViewMapper CreateRenderer(ExportMapperAttribute arg)
        {
            return Activator.CreateInstance(arg.MapperType) as IViewMapper;
        }
        
        private object Visit(ShibaExtension item, IShibaContext context)
        {
            var executor =
                ShibaApp.Instance.Configuration.ExtensionExecutors.FirstOrDefault(it => it.Name == item.Type);
            if (executor != null)
            {
                var value = executor.ProvideValue(context, item);
                if (string.IsNullOrEmpty(item.ScriptName)) return value;

                var func = new ShibaFunction(item.ScriptName) {Parameters = {item}};
                if (value is NativeBinding binding)
                {
                    if (binding.Converter != null)
                        binding.ConverterParameter = new ShibaConverterParameter
                        {
                            InnerConverter = binding.Converter,
                            InnerParameter = binding.ConverterParameter,
                            Function = func
                        };
                    else
                        binding.ConverterParameter = func;
                    binding.Converter = Singleton<SingleBindingFunctionConverter>.Instance;
                    return binding;
                }

                return new NativeBinding
                {
                    ConverterParameter =
                        Singleton<SingleBindingShibaFunctionExecutor>.Instance.Execute(func, item),
                    Converter = Singleton<RawDataConverter>.Instance
                };
            }

            throw new NotImplementedException();
        }
        
        private NativeBinding Visit(ShibaFunction item, IShibaContext context)
        {
            var function = ParseFunction(item, context);
            var extensions = GetExtensions(function)?.ToList();
            if (extensions == null || !extensions.Any())
                return new NativeBinding
                {
                    ConverterParameter = Singleton<ShibaFunctionExecutor>.Instance.Execute(function, null),
                    Converter = Singleton<RawDataConverter>.Instance
                };

            if (extensions.Count == 1)
            {
                var extension = extensions.FirstOrDefault();

                var extensionValue = DynamicVisit(extension, context);

                switch (extensionValue)
                {
                    case NativeBinding binding:
                        if (binding.Converter != null)
                            binding.ConverterParameter = new ShibaConverterParameter
                            {
                                InnerConverter = binding.Converter,
                                InnerParameter = binding.ConverterParameter,
                                Function = function
                            };
                        else
                            binding.ConverterParameter = function;
                        binding.Converter = Singleton<SingleBindingFunctionConverter>.Instance;
                        return binding;
                    default:
                        return new NativeBinding
                        {
                            ConverterParameter =
                                Singleton<SingleBindingShibaFunctionExecutor>.Instance.Execute(function,
                                    extensionValue),
                            Converter = Singleton<RawDataConverter>.Instance
                        };
                }
            }

            if (extensions.Count > 1)
                throw new ArgumentOutOfRangeException("Currently only support single ShibaExtension");

            throw new ArgumentOutOfRangeException($"Can not handle function call ${item}");
        }

        private IEnumerable<ShibaExtension> GetExtensions(ShibaFunction function)
        {
            foreach (var item in function.Parameters)
                switch (item)
                {
                    case ShibaExtension extension:
                    {
                        var executor = ShibaApp.Instance.Configuration.ExtensionExecutors
                            .FirstOrDefault(it => it.Name == extension.Type);
                        if (!(executor is IMutableExtensionExecutor)) yield return extension;
                    }
                        break;
                    case ShibaFunction shibaFunction:
                        foreach (var extension in GetExtensions(shibaFunction)) yield return extension;
                        break;
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
                        var executor = ShibaApp.Instance.Configuration.ExtensionExecutors
                            .FirstOrDefault(it => it.Name == extension.Type);
                        if (executor is IMutableExtensionExecutor)
                            item.Parameters[i] = executor.ProvideValue(context, extension);
                        break;
                    default:
                        item.Parameters[i] = DynamicVisit(parameter, context);
                        break;
                }
            }

            return item;
        }


        private List<object> Visit(JArray obj, IShibaContext context)
        {
            return obj.Select(it => DynamicVisit(it, context)).ToList();
        }

        private ShibaObject Visit(JObject item, IShibaContext context)
        {
            var dic = new ShibaObject();
            foreach (var (key, value) in item)
            {
                dic.TryAdd(key, DynamicVisit(value, context));
            }

            return dic;
        }

        private KeyValuePair<string, object> Visit(JProperty item, IShibaContext context)
        {
            return new KeyValuePair<string, object>(item.Name, DynamicVisit(item.Value, context));
        }

        private object Visit(JValue item, IShibaContext context)
        {
            switch (item.Type)
            {
                case JTokenType.Integer:
                    return item.Value<int>();
                case JTokenType.Float:
                    return item.Value<float>();
                case JTokenType.String:
                    return item.Value<string>();
                case JTokenType.Boolean:
                    return item.Value<bool>();
                case JTokenType.Date:
                    return item.Value<DateTime>();
                case JTokenType.Raw:
                    return item.Value<string>();
                case JTokenType.Bytes:
                    return item.Value<byte[]>();
                case JTokenType.Guid:
                    return item.Value<Guid>();
                case JTokenType.Uri:
                    return item.Value<string>();
                case JTokenType.TimeSpan:
                    return item.Value<TimeSpan>();
                default:
                    return null;
            }
        }

        private object Visit(JavaScriptValue value, IShibaContext context)
        {
            switch (value.ValueType)
            {
                case JavaScriptValueType.Error:
                case JavaScriptValueType.Object:
                    return VisitJavascriptObject(value);
                case JavaScriptValueType.Array:
                    return VisitJavascriptArray(value);
                case JavaScriptValueType.Boolean:
                    return value.ToBoolean();
                case JavaScriptValueType.Null:
                    return null;
                case JavaScriptValueType.Number:
                    return value.ToDouble();
                case JavaScriptValueType.String:
                    return value.ToString();
                case JavaScriptValueType.Undefined:
                case JavaScriptValueType.Function:
                default:
                    return value;
            }
        }

        private List<object> VisitJavascriptArray(JavaScriptValue value)
        {
            var result = new List<object>();
            {
                var currentIndex = 0;
                while (value.HasIndexedProperty(currentIndex.ToJavaScriptValue()))
                {
                    result.Add(Visit(value.GetIndexedProperty(currentIndex.ToJavaScriptValue()), null));
                    currentIndex++;
                }
            }
            return result;
        }

        private ShibaObject VisitJavascriptObject(JavaScriptValue value)
        {
            var propers = VisitJavascriptArray(value.GetOwnPropertyNames()).Cast<string>().ToList();
            var obj = new ShibaObject();
            foreach (var name in propers)
            {
                obj.TryAdd(name, Visit(value.GetProperty(name.ToJavaScriptPropertyId()), null));
            }

            return obj;
        }
    }

}