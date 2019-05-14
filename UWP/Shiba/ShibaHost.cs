using Windows.UI.Xaml;
using Shiba.Controls;
using NativeParent = Windows.UI.Xaml.Controls.ContentControl;


namespace Shiba
{
    //[ContentProperty(Name = nameof(Layout))]
    public class ShibaHost : NativeParent, IShibaHost
    {
        public static readonly DependencyProperty ComponentProperty = DependencyProperty.Register(
            nameof(Component), typeof(string), typeof(ShibaHost),
            new PropertyMetadata(default, PropertyChangedCallback));

        public ShibaHost()
        {
            Context = new ShibaContext
            {
                ShibaHost = this
            };
        }

        public string Component
        {
            get => (string) GetValue(ComponentProperty);
            set => SetValue(ComponentProperty, value);
        }

        public IShibaContext Context { get; }

        private static void PropertyChangedCallback(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            if (e.Property == ComponentProperty)
            {
                (d as ShibaHost).OnComponentChanged(e.NewValue as string);
            }
        }

        private void OnComponentChanged(string newValue)
        {
            if (ShibaApp.Instance.Components.TryGetValue(newValue, out var component))
            {
                Content = NativeRenderer.Render(component, Context);
            }
        }
    }
}