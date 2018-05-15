using Windows.UI.Xaml.Controls;

// https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0x804 上介绍了“空白页”项模板

namespace Shiba.UWP.Sample
{
    /// <summary>
    ///     可用于自身或导航至 Frame 内部的空白页。
    /// </summary>
    public sealed partial class MainPage : Page
    {
        public MainPage()
        {
            InitializeComponent();
            this.Host.Layout = this.Layout;
        }

        public string Layout { get; set; } =
            "stack{ text { text=\"test!!!\" } text { text=\"test!!!\" textColor=red } }";
    }
}