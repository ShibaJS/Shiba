using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Shiba.Scripting.Runtime
{
    public class Http
    {
        [JsExport(Name = "get")]
        public async Task<string> Get(string url)
        {
            using (var client = new HttpClient())
            {
                return await client.GetStringAsync(url);
            }
        }
    }
}
