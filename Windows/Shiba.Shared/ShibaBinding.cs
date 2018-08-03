using System;
using System.Collections.Generic;
using System.Text;

namespace Shiba
{
    internal class ShibaBinding
    {
        public string Path { get; set; }

        public ShibaConverter Converter { get; set; }

        public object Parameter { get; set; }
    }

    internal class ShibaMultiBinding : ShibaBinding
    {
        public string[] Paths { get; set; }
    }
}
