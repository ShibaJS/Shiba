using System;
using System.Collections.Generic;
using System.Reflection;

namespace Shiba.Core
{
    public partial class Device
    {
        public IEnumerable<Assembly> GetAssemblies()
        {
            return AppDomain.CurrentDomain.GetAssemblies();
        }
    }
}