using System.Collections.Generic;
using Shiba.Controls;


[assembly: ExportView("text", typeof(Text))]
[assembly: ExportView("switch", typeof(Switch))]
[assembly: ExportView("check", typeof(Check))]
[assembly: ExportView("stackLayout", typeof(StackPanel))]
[assembly: ExportView("grid", typeof(Grid))]
[assembly: ExportView("input", typeof(Input))]
[assembly: ExportView("img", typeof(Image))]


namespace Shiba.Controls
{
    public enum Orientation
    {
        Vertical,
        Horizontal
    }

    public class Text : View
    {
        public string Content { get; set; }
        public string TextColor { get; set; }

        public Text(Dictionary<string, object> arrtibute) : base(arrtibute)
        {
        }
    }

    public class Switch : View
    {
        public bool Checked { get; set; }

        public Switch(Dictionary<string, object> arrtibute) : base(arrtibute)
        {
        }
    }

    public class Check : View
    {
        public bool Checked { get; set; }

        public Check(Dictionary<string, object> arrtibute) : base(arrtibute)
        {
        }
    }

    public class StackPanel : ViewGroup
    {
        public Orientation Orientation { get; set; } = Orientation.Vertical;

        public StackPanel(Dictionary<string, object> arrtibute) : base(arrtibute)
        {
        }
    }

    public class Grid : ViewGroup
    {
        public Grid(Dictionary<string, object> arrtibute) : base(arrtibute)
        {
        }
    }

    public class Input : View
    {
        public string Content { get; set; }
        public string TextColor { get; set; }

        public Input(Dictionary<string, object> arrtibute) : base(arrtibute)
        {
        }
    }

    public class Image : View
    {
        public string Source { get; set; }

        public Image(Dictionary<string, object> arrtibute) : base(arrtibute)
        {
        }
    }
}