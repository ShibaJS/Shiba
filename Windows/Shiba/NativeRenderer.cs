using Shiba.Controls;
using Shiba.Internal;
using Shiba.Parser;
using Shiba.Visitors;
using NativeView = Windows.UI.Xaml.UIElement;

namespace Shiba
{
    internal static class NativeRenderer
    {
        public static View Parse(string layout)
        {
            return Singleton<ShibaParserWrapper>.Instance.Parse(layout) as View;
        }

        public static NativeView Render(string layout, IShibaContext context)
        {
            var tree = Parse(layout);
            return ShibaValueVisitor.GetValue(tree, context) as NativeView;
        }

        public static NativeView Render(View view, IShibaContext context)
        {
            return ShibaValueVisitor.GetValue(view, context) as NativeView;
        }
    }
}