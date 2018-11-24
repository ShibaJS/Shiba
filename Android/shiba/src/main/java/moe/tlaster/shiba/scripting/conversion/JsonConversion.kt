package moe.tlaster.shiba.scripting.conversion

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import moe.tlaster.shiba.common.Singleton

class JsonConversion(override val objectType: Class<*> = JsonNode::class.java) : ITypeConversion {
    override fun convert(value: Any): Any? {
        if (value !is JsonNode) {
            return null
        }

        return when {
            value.isArray -> Singleton.get<ObjectMapper>().convertValue(value, List::class.java)
            value.isObject -> Singleton.get<ObjectMapper>().convertValue(value, Map::class.java)
            else -> return null
        }
    }
}