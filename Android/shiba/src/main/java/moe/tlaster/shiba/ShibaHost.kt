package moe.tlaster.shiba

import android.content.Context
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

fun <T : View> View.findView(predicate: (View) -> Boolean): T? {
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

class ShibaHost : FrameLayout, IShibaContext {

    override val propertyChangedSubscription: ArrayMap<NativeView, ArrayList<ISubscription>> = ArrayMap()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }


    var layout: String? = null

    var dataContext: Any? = null
        set(value) {
            handleDataContextChanged(field, value)
            field = value
        }


    public fun load(layout: String, dataContext: Any?) {
        if (layout != this.layout) {
            removeAllViews()
            this.layout = layout
            addView(NativeRenderer.render(layout, this))
        }
        this.dataContext = dataContext
    }

    override fun removeAllViews() {
        super.removeAllViews()
        propertyChangedSubscription.clear()
    }


    private fun handleDataContextChanged(oldValue: Any?, newValue: Any?) {
        if (oldValue is INotifyPropertyChanged) {
            oldValue.propertyChanged.clear()
        }

        if (newValue is INotifyPropertyChanged) {
            newValue.propertyChanged += { sender, name ->
                handlePropertyChanged(sender, name)
            }
            propertyChangedSubscription.forEach { view, sub ->
                sub.forEach {
                    it.isChanging = true
                    setValueToView(newValue, it, view)
                    it.isChanging = false
                }
            }
        }
    }

    private fun setValueToView(dataContext: Any?, sub: ISubscription, view: NativeView) {
        val value = Shiba.configuration.bindingValueResolver.getValue(dataContext, sub.binding.path)
        if (sub.binding.converter != null) {
            sub.setter.invoke(view, sub.binding.converter?.convert(value, sub.binding.parameter))
        } else {
            sub.setter.invoke(view, value)
        }
    }

    private fun handlePropertyChanged(sender: Any, name: String) {
        propertyChangedSubscription.forEach { view, subs ->
            subs.forEach {
                if (it.binding.path == name) {
                    setValueToView(sender, it, view)
                }
            }
        }
    }

    override fun twowayToDataContext(path: String, it: Any?) {
        if (dataContext != null) {
            Shiba.configuration.bindingValueResolver.setValue(dataContext, path, it)
        }
    }


    fun <T : View> findViewByName(name: String) : T? {
        return findView { it ->
            it.getTag(R.id.shiba_view_name_key) == name
        }
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
//        val typedArray = context?.theme?.obtainStyledAttributes(attrs, R.styleable.ShibaHost, 0, 0)
//        if (typedArray != null) {
//            layout = typedArray.getString(R.styleable.ShibaHost_layout)
//            typedArray.recycle()
//        }
    }

//    override fun onDetachedFromWindow() {
//        dataContext = null
//        layout = null
//        propertyChangedSubscription.clear()
//        super.onDetachedFromWindow()
//    }
}