package moe.tlaster.shiba

import moe.tlaster.shiba.visitors.ShibaValueVisitor


/**
 * Created by SE on 3/5.
 */

data class Property(val name: ShibaToken, val value: Any) {
    override fun toString(): String {
        return "$name = $value"
    }

}

data class ShibaToken(val prefix: String, val value: String) {
    override fun toString(): String {
        return if (prefix.isEmpty()) value else "$prefix:$value"
    }

    fun isCurrentPlatform(value: String): Boolean {
        return isCurrentPlatform() && value == this.value
    }

    fun isCurrentPlatform(): Boolean {
        return (prefix == Shiba.configuration.platformType || prefix.isEmpty())
    }
}

data class ShibaFunction(val name: String) {
    var paramter: List<Any> = ArrayList()
        internal set

    override fun toString(): String {
        return "$name(${paramter.joinToString(separator = ",") { it.toString() }})"
    }
}

class ShibaArray : ArrayList<Any>() {
    override fun toString(): String {
        return "[${this.joinToString(separator = ",") { it.toString() }}]"
    }
}

enum class ShibaValueType {
    String,
    Token,
    Number,
    Null,
    Boolean,
}

data class BasicValue(val typeCode: ShibaValueType, val value: Any?) {
    override fun toString(): String {
        return when (typeCode) {
            ShibaValueType.String -> "\"$value\""
            ShibaValueType.Token -> "$value"
            ShibaValueType.Number -> "$value"
            ShibaValueType.Null -> "null"
            ShibaValueType.Boolean -> value.toString().toLowerCase()
        }
    }
}

data class ShibaMap(val properties: List<Property>) : Map<String, Any> {
    override val entries: Set<Map.Entry<String, Any>>
        get() = properties.map { it.name.toString() to it.value }.toMap().entries
    override val keys: Set<String>
        get() = properties.map { it.name.toString() }.toSet()
    override val size: Int
        get() = properties.size
    override val values: Collection<Any>
        get() = properties.map { it.value }

    override fun containsKey(key: String): Boolean {
        return properties.any { it.name.isCurrentPlatform(key) }
    }

    override fun containsValue(value: Any): Boolean {
        return properties.any { it.value == value }
    }

    override fun get(key: String): Any? {
        val value = properties.firstOrNull { it.name.isCurrentPlatform(key) }?.value ?: return null
        return ShibaValueVisitor.getValue(value, null)
    }

    fun <T> getValue(key: String): T? {
        return this[key] as T?

    }

    override fun isEmpty(): Boolean {
        return !properties.any()
    }

    override fun toString(): String {
        return "[ ${properties.joinToString(separator = " ") { it.toString() }} ]"
    }
}

data class ShibaExtension(val type: String, val value: BasicValue) {
    override fun toString(): String {
        return "\$$type $value"
    }
}

final class View(var viewName: ShibaToken) {
    val children: ArrayList<View> = ArrayList()
    val properties: ArrayList<Property> = ArrayList()
    var defaultValue: Any? = null
}