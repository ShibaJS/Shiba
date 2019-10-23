package org.shibajs.shiba

import android.content.Context
import android.util.ArrayMap
import android.util.Log
import org.shibajs.shiba.commonProperty.GridProperty
import org.shibajs.shiba.commonProperty.ICommonProperty
import org.shibajs.shiba.extensionExecutor.BindingExecutor
import org.shibajs.shiba.extensionExecutor.IExtensionExecutor
import org.shibajs.shiba.extensionExecutor.JsonExecutor
import org.shibajs.shiba.mapper.*
import org.shibajs.shiba.scripting.DefaultScriptRuntime
import org.shibajs.shiba.scripting.IScriptRuntime
import org.shibajs.shiba.scripting.runtime.Http
import org.shibajs.shiba.scripting.runtime.Storage
import org.shibajs.shiba.type.View
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