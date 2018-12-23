package moe.tlaster.shiba.scripting.conversion

import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSValue

interface ITypeConversion {
    val objectType: Class<*>
    fun fromJSValue(value: JSValue): Any?
    fun toJSValue(context: JSContext, value: Any): JSValue
    fun canConvert(value: JSValue): Boolean
}