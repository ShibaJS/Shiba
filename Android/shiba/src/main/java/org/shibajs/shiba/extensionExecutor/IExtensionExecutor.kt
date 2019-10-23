package org.shibajs.shiba.extensionExecutor

import org.shibajs.shiba.IShibaContext
import org.shibajs.shiba.type.ShibaExtension

interface IExtensionExecutor {
    val name: String
    fun provideValue(context: IShibaContext?, extension: ShibaExtension) : Any?
}

interface IMutableExtensionExecutor : IExtensionExecutor

interface IAsyncExtensionExecutor : IExtensionExecutor {
    suspend fun provideValueAsync(context: IShibaContext?, extension: ShibaExtension) : Any?
}