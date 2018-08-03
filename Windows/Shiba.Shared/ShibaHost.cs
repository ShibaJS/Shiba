using System.Diagnostics;
using Shiba.Controls;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using NativeParent = Windows.UI.Xaml.Controls.ContentControl;
#elif WPF
using System.Windows;
using System.Windows.Controls;
using NativeParent = System.Windows.Controls.ContentControl;
#elif FORMS
using Xamarin.Forms;
using NativeParent = Xamarin.Forms.ContentView;
#endif


namespace Shiba
{
    public class ShibaHost : NativeParent, IShibaHost
    {
#if FORMS
        public object DataContext => BindingContext;
        public static readonly BindableProperty LayoutProperty = BindableProperty.Create(
            nameof(Layout), typeof(string), typeof(ShibaHost), propertyChanged: OnLayoutChanged);

        private static void OnLayoutChanged(BindableObject bindable, object oldvalue, object newvalue)
        {
            (bindable as ShibaHost)?.OnLayoutChanged(newvalue as string);
        }
#else
        public static readonly DependencyProperty LayoutProperty = DependencyProperty.Register(
            nameof(Layout), typeof(string), typeof(ShibaHost), new PropertyMetadata(default(string), OnLayoutChanged));


        private static void OnLayoutChanged(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            (d as ShibaHost)?.OnLayoutChanged(e.NewValue as string);
        }
#endif

        public ShibaHost()
        {
            Context = new ShibaContext
            {
                ShibaHost = this
            };
        }

        public IShibaContext Context { get; }

        public string Layout
        {
            get => (string) GetValue(LayoutProperty);
            set => SetValue(LayoutProperty, value);
        }

        private void OnLayoutChanged(string value)
        {
            if (string.IsNullOrEmpty(value))
            {
                return;
            }
            Content = NativeRenderer.Render(value, Context);
        }

        public void ReLayout()
        {
            OnLayoutChanged(Layout);
        }
    }
}