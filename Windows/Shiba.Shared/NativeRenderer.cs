using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Shiba.Controls;
using Shiba.Core;
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
        private static readonly ConcurrentDictionary<Type, IViewRenderer> Renderer = new ConcurrentDictionary<Type, IViewRenderer>();

        public static UIElement Render(string layout)
        {
            var viewTree = Parser.Parse(layout);
            return Render(viewTree);
        }

        public static UIElement Render(View view)
        {
            var attribute = ViewMapping.Instance.Renderers.LastOrDefault(it => it.ViewType == view.GetType());
            if (attribute == null)
            {
                return null;
            }

            var renderer = Renderer.GetOrAdd(attribute.RendererType, CreateRenderer);
            var target = renderer.Render(view) as UIElement;
            if (view is ViewGroup viewGroup && target is Panel panel)
            {
                viewGroup.Children.ForEach(it => panel.Children.Add(Render(it)));
            }

            return target;
        }

        private static IViewRenderer CreateRenderer(Type arg)
        {
            throw new NotImplementedException();
        }
    }
}
