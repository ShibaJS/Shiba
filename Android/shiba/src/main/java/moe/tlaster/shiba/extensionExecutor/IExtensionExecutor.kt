package moe.tlaster.shiba.extensionExecutor

import moe.tlaster.shiba.IShibaContext
import moe.tlaster.shiba.type.ShibaExtension

interface IExtensionExecutor {
    val name: String
    fun provideValue(context: IShibaContext?, extension: ShibaExtension) : Any?
}

interface IMutableExtensionExecutor : IExtensionExecutor

interface IAsyncExtensionExecutor : IExtensionExecutor {
    suspend fun provideValueAsync(context: IShibaContext?, extension: ShibaExtension) : Any?
}