using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Shiba.Controls;
using Shiba.ViewMappers;
#if WINDOWS_UWP
using Windows.UI.Xaml.Controls;
using NativeView = Windows.UI.Xaml.Controls.Grid;
#elif WPF
using System.Windows.Controls;
using NativeView = System.Windows.Controls.Grid;
#elif FORMS
using Xamarin.Forms;
using NativeView = Xamarin.Forms.Grid;
#endif

[assembly: ExportMapper("grid", typeof(GridMapper))]
namespace Shiba.ViewMappers
{
    public class GridMapper : ViewMapper<NativeView>
    {
        public override IEnumerable<IValueMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetPropertyMaps());
        }

        private IEnumerable<IValueMap> GetPropertyMaps()
        {
#if WINDOWS_UWP
            yield return new PropertyMap("columnSpacing", Grid.ColumnSpacingProperty, typeof(double));
            yield return new PropertyMap("rowSpacing", Grid.RowSpacingProperty, typeof(double));
#elif WPF
            yield return new PropertyMap("showGridLines", Grid.ShowGridLinesProperty, typeof(bool));
#endif
            yield return new ManuallyValueMap("row", typeof(int), (element, o) =>
            {
                if (o is int value && element is NativeView target)
                {
                    Enumerable.Range(0, Convert.ToInt32(value))
                        .Select(it => new RowDefinition())
                        .ToList()
                        .ForEach(it => target.RowDefinitions.Add(it));
                }
            });
            yield return new ManuallyValueMap("colunm", typeof(int), (element, o) =>
            {
                if (o is int value && element is NativeView target)
                {
                    Enumerable.Range(0, Convert.ToInt32(value))
                        .Select(it => new ColumnDefinition())
                        .ToList()
                        .ForEach(it => target.ColumnDefinitions.Add(it));
                }
            });
        }
    }
}
