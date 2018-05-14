namespace Shiba.Controls
{
    public interface IViewRenderer
    {
        object Render(View view, object dataContext);
    }
}