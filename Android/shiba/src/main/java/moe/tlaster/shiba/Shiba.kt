package moe.tlaster.shiba

import android.util.ArrayMap
import moe.tlaster.shiba.commonProperty.GridProperty
import moe.tlaster.shiba.commonProperty.ICommonProperty
import moe.tlaster.shiba.mapper.*
import org.mozilla.javascript.Context
import org.mozilla.javascript.Function
import org.mozilla.javascript.ScriptableObject
import java.lang.reflect.Method

typealias NativeView = android.view.View
typealias ShibaView = moe.tlaster.shiba.View

class ShibaConfiguration {
    var jsonValueResolver: IJsonValueResolver = DefaultJsonValueResolver()
    var resourceValueResolver: IValueResolver = DefaultResourceValueResolver()
    var bindingValueResolver: IBindingValueResolver = DefaultBindingValueResolver()
    var converterExecutor: IConverterExecutor = DefaultConverterExecutor()
    var platformType = "Android"
    val commonProperties = ArrayList<ICommonProperty>()
}

interface IConverterExecutor {
    fun execute(name: String, parameters: Array<Any?>): Any?
}

interface IBindingValueResolver {
    fun getValue(dataContext: Any?, name: String): Any?
    fun setValue(dataContext: Any?, name: String, value: Any?)
}

interface IJsonValueResolver {
    fun getValue(dataContext: Any?, name: String): Any?
}

interface IValueResolver {
    fun getValue(value: Any?): Any?
}

class DefaultConverterExecutor : IConverterExecutor {
    private val context = Context.enter().apply {
        optimizationLevel = -1
        languageVersion = Context.VERSION_ES6
    }
    private val scope: ScriptableObject by lazy {
        context.initStandardObjects()
    }

    public fun addConverter(converter: String) {
        context.evaluateString(scope, converter, "JavaScript", 0, null)
    }

    override fun execute(name: String, parameters: Array<Any?>): Any? {
        val obj = scope.get(name, scope)
        if (obj is Function) {
            return obj.call(context, scope, scope, parameters)
        }
        return null
    }
}

class DefaultJsonValueResolver : IJsonValueResolver {
    override fun getValue(dataContext: Any?, name: String): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

private data class PropertyMethod(val getter: Method?, val setter: Method?)

class DefaultBindingValueResolver : IBindingValueResolver {
    private val typeCache = ArrayMap<Class<*>, Map<String, PropertyMethod>>()
    private val rawTypeCache = ArrayMap<Class<*>, Map<String, PropertyMethod>>()
    override fun setValue(dataContext: Any?, name: String, value: Any?) {
        if (dataContext == null) {
            return
        }
        if (name.isEmpty()) {
            //TODO: add twoway binding for root data context
        }
        (getPropertyMethods(dataContext) ?: getRawPropertyMethods(dataContext))?.get(name)?.setter?.invoke(dataContext, value)
    }

    override fun getValue(dataContext: Any?, name: String): Any? {
        if (dataContext == null) {
            return null
        }
        if (name.isEmpty()) {
            return dataContext
        }
        return (getPropertyMethods(dataContext) ?: getRawPropertyMethods(dataContext))?.get(name)?.getter?.invoke(dataContext)
    }

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
        val setter = method.value.firstOrNull {
            it.parameterTypes.count { it == (getter?.returnType ?: it) } == 1
        }
        return PropertyMethod(getter, setter)
    }


}

class DefaultResourceValueResolver : IValueResolver {
    override fun getValue(value: Any?): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

object Shiba {
    val viewMapping = ArrayMap<String, IViewMapper<*>>()
    val configuration = ShibaConfiguration()

    init {
        addRenderer("stack", StackMapper())
        addRenderer("text", TextMapper())
        addRenderer("input", InputMapper())
        addRenderer("list", ListMapper())
        addRenderer("grid", GridMapper())
        configuration.commonProperties.add(GridProperty())
    }

    public fun addConverter(converter: String) {
        if (configuration.converterExecutor is DefaultConverterExecutor) {
            (configuration.converterExecutor as DefaultConverterExecutor).addConverter(converter)
        }
    }

    public fun addRenderer(name: String, mapper: IViewMapper<*>) {
        viewMapping[name] = mapper
    }
}