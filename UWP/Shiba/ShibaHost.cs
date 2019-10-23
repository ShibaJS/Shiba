using Windows.ApplicationModel.Core;
using Windows.UI.Core;
using Windows.UI.Xaml;
using Shiba.Controls;
using NativeParent = Windows.UI.Xaml.Controls.ContentControl;


namespace Shiba
{
    //[ContentProperty(Name = nameof(Layout))]
    public class ShibaHost : NativeParent, IShibaContext
    {
        public static readonly DependencyProperty ComponentProperty = DependencyProperty.Register(
            nameof(Component), typeof(string), typeof(ShibaHost),
            new PropertyMetadata(default, PropertyChangedCallback));

        private string _creator;

        public string Component
        {
            get => (string) GetValue(ComponentProperty);
            set => SetValue(ComponentProperty, value);
        }

        public string Creator
        {
            get => _creator;
            set
            {
                _creator = value;
                OnCreatorChanged(value);
            }
        }

        public void EventCallback(string name)
        {
            if (ShibaApp.Instance.Configuration.ScriptRuntime.HasFunction(name))
            {
                ShibaApp.Instance.Configuration.ScriptRuntime.CallFunction(functionName: name, parameters: DataContext);
            }
            else if (DataContext != null &&
                     ShibaApp.Instance.Configuration.ScriptRuntime.HasFunction(DataContext, name))
            {
                ShibaApp.Instance.Configuration.ScriptRuntime.CallFunction(instance: DataContext, functionName: name, parameters: DataContext);
            }
        }

        private void OnCreatorChanged(string value)
        {
            Content = NativeRenderer.RenderFromFunction(value, this);
        }


        private static void PropertyChangedCallback(DependencyObject d, DependencyPropertyChangedEventArgs e)
        {
            if (e.Property == ComponentProperty) (d as ShibaHost).OnComponentChanged(e.NewValue as string);
        }

        private void OnComponentChanged(string newValue)
        {
            if (ShibaApp.Instance.Components.TryGetValue(newValue, out var component))
                Content = NativeRenderer.Render(component, this);
        }
    }
}