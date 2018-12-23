namespace Shiba.Controls
{
    public interface IShibaContext
    {
        IShibaHost ShibaHost { get; }
    }

    public interface IShibaHost
    {
        object DataContext { get; }
        IShibaContext Context { get; }
    }

    public class ShibaContext : IShibaContext
    {
        public IShibaHost ShibaHost { get; set; }
    }

    public interface IViewMapper
    {
        object Map(View view, IShibaContext context);
    }

    public interface IViewMapper<T> : IViewMapper
    {
        T CreateNativeView();
        new T Map(View view, IShibaContext context);
    }
}