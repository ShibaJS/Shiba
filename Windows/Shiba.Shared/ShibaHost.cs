#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;

#else
using System.Windows;
using System.Windows.Controls;
#endif


namespace Shiba
{
    public class ShibaHost : ContentPresenter
    {
        public static readonly DependencyProperty LayoutProperty = DependencyProperty.Register(
            nameof(Layout), typeof(string), typeof(ShibaHost), new PropertyMetadata(default(string), OnLayoutChanged));

        public string Layout
        {
            get => (string) GetValue(LayoutProperty);
            set => SetValue(LayoutProperty, value);
        }

        private static void OnLayoutChanged(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            (d as ShibaHost)?.OnLayoutChanged(e.NewValue as string);
        }

        private void OnLayoutChanged(string value)
        {
            if (string.IsNullOrEmpty(value))
            {
                return;
            }

            Content = NativeRenderer.Render(value, DataContext);
        }
    }
}