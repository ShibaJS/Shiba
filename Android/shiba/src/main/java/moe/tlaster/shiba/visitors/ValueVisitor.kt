package moe.tlaster.shiba.visitors

import android.view.ViewGroup
import moe.tlaster.shiba.*
import moe.tlaster.shiba.converters.FunctionConverter
import moe.tlaster.shiba.converters.RawConverter
import moe.tlaster.shiba.converters.SingleBindingFunctionConverter

private interface IValueVisitor {
    val type: Class<*>
    fun visit(item: Any, context: IShibaContext?): Any
}

private val visitors = ArrayList<IValueVisitor>().apply {
    add(ViewVisitor())
    add(ShibaExtensionVisitor())
    add(ShibaFunctionVisitor())
    add(BasicValueVisitor())
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
        val mapper = Shiba.viewMapping.filter { tree.viewName.isCurrentPlatform(it.key) }.values.firstOrNull() ?:
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
    override fun parse(tree: ShibaExtension, context: IShibaContext?): Any? {
        val executor = Shiba.configuration.extensionExecutors.firstOrNull { it.name == tree.type }
        if (executor != null) {
            return executor.provideValue(context, tree)
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
            val extensionValue = Shiba.configuration.extensionExecutors.firstOrNull { it.name == extension.type }?.provideValue(context, extension)
            return when (extensionValue) {
                is ShibaBinding -> {
                    extensionValue.apply {
                        converter = Singleton.get<SingleBindingFunctionConverter>()
                        parameter = function
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
                is ShibaExtension -> arrayListOf(it)
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
                            is ShibaExtension -> it
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

private class BasicValueVisitor(override val type: Class<*> = BasicValue::class.java) : AbsValueVisitor<BasicValue, Any?>() {
    override fun parse(tree: BasicValue, context: IShibaContext?): Any? {
        return tree.value
    }
}