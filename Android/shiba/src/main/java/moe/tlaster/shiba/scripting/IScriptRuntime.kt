package moe.tlaster.shiba.scripting

import moe.tlaster.shiba.scripting.conversion.ITypeConversion

interface IScriptRuntime {
    fun execute(name: String, parameters: Array<Any?>): Any?
    fun execute(script: String): Any?
    fun addTypeConversion(conversion: ITypeConversion)
}