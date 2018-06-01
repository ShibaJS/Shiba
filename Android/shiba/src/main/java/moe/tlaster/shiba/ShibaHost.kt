package moe.tlaster.shiba

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.content.res.TypedArray
import android.util.ArrayMap
import android.util.Log
import android.view.View
import java.lang.reflect.Method


class ShibaHost : FrameLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    private val propertyChangedSubscription = ArrayMap<View, ArrayList<PropertyChangedSubscription>>()

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
            addView(NativeRenderer.render(layout, context, propertyChangedSubscription))
        }
        this.dataContext = dataContext
    }

    override fun removeAllViews() {
        super.removeAllViews()
        propertyChangedSubscription.clear()
    }


    private fun handleDataContextChanged(oldValue: Any?, newValue: Any?) {
        if (oldValue is INotifyPropertyChanged) {
            propertyChangedSubscription.forEach{ _, list ->
                list.forEach { sub ->
                    sub.dataContext = null
                }
            }
            oldValue.propertyChanged.clear()
        }
        if (newValue is INotifyPropertyChanged) {
            newValue.propertyChanged += { sender, name ->
                propertyChangedSubscription.forEach { view, list ->
                    handlePropertyChanged(sender, name, view, list)
            }}
            propertyChangedSubscription.forEach { view, list ->
                list.forEach { sub ->
                    sub.dataContext = newValue
                }
                list.filter { !it.isChanging }.forEach { sub ->
                    sub.isChanging = true
                    sub.setter.invoke(view, Shiba.configuration.bindingValueResolver.getValue(sub.dataContext, sub.name))
                    sub.isChanging = false
                }
            }
        }
    }

    private fun handlePropertyChanged(sender: Any, name: String, target: View, subscription: List<PropertyChangedSubscription>) {
        val subscriptions = subscription.filter { item -> item.name == name }
        if (subscriptions.any()) {
            subscriptions.filter { !it.isChanging }.forEach {
                it.isChanging = true
                it.setter.invoke(target, Shiba.configuration.bindingValueResolver.getValue(sender, name))
                it.isChanging = false
            }
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