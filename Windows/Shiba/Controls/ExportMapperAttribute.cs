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
    public class ExportMapperAttribute : Attribute
    {
        public ExportMapperAttribute(string viewName, Type mapperType)
        {
            MapperType = mapperType;
            ViewName = viewName;
        }

        public string ViewName { get; }
        public Type MapperType { get; }
    }
}