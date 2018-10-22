using System;
using Shiba.Controls;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using NativeParent = Windows.UI.Xaml.Controls.ContentControl;
using Windows.UI.Xaml.Markup;
#elif WPF
using System.Windows;
using System.Windows.Controls;
using System.Windows.Markup;
using NativeParent = System.Windows.Controls.ContentControl;
#elif FORMS
using Xamarin.Forms;
using NativeParent = Xamarin.Forms.ContentView;
using View = Shiba.Controls.View;
#endif


namespace Shiba
{
#if WINDOWS_UWP
    [ContentProperty(Name = nameof(Layout))]
#elif WPF || FORMS
    [ContentProperty(nameof(Layout))]
#endif
    public class ShibaHost : NativeParent, IShibaHost
    {
#if FORMS
        public object DataContext => BindingContext;
        
        public static readonly BindableProperty ShibaLayoutProperty = BindableProperty.Create(
            nameof(ShibaLayout), typeof(View), typeof(ShibaHost), propertyChanged: OnShibaLayoutChanged);

        private static void OnShibaLayoutChanged(BindableObject bindable, object oldValue, object newValue)
        {
            (bindable as ShibaHost)?.OnShibaLayoutChanged(newValue as View);
        }

        public static readonly BindableProperty LayoutProperty = BindableProperty.Create(
            nameof(Layout), typeof(string), typeof(ShibaHost), propertyChanged: OnLayoutChanged);

        private static void OnLayoutChanged(BindableObject bindable, object oldvalue, object newvalue)
        {
            (bindable as ShibaHost)?.OnLayoutChanged(newvalue as string);
        }
#else
        public static readonly DependencyProperty ShibaLayoutProperty = DependencyProperty.Register(
            nameof(ShibaLayout), typeof(View), typeof(ShibaHost), new PropertyMetadata(default(View), OnShibaLayoutChanged));

        public static readonly DependencyProperty LayoutProperty = DependencyProperty.Register(
            nameof(Layout), typeof(string), typeof(ShibaHost), new PropertyMetadata(default(string), OnLayoutChanged));

        private static void OnShibaLayoutChanged(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            (d as ShibaHost)?.OnShibaLayoutChanged(e.NewValue as View);
        }

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

        public View ShibaLayout
        {
            get => (View) GetValue(ShibaLayoutProperty);
            set => SetValue(ShibaLayoutProperty, value);
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

        private void OnLayoutChanged(string value)
        {
            if (string.IsNullOrEmpty(value))
            {
                return;
            }
            ShibaLayout = NativeRenderer.Parse(value);
        }
        
        public void ReLayout()
        {
            OnLayoutChanged(Layout);
        }
    }
}