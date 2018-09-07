using System.ComponentModel;
using System.Linq;
using System.Runtime.CompilerServices;
using Windows.UI.Xaml.Controls;

// https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0x804 上介绍了“空白页”项模板

namespace Shiba.UWP.Sample
{
    public class Model : INotifyPropertyChanged
    {
        private string _uwpText = "UWP!";
        private bool _isChecked;

        public string UWPText
        {
            get => _uwpText;
            set
            {
                _uwpText = value;
                OnPropertyChanged();
            }
        }

        public bool IsChecked
        {
            get => _isChecked;
            set
            {
                _isChecked = value;
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
    ///     可用于自身或导航至 Frame 内部的空白页。
    /// </summary>
    public sealed partial class MainPage : Page
    {
        public Model ViewModel { get; set; } = new Model();

        public MainPage()
        {
            InitializeComponent();
            //var items = Enumerable.Range(0, 1000).Select(it => new Model
            //{
            //    UWPText = $"UWP! {it}"
            //}).ToList();
            //ListView.ItemsSource = items;

        }

        public string Layout { get; set; } =
            "stack { text { text=[WPF: $bind Text, UWP:$bind UWPText] } input { text=[WPF: $bind Text, UWP:$bind UWPText] } }";
    }
}