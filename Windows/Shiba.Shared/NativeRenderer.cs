using Shiba.Controls;
using Shiba.Internal;
using Shiba.Parser;
using Shiba.Visitors;
#if WINDOWS_UWP
using NativeView = Windows.UI.Xaml.UIElement;
#elif WPF
using NativeView = System.Windows.UIElement;
#elif FORMS
using NativeView = Xamarin.Forms.View;
#endif

namespace Shiba
{
    internal static class NativeRenderer
    {
        public static NativeView Render(string layout, IShibaContext context)
        {
            var tree = Singleton<ShibaParserWrapper>.Instance.Parse(layout);
            return ShibaValueVisitor.GetValue(tree, context) as NativeView;
        }
        
    }
}