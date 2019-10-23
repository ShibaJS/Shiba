package org.shibajs.shiba

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import org.shibajs.shiba.dataBinding.ShibaBinding

fun <T : View> View.findName(name: String): T? {
    return findView { it ->
        it.getTag(R.id.shiba_view_name_key) == name
    }
}

private fun <T : View> View.findView(predicate: (View) -> Boolean): T? {
    if (predicate.invoke(this)) {
        return this as T
    }

    if (this is ViewGroup) {
        (0 until childCount).forEach {
            val child = getChildAt(it).findView<T>(predicate)
            if (child != null) {
                return child
            }
        }
    }

    return null

}

class ShibaHost : FrameLayout, IShibaContext, INotifyPropertyChanged {
    override var propertyChanged: Event<String> = Event()
    override val bindings: ArrayList<ShibaBinding> = ArrayList()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @get:Binding(name = "dataContext")
    @set:Binding(name = "dataContext")
    override var dataContext: Any? = null
        set(value) {
            field = value
            propertyChanged.invoke(this, "dataContext")
            onDataContextChanged(value)
        }

    private fun onDataContextChanged(value: Any?) {
        if (Shiba.configuration.scriptRuntime.isObject(value) && value != null) {
            Shiba.configuration.scriptRuntime.injectFunction<String>(value, "onPropertyChanged") { name ->
                bindings.filter { it.actualPath == name }.forEach {
                    it.setValueToView()
                }
            }
        }
    }

    var component: String? = null
        set(value) {
            field = value
            removeAllViews()
            if (Shiba.components.containsKey(field)) {
                viewComponent = Shiba.components[field]
            }
        }

    var creator: String? = null
        set(value) {
            field = value
            if (value != null) {
                addView(NativeRenderer.renderFromFunction(value, this))
            }
        }

    internal var viewComponent: ShibaView? = null
        set(value) {
            field = value
            addView(NativeRenderer.render(field, this))
        }

    override fun eventCallback(name: String) {
        if (Shiba.configuration.scriptRuntime.hasFunction(name)) {
            Shiba.configuration.scriptRuntime.callFunction(name, dataContext)
        } else if (dataContext != null && Shiba.configuration.scriptRuntime.hasFunction(dataContext, name)) {
            Shiba.configuration.scriptRuntime.callFunction(dataContext, name, dataContext)
        }
    }
//
//    public fun load(view: org.shibajs.shiba.type.View?, dataContext: Any?) {
//        if (view != null) {
//            removeAllViews()
//            addView(NativeRenderer.render(view, this))
//        }
//        this.dataContext = dataContext
//    }
//
//    public fun load(layout: String, dataContext: Any?) {
//        removeAllViews()
//        addView(NativeRenderer.render(layout, this))
//        this.dataContext = dataContext
//    }

    override fun removeAllViews() {
        super.removeAllViews()
        bindings.forEach { it.release() }
        bindings.clear()
    }

    fun <T : View> findViewByName(name: String): T? {
        return findView { it ->
            it.getTag(R.id.shiba_view_name_key) == name
        }
    }
}