package moe.tlaster.shiba.mapper

import android.content.res.Resources
import android.util.TypedValue
import android.view.ViewGroup
import moe.tlaster.shiba.IShibaContext
import moe.tlaster.shiba.NativeView
import moe.tlaster.shiba.R
import moe.tlaster.shiba.dataBinding.ShibaBinding
import moe.tlaster.shiba.type.ShibaObject
import moe.tlaster.shiba.type.View
import moe.tlaster.shiba.visitors.ValueVisitor

internal val Number.dp: Int
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()

interface IViewMapper<T : NativeView> {
    fun map(view: View, context: IShibaContext): T
    fun createNativeView(context: IShibaContext): T
}

interface IValueMap {
    val name: String
    val valueType: Class<*>?
    val setter: (NativeView, Any?) -> Unit
}

interface IEventMap {
    val name: String
    val setter: (NativeView, funcName: String, IShibaContext) -> Unit
}

data class EventMap(override val name: String, override val setter: (NativeView, funcName: String, IShibaContext) -> Unit) : IEventMap

internal interface IAllowChildViewMapper<T : NativeView> : IViewMapper<T> {
    fun addChild(parent: NativeView, child: NativeView)
}

open class PropertyMap(override val name: String, override val setter: (NativeView, Any?) -> Unit, override val valueType: Class<*>? = null) : IValueMap

class TwoWayPropertyMap(name: String, setter: (NativeView, Any?) -> Unit, val twowayInitializer: ((NativeView, (Any?) -> Unit) -> Unit)) : PropertyMap(name, setter)

abstract class ViewMapper<TNativeView : NativeView> : IViewMapper<TNativeView> {
    private val _propertyCache by lazy {
        propertyMaps()
    }
    private val _eventCache by lazy {
        eventMaps()
    }

    protected open val hasDefaultProperty = false
    protected open val defaultPropertyMap: PropertyMap? = null

    open fun getViewLayoutParams() : ViewGroup.LayoutParams {
        return ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun map(view: View, context: IShibaContext): TNativeView {

        val target = createNativeView(context).apply {
            layoutParams = getViewLayoutParams()
        }

        if (view.defaultValue != null && defaultPropertyMap != null && hasDefaultProperty) {
            setValue(context, view.defaultValue!!, defaultPropertyMap!!, target)
        }

        view.properties.forEach { property ->
            val cache = _propertyCache.lastOrNull { it.name == property.name }
            if (cache != null) {
                setValue(context, property.value, cache, target)
            } else {
                val eventCache = _eventCache.lastOrNull { it.name == property.name }
                if (eventCache != null && property.value is String) {
                    eventCache.setter.invoke(target, property.value, context)
                }
            }
        }

        return target
    }

    protected open fun setValue(context: IShibaContext, value: Any?, propertyMap: PropertyMap, target: TNativeView) {
        val targetValue = convertSetValueType(propertyMap, value, context)

        when (targetValue) {
            is ShibaBinding -> {
                targetValue.apply {
                    targetView = target
                    viewSetter = propertyMap.setter
                }
                if (propertyMap is TwoWayPropertyMap) {
                    propertyMap.twowayInitializer.invoke(target) {
                        targetValue.setValueToDataContext(it)
                    }
                }
                targetValue.setValueToView()
                context.bindings += targetValue
            }
            else -> {
                propertyMap.setter.invoke(target, targetValue)
            }
        }
    }

    protected open fun convertSetValueType(
        propertyMap: PropertyMap,
        value: Any?,
        context: IShibaContext
    ): Any? {
        return if (propertyMap.valueType != null && value != null && propertyMap.valueType == value.javaClass) {
            value
        } else {
            ValueVisitor.visit(value, context)
        }
    }

    abstract override fun createNativeView(context: IShibaContext): TNativeView

    protected open fun eventMaps(): List<IEventMap> {
        return listOf(
            EventMap("click") {view, funcName, context ->
                view.setOnClickListener {
                    context.eventCallback(funcName)
                }
            }
        )
    }
    protected open fun propertyMaps(): ArrayList<PropertyMap> {
        return arrayListOf(
                PropertyMap("visible", { view, it ->
                    if (it is Boolean) {
                        view.visibility = if (it) NativeView.VISIBLE else NativeView.GONE
                    }
                }),
                PropertyMap("enable", { view, it -> if (it is Boolean) view.isEnabled = it }),
                PropertyMap("width", { view, it ->
                    val param = view.layoutParams
                    if (it is Number) param.width = it.toInt().dp
                    else if (it is String) {
                        if (it.equals("fill", true)) param.width = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    view.layoutParams = param
                }),
                PropertyMap("height", { view, it ->
                    val param = view.layoutParams
                    if (it is Number) {
                        param.height = it.toInt().dp
                    } else if (it is String) {
                        if (it.equals("fill", true)) param.height = ViewGroup.LayoutParams.MATCH_PARENT
                    }
                    view.layoutParams = param
                }),
                PropertyMap("margin", { view, it ->
                    val lp = view.layoutParams
                    if (lp is ViewGroup.MarginLayoutParams) {
                        when (it) {
                            is ShibaObject -> {
                                lp.setMargins(
                                        it["left"]?.toString()?.toDouble()?.dp ?: 0,
                                        it["top"]?.toString()?.toDouble()?.dp ?: 0,
                                        it["right"]?.toString()?.toDouble()?.dp ?: 0,
                                        it["bottom"]?.toString()?.toDouble()?.dp ?: 0)
                            }
                            is Number -> {
                                lp.setMargins(
                                        it.toInt().dp,
                                        it.toInt().dp,
                                        it.toInt().dp,
                                        it.toInt().dp)
                            }
                        }
                    }
                }),
                PropertyMap("padding", { view, it ->
                    when (it) {
                        is ShibaObject -> {
                            view.setPaddingRelative(
                                    it["left"]?.toString()?.toDouble()?.dp ?: 0,
                                    it["top"]?.toString()?.toDouble()?.dp ?: 0,
                                    it["right"]?.toString()?.toDouble()?.dp ?: 0,
                                    it["bottom"]?.toString()?.toDouble()?.dp ?: 0)
                        }
                        is Number -> {
                            view.setPaddingRelative(
                                    it.toInt().dp,
                                    it.toInt().dp,
                                    it.toInt().dp,
                                    it.toInt().dp)
                        }
                    }
                }),
                PropertyMap("alpha", { view, it -> if (it is Number) view.alpha = it.toFloat() }),
                PropertyMap("name", { view, it -> if (it is String) view.setTag(R.id.shiba_view_name_key, it) }),
                PropertyMap("background", { view, it ->
                    when (it) {
                        is Int -> view.setBackgroundColor(it)
                    }
                })
        ).apply {
            if (defaultPropertyMap != null) {
                add(defaultPropertyMap!!)
            }
        }
    }
}