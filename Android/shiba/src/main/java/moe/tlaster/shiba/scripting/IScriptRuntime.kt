package moe.tlaster.shiba.scripting

import moe.tlaster.shiba.scripting.conversion.ITypeConversion

interface IScriptRuntime {
    fun hasFunction(name: String): Boolean
    fun hasFunction(instance: Any?, name: String): Boolean
    fun callFunction(name: String, vararg parameters: Any?): Any?
    fun callFunction(instance: Any?, name: String, vararg parameters: Any?): Any?
    fun execute(script: String): Any?
    fun addTypeConversion(conversion: ITypeConversion)
    fun getProperty(instance: Any?, name: String): Any?
    fun isArray(instance: Any?): Boolean
    fun isObject(instance: Any?): Boolean
    fun toArray(instance: Any?): List<Any>
    fun injectFunction(instance: Any?, name: String, block: () -> Unit)
    fun <T: Any> injectFunction(instance: Any?, name: String, block: (T) -> Unit)
}