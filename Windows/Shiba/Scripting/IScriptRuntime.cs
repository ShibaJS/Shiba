namespace Shiba.Scripting
{
    public interface IScriptRuntime
    {
        object Execute(string functionName, params object[] parameters);
        object Execute(string script);
        void AddObject(string name, object value);
    }
}