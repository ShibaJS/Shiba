package moe.tlaster.shiba

import android.animation.ObjectAnimator
import android.content.Context
import android.databinding.Observable
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import moe.tlaster.shiba.parser.*
import moe.tlaster.shiba.parser.Function
import java.lang.reflect.Method

data class PropertyMap(val name: String, val setter: (View, Any?) -> Unit)

open class ViewRenderer<T> : IViewRenderer where T : View {

    override fun renderer(view: moe.tlaster.shiba.View, dataContext: Any?, context: Context): View {
        val target = createView(context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }

        val contextBindings = Shiba.typeCache.getOrPut(dataContext?.javaClass, {
            dataContext
                    ?.javaClass
                    ?.declaredMethods
                    ?.filter { it.isAnnotationPresent(Binding::class.java) }
                    ?.map { it.getAnnotation(Binding::class.java).name to it }
                    ?.toMap()
        })

        val propertyChanged: (String) -> Unit = {

        }
        if (contextBindings != null && dataContext is INotifyPropertyChanged) {
            dataContext.propertyChanged = propertyChanged
        }
        view.properties.forEach { key, value ->
            propertyCache.findLast { it.name == key }
                    ?.let {
                        setValue(it, value, target, dataContext)
                    }
        }

        return target;
    }

    protected fun setValue(propertyMap: PropertyMap, token: IToken, target: View, dataContext: Any?) {
        if (token is NullToken) {
            return
        }
        val value = token.value ?: return
        when (value) {
            is NativeResource -> {
                propertyMap.setter.invoke(target, Shiba.shibaConfiguration.resourceValueResolver.getValue(value))
            }
            is Function -> {

            }
            is IBindingValue -> {

            }
            else -> propertyMap.setter.invoke(target, value)
        }
    }

    private var propertyCache: List<PropertyMap>

    init {
        propertyCache = properMaps()
    }


    protected open fun properMaps(): ArrayList<PropertyMap> {
        return arrayListOf(
                PropertyMap("visible", { view, it ->
                    if (it is Boolean) {
                        view.visibility = if (it) View.VISIBLE else View.GONE
                    }
                }),
                PropertyMap("enable", { view, it -> if (it is Boolean) view.isEnabled = it }),
                PropertyMap("width", { view, it -> if (it is Number) view.layoutParams.width = it.toInt() }),
                PropertyMap("height", { view, it -> if (it is Number) view.layoutParams.height = it.toInt() }),
                PropertyMap("margin", { view, it ->
                    if (it is Thickness && view.layoutParams is ViewGroup.MarginLayoutParams) {
                        (view.layoutParams as ViewGroup.MarginLayoutParams).setMargins(it.left.toInt(), it.top.toInt(), it.right.toInt(), it.bottom.toInt())
                    }
                }),
                PropertyMap("padding", { view, it -> if (it is Thickness) view.setPaddingRelative(it.left.toInt(), it.top.toInt(), it.right.toInt(), it.bottom.toInt()) }),
                PropertyMap("alpha", { view, it -> if (it is Number) view.alpha = it.toFloat() else if (it is Percent) view.alpha = it.value.toFloat() })
//                PropertyMap("name", {view, it -> if (it is String) })
//                PropertyMap("background", {view, it ->  })
        )
    }

    protected open fun createView(context: Context): View {
        return View(context)
    }
}

class InputRenderer : ViewRenderer<EditText>() {
    override fun createView(context: Context): View {
        return EditText(context)
    }

}

class StackRenderer : ViewRenderer<LinearLayout>() {
    override fun createView(context: Context): View {
        return LinearLayout(context).apply { orientation = LinearLayout.VERTICAL }
    }
}

class TextRenderer : ViewRenderer<TextView>() {
    override fun createView(context: Context): View {
        return TextView(context)
    }

    override fun properMaps(): ArrayList<PropertyMap> {
        return super.properMaps().apply {
            add(PropertyMap("text", { view, it -> if (it is String && view is TextView) view.text = it }))
        }
    }
}