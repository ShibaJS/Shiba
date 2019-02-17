using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

[assembly: XamlCompilation(XamlCompilationOptions.Compile)]
namespace Shiba.Forms.Sample
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();
            ShibaApp.Init();
            AbstractShiba.Instance.AddConverter("function awesome(value) { return value + \" is awesome!\" }");
            AbstractShiba.Instance.AddConverter("function reverse(value) { return value.split(\"\").reverse().join(\"\"); }");
            MainPage = new MainPage();
        }

        protected override void OnStart()
        {
            // Handle when your app starts
        }

        protected override void OnSleep()
        {
            // Handle when your app sleeps
        }

        protected override void OnResume()
        {
            // Handle when your app resumes
        }
    }
}
