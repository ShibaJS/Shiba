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
    }

    public class Switch : View
    {
        public bool Checked { get; set; }
    }

    public class Check : View
    {
        public bool Checked { get; set; }
    }

    public class StackPanel : ViewGroup
    {
        public Orientation Orientation { get; set; } = Orientation.Vertical;
    }

    public class Grid : ViewGroup
    {
    }

    public class Edit : View
    {
        public string Content { get; set; }
        public string TextColor { get; set; }
    }

    public class Image : View
    {
        public string Source { get; set; }
    }
}