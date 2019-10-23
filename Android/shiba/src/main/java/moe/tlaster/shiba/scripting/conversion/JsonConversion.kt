package moe.tlaster.shiba.scripting.conversion

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import moe.tlaster.shiba.common.Singleton
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSObject
import org.liquidplayer.javascript.JSValue

class JsonConversion(override val objectType: Class<*> = JsonNode::class.java) : ITypeConversion {
    override fun toJSValue(context: JSContext, value: Any): JSValue {

        if (value !is JsonNode) {
            return JSValue(context,null)
        }
        if (value.isObject) {
            return JSObject(context, Singleton.get<ObjectMapper>().convertValue(value, Map::class.java))
        }
        return JSValue(context, when {
            value.isArray -> Singleton.get<ObjectMapper>().convertValue(value, List::class.java)
            else -> null
        })
    }

    override fun fromJSValue(value: JSValue): Any? {
        throw NotImplementedError()
    }

    override fun canConvert(value: JSValue): Boolean {
        return false
    }
}