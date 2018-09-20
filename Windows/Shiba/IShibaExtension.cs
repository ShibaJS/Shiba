using System;
using System.Threading.Tasks;
using Shiba.Controls;

namespace Shiba
{
    public interface IShibaExtensionExecutor
    {
        string Name { get; }
        object ProvideValue(IShibaContext context, ShibaExtension value);
    }

    public interface IMutableShibaExtensionExecutor : IShibaExtensionExecutor
    {
        
    }
    
    public interface IAsyncShibaExtensionExecutor : IShibaExtensionExecutor
    {
        Task<object> ProvideValueAsync(IShibaContext context, ShibaExtension value);
    }
}