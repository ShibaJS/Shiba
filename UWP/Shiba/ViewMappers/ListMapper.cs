using System.Collections;
using System.Collections.Generic;
using System.Linq;
using Windows.UI.Xaml.Controls;
using Shiba.Controls;
using Shiba.ViewMappers;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeView = Windows.UI.Xaml.Controls.ListView;

[assembly: ExportMapper("list", typeof(ListMapper))]

namespace Shiba.ViewMappers
{
    public class ListMapper : ViewMapper<NativeView>
    {
        protected  override IEnumerable<IValueMap> PropertyMaps()
        {
            return base.PropertyMaps().Concat(GetProperties());
        }

        private IEnumerable<IValueMap> GetProperties()
        {
            yield return new ManuallyValueMap("itemLayout", typeof(View), (element, value) =>
            {
                if (element is NativeView nativeView && value is View itemLayout)
                {
                    var template = itemLayout.ToDataTemplate();
                    nativeView.SetValue(ItemsControl.ItemTemplateProperty, template);
                }
            });
            yield return new ManuallyValueMap("items", typeof(IEnumerable), (element, value) =>
            {
                if (element is NativeView nativeView)
                {
                    if (value is NativeBinding binding)
                        nativeView.SetBinding(ItemsControl.ItemsSourceProperty, binding);
                    else
                        nativeView.SetBinding(ItemsControl.ItemsSourceProperty, new NativeBinding
                        {
                            Source = value
                        });
                }
            });
        }
    }
}