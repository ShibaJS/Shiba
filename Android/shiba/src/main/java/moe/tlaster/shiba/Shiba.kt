package moe.tlaster.shiba

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import moe.tlaster.shiba.commonProperty.GridProperty
import moe.tlaster.shiba.commonProperty.ICommonProperty
import moe.tlaster.shiba.extensionExecutor.BindingExecutor
import moe.tlaster.shiba.extensionExecutor.IExtensionExecutor
import moe.tlaster.shiba.extensionExecutor.JsonExecutor
import moe.tlaster.shiba.mapper.*
import moe.tlaster.shiba.scripting.DefaultScriptRuntime
import moe.tlaster.shiba.scripting.IScriptRuntime
import moe.tlaster.shiba.scripting.runtime.Http
import moe.tlaster.shiba.scripting.runtime.Storage
import moe.tlaster.shiba.type.View
import org.liquidplayer.javascript.JSFunction
import org.liquidplayer.javascript.JSObject

internal typealias NativeView = android.view.View
internal typealias ShibaView = View

class ShibaConfiguration {
    var scriptRuntime: IScriptRuntime = DefaultScriptRuntime()
    var platformType = "Android"
    val commonProperties = ArrayList<ICommonProperty>()
    val extensionExecutors = ArrayList<IExtensionExecutor>()
    val nativeConverter = androidx.collection.ArrayMap<String, ((List<Any?>) -> Any?)>()
}

object Shiba {
    internal var appComponent: ShibaView? = null
    val viewMapping = ArrayMap<String, IViewMapper<*>>()
    val configuration = ShibaConfiguration()
    internal val components = ArrayMap<String, ShibaView>()

    fun init(application: Context) {
        addRenderer("stack", StackMapper())
        addRenderer("text", TextMapper())
        addRenderer("input", InputMapper())
        addRenderer("list", ListMapper())
        addRenderer("grid", GridMapper())
        addRenderer("button", ButtonMapper())
        addExtensionExecutor(BindingExecutor())
        addExtensionExecutor(JsonExecutor())
        configuration.commonProperties.add(GridProperty())
        if (configuration.scriptRuntime is DefaultScriptRuntime) {
            (configuration.scriptRuntime as DefaultScriptRuntime).addObject("shibaStorage") {
                Storage(application, it)
            }
            (configuration.scriptRuntime as DefaultScriptRuntime).addObject("http") {
                Http(it)
            }
            (configuration.scriptRuntime as DefaultScriptRuntime).addObject("console") {
                object : JSObject(it) {
                    init {
                        property("log", object : JSFunction(it, "log") {
                            fun log(msg: String) {
                                Log.i("Javascript", msg)
                            }
                        })
                    }
                }
            }
        }
    }

    fun addConverter(name: String, converter: ((List<Any?>) -> Any?)) {
        configuration.nativeConverter[name] = converter
    }

    fun addRenderer(name: String, mapper: IViewMapper<*>) {
        viewMapping[name] = mapper
    }

    fun addExtensionExecutor(executor: IExtensionExecutor) {
        configuration.extensionExecutors.add(executor)
    }
}