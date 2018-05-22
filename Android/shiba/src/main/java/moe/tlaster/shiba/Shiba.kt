package moe.tlaster.shiba

import android.util.ArrayMap
import org.mozilla.javascript.Context
import org.mozilla.javascript.Function
import org.mozilla.javascript.ScriptableObject


data class ViewMap(val name: String, val renderer: IViewRenderer)

class ShibaConfiguration {
    var jsonValueResolver: IJsonValueResolver = DefaultJsonValueResolver()
    var resourceValueResolver: IValueResolver = DefaultResourceValueResolver()
    var bindingValueResolver: IBindingValueResolver = DefaultBindingValueResolver()
    var converterExecutor: IConverterExecutor = DefaultConverterExecutor()
    var platformType = "Android"
    public fun addConverter(converter: String) {
        if (converterExecutor is DefaultConverterExecutor) {
            (converterExecutor as DefaultConverterExecutor).addConverter(converter)
        }
    }
}

interface IConverterExecutor {
    fun execute(name: String, parameters: Array<Any?>): Any?
}

interface IBindingValueResolver {
    fun getValue(dataContext: Any?, name: String): Any?
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
    }
    private val scope: ScriptableObject by lazy {
        context.initStandardObjects()
    }

    public fun addConverter(converter: String) {
        context.evaluateString(scope, converter,"JavaScript", 0, null)
    }

    override fun execute(name: String, parameters: Array<Any?>): Any? {
        val obj = scope.get(name, scope)
        if (obj is Function) {
            val jsResult = obj.call(context, scope, scope, parameters)
            return jsResult
        }
        return null
    }
}

class DefaultJsonValueResolver : IJsonValueResolver {
    override fun getValue(dataContext: Any?, name: String): Any? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DefaultBindingValueResolver : IBindingValueResolver {
    override fun getValue(dataContext: Any?, name: String): Any? {
        if (dataContext == null) {
            return null
        }
        return Shiba.typeCache[dataContext.javaClass]?.get(name)?.getter?.invoke(dataContext)
    }
}

class DefaultResourceValueResolver : IValueResolver {
    override fun getValue(value: Any?): Any? {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

object Shiba {
    val viewMapping = ArrayList<ViewMap>()
    val configuration = ShibaConfiguration()
    internal val typeCache = ArrayMap<Class<Any>, Map<String, PropertyMethod>>()
    internal val FunctionConverter = moe.tlaster.shiba.FunctionConverter()
    internal val SingleParamterFunctionConverter = moe.tlaster.shiba.SingleParamterFunctionConverter()

    init {
        addRenderer("stack", StackRenderer())
        addRenderer("text", TextRenderer())
        addRenderer("input", InputRenderer())
    }

    public fun addRenderer(name: String, renderer: IViewRenderer) {
        viewMapping.add(ViewMap(name, renderer))
    }
}