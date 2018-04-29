using System;
using System.Collections.Generic;
using Shiba.Controls;
using Shiba.Controls.Common;

[assembly: ExportView("view", typeof(View))]

namespace Shiba.Controls
{
    public class SbEvent
    {
        public SbEvent(string target)
        {
            Target = target;
        }

        public string Target { get; }
    }

    public class View
    {
        public View(Dictionary<string, object> attribute)
        {
            if (attribute.TryGetValue("width", out var width)) Width = width.To<float>();

            if (attribute.TryGetValue("height", out var height)) Height = height.To<float>();

            if (attribute.TryGetValue("minWidth", out var minWidth)) MinWidth = minWidth.To<float>();

            if (attribute.TryGetValue("minHeight", out var minHeight)) MinHeight = minHeight.To<float>();

            if (attribute.TryGetValue("maxWidth", out var maxWidth)) MaxWidth = maxWidth.To<float>();

            if (attribute.TryGetValue("maxHeight", out var maxHeight)) MaxHeight = maxHeight.To<float>();

            if (attribute.TryGetValue("isVisible", out var isVisible)) IsVisible = isVisible.To<bool>();

            if (attribute.TryGetValue("gravity", out var gravity) &&
                Enum.TryParse<Gravity>(gravity.ToString(), true, out var gravityResult)) Gravity = gravityResult;

            if (attribute.TryGetValue("margin", out var margin)) Margin = margin.To<Thickness>();

            if (attribute.TryGetValue("padding", out var padding)) Padding = padding.To<Thickness>();

            if (attribute.TryGetValue("enable", out var enable)) Enable = enable.To<bool>();

            if (attribute.TryGetValue("background", out var background)) Background = background?.ToString();

            if (attribute.TryGetValue("click", out var click)) Click = new SbEvent(click?.ToString());

            if (attribute.TryGetValue("sizeChanged", out var sizeChanged))
                SizeChanged = new SbEvent(sizeChanged?.ToString());

            if (attribute.TryGetValue("alpha", out var alpha)) Alpha = alpha.To<float>();

            if (attribute.TryGetValue("name", out var name)) Name = name.To<string>();
        }

        public float Width { get; } = float.NaN;
        public float Height { get; } = float.NaN;
        public float MinWidth { get; } = float.NaN;
        public float MinHeight { get; } = float.NaN;
        public float MaxWidth { get; } = float.NaN;
        public float MaxHeight { get; } = float.NaN;
        public bool IsVisible { get; }
        public Gravity Gravity { get; } = Gravity.Start;
        public Thickness Margin { get; }
        public Thickness Padding { get; }
        public bool Enable { get; } = true;
        public string Background { get; }
        public SbEvent Click { get; }
        public SbEvent SizeChanged { get; }
        public float Alpha { get; } = 1f;
        public string Name { get; }

        internal List<IComputValue> ComputValues { get; } = new List<IComputValue>();

        internal void SetComput(IComputValue value)
        {
            ComputValues.Add(value);
        }
    }

    public class ViewGroup : View
    {
        public ViewGroup(Dictionary<string, object> attribute) : base(attribute)
        {
        }

        public List<View> Children { get; } = new List<View>();
    }

    public class Comput
    {
        public Comput InnerComput { get; set; }
        public string FuncName { get; set; }
        public string Value { get; set; }
    }

    internal interface IComputValue
    {
        Comput Value { get; }
    }

    public struct Binding : IComputValue
    {
        public Binding(Comput value)
        {
            Value = value;
        }

        public Comput Value { get; }
    }

    public struct JsonPath : IComputValue
    {
        public JsonPath(Comput value)
        {
            Value = value;
        }

        public Comput Value { get; }
    }

    public struct NativeResource : IComputValue
    {
        public NativeResource(Comput value)
        {
            Value = value;
        }

        public Comput Value { get; }
    }
}