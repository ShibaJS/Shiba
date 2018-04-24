using System;

namespace Shiba.Core
{
    [AttributeUsage(AttributeTargets.Assembly, AllowMultiple = true)]
    public class ExportRendererAttribute : Attribute
    {
        public ExportRendererAttribute(string layoutName, Type rendererType)
        {
            LayoutName = layoutName;
            RendererType = rendererType;
        }
        
        public string LayoutName { get; }
        public Type RendererType { get; }
    }
}