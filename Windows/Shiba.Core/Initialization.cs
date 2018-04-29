using Shiba.Controls;
using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Diagnostics;
using System.Linq;
using System.Reflection;

namespace Shiba.Core
{
    public class Initialization
    {
        public static void Init()
        {
            ViewMapping.Instance.Init();
        }
    }
}