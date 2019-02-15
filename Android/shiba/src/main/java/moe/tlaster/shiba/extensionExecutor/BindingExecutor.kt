package moe.tlaster.shiba.extensionExecutor

import moe.tlaster.shiba.*
import moe.tlaster.shiba.dataBinding.ShibaBinding
import moe.tlaster.shiba.type.ShibaExtension

private const val dataContextPath = "dataContext"
class BindingExecutor(override val name: String = "bind") : IExtensionExecutor {
    override fun provideValue(context: IShibaContext?, extension: ShibaExtension): Any? {
        val path = extension.value
        val bindingPath = if (path == null) dataContextPath else "$dataContextPath.$path"
        return ShibaBinding(bindingPath).apply {
            source = context
        }
    }
}