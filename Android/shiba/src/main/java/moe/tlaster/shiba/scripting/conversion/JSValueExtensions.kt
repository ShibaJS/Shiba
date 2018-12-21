package moe.tlaster.shiba.scripting.conversion

import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSValue

internal val conversions = ArrayList<ITypeConversion>()

fun JSValue.toNative(): Any? {
    return when {
        isNull || isUndefined -> null
        isString -> toString()
        isBoolean -> toBoolean()
        isNumber -> toNumber()
        else -> {
            val converter = conversions.firstOrNull {
                it.canConvert(this)
            }
            return converter?.fromJSValue(this)
        }
    }
}

fun Any?.toJSValue(context: JSContext): JSValue {
    if (this == null) {
        return JSValue(context, null)
    }
    return when (this) {
        is String,
        is Double,
        is Int,
        is Float,
        is Boolean,
        is Long,
        is Short,
        is Byte ->
            JSValue(context, this)
        else -> {
            var converter: ITypeConversion? = null
            for (conversion in conversions) {
                if (conversion.objectType == this.javaClass) {
                    converter = conversion
                    break
                }
                if (conversion.objectType.isAssignableFrom(this.javaClass)) {
                    converter = conversion
                }
            }
            if (converter != null) {
                return converter.toJSValue(context, this)
            }
            return JSValue(context, null)
        }
    }
}