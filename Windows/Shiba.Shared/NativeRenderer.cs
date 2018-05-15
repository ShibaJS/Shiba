using System;
using System.Collections.Concurrent;
using System.Linq;
using Shiba.Controls;
using Shiba.Parser;
#if WINDOWS_UWP
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;

#else
using System.Windows;
using System.Windows.Controls;
#endif

namespace Shiba
{
    internal static class NativeRenderer
    {
        private static readonly ShibaParserWrapper Parser = new ShibaParserWrapper();

        private static readonly ConcurrentDictionary<string, IViewRenderer> Renderer =
            new ConcurrentDictionary<string, IViewRenderer>();

        public static UIElement Render(string layout, object datacontext = null)
        {
            var viewTree = Parser.Parse(layout);
            return Render(viewTree, datacontext);
        }

        public static UIElement Render(View view, object datacontext = null)
        {
            var attribute = AbstractShiba.Instance.ViewMapping.Renderers.LastOrDefault(it => it.ViewName == view.ViewName);
            if (attribute == null)
            {
                return null;
            }

            var renderer = Renderer.GetOrAdd(attribute.ViewName, type => CreateRenderer(attribute));
            var target = renderer.Render(view, datacontext) as UIElement;
            if (view.Children.Any() && target is Panel panel)
            {
                view.Children.ForEach(it => panel.Children.Add(Render(it)));
            }

            return target;
        }

        private static IViewRenderer CreateRenderer(ExportRendererAttribute arg)
        {
            return Activator.CreateInstance(arg.RendererType) as IViewRenderer;
        }
    }
}