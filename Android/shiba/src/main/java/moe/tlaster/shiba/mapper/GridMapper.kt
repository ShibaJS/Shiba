package moe.tlaster.shiba.mapper

import androidx.gridlayout.widget.GridLayout
import moe.tlaster.shiba.IShibaContext

class GridMapper : ViewMapper<androidx.gridlayout.widget.GridLayout>()  {
    override fun createNativeView(context: IShibaContext): androidx.gridlayout.widget.GridLayout {
        return androidx.gridlayout.widget.GridLayout(context.getContext())
    }
    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("row", { view, value ->
                if (view is androidx.gridlayout.widget.GridLayout && value is Number) {
                    view.rowCount = value.toInt()
                }
            }))
            add(PropertyMap("column", { view, value ->
                if (view is androidx.gridlayout.widget.GridLayout && value is Number) {
                    view.columnCount = value.toInt()
                }
            }))
        }
    }
}