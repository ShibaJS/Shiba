using System.ComponentModel;
using System.Runtime.CompilerServices;
using System.Windows;

namespace Shiba.WPF.Sample
{

    class Model : INotifyPropertyChanged
    {
        private string _text = "WPF!";

        public string Text
        {
            get => _text;
            set
            {
                _text = value;
                OnPropertyChanged();
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged([CallerMemberName] string propertyName = null)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }

    /// <summary>
    ///     MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
            Host.DataContext = new Model();
            this.Host.Layout = this.Layout;
        }

        public string Layout { get; set; } =
            "stack { text { text=[WPF: $bind Text, UWP:$bind UWPText] } input { text=[WPF: $bind Text, UWP:$bind UWPText] } }";
    }
}