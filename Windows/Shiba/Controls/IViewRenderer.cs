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
        new T Map(View view, IShibaContext context);
        T CreateNativeView();
    }
}