package moe.tlaster.shiba.visitors

import android.view.ViewGroup
import moe.tlaster.shiba.*
import moe.tlaster.shiba.common.Singleton
import moe.tlaster.shiba.common.sha1
import moe.tlaster.shiba.converters.RawConverter
import moe.tlaster.shiba.converters.ShibaConverterParameter
import moe.tlaster.shiba.converters.SingleBindingFunctionConverter
import moe.tlaster.shiba.dataBinding.ShibaBinding
import moe.tlaster.shiba.extensionExecutor.IMutableExtensionExecutor
import moe.tlaster.shiba.type.*
import java.util.concurrent.ConcurrentHashMap

private interface IValueVisitor {
    val type: Class<*>
    fun visit(item: Any, context: IShibaContext?): Any
}

private val visitors = ArrayList<IValueVisitor>().apply {
    add(ViewVisitor())
    add(ShibaExtensionVisitor())
    add(ShibaFunctionVisitor())
    add(ShibaArrayVisitor())
}

private fun <T> Any.visit(context: IShibaContext?): T? {
    val visitor = visitors.find { it.type == this.javaClass } ?: return this as T?
    return visitor.visit(this, context) as T?
}

private abstract class AbsValueVisitor<T, K> : IValueVisitor {
    override fun visit(item: Any, context: IShibaContext?): Any {
        return parse(item as T, context) as Any
    }

    abstract fun parse(tree: T, context: IShibaContext?): K
}

object ShibaValueVisitor {
    fun getValue(item: Any, context: IShibaContext?) : Any? {
        return item.visit<Any>(context)
    }
}

private class ViewVisitor(override val type: Class<*> = View::class.java) : AbsValueVisitor<View, NativeView>() {
    override fun parse(tree: View, context: IShibaContext?): NativeView {
        val mapper = Shiba.viewMapping.filter { tree.viewName.isCurrentPlatformAndCheckValue(it.key) }.values.firstOrNull() ?:
        throw IllegalArgumentException("can not find mapper for ${tree.viewName}")
        if (context == null) {
            throw IllegalArgumentException()
        }
        val target = mapper.map(tree, context)
        if (tree.children.any() && target is ViewGroup) {
            val commonProps = ArrayList<Triple<View, NativeView, List<Property>>>()
            tree.children.forEach {
                val child = parse(it, context)
                target.addView(child)
                val comprop = it.properties.filter { it.name.isCurrentPlatform() && Shiba.configuration.commonProperties.any { cp -> cp.name == it.name.value } }.toList()
                if (comprop.any()) {
                    commonProps.add(Triple(it, child, comprop))
                }
            }

            commonProps.forEach {
                it.third.forEach { prop ->
                    Shiba.configuration.commonProperties.filter { cp -> cp.name == prop.name.value }.forEach { cp -> cp.handle(prop.value, it.second, target) }
                }
            }

        }
        return target
    }
}

private class ShibaExtensionVisitor(override val type: Class<*> = ShibaExtension::class.java) : AbsValueVisitor<ShibaExtension, Any?>() {

    private val scriptsCache = ConcurrentHashMap<String, String>()

    override fun parse(tree: ShibaExtension, context: IShibaContext?): Any? {
        val executor = Shiba.configuration.extensionExecutors.firstOrNull { it.name == tree.type }
        if (executor != null) {
            val value = executor.provideValue(context, tree)
            if (tree.script.isNullOrBlank()) {
                return value
            }

            val funcName = scriptsCache.getOrPut(tree.script) {
                val name = "_${tree.script.sha1()}"
                Shiba.addConverter("function $name(it){ ${tree.script.trimStart('$').trimStart('{').trimEnd('}')} }")
                name
            }

            val func = ShibaFunction(funcName).apply {
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
}

private class ShibaFunctionVisitor(override val type: Class<*> = ShibaFunction::class.java) : AbsValueVisitor<ShibaFunction, ShibaBinding>() {
    override fun parse(tree: ShibaFunction, context: IShibaContext?): ShibaBinding {
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
            val extensionValue = extension.visit<Any>(context)
            return when (extensionValue) {
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
                                val result = it.visit<Any>(context)
                                result
                            }
                        }
                    }.filter { it != null }.map { it!! }
        }
    }
}

private class ShibaArrayVisitor(override val type: Class<*> = ShibaArray::class.java) : AbsValueVisitor<ShibaArray, List<Any?>>() {
    override fun parse(tree: ShibaArray, context: IShibaContext?): List<Any?> {
        return tree.map { it?.visit<Any>(context) }
    }
}