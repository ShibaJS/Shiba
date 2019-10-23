package org.shibajs.shiba.scripting.conversion

import org.liquidplayer.javascript.*


interface ITypeConversion {
    val objectType: Class<*>
    fun fromJSValue(value: JSValue): Any?
    fun toJSValue(context: JSContext, value: Any): JSValue
    fun canConvert(value: JSValue): Boolean
}