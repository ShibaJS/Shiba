package moe.tlaster.shiba.scripting

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

            return result?.toNative()
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