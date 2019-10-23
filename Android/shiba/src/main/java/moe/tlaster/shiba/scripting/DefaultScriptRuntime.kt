package moe.tlaster.shiba.scripting

import android.util.Log
import awaitString
import com.github.kittinunf.fuel.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import moe.tlaster.shiba.Shiba
import moe.tlaster.shiba.ShibaView
import moe.tlaster.shiba.scripting.conversion.*
import moe.tlaster.shiba.scripting.visitors.JSViewVisitor
import moe.tlaster.shiba.visitors.ValueVisitor
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

    override fun injectFunction(instance: Any?, name: String, block: () -> Unit) {
        if (instance !is JSValue || !instance.isObject) {
            return
        }
        val obj = instance.toObject()
        object : JSFunction(runtime, "func") {
            fun func() {
                block.invoke()
            }
        }.let {
            obj.property(name, it)
        }
    }

    override fun <T: Any> injectFunction(instance: Any?, name: String, block: (T) -> Unit) {
        if (instance !is JSValue || !instance.isObject) {
            return
        }
        val obj = instance.toObject()
        object : JSFunction(runtime, "func") {
            fun func(value: JSValue) {
                block.invoke(value.toNative() as T)
            }
        }.let {
            obj.property(name, it)
        }
    }

    override fun isObject(instance: Any?): Boolean {
        return instance is JSValue && instance.isObject
    }

    fun addObject(name: String, value: (JSContext) -> Any) {
        runtime.property(name, value.invoke(runtime))
    }

    override fun hasFunction(name: String): Boolean {
        return hasFunction(runtime, name)
    }

    override fun hasFunction(instance: Any?, name: String): Boolean {
        if (instance !is JSValue || !instance.isObject) {
            return false
        }
        return instance.toObject().takeIf {
            it.hasProperty(name)
        }?.let {
            it.property(name)
        }?.takeIf {
            it.isObject
        }?.let {
            it.toObject()
        }?.let {
            it.isFunction
        } ?: false
    }

    override fun callFunction(name: String, vararg parameters: Any?): Any? {
        return callFunction(runtime, name, *parameters)
    }

    override fun callFunction(instance: Any?, name: String, vararg parameters: Any?): Any? {
        if (instance !is JSValue || !instance.isObject) {
            return null
        }
        val obj = instance.toObject().property(name).toObject()
        if (obj.isFunction) {
            kotlin.runCatching {
                // TODO: sometime cast will fail
                (obj as JSFunction).apply(instance.toObject(), parameters.map { it.toJSValue(runtime) }.toTypedArray())
                    ?.toNative()
            }.onSuccess {
                return it
            }.onFailure {
                Log.e("script", it.message + "")
                it.printStackTrace()
            }
        } else {
            Log.i("err", "func")
            Log.i("err", obj.toString())
        }
        return null
    }

    override fun isArray(instance: Any?): Boolean {
        return instance is JSValue && instance.isArray
    }

    override fun toArray(instance: Any?): List<Any> {
        if (isArray(instance)) {
            return (instance as JSValue).toJSArray()
        }
        return emptyList()
    }

    override fun getProperty(instance: Any?, name: String): Any? {
        if (instance is JSValue && instance.isObject) {
            val obj = instance.toObject()
            return obj.property(name).toNative()
        }
        return null
    }

    private val http = object : JSFunction(runtime, "http") {
        fun http(link: String, param: JSValue?): JSValue {
            return GlobalScope.async {
                if (param == null) {
                    return@async link.httpGet().awaitString()
                } else {
                    val params = ValueVisitor.visit(param, null) as Map<String, Any?>
                    val request = when (params["method"]) {

                        "HEAD" -> link.httpHead()
                        "DELETE" -> link.httpDelete()
                        "PUT" -> link.httpPut()
                        "POST" -> link.httpPost()
                        "GET" -> link.httpGet()
                        else -> link.httpGet()
                    }
                    if (params.containsKey("headers")) {
                        params["headers"]?.let {
                            it as Map<String, Any?>
                        }?.also {
                            request.header(it.map { it.key to it.value?.toString() as Any }.toMap())
                        }
                    }

                    if (params.containsKey("body")) {
                        when (val body = params["body"]) {
                            is String -> {
                                request.body(body)
                            }
                            is ByteArray -> {
                                request.body(body)
                            }
                        }
                    }

                    return@async request.awaitString()
                }
            }.toJSValue(context)
        }
    }

    private val runShibaApp = object : JSFunction(runtime, "runShibaApp") {
        fun runShibaApp(view: JSValue?): Boolean {
            if (view == null) {
                return false
            }
            val component = JSViewVisitor.visit(view)
            if (component is ShibaView) {
                Shiba.appComponent = component
                return true
            }
            return false
        }
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
        addTypeConversion(JsonConversion())
        addTypeConversion(PromiseConversion())
        runtime.property("rehttp", http)
        runtime.property("registerComponent", registerComponent)
        runtime.property("runShibaApp", runShibaApp)
    }

}
