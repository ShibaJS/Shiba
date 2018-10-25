package moe.tlaster.shiba

import android.util.ArrayMap
import moe.tlaster.shiba.commonProperty.GridProperty
import moe.tlaster.shiba.commonProperty.ICommonProperty
import moe.tlaster.shiba.extensionExecutor.BindingExecutor
import moe.tlaster.shiba.mapper.*
import org.mozilla.javascript.Context
import org.mozilla.javascript.Function
import org.mozilla.javascript.ScriptableObject

internal typealias NativeView = android.view.View
internal typealias ShibaView = moe.tlaster.shiba.View

class ShibaConfiguration {
    var converterExecutor: IConverterExecutor = DefaultConverterExecutor()
    var platformType = "Android"
    val commonProperties = ArrayList<ICommonProperty>()
    val extensionExecutors = ArrayList<IExtensionExecutor>()
}

interface IConverterExecutor {
    fun execute(name: String, parameters: Array<Any?>): Any?
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

object Shiba {
    val viewMapping = ArrayMap<String, IViewMapper<*>>()
    val configuration = ShibaConfiguration()

    init {
        addRenderer("stack", StackMapper())
        addRenderer("text", TextMapper())
        addRenderer("input", InputMapper())
        addRenderer("list", ListMapper())
        addRenderer("grid", GridMapper())
        addExtensionExecutor(BindingExecutor())
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

    public fun addExtensionExecutor(executor: IExtensionExecutor) {
        configuration.extensionExecutors.add(executor)
    }
}