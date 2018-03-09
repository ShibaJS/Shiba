using System.Collections.Generic;
using System.Xml.Serialization;

namespace Shiba.Controls
{
    public enum Gravity
    {
        Start,
        End,
        Top,
        Bottom
    }

    public struct Thickness
    {
        public Thickness(float top, float left, float right, float bottom) : this()
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
        public float Width { get; set; } = float.NaN;
        public float Height { get; set; } = float.NaN;
        public float MinWidth { get; set; } = float.NaN;
        public float MinHeight { get; set; } = float.NaN;
        public float MaxWidth { get; set; } = float.NaN;
        public float MaxHeight { get; set; } = float.NaN;
        public Gravity Gravity { get; set; } = Gravity.Start;
        public Thickness Margin { get; set; } = new Thickness();
        public Thickness Padding { get; set; } = new Thickness();
        public bool IsEnabled { get; set; } = true;
        public string BackgroundColor { get; set; }

        public string Click { get; set; }
        public string SizeChanged { get; set; }
        public float Alpha { get; set; } = 1f;
        public string Name { get; set; }
    }

    public class ViewGroup : View
    {
       public List<View> Children { get; set; }
    }
}