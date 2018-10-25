package moe.tlaster.shiba

interface IExtensionExecutor {
    val name: String
    fun provideValue(context: IShibaContext?, extension: ShibaExtension) : Any?
}

interface IMutableExtensionExecutor : IExtensionExecutor {

}

interface IAsyncExtensionExecutor : IExtensionExecutor {
    suspend fun provideValueAsync(context: IShibaContext?, extension: ShibaExtension) : Any?
}