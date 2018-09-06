package moe.tlaster.shiba

import android.os.Build
import android.support.annotation.RequiresApi
import moe.tlaster.shiba.visitors.ShibaValueVisitor
import java.util.function.Predicate


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

enum class CollectionChangedType {
    Add,
    Remove,
    Update,
}

class CollectionChangedEventArg(val type: CollectionChangedType) {

}

class ShibaArray : ArrayList<Any?>() {
    var collectionChanged: Event<CollectionChangedEventArg> = Event()

    override fun toString(): String {
        return "[${this.joinToString(separator = ",") { it.toString() }}]"
    }

    override fun add(element: Any?): Boolean {
        val result = super.add(element)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Add))
        }
        return result
    }

    override fun add(index: Int, element: Any?) {
        super.add(index, element)
        collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Add))
    }

    override fun addAll(elements: Collection<Any?>): Boolean {
        val result = super.addAll(elements)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Add))
        }
        return result
    }

    override fun addAll(index: Int, elements: Collection<Any?>): Boolean {
        val result = super.addAll(index, elements)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Add))
        }
        return result
    }

    override fun clear() {
        super.clear()
        collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
    }

    override fun remove(element: Any?): Boolean {
        val result = super.remove(element)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
        }
        return result
    }

    override fun removeAll(elements: Collection<Any?>): Boolean {
        val result = super.removeAll(elements)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
        }
        return result
    }

    override fun removeAt(index: Int): Any? {
        val result = super.removeAt(index)
        if (result != null) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun removeIf(filter: Predicate<in Any?>): Boolean {
        val result = super.removeIf(filter)
        if (result) {
            collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
        }
        return result
    }

    override fun removeRange(fromIndex: Int, toIndex: Int) {
        super.removeRange(fromIndex, toIndex)
        collectionChanged.invoke(this, CollectionChangedEventArg(CollectionChangedType.Remove))
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

data class ShibaExtension(val type: String, val value: BasicValue?) {
    override fun toString(): String {
        return if (value != null) "\$$type $value" else "\$$type"
    }
}

final class View(var viewName: ShibaToken) {
    val children: ArrayList<View> = ArrayList()
    val properties: ArrayList<Property> = ArrayList()
    var defaultValue: Any? = null
}