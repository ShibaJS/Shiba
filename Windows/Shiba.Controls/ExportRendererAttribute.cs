using System;

namespace Shiba.Controls
{
    [AttributeUsage(AttributeTargets.Assembly, AllowMultiple = true)]
    public sealed class ExportViewAttribute : Attribute
    {
        public ExportViewAttribute(string viewName, Type viewType)
        {
            ViewName = viewName;
            ViewType = viewType;
        }

        public string ViewName { get; }
        public Type ViewType { get; }
    }

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