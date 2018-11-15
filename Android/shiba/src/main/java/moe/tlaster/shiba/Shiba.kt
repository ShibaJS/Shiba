package moe.tlaster.shiba

import android.util.ArrayMap
import moe.tlaster.shiba.commonProperty.GridProperty
import moe.tlaster.shiba.commonProperty.ICommonProperty
import moe.tlaster.shiba.extensionExecutor.BindingExecutor
import moe.tlaster.shiba.mapper.*
import org.liquidplayer.javascript.JSContext

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

interface ITypeConversion {
    val objectType: Class<*>
}

interface IObjectConversion : ITypeConversion {
    fun convert(value: Any): Map<String, Any?>
}

interface IArrayConversion : ITypeConversion {
    fun convert(value: Any): List<Any?>
}

class DefaultConverterExecutor : IConverterExecutor {
    private val runtime = JSContext()

    private val conversions = ArrayList<ITypeConversion>()

    public fun addTypeConversion(conversion: ITypeConversion) {
        conversions.add(conversion)
    }

    public fun addConverter(converter: String) {
        runtime.evaluateScript(converter)
    }

    override fun execute(name: String, parameters: Array<Any?>): Any? {

        val obj = runtime.property(name).toFunction()
        if (obj != null) {
            val result = obj.call(null, parameters.map { parameter ->
                if (parameter == null) {
                    return@map parameter
                }
                val converter = conversions.firstOrNull { conversion -> conversion.objectType == parameter.javaClass }
                if (converter != null) {
                    return@map when (converter) {
                        is IObjectConversion -> converter.convert(parameter)
                        is IArrayConversion -> converter.convert(parameter)
                        else -> throw IllegalArgumentException()
                    }
                }
                return@map parameter
            })
            when {
                result.isString -> return result.toString()
                result.isBoolean -> return result.toBoolean()
                result.isNumber -> return result.toNumber()
                result.isArray -> return result.toJSArray()
            }
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