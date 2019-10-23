using System;
using System.Collections.Generic;
using System.Linq;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Shiba.Controls;
using Shiba.ViewMappers;
using NativeView = Windows.UI.Xaml.Controls.StackPanel;

[assembly: ExportMapper("stack", typeof(StackMapper))]

namespace Shiba.ViewMappers
{
    public class StackMapper : AllowChildViewMapper<NativeView>
    {
        protected  override IEnumerable<IValueMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetProperties());
        }

        private IEnumerable<PropertyMap> GetProperties()
        {
            yield return new PropertyMap("orientation", NativeView.OrientationProperty, typeof(Orientation),
                OrientationConverter);
            yield return new PropertyMap("padding", NativeView.PaddingProperty, typeof(ShibaObject), typeof(Thickness),
                value =>
                {
                    Thickness thickness;
                    switch (value)
                    {
                        case ShibaObject shibaMap:
                            thickness = shibaMap.ToNativeThickness();
                            break;
                        default:
                            thickness = value.TryChangeType<int>(out var ivalue) ? new Thickness(ivalue, ivalue, ivalue, ivalue) : new Thickness();
                            break;
                    }

                    return thickness;
                });
        }

        private object OrientationConverter(object arg)
        {
            if (!(arg is string value)) throw new ArgumentException();

            if (Enum.TryParse(value, true, out Orientation result)) return result;

            throw new ArgumentOutOfRangeException();
        }
    }
}