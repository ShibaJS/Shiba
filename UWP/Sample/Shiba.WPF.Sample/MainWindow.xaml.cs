using System.Collections.Generic;
using System.ComponentModel;
using System.Diagnostics;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Windows;
using System.Windows.Controls;

namespace Shiba.WPF.Sample
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
    ///     MainWindow.xaml 的交互逻辑
    /// </summary>
    public partial class MainWindow : Window
    {
        public Model ViewModel { get; set; } = new Model();
        public MainWindow()
        {
            InitializeComponent();
        }

        public string Layout { get; set; } =
            "stack { text { text=[WPF: $bind Text, UWP:$bind UWPText] } input { text=[WPF: $bind Text, UWP:$bind UWPText] } }";
    }
}