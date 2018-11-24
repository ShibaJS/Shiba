package moe.tlaster.shiba.scripting

import android.util.Log
import moe.tlaster.shiba.scripting.conversion.ITypeConversion
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSValue

class DefaultScriptRuntime : IScriptRuntime {
    private val runtime = JSContext()
    private val conversions = ArrayList<ITypeConversion>()

    override fun execute(script: String): Any? {
        return runtime.evaluateScript(script)?.toNative()
    }

    override fun addTypeConversion(conversion: ITypeConversion) {
        conversions.add(conversion)
    }

    public fun addObject(name: String, value: (JSContext) -> Any) {
        runtime.property(name, value.invoke(runtime))
    }

    override fun execute(name: String, parameters: Array<Any?>): Any? {
        val obj = runtime.property(name).toFunction()
        if (obj != null) {

            try {
                val result = obj.apply(null, parameters.map { parameter ->
                    if (parameter == null) {
                        return@map parameter
                    }
                    var converter: ITypeConversion? = null
                    for (conversion in conversions) {
                        if (conversion.objectType == parameter.javaClass) {
                            converter = conversion
                            break
                        }
                        if (conversion.objectType.isAssignableFrom(parameter.javaClass)) {
                            converter = conversion
                        }
                    }
                    if (converter != null) {
                        return@map converter.convert(parameter)
                    }
                    return@map parameter
                }.toTypedArray())

                return result?.toNative()
            } catch (e: Error) {
                Log.e("script", e.message)
                e.printStackTrace()
            } catch (e: Exception) {
                Log.e("script", e.message)
                e.printStackTrace()
            }

        }
        return null
    }

}

fun JSValue.toNative(): Any? {
    return when {
        isString -> toString()
        isBoolean -> toBoolean()
        isNumber -> toNumber()
        isArray -> toJSArray()
        isObject -> toObject()
        else -> null
    }
}