package moe.tlaster.shiba.mapper

import android.graphics.Color
import android.widget.TextView
import moe.tlaster.shiba.IShibaContext

class TextMapper : ViewMapper<TextView>() {
    override var hasDefaultProperty = true
    override var defaultPropertyMap: PropertyMap? = PropertyMap("text", { view, it -> if (it is CharSequence && view is TextView) view.text = it })
    override fun createNativeView(context: IShibaContext): TextView {
        return TextView(context.getContext())
    }

    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("color", { view, it ->
                if (view is TextView && it is String) {
                    view.setTextColor(Color.parseColor(it))
                }
            }))
        }
    }
}