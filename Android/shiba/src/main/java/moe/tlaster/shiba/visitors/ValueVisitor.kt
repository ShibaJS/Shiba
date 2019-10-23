package moe.tlaster.shiba.visitors

import android.view.ViewGroup
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.ValueNode
import moe.tlaster.shiba.*
import moe.tlaster.shiba.common.Singleton
import moe.tlaster.shiba.converters.RawConverter
import moe.tlaster.shiba.converters.ShibaConverterParameter
import moe.tlaster.shiba.converters.SingleBindingFunctionConverter
import moe.tlaster.shiba.dataBinding.ShibaBinding
import moe.tlaster.shiba.extensionExecutor.IMutableExtensionExecutor
import moe.tlaster.shiba.mapper.ComponentMapper
import moe.tlaster.shiba.mapper.IAllowChildViewMapper
import moe.tlaster.shiba.type.Property
import moe.tlaster.shiba.type.ShibaExtension
import moe.tlaster.shiba.type.ShibaFunction
import moe.tlaster.shiba.type.ShibaObject
import org.liquidplayer.javascript.*


internal object ValueVisitor {
    fun visit(item: Any?, context: IShibaContext?): Any? {
        return when (item) {
            is ShibaView -> visit(item, context)
            is ShibaExtension -> visit(item, context)
            is ShibaFunction -> visit(item, context)
            is ObjectNode -> visit(item, context)
            is ArrayNode -> visit(item, context)
            is ValueNode -> visit(item, context)
            is JSValue -> visit(item, context)
            null -> null
            else -> item
        }
    }

    private val shibaHostMapper by lazy {
        ComponentMapper()
    }

    private fun visit(tree: ShibaView, context: IShibaContext?): NativeView {
        val mapper = Shiba.viewMapping.filter { tree.viewName == it.key }.values.firstOrNull()

        if (context == null) {
            throw IllegalArgumentException()
        }
        if (mapper == null) {
            if (Shiba.components.contains(tree.viewName)) {
                tree.properties.add(Property("componentName", tree.viewName))
                return shibaHostMapper.map(tree, context)
            }
            throw ClassNotFoundException("${tree.viewName} not found")
        } else {
            val target = mapper.map(tree, context)
            if (tree.children.any() && target is ViewGroup) {
                val commonProps = ArrayList<Triple<ShibaView, NativeView, List<Property>>>()
                tree.children.forEach {
                    val child = visit(it, context)
                    if (mapper is IAllowChildViewMapper) {
                        mapper.addChild(target, child)
                    } else {
                        target.addView(child)
                    }
                    val comprop = it.properties.filter { Shiba.configuration.commonProperties.any { cp -> cp.name == it.name } }.toList()
                    if (comprop.any()) {
                        commonProps.add(Triple(it, child, comprop))
                    }
                }
                commonProps.forEach {
                    it.third.forEach { prop ->
                        Shiba.configuration.commonProperties.filter { cp -> cp.name == prop.name }.forEach { cp -> cp.handle(prop.value, it.second, target) }
                    }
                }
            }
            return target
        }

    }

    private fun visit(tree: ShibaExtension, context: IShibaContext?): Any? {
        val executor = Shiba.configuration.extensionExecutors.firstOrNull { it.name == tree.type }
        if (executor != null) {
            val value = executor.provideValue(context, tree)
            if (tree.scriptName.isNullOrEmpty() || tree.scriptName.isNullOrBlank()) {
                return value
            }
            val func = ShibaFunction(tree.scriptName).apply {
                parameter = arrayListOf(tree)
            }
            return when (value) {
                is ShibaBinding -> value.apply {
                    parameter = if (converter != null) {
                        ShibaConverterParameter(converter, parameter, func)
                    } else {
                        func
                    }
                    converter = Singleton.get<SingleBindingFunctionConverter>()
                }
                else ->
                    ShibaBinding("").apply {
                        converter = Singleton.get<RawConverter>()
                        parameter = Singleton.get<SingleBindingShibaFunctionExecutor>().execute(func, tree)
                    }
            }
        }
        throw NotImplementedError()
    }

    private fun visit(tree: ShibaFunction, context: IShibaContext?) : ShibaBinding {
        val function = parseFunction(tree, context)
        val extensions = getExtensions(function)
        if (!extensions.any()) {
            return ShibaBinding("").apply {
                converter = Singleton.get<RawConverter>()
                parameter = Singleton.get<ShibaFunctionExecutor>().execute(function, null)
            }
        }
        if (extensions.size == 1) {
            val extension = extensions.first()
            return when (val extensionValue = visit(extension, context)) {
                is ShibaBinding -> {
                    extensionValue.apply {
                        parameter = if (converter != null) {
                            ShibaConverterParameter(converter, parameter, function)
                        } else {
                            function
                        }
                        converter = Singleton.get<SingleBindingFunctionConverter>()
                    }
                }
                else -> {
                    ShibaBinding("").apply {
                        converter = Singleton.get<RawConverter>()
                        parameter = Singleton.get<SingleBindingShibaFunctionExecutor>().execute(function, extensionValue)
                    }
                }
            }
        }
        if (extensions.size > 1) {
            throw IllegalArgumentException("Currently only support single ShibaExtension")
        }
        throw IllegalArgumentException()
    }

    private fun getExtensions(function: ShibaFunction): List<ShibaExtension> {
        return function.parameter.map {
            when (it) {
                is ShibaExtension -> {
                    val executor = Shiba.configuration.extensionExecutors.firstOrNull { e -> e.name == it.type }
                    if (executor !is IMutableExtensionExecutor) {
                        arrayListOf(it)
                    } else {
                        null
                    }
                }
                is ShibaFunction -> getExtensions(it)
                else -> null
            }
        }.filter { it != null }.flatMap { it!! }
    }

    private fun parseFunction(function: ShibaFunction, context: IShibaContext?) : ShibaFunction {
        return function.apply {
            parameter = parameter.map {
                when (it) {
                    is ShibaFunction -> parseFunction(it, context)
                    is ShibaExtension -> {
                        val executor = Shiba.configuration.extensionExecutors.firstOrNull { e -> e.name == it.type }
                        if (executor is IMutableExtensionExecutor) {
                            executor.provideValue(context, it)
                        } else {
                            it
                        }
                    }
                    else -> {
                        val result = visit(it, context)
                        result
                    }
                }
            }.filter { it != null }.map { it!! }
        }
    }

    private fun visit(item: ObjectNode, context: IShibaContext?): ShibaObject {
        val map = ShibaObject()
        item.fieldNames().forEach {
            map[it] = visit(item.get(it), context)
        }
        return map
    }

    private fun visit(item: ArrayNode, context: IShibaContext?): List<Any?> {
        return item.map { visit(it, context) }
    }

    private fun visit(item: ValueNode, context: IShibaContext?): Any? {
        return item.run {
            when {
                isTextual -> textValue()
                isFloat -> floatValue()
                isDouble -> doubleValue()
                isInt -> intValue()
                isNumber -> numberValue()
                isBoolean -> booleanValue()
                isNull -> null
                else -> null
            }
        }
    }

    private fun visit(item: JSValue, context: IShibaContext?): Any? {
        return when {
            item.isObject -> return visitJSObject(item)
            item.isArray -> return visitJSArray(item)
            item.isBoolean -> item.toBoolean()
            item.isNumber -> item.toNumber()
            item.isString -> item.toString()
            item.isNull -> null
            else -> item
        }
    }

    private fun visitJSArray(item: JSValue): List<Any?> {
        return item.toJSArray().map { visit(it, null) }
    }

    private fun visitJSObject(item: JSValue): ShibaObject {
        val jsobj = item.toObject()
        val obj = ShibaObject()
        item.toObject().propertyNames().forEach {
            obj[it] = visit(jsobj.property(it), null)
        }
        return obj
    }
}