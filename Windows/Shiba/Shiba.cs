using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using ChakraCore.NET;
using ChakraCore.NET.API;
using ChakraCore.NET.Hosting;
using Newtonsoft.Json.Linq;
using Shiba.Controls;

namespace Shiba
{
    public abstract class AbstractShiba
    {
        protected AbstractShiba(Action<ShibaConfiguration> action)
        {
            action?.Invoke(Configuration);
            ViewMapping.Init();
        }

        public ViewMapping ViewMapping { get; } = new ViewMapping();

        public ShibaConfiguration Configuration { get; } = new ShibaConfiguration();

        public static AbstractShiba Instance { get; protected set; }

        public void AddConverter(string converter)
        {
            if (Configuration?.ConverterExecutor is DefaultConverterExecutor executor) executor.Register(converter);
        }
    }

    public class ShibaConfiguration
    {
        public IConverterExecutor ConverterExecutor { get; set; } = new DefaultConverterExecutor();
        public string PlatformType { get; set; } = "Windows";

        public List<IExtensionExecutor> ExtensionExecutors { get; } =
            AppDomain.CurrentDomain.GetAssemblies()
                .Where(it => !it.IsDynamic)
                .SelectMany(it => it.ExportedTypes)
                .Where(it => it.IsClass && !it.IsAbstract && typeof(IExtensionExecutor).IsAssignableFrom(it))
                .Select(it => Activator.CreateInstance(it) as IExtensionExecutor).ToList();

        public List<ICommonProperty> CommonProperties { get; } =
            AppDomain.CurrentDomain.GetAssemblies()
                .Where(it => !it.IsDynamic)
                .SelectMany(it => it.ExportedTypes)
                .Where(it => it.IsClass && !it.IsAbstract && typeof(ICommonProperty).IsAssignableFrom(it))
                .Select(it => Activator.CreateInstance(it) as ICommonProperty).ToList();
    }

    public interface IConverterExecutor
    {
        object Execute(string functionName, Type targetType, params object[] parameters);
    }


    public interface ITypeConversion
    {
        Type ObjectType { get; }
        JavaScriptValueType JsType { get; }
        Func<object, JavaScriptValue> ToJsValue { get; }
        Func<JavaScriptValue, object> FromJsValue { get; }
    }

    public class TypeConversion : ITypeConversion
    {
        public TypeConversion(Type objectType, JavaScriptValueType jsType, Func<object, JavaScriptValue> toJsValue, Func<JavaScriptValue, object> fromJsValue)
        {
            ObjectType = objectType;
            JsType = jsType;
            ToJsValue = toJsValue;
            FromJsValue = fromJsValue;
        }

        public Type ObjectType { get; }
        public JavaScriptValueType JsType { get; }
        public Func<object, JavaScriptValue> ToJsValue { get; }
        public Func<JavaScriptValue, object> FromJsValue { get; }
    }

    public class DefaultConverterExecutor : IConverterExecutor
    {
        private readonly ChakraContext _context;
        private JavaScriptValue[] _prefix;

        private readonly List<ITypeConversion> _conversions = new List<ITypeConversion>();

        public void AddConversion(ITypeConversion conversion)
        {
            _conversions.Add(conversion);
        }

        public void AddConversion<T>(JavaScriptValueType jsType, Func<T, JavaScriptValue> toJsValue,
            Func<JavaScriptValue, T> fromJsValue)
        {
            AddConversion(new TypeConversion(typeof(T), jsType,
                it => it is T target ? toJsValue.Invoke(target) : JavaScriptValue.Undefined,
                it => fromJsValue.Invoke(it)));
        }
        
        private void InitConversion()
        {
            AddConversion(JavaScriptValueType.String, JavaScriptValue.FromString, it => it.ToString());
            AddConversion(JavaScriptValueType.Number, JavaScriptValue.FromDouble, it => it.ToDouble());
            AddConversion(JavaScriptValueType.Number, JavaScriptValue.FromInt32, it => it.ToInt32());
            AddConversion(JavaScriptValueType.Boolean, JavaScriptValue.FromBoolean, it => it.ToBoolean());
            AddConversion(JavaScriptValueType.Number, it => JavaScriptValue.FromDouble(it), it => Convert.ToSingle(it.ToDouble()));
            AddConversion(JavaScriptValueType.Number, it => JavaScriptValue.FromDouble(Convert.ToDouble(it)), it => Convert.ToDecimal(it.ToDouble()));
        }
        
        public DefaultConverterExecutor(ChakraContext context = null)
        {
            _context = context ?? ChakraRuntime.Create().CreateContext(false);
            _context.ServiceNode.WithContext(() =>
                {
                    _prefix = new[] {JavaScriptValue.FromBoolean(false)};
                });
            InitConversion();
        }

        public object Execute(string functionName, Type targetType, params object[] parameters)
        {
            return  _context.ServiceNode.WithContext(() =>
            {
                var func = _context.GlobalObject.ReferenceValue.GetProperty(JavaScriptPropertyId.FromString(functionName));

                if (func.ValueType == JavaScriptValueType.Function)
                {
                    var param = _prefix.Concat(parameters.Select(it =>
                    {
                        if (it == null)
                        {
                            return JavaScriptValue.Null;
                        }

                        var converter =
                            _conversions.FirstOrDefault(conversion => conversion.ObjectType == it.GetType());
                        
                        return converter?.ToJsValue?.Invoke(it) ?? JavaScriptValue.Undefined;
                    })).ToArray();

                    var result = func.CallFunction(param);

                    ITypeConversion resultConverter = null;

                    foreach (var item in _conversions)
                    {
                        if (item.JsType == result.ValueType)
                        {
                            if (item.ObjectType == targetType)
                            {
                                resultConverter = item;
                                break;
                            }

                            if (targetType.IsAssignableFrom(item.ObjectType))
                            {
                                resultConverter = item;
                            }
                        }
                    }
                    return resultConverter?.FromJsValue(result);
                }

                return null;
            });   
        }

        public void Register(string converter)
        {
            _context.RunScript(converter);
        }

        public void Register(string name, Delegate @delegate)
        {
        }
    }
}