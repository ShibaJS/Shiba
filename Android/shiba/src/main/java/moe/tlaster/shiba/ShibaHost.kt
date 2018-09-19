package moe.tlaster.shiba

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.ArrayMap
import android.util.AttributeSet
import android.widget.FrameLayout
import android.view.View
import android.view.ViewGroup
import moe.tlaster.shiba.mapper.ISubscription

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

class ShibaHost : ConstraintLayout, IShibaContext, INotifyPropertyChanged {
    override var propertyChanged: Event<String> = Event()
    override val bindings: ArrayList<ShibaBinding> = ArrayList()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @get:Binding(name = "dataContext")
    @set:Binding(name = "dataContext")
    var dataContext: Any? = null
        set(value) {
            field = value
            propertyChanged.invoke(this, "dataContext")
        }


    public fun load(view: moe.tlaster.shiba.View?, dataContext: Any?) {
        if (view != null) {
            removeAllViews()
            addView(NativeRenderer.render(view, this))
        }
        this.dataContext = dataContext
    }

    public fun load(layout: String, dataContext: Any?) {
        removeAllViews()
        addView(NativeRenderer.render(layout, this))
        this.dataContext = dataContext
    }

    override fun removeAllViews() {
        super.removeAllViews()
        bindings.forEach { it.release() }
        bindings.clear()
    }

    fun <T : View> findViewByName(name: String) : T? {
        return findView { it ->
            it.getTag(R.id.shiba_view_name_key) == name
        }
    }
}