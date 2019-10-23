using System;
using System.Collections.Generic;
using System.Linq;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Shiba.Controls;
using Shiba.ViewMappers;
using NativeView = Windows.UI.Xaml.Controls.Grid;

[assembly: ExportMapper("grid", typeof(GridMapper))]

namespace Shiba.ViewMappers
{
    public class GridMapper : AllowChildViewMapper<NativeView>
    {

        protected override IEnumerable<IValueMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetPropertyMaps());
        }

        private IEnumerable<IValueMap> GetPropertyMaps()
        {
            yield return new PropertyMap("columnSpacing", NativeView.ColumnSpacingProperty, typeof(double));
            yield return new PropertyMap("rowSpacing", NativeView.RowSpacingProperty, typeof(double));
            yield return new ManuallyValueMap("row", typeof(int), (element, o) =>
            {
                if (o is int value && element is NativeView target)
                    Enumerable.Range(0, Convert.ToInt32(value))
                        .Select(it => new RowDefinition())
                        .ToList()
                        .ForEach(it => target.RowDefinitions.Add(it));
            });
            yield return new ManuallyValueMap("column", typeof(int), (element, o) =>
            {
                if (o is int value && element is NativeView target)
                    Enumerable.Range(0, Convert.ToInt32(value))
                        .Select(it => new ColumnDefinition())
                        .ToList()
                        .ForEach(it => target.ColumnDefinitions.Add(it));
            });
        }
    }
}