using System;
using System.Collections.Generic;
using System.Text;
using Shiba.Controls;

namespace Shiba.Core
{
    public interface IViewRenderer
    {
        object Render(View view, object dataContext);
    }
}
