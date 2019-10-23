using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Shiba.Controls;

namespace Shiba.ViewMappers
{
    internal class ComponentMapper : ViewMapper<ShibaHost>
    {
        protected override ShibaHost Map(View view, IShibaContext context)
        {
            var target = CreateNativeView(context);

            foreach (var property in view.Properties)
            {
                var cache = _propertyCache.Value.LastOrDefault(it => it.Name == property.Name);
                if (cache != null)
                {
                    SetValue(context, property.Value, cache, target);
                }
            }

            return target;
        }

        protected override IEnumerable<IValueMap> ExtendPropertyMap()
        {
            yield return new ManuallyValueMap("componentName", typeof(string), (element, o) =>
            {
                if (element is ShibaHost shibaHost && o is string value)
                {
                    shibaHost.Component = value;
                }
            });
            yield return new PropertyMap("dataContext", ShibaHost.DataContextProperty, typeof(object), typeof(object));
        }
    }
}
