using System;
using System.Collections.Generic;
using System.Text;
using Shiba.Controls;
using Shiba.Core;
using Shiba.Renderers;
#if WINDOWS_UWP
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml;
#else
using System.Windows.Controls;
using System.Windows;
#endif


[assembly: ExportRenderer(typeof(Text), typeof(TextRenderer))]

namespace Shiba.Renderers
{
    public class ViewRenderer<TView, TNativeView> : IViewRenderer 
        where TView: View
        where TNativeView: UIElement, new()
    {
        public object Render(View view)
        {
            if (view is TView tView)
            {
                var target = new TNativeView
                {
                    Opacity = view.Alpha,
                };
                if (target is Control control)
                {
                    control.IsEnabled = view.Enable;
                }
                Render(tView, ref target);
                return target;
            }

            return null;
        }

        protected virtual void Render(TView tView, ref TNativeView target)
        {

        }
    }

    public class TextRenderer : ViewRenderer<Text, TextBlock>
    {
        protected override void Render(Text tView, ref TextBlock target)
        {
            target.Text = tView.TextColor;
        }
    }
}
