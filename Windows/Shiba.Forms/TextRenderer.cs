using Shiba.Controls;
using Xamarin.Forms;
using NativeView = Xamarin.Forms.View;
using View = Shiba.Controls.View;

namespace Shiba.Renderers
{
    
    public class ViewRenderer<T> : IViewRenderer 
        where T : NativeView, new()
    {
        
        public object Render(View view, IShibaHost rootHost)
        {
            throw new System.NotImplementedException();
        }
    }

    public class TextRenderer : ViewRenderer<Label>
    {
        
    }
}