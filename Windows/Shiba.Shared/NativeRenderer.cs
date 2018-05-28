using System;
using System.Collections.Concurrent;
using System.Linq;
using Shiba.Controls;
using Shiba.Parser;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using NativeView = Windows.UI.Xaml.UIElement;
using NativeViewGroup = Windows.UI.Xaml.Controls.Panel;
#elif WPF
using System.Windows;
using System.Windows.Controls;
using NativeView = System.Windows.UIElement;
using NativeViewGroup = System.Windows.Controls.Panel;
#elif FORMS
using NativeView = Xamarin.Forms.View;
using NativeViewGroup = Xamarin.Forms.Layout<Xamarin.Forms.View>;
#endif

namespace Shiba
{
    internal static class NativeRenderer
    {
        private static readonly ShibaParserWrapper Parser = new ShibaParserWrapper();

        //TODO: Dictionary means it does not support multiple renderr for one view (like Xamarin.Forms custom renderer)
        private static readonly ConcurrentDictionary<string, IViewRenderer> Renderer =
            new ConcurrentDictionary<string, IViewRenderer>();

        public static NativeView Render(string layout, IShibaHost datacontext)
        {
            var viewTree = Parser.Parse(layout);
            return Render(viewTree, datacontext);
        }

        public static NativeView Render(View view, IShibaHost datacontext)
        {
            var attribute = AbstractShiba.Instance.ViewMapping.Renderers.LastOrDefault(it => it.ViewName == view.ViewName);
            if (attribute == null)
            {
                return null;
            }

            var renderer = Renderer.GetOrAdd(attribute.ViewName, type => CreateRenderer(attribute));
            var target = renderer.Render(view, datacontext) as NativeView;
            if (view.Children.Any() && target is NativeViewGroup panel)
            {
                view.Children.ForEach(it => panel.Children.Add(Render(it, datacontext)));
            }

            return target;
        }

        private static IViewRenderer CreateRenderer(ExportRendererAttribute arg)
        {
            return Activator.CreateInstance(arg.RendererType) as IViewRenderer;
        }
    }
}