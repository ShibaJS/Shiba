using System.Collections.Generic;
using Shiba.Controls;

namespace Shiba
{
    public interface ICommonProperty
    {
        string Name { get; }
        void Handle(object targetValue, object targetNativeView, object parentNativeView);
    }
}