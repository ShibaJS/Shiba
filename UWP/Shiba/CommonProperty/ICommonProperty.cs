using Shiba.Controls;

namespace Shiba.CommonProperty
{
    public interface ICommonProperty
    {
        string Name { get; }
        void Handle(object targetValue, View targetShibaView, object targetNativeView, object parentNativeView);
    }
}