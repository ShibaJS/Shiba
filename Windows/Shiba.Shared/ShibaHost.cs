using System.Diagnostics;
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
    public class ShibaHost : NativeParent
    {
#if FORMS
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
#if FORMS
            Content = NativeRenderer.Render(value, BindingContext);
#else
            Content = NativeRenderer.Render(value, DataContext);
#endif
        }

        public void ReLayout()
        {
            OnLayoutChanged(Layout);
        }
    }
}