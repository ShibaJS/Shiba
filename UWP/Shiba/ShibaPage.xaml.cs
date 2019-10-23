using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using Shiba.Controls;

// The Blank Page item template is documented at https://go.microsoft.com/fwlink/?LinkId=234238

namespace Shiba
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public partial class ShibaPage : Page
    {
        public ShibaPage()
        {
            this.InitializeComponent();
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);
            switch (e.Parameter)
            {
                case null:
                {
                    if (ShibaApp.Instance.AppComponent == null)
                    {
                        // TODO: Init
                    }

                    Content = NativeRenderer.Render(ShibaApp.Instance.AppComponent, Context);
                    break;
                }

                case string name:// TODO: Url
                {
                    if (ShibaApp.Instance.Components.TryGetValue(name, out var component))
                    {
                        Content = NativeRenderer.Render(component, Context);
                    }
                }
                    break;
                case View component:
                {
                    Content = NativeRenderer.Render(component, Context);
                }
                    break;
            }
        }

        public IShibaContext Context { get; }
    }
}
