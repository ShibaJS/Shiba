using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Shiba.Scripting.Runtime
{
    public class Console
    {
        [JsExport(Name = "log")]
        public void Log(string value)
        {
            Debug.WriteLine(value);
        }
    }
}
