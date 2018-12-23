namespace Shiba.CommonProperty
{
    public interface ICommonProperty
    {
        string Name { get; }
        void Handle(object targetValue, object targetNativeView, object parentNativeView);
    }
}