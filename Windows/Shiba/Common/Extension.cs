using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Shiba.Controls;
using Shiba.Controls.Common;

namespace Shiba.Common
{
    public static class Extension
    {
        public static T To<T>(this BasicValue value)
        {
            return value == null ? default : value.Value.To<T>();
        }

        public static Dictionary<string, object> ToDictionary(this ShibaMap shibaObject)
        {
            return shibaObject.Properties.Where(it => string.IsNullOrEmpty(it.Name.Prefix) ||
                                                      !string.IsNullOrEmpty(it.Name.Prefix) && it.Name.Prefix ==
                                                      AbstractShiba.Instance.Configuration.PlatformType)
                .ToDictionary(it => it.Name.Value, it => it.Value);
        }

        public static T GetValue<T>(this ShibaMap shibaObject, string name)
        {
            return shibaObject.Properties.FirstOrDefault(it => name == it.Name).GetValue<T>();
        }

        public static T GetValue<T>(this Property property)
        {
            if (property == null)
            {
                return default;
            }

            if (!string.IsNullOrEmpty(property.Name.Prefix) && property.Name.Prefix != AbstractShiba.Instance.Configuration.PlatformType)
            {
                return default;
            }

            if (property.Value is T result)
            {
                return result;
            }
            
            
            return default;
        }
    }
}
