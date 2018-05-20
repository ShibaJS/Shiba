package moe.tlaster.shiba

import android.content.res.Resources
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.util.ArrayMap
import java.lang.reflect.Method
import java.util.concurrent.ConcurrentHashMap
import com.squareup.duktape.Duktape




data class ViewMap(val name: String, val renderer: IViewRenderer)

class ShibaConfiguration {
    var jsonValueResolver: IJsonValueResolver = DefaultJsonValueResolver()
    var resourceValueResolver: IValueResolver = DefaultResourceValueResolver()
    var bindingValueResolver: IBindingValueResolver = DefaultBindingValueResolver()
    var converterExecutor: IConverterExecutor = DefaultConverterExecutor()
    var platformType = "Android"
}

interface IConverterExecutor {
    fun execute(name: String, vararg parameters: Any?): Any?
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
    var duktape = Duktape.create()
    override fun execute(name: String, vararg parameters: Any?): Any? {
        TODO()
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