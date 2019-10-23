package org.shibajs.shiba.dataBinding

import android.util.ArrayMap
import org.shibajs.shiba.Binding
import org.shibajs.shiba.INotifyPropertyChanged
import org.shibajs.shiba.NativeView
import org.shibajs.shiba.converters.ShibaConverter
import java.lang.reflect.Method

private data class BindingContext(val propertyPath: String, var dataContext: Any?, var callback: ((Any, String) -> Unit)?)

private data class PropertyMethod(val getter: Method?, val setter: Method?)
private val typeCache = ArrayMap<Class<*>, Map<String, PropertyMethod>>()
private val rawTypeCache = ArrayMap<Class<*>, Map<String, PropertyMethod>>()

private fun getPropertyMethods(dataContext: Any?): Map<String, PropertyMethod>? {
    return typeCache.getOrPut(dataContext?.javaClass) {
        dataContext
                ?.javaClass
                ?.declaredMethods
                ?.filter { it.isAnnotationPresent(Binding::class.java) }
                ?.groupBy { it.getAnnotation(Binding::class.java).name }
                ?.map { method -> method.key to generatePropertyMethod(method) }
                ?.toMap()
    }
}

private fun getRawPropertyMethods(dataContext: Any?): Map<String, PropertyMethod>? {
    return rawTypeCache.getOrPut(dataContext?.javaClass) {
        dataContext
                ?.javaClass
                ?.declaredMethods
                ?.filter { ((it.name.startsWith("get")) && it.parameterTypes.isEmpty()) || (it.name.startsWith("set") && it.parameterTypes.size == 1) }
                ?.groupBy { it.name.substring(3) }
                ?.map { it.key to generatePropertyMethod(it) }
                ?.toMap()
    }
}


private fun generatePropertyMethod(method: Map.Entry<String, List<Method>>): PropertyMethod {
    val getter = method.value.firstOrNull { !it.parameterTypes.any() }
    val setter = method.value.firstOrNull { setterMethod ->
        setterMethod.parameterTypes.count { it == (getter?.returnType ?: it) } == 1
    }
    return PropertyMethod(getter, setter)
}


class ShibaBinding(val path: String, val actualPath: String = "") {
    private val bindingSources = ArrayList<BindingContext>()

    init {
        bindingSources.addAll(path.split('.').map { BindingContext(it, null, null) })
    }

    var source: Any? = null
    set(value) {
        field = value
        updateBindingSource(value)
    }
    var converter: ShibaConverter? = null
    var parameter: Any? = null
    var targetView: NativeView? = null
    private var isChanging = false
    var viewSetter: ((NativeView, Any?) -> Unit)? = null

    private fun updateBindingSource(newValue: Any?) {
        releaseBindingSources()
        if (newValue == null) {
            return
        }
        var currentRoot = newValue
        bindingSources.forEach {
            val dataContext = currentRoot
            it.dataContext = dataContext
            if (dataContext is INotifyPropertyChanged) {
                val callback = { sender: Any, name: String ->
                    handlePropertyChanged(sender, name)
                }
                it.callback = callback
                dataContext.propertyChanged += callback
            }
            currentRoot = getValueFromDataContext(dataContext, it.propertyPath)
        }
        setValueToView()
    }

    fun setValueToView() {
        val source = bindingSources.lastOrNull()
        val target = targetView
        val setter = viewSetter
        if (source != null && target != null && setter != null && !isChanging) {
            isChanging = true
            val value = getValueFromDataContext(source.dataContext, source.propertyPath)
            val convertValue = executeConverter(value)
            setter.invoke(target, convertValue)
            isChanging = false
        }
    }

    private fun getValueFromDataContext(dataContext: Any?, path: String) : Any? {
        if (path.isEmpty()) {
            return dataContext
        }
        return when (dataContext) {
            is INotifyPropertyChanged -> {
                getPropertyMethods(dataContext)?.get(path)?.getter?.invoke(dataContext)
            }
            else -> {
                getRawPropertyMethods(dataContext)?.get(path)?.getter?.invoke(dataContext)
            }
        }
    }

    private fun executeConverter(dataContext: Any?) : Any? {
        val c = converter
        if (c != null) {
            return c.convert(dataContext, parameter)
        }
        return dataContext
    }

    private fun handlePropertyChanged(sender: Any, name: String) {
        val index = bindingSources.indexOfFirst { it.dataContext == sender }
        if (index == -1) {
            return
        }
        val item = bindingSources[index]
        if (item.propertyPath != name) {
            return
        }

        var currentRoot: Any? = sender
        bindingSources.drop(index).forEach {
            val dataContext = currentRoot
            if (dataContext != sender) {
                val oldDataContext = it.dataContext
                val oldCallback = it.callback
                if (oldDataContext is INotifyPropertyChanged && oldCallback != null) {
                    oldDataContext.propertyChanged -= oldCallback
                }
                it.dataContext = dataContext
                it.callback = null
                if (dataContext is INotifyPropertyChanged) {
                    val callback = { sender: Any, name: String ->
                        handlePropertyChanged(sender, name)
                    }
                    it.callback = callback
                    dataContext.propertyChanged += callback
                }
            }
            currentRoot = getValueFromDataContext(dataContext, it.propertyPath)
        }
        setValueToView()
    }

    internal fun setValueToDataContext(value: Any?) {
        val source = bindingSources.lastOrNull() ?: return
        if (source.dataContext == null) {
            return
        }
        val dataContext = source.dataContext
        if (!isChanging) {
            isChanging = true
            if (source.propertyPath.isEmpty()) {
//                source.dataContext = value
                // TODO: Add twoway binding for root data context
            } else {
                val setter = when (dataContext) {
                    is INotifyPropertyChanged -> {
                        getPropertyMethods(dataContext)?.get(source.propertyPath)?.setter
                    }
                    else -> {
                        getRawPropertyMethods(dataContext)?.get(source.propertyPath)?.setter
                    }
                }
                setter?.invoke(dataContext, value)
            }

            isChanging = false
        }
    }

    private fun releaseBindingSources() {
        bindingSources.forEach {
            val context = it.dataContext
            val callback = it.callback
            if (context is INotifyPropertyChanged && callback != null) {
                context.propertyChanged -= callback
            }
            it.callback = null
            it.dataContext = null
        }
    }

    fun release() {
        releaseBindingSources()
        targetView = null
    }
}
