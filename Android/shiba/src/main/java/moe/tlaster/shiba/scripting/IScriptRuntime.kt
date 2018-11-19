package moe.tlaster.shiba.scripting

interface IScriptRuntime {
    fun execute(name: String, parameters: Array<Any?>): Any?
    fun execute(script: String): Any?
    fun addTypeConversion(conversion: ITypeConversion)
}