package moe.tlaster.shiba

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import moe.tlaster.shiba.dataBinding.ShibaBinding

fun <T : View> View.findName(name: String) : T? {
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
    internal var hostBinding: ShibaBinding? = null
        set(value) {
            field?.release()
            value?.targetView = this
            value?.viewSetter = { view, any ->
                if (view is ShibaHost) {
                    view.dataContext = any
                }
            }
            field = value
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @get:Binding(name = "dataContext")
    @set:Binding(name = "dataContext")
    override var dataContext: Any? = null
        set(value) {
            field = value
            propertyChanged.invoke(this, "dataContext")
        }

    var component: String? = null
        set(value) {
            field = value
            removeAllViews()
            if (Shiba.components.containsKey(field)) {
                val component = Shiba.components[field]
                addView(NativeRenderer.render(component, this))
            }
        }



//
//    public fun load(view: moe.tlaster.shiba.type.View?, dataContext: Any?) {
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

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        // TODO: Release all resources
    }

    fun <T : View> findViewByName(name: String) : T? {
        return findView { it ->
            it.getTag(R.id.shiba_view_name_key) == name
        }
    }
}