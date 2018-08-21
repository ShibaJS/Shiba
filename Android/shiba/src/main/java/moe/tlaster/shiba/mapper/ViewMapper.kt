package moe.tlaster.shiba.mapper

import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.view.ViewGroup
import moe.tlaster.shiba.*
import moe.tlaster.shiba.visitors.ShibaValueVisitor
import org.mozilla.javascript.regexp.SubString

internal val Number.dp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()

interface IViewMapper<T : NativeView> {
    fun map(view: View, context: IShibaContext): T
    fun createNativeView(context: IShibaContext): T
}

interface IValueMap {
    val name: String
    val setter: (NativeView, Any?) -> Unit
}

interface ISubscription {
    var isChanging: Boolean
    val binding: ShibaBinding
    val setter: (NativeView, Any?) -> Unit
}

open class PropertyMap(override val name: String, override val setter: (NativeView, Any?) -> Unit) : IValueMap

class TwoWayPropertyMap(name: String, setter: (NativeView, Any?) -> Unit, val twowayInitializer: ((NativeView, (Any?) -> Unit) -> Unit)) : PropertyMap(name, setter)

class SingleSubscription(override val setter: (NativeView, Any?) -> Unit, override val binding: ShibaBinding) : ISubscription {
    override var isChanging: Boolean = false
}

class MultiSubscription(override val setter: (NativeView, Any?) -> Unit, override val binding: ShibaBinding) : ISubscription {
    override var isChanging: Boolean = false
}

class TwoWaySubscription(override val setter: (NativeView, Any?) -> Unit, override val binding: ShibaBinding) : ISubscription {
    override var isChanging: Boolean = false
}

open class ViewMapper<TNativeView : NativeView> : IViewMapper<TNativeView> {
    private val _propertyCache by lazy {
        propertyMaps()
    }

    protected open val hasDefaultProperty = false
    protected open val defaultPropertyMap: PropertyMap? = null

    override fun map(view: View, context: IShibaContext): TNativeView {
        val target = createNativeView(context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        val subscriptions = ArrayList<ISubscription>()

        fun setValue(context: IShibaContext, value: Any, propertyMap: PropertyMap, target: TNativeView) {
            val targetValue = ShibaValueVisitor.getValue(value, context)

            when (targetValue) {
                is ShibaMultiBinding -> {
                    if (propertyMap is TwoWayPropertyMap) {
                        Log.i("Shiba", "two way multi binding is not support")
                        return
                    }
                    subscriptions += MultiSubscription(propertyMap.setter, targetValue)
                }
                is ShibaBinding -> {
                    subscriptions += when (propertyMap) {
                        is TwoWayPropertyMap -> {
                            TwoWaySubscription(propertyMap.setter, targetValue).apply {
                                propertyMap.twowayInitializer.invoke(target) {
                                    if (!isChanging) {
                                        isChanging = true
                                        context.twowayToDataContext(binding.path, it)
                                        isChanging = false
                                    }
                                }
                            }
                        }
                        else -> {
                            SingleSubscription(propertyMap.setter, targetValue)
                        }
                    }
                }
                else -> {
                    propertyMap.setter.invoke(target, targetValue)
                }
            }
        }

        if (view.defaultValue != null && defaultPropertyMap != null && hasDefaultProperty) {
            setValue(context, view.defaultValue!!, defaultPropertyMap!!, target)
        }

        view.properties.forEach { property ->
            if (property.name.isCurrentPlatform()) {
                val cache = _propertyCache.firstOrNull { it.name == property.name.value }
                if (cache != null) {
                    setValue(context, property.value, cache, target)
                }
            }
        }

        context.propertyChangedSubscription[target] = subscriptions

        return target
    }


    open override fun createNativeView(context: IShibaContext): TNativeView {
        return NativeView(context.getContext()) as TNativeView
    }

    protected open fun propertyMaps(): ArrayList<PropertyMap> {
        return arrayListOf(
                PropertyMap("visible") { view, it ->
                    if (it is Boolean) {
                        view.visibility = if (it) NativeView.VISIBLE else NativeView.GONE
                    }
                },
                PropertyMap("enable") { view, it -> if (it is Boolean) view.isEnabled = it },
                PropertyMap("width") { view, it ->
                    val param = view.layoutParams
                    if (it is Number) param.width = it.toInt().dp
                    else if (it is String) {
                        if (it.equals("wrap_content", true)) param.width = ViewGroup.LayoutParams.WRAP_CONTENT
                        else if (it.equals("match_parent", true)) param.width = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    view.layoutParams = param
                },
                PropertyMap("height") { view, it ->
                    val param = view.layoutParams
                    if (it is Number) {
                        param.height = it.toInt().dp
                    } else if (it is String) {
                        if (it.equals("wrap_content", true)) param.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        else if (it.equals("match_parent", true)) param.height = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    view.layoutParams = param
                },
                PropertyMap("margin") { view, it ->
                    if (it is ShibaMap && view.layoutParams is ViewGroup.MarginLayoutParams) {
                        (view.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                                it.getValue<BasicValue>("left")?.value?.toString()?.toInt()?.dp
                                        ?: 0,
                                it.getValue<BasicValue>("top")?.value?.toString()?.toInt()?.dp ?: 0,
                                it.getValue<BasicValue>("right")?.value?.toString()?.toInt()?.dp
                                        ?: 0,
                                it.getValue<BasicValue>("bottom")?.value?.toString()?.toInt()?.dp
                                        ?: 0)
                    }
                },
                PropertyMap("padding") { view, it ->
                    if (it is ShibaMap) {
                        view.setPaddingRelative(
                                it.getValue<BasicValue>("left")?.value?.toString()?.toInt()?.dp
                                        ?: 0,
                                it.getValue<BasicValue>("top")?.value?.toString()?.toInt()?.dp ?: 0,
                                it.getValue<BasicValue>("right")?.value?.toString()?.toInt()?.dp
                                        ?: 0,
                                it.getValue<BasicValue>("bottom")?.value?.toString()?.toInt()?.dp
                                        ?: 0)
                    }
                },
                PropertyMap("alpha") { view, it -> if (it is Number) view.alpha = it.toFloat() },
                PropertyMap("name") { view, it -> if (it is String) view.setTag(R.id.shiba_view_name_key, it) },
                PropertyMap("background") { view, it -> }
        ).apply {
            if (defaultPropertyMap != null) {
                add(defaultPropertyMap!!)
            }
        }
    }
}