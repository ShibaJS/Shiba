namespace Shiba.Controls
{
    public interface IShibaHost
    {

    }
    public interface IViewRenderer
    {
        object Render(View view, IShibaHost rootHost);
    }
}