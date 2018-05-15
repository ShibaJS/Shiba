using System.Windows;

namespace Shiba.WPF.Sample
{
    /// <summary>
    ///     App.xaml 的交互逻辑
    /// </summary>
    public partial class App : Application
    {
        public App()
        {
            ShibaApp.Init();
        }
    }
}