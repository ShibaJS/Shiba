package moe.tlaster.shiba

import android.util.ArrayMap
import com.eclipsesource.v8.*
import com.eclipsesource.v8.utils.V8ObjectUtils
import moe.tlaster.shiba.commonProperty.GridProperty
import moe.tlaster.shiba.commonProperty.ICommonProperty
import moe.tlaster.shiba.extensionExecutor.BindingExecutor
import moe.tlaster.shiba.mapper.*

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
    private val runtime = V8.createV8Runtime()

    private val conversions = ArrayList<ITypeConversion>()

    public fun addTypeConversion(conversion: ITypeConversion) {
        conversions.add(conversion)
    }

    public fun addConverter(converter: String) {
        runtime.executeScript(converter)
    }

    override fun execute(name: String, parameters: Array<Any?>): Any? {
        val obj = runtime.getObject(name)
        if (obj is V8Function) {
            val v8Parameters = V8Array(runtime)
            parameters.forEach {
                when (it) {
                    is Double -> v8Parameters.push(it)
                    is Int -> v8Parameters.push(it)
                    is String -> v8Parameters.push(it)
                    else -> {
                        when (it) {
                            null -> v8Parameters.pushNull()
                            is List<*> -> {
                                v8Parameters.push(V8ObjectUtils.toV8Array(runtime, it))
                            }
                            else -> {
                                val converter = conversions.firstOrNull { conversion -> conversion.objectType == it.javaClass }
                                when (converter) {
                                    is IObjectConversion -> v8Parameters.push(V8ObjectUtils.toV8Object(runtime, converter.convert(it)))
                                    is IArrayConversion -> v8Parameters.push(V8ObjectUtils.toV8Array(runtime, converter.convert(it)))
                                    else -> v8Parameters.pushUndefined()
                                }
                            }
                        }
                    }
                }
            }

            val result = obj.call(runtime, v8Parameters)
            v8Parameters.release()
            return result
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