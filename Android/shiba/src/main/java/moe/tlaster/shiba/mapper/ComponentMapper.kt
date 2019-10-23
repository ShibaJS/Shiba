package moe.tlaster.shiba.mapper

import moe.tlaster.shiba.IShibaContext
import moe.tlaster.shiba.ShibaHost
import moe.tlaster.shiba.type.ShibaExtension

class ComponentMapper : ViewMapper<ShibaHost>() {
    override fun createNativeView(context: IShibaContext): ShibaHost {
        return ShibaHost(context.getContext())
    }

    override fun convertSetValueType(propertyMap: PropertyMap, value: Any?, context: IShibaContext): Any? {
        return if (propertyMap.name != "dataContext") {
            super.convertSetValueType(propertyMap, value, context)
        } else {
            if (value is ShibaExtension) {
                super.convertSetValueType(propertyMap, value, context)
            } else {
                value
            }
        }
    }
    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("componentName", { view, value ->
                if (view is ShibaHost && value is String) {
                    view.component = value
                }
            }))
            add(PropertyMap("dataContext", { view, value ->
                if (view is ShibaHost) {
                    view.dataContext = value
                }
            }))
        }
    }
}