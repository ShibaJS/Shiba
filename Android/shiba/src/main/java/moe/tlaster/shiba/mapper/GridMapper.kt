package moe.tlaster.shiba.mapper

import android.support.v7.widget.GridLayout
import moe.tlaster.shiba.IShibaContext

class GridMapper : ViewMapper<GridLayout>()  {
    override fun createNativeView(context: IShibaContext): GridLayout {
        return GridLayout(context.getContext())
    }
    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("row", { view, value ->
                if (view is GridLayout && value is Number) {
                    view.rowCount = value.toInt()
                }
            }))
            add(PropertyMap("column", { view, value ->
                if (view is GridLayout && value is Number) {
                    view.columnCount = value.toInt()
                }
            }))
        }
    }
}