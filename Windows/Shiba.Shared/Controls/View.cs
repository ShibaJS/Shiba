using System;
using System.Collections.Generic;
using System.Linq;
using Shiba.Controls;

[assembly: ExportView("view", typeof(View))]

namespace Shiba.Controls
{
    [AttributeUsage(AttributeTargets.Assembly, AllowMultiple = true)]
    public sealed class ExportViewAttribute : Attribute
    {
        public ExportViewAttribute(string viewName, Type viewType)
        {
            ViewName = viewName;
            ViewType = viewType;
        }

        public string ViewName { get; }
        public Type ViewType { get; }
    }

    public class SbEvent
    {
        public string Target { get; set; }
    }

    public enum Gravity
    {
        Start,
        End,
        Top,
        Bottom
    }

    public struct Thickness
    {
        internal Thickness(string value)
        {
            var values = value.Split(',');
            Top = float.TryParse(values.ElementAtOrDefault(0), out var top) ? top : 0F;
            Right = float.TryParse(values.ElementAtOrDefault(1), out var right) ? right : 0F;
            Bottom = float.TryParse(values.ElementAtOrDefault(2), out var bottom) ? bottom : 0F;
            Left = float.TryParse(values.ElementAtOrDefault(3), out var left) ? left : 0F;

        }
        internal Thickness(float top, float left, float right, float bottom)
        {
            Top = top;
            Left = left;
            Right = right;
            Bottom = bottom;
        }

        public float Top { get; set; }
        public float Left { get; set; }
        public float Right { get; set; }
        public float Bottom { get; set; }
    }

    public class View
    {
        public View(Dictionary<string, object> arrtibute)
        {
            
        }

        public float Width { get; set; } = float.NaN;
        public float Height { get; set; } = float.NaN;
        public float MinWidth { get; set; } = float.NaN;
        public float MinHeight { get; set; } = float.NaN;
        public float MaxWidth { get; set; } = float.NaN;
        public float MaxHeight { get; set; } = float.NaN;
        public bool IsVisible { get; set; }
        public Gravity Gravity { get; set; } = Gravity.Start;
        public Thickness Margin { get; set; } = new Thickness();
        public Thickness Padding { get; set; } = new Thickness();
        public bool IsEnabled { get; set; } = true;
        public string BackgroundColor { get; set; }
        public SbEvent Click { get; set; }
        public SbEvent SizeChanged { get; set; }
        public float Alpha { get; set; } = 1f;
        public string Name { get; set; }
    }

    public class ViewGroup : View
    {
        public List<View> Children { get; set; } = new List<View>();

        public ViewGroup(Dictionary<string, object> arrtibute) : base(arrtibute)
        {
        }
    }
}