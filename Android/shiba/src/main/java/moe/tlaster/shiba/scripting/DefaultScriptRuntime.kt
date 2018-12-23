package moe.tlaster.shiba.scripting

import android.util.Log
import moe.tlaster.shiba.scripting.conversion.*
import moe.tlaster.shiba.scripting.runtime.Http
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSValue

class DefaultScriptRuntime : IScriptRuntime {

    private val runtime = JSContext()

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
            kotlin.runCatching {
                obj.apply(null, parameters.map { it.toJSValue(runtime) }.toTypedArray())?.toNative()
            }.onSuccess {
                return it
            }.onFailure {
                Log.e("script", it.message)
                it.printStackTrace()
            }
        }
        return null
    }

    init {
        addObject("http") {
            Http(it)
        }
        addTypeConversion(JsonConversion())
        addTypeConversion(PromiseConversion())
    }

}
