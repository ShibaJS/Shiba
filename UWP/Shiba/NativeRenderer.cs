using ChakraHosting;
using Shiba.Controls;
using Shiba.Internal;
using Shiba.Scripting.Visitors;
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

        public static NativeView RenderFromFunction(string name, IShibaContext context)
        {
            var result = ShibaApp.Instance.Configuration.ScriptRuntime.CallFunction(name);
            if (result is JavaScriptValue javaScriptValue)
                return Render(Singleton<JSViewVisitor>.Instance.Visit(javaScriptValue) as View, context);
            return null;
        }
    }
}