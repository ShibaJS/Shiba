//package moe.tlaster.shiba
//
//import android.content.Context
//import android.content.res.Resources
//import android.support.v7.widget.GridLayout
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.TypedValue
//import android.view.View
//import android.view.ViewGroup
//import android.widget.EditText
//import android.widget.LinearLayout
//import android.widget.TextView
//import moe.tlaster.shiba.parser.Function
//import java.lang.reflect.Method
//
//internal val Number.dp: Int
//    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()
//
//class PropertyMap(val name: String, val setter: (View, Any?) -> Unit, val twoway: ((View, (Any?) -> Unit) -> Unit)? = null)
//
//class PropertyChangedSubscription(val name: String, val setter: (View, Any?) -> Unit, var twowayCallback: ((Any?) -> Unit)? = null) {
//    internal var isChanging = false
//    internal var dataContext: Any? = null
//}
//
//internal data class PropertyMethod(val getter: Method?, val setter: Method?)
//
//internal class SingleParameterFunctionConverter : FunctionConverter() {
//    override fun getValueFromDataContext(value: IBindingValue, dataContext: Any?): Any? {
//        return dataContext
//    }
//}
//
//internal open class FunctionConverter {
//    public fun executeFunction(function: ShibaFunction, dataContext: Any?): Any? {
//        return Shiba.configuration.converterExecutor.execute(function.name, function.parameter.map { getParameterValue(it, dataContext) }.toTypedArray())
//    }
//
//    private fun getParameterValue(parameter: IParameter, dataContext: Any?): Any? {
//        return when (parameter) {
//            is ShibaFunction -> executeFunction(parameter, dataContext)
//            is ValueParameter -> {
//                when (parameter.value.value) {
//                    is IBindingValue -> getValueFromDataContext(parameter.value.value as IBindingValue, dataContext)
//                    is ShibaFunction -> executeFunction(parameter.value.value as ShibaFunction, dataContext)
//                    else -> parameter.value.value
//                }
//            }
//            else -> null
//        }
//    }
//
//    open fun getValueFromDataContext(value: IBindingValue, dataContext: Any?): Any? {
//        return when (value) {
//            is moe.tlaster.shiba.parser.Binding -> Shiba.configuration.bindingValueResolver.getValue(dataContext, value.getTokenValue())
//            is JsonPath -> Shiba.configuration.jsonValueResolver.getValue(dataContext, value.getTokenValue())
//            is NativeResource -> Shiba.configuration.resourceValueResolver.getValue(value.getTokenValue())
//            else -> null
//        }
//    }
//}
//
//open class ViewMapper<T> : IViewMapper where T : View {
//
//    override fun map(view: moe.tlaster.shiba.View, shibaContext: IShibaContext): View {
//        val target = createView(shibaContext.context).apply {
//            layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        }
//
//        if (propertyCache == null) {
//            propertyCache = propertyMaps()
//        }
//        val subscriptions = ArrayList<PropertyChangedSubscription>()
//        if (view.defaultValue != null && hasDefaultProperty) {
//            if (defaultPropertyMap != null) {
//                val subscription = setValue(defaultPropertyMap!!, view.defaultValue!!, target)
//                if (subscription != null) {
//                    subscriptions += subscription
//                }
//            }
//        } else {
//            view.properties?.forEach { key, value ->
//                val property = propertyCache!!.findLast { it.name == key }
//                if (property != null) {
//                    val subscription = setValue(property, value, target)
//                    if (subscription != null) {
//                        subscriptions += subscription
//                    }
//                } else if (key.contains(".")) {
//                    attachedPropertyHandler(key, value, view, target, shibaContext)
//                } else {
//
//                }
//            }
//        }
//        shibaContext.propertyChangedSubscription[target] = subscriptions
////        if (contextBindings != null && dataContext is INotifyPropertyChanged) {
////            dataContext.propertyChanged += { sender: Any, name: String -> handlePropertyChanged(sender, name, target, propertyChangedSubscription) }
////        }
//
//        return target
//    }
//
//    private fun attachedPropertyHandler(key: String, value: IToken, view: moe.tlaster.shiba.View, target: View, shibaContext: IShibaContext) {
//        val tokens = key.split(".")
//        if (tokens.count() < 2) {
//            return
//        }
//
//        when (tokens.firstOrNull()) {
//            "grid" -> {
//                if (value !is NumberToken) {
//                    return
//                }
//
//                val layoutParams = target.layoutParams
//                if (layoutParams is GridLayout.LayoutParams) {
//                    when (tokens.drop(1).firstOrNull()) {
//                        "row" -> {
//                            layoutParams.rowSpec = GridLayout.spec()
//                        }
//                    }
//                }
//            }
//            "relative" -> {
//
//            }
//        }
//
//    }
//
//
//    protected fun setValue(propertyMap: PropertyMap, token: IToken, target: View): PropertyChangedSubscription? {
//        if (token is NullToken) {
//            return null
//        }
//        val value = token.value ?: return null
//        when (value) {
//            is ShibaFunction -> {
//                val bindings = getFunctionBindings(value)
//                if (bindings.count() == 1) {
//                    val binding = bindings.first()
////                    val targetValue = Shiba.configuration.bindingValueResolver.getValue(dataContext, binding.getTokenValue())
////                    propertyMap.setter.invoke(target, Shiba.SingleParameterFunctionConverter.executeFunction(value, targetValue))
//                    return PropertyChangedSubscription(binding.getTokenValue(), { view, notifyValue ->
//                        propertyMap.setter.invoke(view, Shiba.SingleParameterFunctionConverter.executeFunction(value, notifyValue))
//                    })
//                } else {
//                    throw NotImplementedError()
//                }
//            }
//            is IBindingValue -> {
//                return getBinding(value, target, propertyMap)
//            }
//            else -> propertyMap.setter.invoke(target, value)
//        }
//        return null
//    }
//
//    private fun getFunctionBindings(parameter: IParameter): List<IBindingValue> {
//        return when (parameter) {
//            is ShibaFunction -> {
//                parameter.parameter.map { getFunctionBindings(it) }.flatten()
//            }
//            is ValueParameter -> {
//                when (parameter.value.value) {
//                    is IBindingValue -> listOf(parameter.value.value as IBindingValue)
//                    is ShibaFunction -> getFunctionBindings(parameter.value.value as ShibaFunction)
//                    else -> throw IllegalArgumentException()
//                }
//            }
//            else -> throw IllegalArgumentException()
//        }
//    }
//
//    private fun getBinding(value: IBindingValue, target: View, propertyMap: PropertyMap): PropertyChangedSubscription? {
//        when (value) {
//            is moe.tlaster.shiba.parser.Binding -> {
////                val targetValue = Shiba.configuration.bindingValueResolver.getValue(dataContext, value.getTokenValue())
////                propertyMap.setter.invoke(target, targetValue)
//                if (propertyMap.twoway != null) {
//                    return PropertyChangedSubscription(value.getTokenValue(), propertyMap.setter).apply {
//                        twowayCallback = {
//                            if (!isChanging) {
//                                isChanging = true
//                                Shiba.configuration.bindingValueResolver.setValue(dataContext, name, it)
//                                isChanging = false
//                            }
//                        }
//                        propertyMap.twoway.invoke(target, twowayCallback!!)
//                    }
//                }
//                return PropertyChangedSubscription(value.getTokenValue(), propertyMap.setter)
//            }
//            is JsonPath -> {
//                TODO("Temporarily remove json path support")
////                propertyMap.setter.invoke(target, Shiba.configuration.jsonValueResolver.getValue(dataContext, value.getTokenValue()))
//            }
//            is NativeResource -> {
//                propertyMap.setter.invoke(target, Shiba.configuration.resourceValueResolver.getValue(value.getTokenValue()))
//            }
//        }
//        return null
//    }
//
//    private var propertyCache: List<PropertyMap>? = null
//
//    protected open var hasDefaultProperty = false
//
//    protected open var defaultPropertyMap: PropertyMap? = null
//
//    protected open fun propertyMaps(): ArrayList<PropertyMap> {
//        return arrayListOf(
//                PropertyMap("visible", { view, it ->
//                    if (it is Boolean) {
//                        view.visibility = if (it) View.VISIBLE else View.GONE
//                    }
//                }),
//                PropertyMap("enable", { view, it -> if (it is Boolean) view.isEnabled = it }),
//                PropertyMap("width", { view, it ->
//                    val param = view.layoutParams
//                    if (it is Number) param.width = it.toInt().dp
//                    else if (it is String) {
//                        if (it.equals("wrap_content", true)) param.width = ViewGroup.LayoutParams.WRAP_CONTENT
//                        else if (it.equals("match_parent", true)) param.width = ViewGroup.LayoutParams.MATCH_PARENT
//                    }
//                    view.layoutParams = param
//                }),
//                PropertyMap("height", { view, it ->
//                    val param = view.layoutParams
//                    if (it is Number) {
//                        param.height = it.toInt().dp
//                    }
//                    else if (it is String) {
//                        if (it.equals("wrap_content", true)) param.height = ViewGroup.LayoutParams.WRAP_CONTENT
//                        else if (it.equals("match_parent", true)) param.height = ViewGroup.LayoutParams.MATCH_PARENT
//                    }
//                    view.layoutParams = param
//                }),
//                PropertyMap("margin", { view, it ->
//                    if (it is Thickness && view.layoutParams is ViewGroup.MarginLayoutParams) {
//                        (view.layoutParams as ViewGroup.MarginLayoutParams).setMargins(it.left.toInt().dp, it.top.toInt().dp, it.right.toInt().dp, it.bottom.toInt().dp)
//                    }
//                }),
//                PropertyMap("padding", { view, it -> if (it is Thickness) view.setPaddingRelative(it.left.toInt().dp, it.top.toInt().dp, it.right.toInt().dp, it.bottom.toInt().dp) }),
//                PropertyMap("alpha", { view, it -> if (it is Number) view.alpha = it.toFloat() else if (it is Percent) view.alpha = it.value.toFloat() }),
//                PropertyMap("name", { view, it -> if (it is String) view.setTag(R.id.shiba_view_name_key, it) })
////                PropertyMap("name", {view, it -> if (it is String) })
////                PropertyMap("background", {view, it ->  })
//        ).apply {
//            if (defaultPropertyMap != null) {
//                add(defaultPropertyMap!!)
//            }
//        }
//    }
//
//    protected open fun createView(context: Context): View {
//        return View(context)
//    }
//}
//
//class InputMapper : ViewMapper<EditText>() {
//    override fun createView(context: Context): View {
//        return EditText(context)
//    }
//
//
//    override fun propertyMaps(): ArrayList<PropertyMap> {
//        return super.propertyMaps().apply {
//            add(PropertyMap("text", { view, it -> if (it is CharSequence && view is EditText) view.setText(it) }, { view, callback ->
//                if (view is EditText) {
//                    view.addTextChangedListener(object : TextWatcher {
//                        override fun afterTextChanged(s: Editable?) {
//                        }
//
//                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                        }
//
//                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                            callback.invoke(s?.toString())
//                        }
//                    })
//                }
//            }))
//        }
//    }
//}
//
//class StackMapper : ViewMapper<LinearLayout>() {
//    override fun createView(context: Context): View {
//        return LinearLayout(context).apply { orientation = LinearLayout.VERTICAL }
//    }
//}
//
//class TextMapper : ViewMapper<TextView>() {
//
//    override var hasDefaultProperty = true
//    override var defaultPropertyMap: PropertyMap? = PropertyMap("text", { view, it -> if (it is CharSequence && view is TextView) view.text = it })
//
//    override fun createView(context: Context): View {
//        return TextView(context)
//    }
//}