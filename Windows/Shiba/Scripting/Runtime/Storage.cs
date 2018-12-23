#if WINDOWS_UWP
using Windows.Storage;

namespace Shiba.Scripting.Runtime
{
    public class Storage
    {
        [JsExport(Name = "load")]
        public object Load(string key, object defaultValue)
        {
            if (ApplicationData.Current.LocalSettings.Values.TryGetValue(key, out var obj))
                return obj;
            return defaultValue;
        }

        [JsExport(Name = "save")]
        public void Save(string key, object value)
        {
            ApplicationData.Current.LocalSettings.Values.Add(key, value);
        }
    }
}
#endif