using Windows.UI.Xaml;
using Windows.UI.Xaml.Markup;
using Shiba.Controls;
using NativeParent = Windows.UI.Xaml.Controls.ContentControl;


namespace Shiba
{
    [ContentProperty(Name = nameof(Layout))]
    public class ShibaHost : NativeParent, IShibaHost
    {
        public static readonly DependencyProperty ShibaLayoutProperty = DependencyProperty.Register(
            nameof(ShibaLayout), typeof(View), typeof(ShibaHost),
            new PropertyMetadata(default(View), OnShibaLayoutChanged));

        public static readonly DependencyProperty LayoutProperty = DependencyProperty.Register(
            nameof(Layout), typeof(string), typeof(ShibaHost), new PropertyMetadata(default(string), OnLayoutChanged));

        public ShibaHost()
        {
            Context = new ShibaContext
            {
                ShibaHost = this
            };
        }

        public string Layout
        {
            get => (string) GetValue(LayoutProperty);
            set => SetValue(LayoutProperty, value);
        }

        public View ShibaLayout
        {
            get => (View) GetValue(ShibaLayoutProperty);
            set => SetValue(ShibaLayoutProperty, value);
        }

        public IShibaContext Context { get; }

        public void ReLayout()
        {
            OnLayoutChanged(Layout);
        }

        private static void OnLayoutChanged(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            (d as ShibaHost)?.OnLayoutChanged(e.NewValue as string);
        }

        private void OnLayoutChanged(string value)
        {
            if (string.IsNullOrEmpty(value)) return;
            ShibaLayout = NativeRenderer.Parse(value);
        }

        private static void OnShibaLayoutChanged(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            (d as ShibaHost)?.OnShibaLayoutChanged(e.NewValue as View);
        }

        private void OnShibaLayoutChanged(View value)
        {
            if (value == null)
            {
                Content = null;
                return;
            }

            Content = NativeRenderer.Render(value, Context);
        }
    }
}