package moe.tlaster.shiba

import android.app.Application
import android.content.Context
import android.util.ArrayMap
import moe.tlaster.shiba.commonProperty.GridProperty
import moe.tlaster.shiba.commonProperty.ICommonProperty
import moe.tlaster.shiba.extensionExecutor.BindingExecutor
import moe.tlaster.shiba.extensionExecutor.IExtensionExecutor
import moe.tlaster.shiba.extensionExecutor.JsonExecutor
import moe.tlaster.shiba.mapper.*
import moe.tlaster.shiba.scripting.*
import moe.tlaster.shiba.scripting.conversion.JsonConversion
import moe.tlaster.shiba.scripting.runtime.Storage
import moe.tlaster.shiba.type.View

internal typealias NativeView = android.view.View
internal typealias ShibaView = View

class ShibaConfiguration {
    var scriptRuntime: IScriptRuntime = DefaultScriptRuntime()
    var platformType = "Android"
    val commonProperties = ArrayList<ICommonProperty>()
    val extensionExecutors = ArrayList<IExtensionExecutor>()
}

object Shiba {
    val viewMapping = ArrayMap<String, IViewMapper<*>>()
    val configuration = ShibaConfiguration()

    public fun init(application: Context) {
        addRenderer("stack", StackMapper())
        addRenderer("text", TextMapper())
        addRenderer("input", InputMapper())
        addRenderer("list", ListMapper())
        addRenderer("grid", GridMapper())
        addExtensionExecutor(BindingExecutor())
        addExtensionExecutor(JsonExecutor())
        configuration.commonProperties.add(GridProperty())
        configuration.scriptRuntime.addTypeConversion(JsonConversion())
        if (configuration.scriptRuntime is DefaultScriptRuntime) {
            (configuration.scriptRuntime as DefaultScriptRuntime).addObject("storage") {
                Storage(application, it)
            }
        }
    }

    public fun addConverter(converter: String) {
        configuration.scriptRuntime.execute(converter)
    }

    public fun addRenderer(name: String, mapper: IViewMapper<*>) {
        viewMapping[name] = mapper
    }

    public fun addExtensionExecutor(executor: IExtensionExecutor) {
        configuration.extensionExecutors.add(executor)
    }
}