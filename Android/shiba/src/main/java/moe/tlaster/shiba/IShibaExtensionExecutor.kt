package moe.tlaster.shiba

interface IShibaExtensionExecutor {
    val name: String
    fun provideValue(context: IShibaContext?, extension: ShibaExtension) : Any?
}

interface IAsyncShibaExtensionExecutor : IShibaExtensionExecutor {
    suspend fun provideValueAsync(context: IShibaContext?, extension: ShibaExtension) : Any?
}