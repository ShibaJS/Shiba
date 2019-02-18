using Shiba.Controls;
using NativeBinding = Windows.UI.Xaml.Data.Binding;


namespace Shiba.ExtensionExecutors
{
    public interface IBindingExtensionExecutor : IExtensionExecutor
    {
        NativeBinding ProvideNativeBinding(IShibaContext context, ShibaExtension value);
    }
}