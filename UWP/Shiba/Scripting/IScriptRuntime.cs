using System.Collections;

namespace Shiba.Scripting
{
    public interface IScriptRuntime
    {
        void AddObject(string name, object value);
        bool HasFunction(string name);
        bool HasFunction(object instance, string name);
        object CallFunctionWithCustomThis(object thiz, string functionName, params object[] parameters);
        object CallFunction(string functionName, params object[] parameters);
        object CallFunction(object instance, string functionName, params object[] parameters);
        object GetProperty(object instance, string propertyName);
        object GetAtIndex(object instance, int index);
        void AddRef(object instance);
        void ReleaseObj(object instance);
        bool IsArray(object instance);
        IEnumerable ToArray(object instance);
        object Execute(string script);
    }
}