package moe.tlaster.shiba.type

import moe.tlaster.shiba.visitors.ShibaValueVisitor

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