package org.shibajs.shiba.extensionExecutor

import org.shibajs.shiba.IShibaContext
import org.shibajs.shiba.dataBinding.ShibaBinding
import org.shibajs.shiba.type.ShibaExtension

private const val dataContextPath = "dataContext"
open class BindingExecutor(override val name: String = "bind") : IExtensionExecutor {
    override fun provideValue(context: IShibaContext?, extension: ShibaExtension): Any? {
        val path = extension.value
        val bindingPath = if (path == null) dataContextPath else "$dataContextPath.$path"
        return ShibaBinding(bindingPath, path.toString()).apply {
            source = context
        }
    }
}