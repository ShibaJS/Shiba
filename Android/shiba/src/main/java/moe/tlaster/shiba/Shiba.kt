package moe.tlaster.shiba

import android.util.ArrayMap
import java.lang.reflect.Method
import java.util.concurrent.ConcurrentHashMap


data class ViewMap(val name: String, val renderer: IViewRenderer)

class ShibaConfiguration {
    var jsonValueResolver = DefaultJsonValueResolver()
    var resourceValueResolver = DefaultResourceValueResolver()
    var bindingValueResolver = DefaultBindingValueResolver()
    var converterExecutor = DefaultConverterExecutor()
    var platformType = "Android"
}

interface IConverterExecutor {
    fun execute(name: String, vararg parameters: Any): Any
}

interface IBindingValueResolver {
    fun getValue(dataContext: Any, name: String): Any
}

interface IJsonValueResolver {
    fun getValue(dataContext: Any, name: String): Any
}

interface IValueResolver {
    fun getValue(value: Any): Any
}

class DefaultConverterExecutor : IConverterExecutor {
    override fun execute(name: String, vararg parameters: Any): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DefaultJsonValueResolver : IJsonValueResolver {
    override fun getValue(dataContext: Any, name: String): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DefaultBindingValueResolver : IBindingValueResolver {
    override fun getValue(dataContext: Any, name: String): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

class DefaultResourceValueResolver : IValueResolver {
    override fun getValue(value: Any): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

object Shiba {
    val viewMapping = ArrayList<ViewMap>()
    val shibaConfiguration = ShibaConfiguration()
    internal val typeCache = ArrayMap<Class<Any>, Map<String, Method>>()

    init {
        addRenderer("stack", StackRenderer())
        addRenderer("text", TextRenderer())
        addRenderer("input", InputRenderer())
    }

    public fun addRenderer(name: String, renderer: IViewRenderer) {
        viewMapping.add(ViewMap(name, renderer))
    }
}