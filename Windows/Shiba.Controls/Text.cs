using System.Collections.Generic;
using Shiba.Controls;
using Shiba.Controls.Common;

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
        public Text(Dictionary<string, object> attribute) : base(attribute)
        {
            if (attribute.TryGetValue("content", out var content)) Content = content.To<string>();

            if (attribute.TryGetValue("textColor", out var textColor)) TextColor = textColor.To<string>();
        }

        public string Content { get; }
        public string TextColor { get; }
    }

    public class Switch : View
    {
        public Switch(Dictionary<string, object> attribute) : base(attribute)
        {
            if (attribute.TryGetValue("checked", out var check)) Checked = check.To<bool>();
        }

        public bool Checked { get; }
    }

    public class Check : Switch
    {
        public Check(Dictionary<string, object> attribute) : base(attribute)
        {
        }
    }

    public class StackPanel : ViewGroup
    {
        public StackPanel(Dictionary<string, object> attribute) : base(attribute)
        {
            if (attribute.TryGetValue("orientation", out var orientation))
                Orientation = orientation.To<string>().FirstToUpper().To<Orientation>();
        }

        public Orientation Orientation { get; } = Orientation.Vertical;
    }

    public class Grid : ViewGroup
    {
        public Grid(Dictionary<string, object> attribute) : base(attribute)
        {
        }
    }

    public class Input : Text
    {
        public Input(Dictionary<string, object> attribute) : base(attribute)
        {
        }
    }

    public class Image : View
    {
        public Image(Dictionary<string, object> attribute) : base(attribute)
        {
            if (attribute.TryGetValue("source", out var source)) Source = source.To<string>();
        }

        public string Source { get; }
    }
}