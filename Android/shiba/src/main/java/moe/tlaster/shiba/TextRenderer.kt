package moe.tlaster.shiba

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import moe.tlaster.shiba.parser.*
import moe.tlaster.shiba.parser.Function
import java.lang.reflect.Method

class PropertyMap(val name: String, val setter: (View, Any?) -> Unit, val twoway: ((View, (Any?) -> Unit) -> Unit)? = null)

class PropertyChangedSubscription(val name: String, val setter: (View, Any?) -> Unit, var twowayCallback: ((Any?) -> Unit)? = null) {
    internal var isChanging = false
}

internal data class PropertyMethod(val getter: Method?, val setter: Method?)

internal class SingleParamterFunctionConverter : FunctionConverter() {
    override fun getValueFromDataContext(value: IBindingValue, dataContext: Any?): Any? {
        return dataContext
    }
}

internal open class FunctionConverter {
    public fun executeFunction(function: Function, dataContext: Any?) : Any? {
        return Shiba.configuration.converterExecutor.execute(function.name, function.paramter.map { getParameterValue(it, dataContext) })
    }

    private fun getParameterValue(paramter: IParamter, dataContext: Any?) : Any? {
        return when (paramter) {
            is Function -> executeFunction(paramter, dataContext)
            is ValueParamter -> {
                when (paramter.value.value) {
                    is IBindingValue -> getValueFromDataContext(paramter.value.value as IBindingValue, dataContext)
                    is Function -> executeFunction(paramter.value.value as Function, dataContext)
                    else -> paramter.value.value
                }
            }
            else -> null
        }
    }

    open fun getValueFromDataContext(value: IBindingValue, dataContext: Any?): Any? {
        return when (value) {
            is moe.tlaster.shiba.parser.Binding -> Shiba.configuration.bindingValueResolver.getValue(dataContext, value.getTokenValue())
            is JsonPath -> Shiba.configuration.jsonValueResolver.getValue(dataContext, value.getTokenValue())
            is NativeResource -> Shiba.configuration.resourceValueResolver.getValue(value.getTokenValue())
            else -> null
        }
    }
}

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
                    ?.groupBy { it.getAnnotation(Binding::class.java).name }
                    ?.map { method -> method.key to generatePropertyMethod(method) }
                    ?.toMap()
        })
        val propertyChangedSubscription = ArrayList<PropertyChangedSubscription>()
        view.properties.forEach { key, value ->
            propertyCache.findLast { it.name == key }
                    ?.let {
                        val subscription = setValue(it, value, target, dataContext)
                        if (subscription != null) {
                            propertyChangedSubscription += subscription
                        }
                    }
        }
        if (contextBindings != null && dataContext is INotifyPropertyChanged) {
            dataContext.propertyChanged += {sender: Any, name: String -> handlePropertyChanged(sender, name, target, propertyChangedSubscription) }
        }
        return target
    }

    private fun handlePropertyChanged(sender: Any, name: String, target: View, propertyChangedSubscription: List<PropertyChangedSubscription>) {
        val subscriptions = propertyChangedSubscription.filter { item -> item.name == name }
        val propertyMethod = Shiba.typeCache[sender.javaClass]?.get(name)
        if (propertyMethod?.getter != null && subscriptions.any()) {
            subscriptions.filter { !it.isChanging }.forEach {
                it.isChanging = true
                it.setter.invoke(target, propertyMethod.getter.invoke(sender))
                it.isChanging = false
            }
        }
    }

    private fun generatePropertyMethod(method: Map.Entry<String, List<Method>>): PropertyMethod {
        val getter = method.value.firstOrNull { !it.parameterTypes.any() }
        val setter = method.value.firstOrNull {
            it.parameterTypes.count { it == (getter?.returnType ?: it) } == 1
        }
        return PropertyMethod(getter, setter)
    }

    protected fun setValue(propertyMap: PropertyMap, token: IToken, target: View, dataContext: Any?): PropertyChangedSubscription? {
        if (token is NullToken) {
            return null
        }
        val value = token.value ?: return null
        when (value) {
            is Function -> {
                val bindings = getFunctionBindings(value)
                if (bindings.count() == 1) {
                    return PropertyChangedSubscription(bindings.first().getTokenValue(), {view, notifyValue ->
                        propertyMap.setter.invoke(view, Shiba.SingleParamterFunctionConverter.executeFunction(value, notifyValue))
                    })
                } else {
                    throw NotImplementedError()
                }
            }
            is IBindingValue -> {
                return getBinding(dataContext, value, target, propertyMap)
            }
            else -> propertyMap.setter.invoke(target, value)
        }
        return null
    }

    private fun getFunctionBindings(paramter: IParamter) : List<IBindingValue> {
        return when(paramter) {
            is Function -> {
                paramter.paramter.map { getFunctionBindings(it) }.flatten()
            }
            is ValueParamter -> {
                when (paramter.value.value) {
                    is IBindingValue -> listOf(paramter.value.value as IBindingValue)
                    is Function -> getFunctionBindings(paramter.value.value as Function)
                    else -> throw IllegalArgumentException()
                }
            }
            else -> throw IllegalArgumentException()
        }
    }

    private fun getBinding(dataContext: Any?, value: IBindingValue, target: View, propertyMap: PropertyMap): PropertyChangedSubscription? {
        when (value) {
            is moe.tlaster.shiba.parser.Binding -> {
                val targetValue = Shiba.configuration.bindingValueResolver.getValue(dataContext, value.getTokenValue())
                propertyMap.setter.invoke(target, targetValue)
                if (propertyMap.twoway != null && dataContext != null) {
                    return PropertyChangedSubscription(value.getTokenValue(), propertyMap.setter).apply {
                        twowayCallback = {
                            val propertyMethod = Shiba.typeCache[dataContext.javaClass]?.get(value.getTokenValue())
                            if (propertyMethod?.setter != null && !isChanging) {
                                isChanging = true
                                propertyMethod.setter.invoke(dataContext, it)
                                isChanging = false
                            }
                        }
                        propertyMap.twoway.invoke(target, twowayCallback!!)
                    }
                }
                return PropertyChangedSubscription(value.getTokenValue(), propertyMap.setter)
            }
            is JsonPath -> {
                propertyMap.setter.invoke(target, Shiba.configuration.jsonValueResolver.getValue(dataContext, value.getTokenValue()))
            }
            is NativeResource -> {
                propertyMap.setter.invoke(target, Shiba.configuration.resourceValueResolver.getValue(value.getTokenValue()))
            }
        }
        return null
    }

    private var propertyCache: List<PropertyMap> = propertyMaps()

    protected open fun propertyMaps(): ArrayList<PropertyMap> {
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


    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("text", { view, it -> if (it is CharSequence && view is EditText) view.setText(it) }, {view, callback ->
                if (view is EditText) {
                    view.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {
                        }

                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                        }

                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            callback.invoke(s?.toString())
                        }
                    })
                }
            }))
        }
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

    override fun propertyMaps(): ArrayList<PropertyMap> {
        return super.propertyMaps().apply {
            add(PropertyMap("text", { view, it -> if (it is CharSequence && view is TextView) view.text = it }))
        }
    }
}