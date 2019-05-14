package moe.tlaster.shiba.scripting

import android.util.Log
import moe.tlaster.shiba.Shiba
import moe.tlaster.shiba.ShibaView
import moe.tlaster.shiba.scripting.conversion.*
import moe.tlaster.shiba.scripting.runtime.Http
import moe.tlaster.shiba.scripting.visitors.JSViewVisitor
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSFunction
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

    private val registerComponent = object : JSFunction(runtime, "registerComponent") {
        fun registerComponent(name: String?, view: JSValue?): Boolean {
            if (name.isNullOrEmpty()) {
                return false
            }
            if (view == null) {
                return false
            }

            val component = JSViewVisitor.visit(view)
            if (component is ShibaView) {
                Shiba.components[name] = component
                return true
            }

            return false
        }
    }

    init {
        addObject("http") {
            Http(it)
        }
        addTypeConversion(JsonConversion())
        addTypeConversion(PromiseConversion())
        runtime.property("registerComponent", registerComponent)
    }

}
