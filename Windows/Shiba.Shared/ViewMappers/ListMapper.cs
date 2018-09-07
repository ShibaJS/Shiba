using System.Collections.Generic;
using System.Linq;
using Shiba.Controls;
using Shiba.ViewMappers;
using View = Shiba.Controls.View;
using System.Collections;
#if WINDOWS_UWP
using Windows.UI.Xaml.Markup;
using Windows.UI.Xaml;
using NativeBinding = Windows.UI.Xaml.Data.Binding;
using NativeView = Windows.UI.Xaml.Controls.ListView;
#elif WPF
using System.Windows;
using NativeBinding = System.Windows.Data.Binding;
using NativeView = System.Windows.Controls.ListView;
#elif FORMS
using Xamarin.Forms;
using NativeBinding = Xamarin.Forms.Binding;
using NativeView = Xamarin.Forms.ListView;
#endif

[assembly: ExportMapper("list", typeof(ListMapper))]
namespace Shiba.ViewMappers
{
    public class ListMapper : ViewMapper<NativeView>
    {

#if FORMS
        public override NativeView CreateNativeView()
        {
            return new NativeView
            {
                HasUnevenRows = true
            };
        }
#endif
        
        public override IEnumerable<IValueMap> PropertyMaps()
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
                    nativeView.SetValue(NativeView.ItemTemplateProperty, template);
                }
            });
            yield return new ManuallyValueMap("items", typeof(IEnumerable), (element, value) =>
            {
                if (element is NativeView nativeView && value is IEnumerable list)
                {
                    nativeView.SetBinding(NativeView.ItemsSourceProperty, new NativeBinding
                    {
                        Source = list
                    });
                }
            });
        }
    }
}