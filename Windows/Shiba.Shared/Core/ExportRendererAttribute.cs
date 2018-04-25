using System;

namespace Shiba.Core
{
    [AttributeUsage(AttributeTargets.Assembly, AllowMultiple = true)]
    public class ExportRendererAttribute : Attribute
    {
        public ExportRendererAttribute(Type viewType, Type rendererType)
        {
            ViewType = viewType;
            RendererType = rendererType;
        }

        public Type ViewType { get; }
        public Type RendererType { get; }
    }
}