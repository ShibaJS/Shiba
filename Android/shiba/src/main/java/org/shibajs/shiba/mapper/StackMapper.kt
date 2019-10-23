package org.shibajs.shiba.mapper

import android.widget.LinearLayout
import org.shibajs.shiba.IShibaContext
import org.shibajs.shiba.converters.ShibaConverter

class StackMapper : ViewMapper<LinearLayout>() {
    override fun createNativeView(context: IShibaContext): LinearLayout {
        return LinearLayout(context.getContext()).apply { orientation = LinearLayout.VERTICAL }
    }

    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("orientation", { view, value ->
                if (view is LinearLayout && value is String) {
                    view.orientation = when (value) {
                        "horizontal" -> LinearLayout.HORIZONTAL
                        else -> LinearLayout.VERTICAL
                    }
                }
            }))
        }
    }
}