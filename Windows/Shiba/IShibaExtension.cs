using System;
using System.Threading.Tasks;
using Shiba.Controls;

namespace Shiba
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