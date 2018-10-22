using Shiba.Controls;

#if WINDOWS_UWP
using NativeBinding = Windows.UI.Xaml.Data.Binding;

#elif FORMS
using NativeBinding = Xamarin.Forms.Binding;

#elif WPF
using NativeBinding = System.Windows.Data.Binding;
#endif

namespace Shiba.ExtensionExecutors
{
    public interface IBindingExtensionExecutor : IExtensionExecutor
    {
        NativeBinding ProvideNativeBinding(IShibaContext context, ShibaExtension value);
    }

}