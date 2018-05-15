using System.Windows;

namespace Shiba.WPF.Sample
{
    /// <summary>
    ///     MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            this.Host.Layout = this.Layout;
        }

        public string Layout { get; set; } =
            "stack{ text { text=\"test!!!\" } text { text=\"test!!!\" textColor=red } }";
    }
}