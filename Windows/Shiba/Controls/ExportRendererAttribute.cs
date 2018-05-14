using System;

namespace Shiba.Controls
{
    //[AttributeUsage(AttributeTargets.Assembly, AllowMultiple = true)]
    //public sealed class ExportViewAttribute : Attribute
    //{
    //    public ExportViewAttribute(string viewName, Type viewType)
    //    {
    //        ViewName = viewName;
    //        ViewType = viewType;
    //    }

    //    public string ViewName { get; }
    //    public Type ViewType { get; }
    //}

    [AttributeUsage(AttributeTargets.Assembly, AllowMultiple = true)]
    public class ExportRendererAttribute : Attribute
    {
        public ExportRendererAttribute(string viewName, Type rendererType)
        {
            RendererType = rendererType;
            ViewName = viewName;
        }

        public string ViewName { get; }
        public Type RendererType { get; }
    }
}