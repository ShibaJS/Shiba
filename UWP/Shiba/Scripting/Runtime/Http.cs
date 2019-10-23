using System.Net.Http;
using System.Threading.Tasks;

namespace Shiba.Scripting.Runtime
{
    public class Http
    {
        [JsExport(Name = "get")]
        public async Task<string> Get(string url, string cookie)
        {
            using (var client = new HttpClient())
            {
                client.DefaultRequestHeaders.Add("Cookie", cookie);
                return await client.GetStringAsync(url);
            }
        }
    }
}