package moe.tlaster.shiba.extensionExecutor

import moe.tlaster.shiba.*

private const val dataContextPath = "dataContext"
class BindingExecutor(override val name: String = "bind") : IShibaExtensionExecutor {
    override fun provideValue(context: IShibaContext?, extension: ShibaExtension): Any? {
        val path = if (extension.value != null && extension.value.typeCode == ShibaValueType.Token) extension.value.value as String else null
        val bindingPath = if (path == null) dataContextPath else "$dataContextPath.$path"
        return ShibaBinding(bindingPath).apply {
            source = context
        }
    }
}