package moe.tlaster.shiba

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.content.res.TypedArray


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

    var layout: String? = null
        set(value) {
            field = value
            onLayoutChanged(value)
        }

    var dataContext: Any? = null

    private fun onLayoutChanged(value: String?) {
        removeAllViews()
        if (value == null) {
            return
        }
        load(value, null)
    }

    public fun load(layout: String, dataContext: Any?) {
        addView(NativeRenderer.render(layout, dataContext, context))
    }

    private fun init(context: Context?, attrs: AttributeSet?) {
//        val typedArray = context?.theme?.obtainStyledAttributes(attrs, R.styleable.ShibaHost, 0, 0)
//        if (typedArray != null) {
//            layout = typedArray.getString(R.styleable.ShibaHost_layout)
//            typedArray.recycle()
//        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        //TODO: clear INotifyPropertyChanged callback
    }
}