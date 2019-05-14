using Shiba.Controls;
using Shiba.Internal;
using Shiba.Visitors;
using NativeView = Windows.UI.Xaml.UIElement;

namespace Shiba
{
    internal static class NativeRenderer
    {
        public static NativeView Render(View view, IShibaContext context)
        {
            return Singleton<ValueVisitor>.Instance.DynamicVisit(view, context) as NativeView;
        }
    }
}