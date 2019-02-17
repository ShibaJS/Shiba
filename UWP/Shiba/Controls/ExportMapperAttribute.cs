using System;

namespace Shiba.Controls
{
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