using Shiba.Controls;

namespace Shiba.ExtensionExecutors
{
    public interface IExtensionExecutor
    {
        string Name { get; }
        object ProvideValue(IShibaContext context, ShibaExtension value);
    }

    public interface IMutableExtensionExecutor : IExtensionExecutor
    {
    }
}