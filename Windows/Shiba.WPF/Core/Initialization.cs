using System;
using System.Collections.Generic;
using System.Reflection;

namespace Shiba.Core
{
    public partial class Initialization
    {
        private IEnumerable<Assembly> GetAssemblies()
        {
            return AppDomain.CurrentDomain.GetAssemblies();
        }
    }
}