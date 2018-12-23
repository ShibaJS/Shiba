namespace Shiba.Scripting
{
    public interface IScriptRuntime
    {
        void AddObject(string name, object value);
        object Execute(string functionName, params object[] parameters);
        object Execute(string script);
    }
}