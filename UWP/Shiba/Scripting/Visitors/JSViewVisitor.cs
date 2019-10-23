using System;
using System.Collections.Generic;
using ChakraHosting;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using Shiba.Controls;
using Shiba.Internal;
using Shiba.Visitors;

namespace Shiba.Scripting.Visitors
{
    internal class JSViewVisitor
    {
        private const string ViewType = "IView";

        public object Visit(JavaScriptValue value)
        {
            const string className = "className";
            if (value.ValueType != JavaScriptValueType.Object || !value.HasProperty(className.ToJavaScriptPropertyId()))
                return value.ToNative();
            var type = value.GetProperty(className.ToJavaScriptPropertyId()).ToNative<string>();
            switch (type)
            {
                case ViewType:
                    return VisitView(value);
                default: throw new ArgumentOutOfRangeException();

            }
        }

        private View VisitView(JavaScriptValue value)
        {
            var name = value.GetProperty("name".ToJavaScriptPropertyId()).ToNative<string>();
            var properties = value.GetProperty("properties".ToJavaScriptPropertyId());
            var children = value.GetProperty("children".ToJavaScriptPropertyId());
            var view = new View(name, string.Empty);
            if (properties.ValueType != JavaScriptValueType.Array)
            {
                throw new ArgumentException();
            }

            if (children.ValueType != JavaScriptValueType.Array)
            {
                throw new ArgumentException();
            }

            {
                var currentIndex = 0;
                while (properties.HasIndexedProperty(currentIndex.ToJavaScriptValue()))
                {
                    view.Properties.Add(VisitProperty(properties.GetIndexedProperty(currentIndex.ToJavaScriptValue())));
                    currentIndex++;
                }
            }

            {
                var currentIndex = 0;
                while (children.HasIndexedProperty(currentIndex.ToJavaScriptValue()))
                {
                    var child = Visit(children.GetIndexedProperty(currentIndex.ToJavaScriptValue()));
                    if (child is View childView)
                    {
                        childView.Parent = view;
                        view.Children.Add(childView);
                    }
                    else
                    {
                        view.DefaultValue = child;
                    }
                    currentIndex++;
                }
            }

            return view;
        }

        private Property VisitProperty(JavaScriptValue value)
        {
            Enum.TryParse<ValueType>(value.GetProperty("valueType".ToJavaScriptPropertyId()).ToNative() + "", out var valueType);
            var name = value.GetProperty("name".ToJavaScriptPropertyId()).ToNative<string>();
            var propertyValue = value.GetProperty("value".ToJavaScriptPropertyId());
            switch (valueType)
            {
                case ValueType.Extension:
                    var target = propertyValue.GetProperty("target".ToJavaScriptPropertyId()).ToNative<string>();
                    var type = propertyValue.GetProperty("type".ToJavaScriptPropertyId()).ToNative<string>();
                    var scriptName = string.Empty;
                    if (propertyValue.HasProperty("scriptName".ToJavaScriptPropertyId()))
                    {
                        scriptName = propertyValue.GetProperty("scriptName".ToJavaScriptPropertyId()).ToNative<string>();
                    }
                    return new Property(name, new ShibaExtension(type, target, scriptName));
                case ValueType.Custom:
                    return new Property(name, Singleton<ValueVisitor>.Instance.DynamicVisit(propertyValue, null));
                case ValueType.Boolean:
                case ValueType.Number:
                case ValueType.String:
                case ValueType.Null:
                    return new Property(name, propertyValue.ToNative());
                default:
                    throw new ArgumentOutOfRangeException();
            }
        }

        private enum ValueType
        {
            Extension,
            Boolean,
            Number,
            String,
            Null,
            Custom
        }
    }
}