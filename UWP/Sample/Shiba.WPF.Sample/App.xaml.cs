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
            AbstractShiba.Instance.AddConverter("function awesome(value, pref) { return pref + value + \" is awesome!\" }");
            AbstractShiba.Instance.AddConverter("function reverse(value) { return value.split(\"\").reverse().join(\"\"); }");
        }
    }
}