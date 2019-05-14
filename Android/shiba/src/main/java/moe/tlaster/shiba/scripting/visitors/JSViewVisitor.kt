package moe.tlaster.shiba.scripting.visitors

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonAppend
import moe.tlaster.shiba.ShibaView
import moe.tlaster.shiba.common.Singleton
import moe.tlaster.shiba.scripting.conversion.toNative
import moe.tlaster.shiba.type.Property
import moe.tlaster.shiba.type.ShibaExtension
import moe.tlaster.shiba.visitors.ValueVisitor
import org.liquidplayer.javascript.JSObject
import org.liquidplayer.javascript.JSValue

internal object JSViewVisitor {
    private const val viewType = "IView"
    fun visit(value: JSValue) : Any? {
        if (!value.isObject) {
            return value.toNative()
        }
        val obj = value.toObject()
        if (!obj.hasProperty("className")) {
            return obj.toNative()
        }
        when (obj.property("className").toString()) {
            viewType -> {
                return visitView(obj)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    private fun visitView(value: JSObject): ShibaView {
        val name = value.property("name").toString()
        val properties = value.property("properties").toJSArray()
        val children = value.property("children").toJSArray()
        val view = ShibaView(name)
        properties.forEach {
            if (it is JSValue && it.isObject) {
                view.properties.add(visitProperty(it.toObject()))
            }
        }
        children.forEach {
            if (it is JSValue) {
                val child = visit(it)
                if (child is ShibaView) {
                    child.parent = view
                    view.children.add(child)
                } else {
                    view.defaultValue = child
                }
            }
        }
        return view
    }

    private fun visitProperty(value: JSObject): Property {
        val valueType = ValueType.fromInt(value.property("valueType").toNumber().toInt())
        val name = value.property("name").toString()
        val propertyValue = value.property("value")
        when (valueType) {
            ValueType.Extension -> {
                val extValue = propertyValue.toObject()
                val target = extValue.property("target").toString()
                val type = extValue.property("type").toString()
                val scriptName = if (extValue.hasProperty("scriptName")) {
                    extValue.property("scriptName").toString()
                } else {
                    null
                }
                return Property(name, ShibaExtension(type, target, scriptName))
            }
            ValueType.Boolean, ValueType.Number, ValueType.String, ValueType.Null -> {
                return Property(name, propertyValue.toNative())
            }
            ValueType.Custom -> {
                return Property(name, ValueVisitor.visit(Singleton.get<ObjectMapper>().readTree(propertyValue.toString()), null))
            }
            null -> {
                return Property(name, null)
            }
        }
    }
}
internal enum class ValueType(val value: Int) {
    Extension(0),
    Boolean(1),
    Number(2),
    String(3),
    Null(4),
    Custom(5);
    companion object {
        private val values = values()
        fun fromInt(value: Int) = values.firstOrNull { it.value == value }
    }
}