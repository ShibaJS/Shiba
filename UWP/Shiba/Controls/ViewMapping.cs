using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Reflection;

namespace Shiba.Controls
{
    public class ViewMapping
    {
        public ReadOnlyCollection<ExportMapperAttribute> Mappers { get; private set; }

        public void Init()
        {
            var assemblies = AppDomain.CurrentDomain.GetAssemblies().ToList();
            Mappers = assemblies
                .Where(item => item.GetCustomAttributes<ExportMapperAttribute>()?.Any() == true)
                .SelectMany(item => item.GetCustomAttributes<ExportMapperAttribute>()).ToList().AsReadOnly();
        }
    }
}