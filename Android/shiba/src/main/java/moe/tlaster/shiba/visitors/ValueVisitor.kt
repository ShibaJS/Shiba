package moe.tlaster.shiba.visitors

import android.view.ViewGroup
import moe.tlaster.shiba.*
import moe.tlaster.shiba.converters.FunctionConverter
import moe.tlaster.shiba.converters.RawConverter
import moe.tlaster.shiba.converters.SingleBindingFunctionConverter


//open class PropertyMap(val name: String, val setter: (NativeView, Any?) -> Unit)
//
//class TwoWayPropertyMap(name: String, setter: (NativeView, Any?) -> Unit, val twowayInitializer: ((NativeView, (Any?) -> Unit) -> Unit)) : PropertyMap(name, setter)
//
//internal class PropertyChangedSubscription(val target: NativeView, val map: PropertyMap, var twowayCallback: ((Any?) -> Unit)? = null) {
//    internal var isChanging = false
//    internal var dataContext: Any? = null
//}

private interface IValueVisitor {
    val type: Class<*>
    fun visit(item: Any, context: IShibaContext): Any
}

private val visitors = ArrayList<IValueVisitor>().apply {
    add(ViewVisitor())
    add(ShibaExtensionVisitor())
    add(ShibaFunctionVisitor())
    add(BasicValueVisitor())
}

private fun <T> Any.visit(context: IShibaContext): T? {
    return visitors.find { it.type == this.javaClass }?.visit(this, context) as T?
}

private abstract class AbsValueVisitor<T, K> : IValueVisitor {
    override fun visit(item: Any, context: IShibaContext): Any {
        return parse(item as T, context) as Any
    }

    abstract fun parse(tree: T, context: IShibaContext): K
}

object ShibaValueVisitor {
    fun getValue(item: Any, context: IShibaContext) : Any? {
        return item.visit<Any>(context)
    }
}

private class ViewVisitor(override val type: Class<*> = View::class.java) : AbsValueVisitor<View, NativeView>() {
    override fun parse(tree: View, context: IShibaContext): NativeView {
        val mapper = Shiba.viewMapping.filter { tree.viewName.isCurrentPlatform(it.key) }.values.firstOrNull() ?:
        throw IllegalStateException()
        val target = mapper.map(tree, context)
        if (tree.children.any() && target is ViewGroup) {
            tree.children.forEach { target.addView(parse(it, context)) }
        }
        return target
    }
}

private class ShibaExtensionVisitor(override val type: Class<*> = ShibaExtension::class.java) : AbsValueVisitor<ShibaExtension, ShibaBinding>() {
    override fun parse(tree: ShibaExtension, context: IShibaContext): ShibaBinding {
        return when (tree.type) {
            "bind" -> bindHandler(tree.value, context)
            else -> throw NotImplementedError()
        }
    }

    private fun bindHandler(value: BasicValue, context: IShibaContext): ShibaBinding {
        return when (value.typeCode) {
            ShibaValueType.Token -> ShibaBinding(value.value.toString())
            ShibaValueType.String, ShibaValueType.Number, ShibaValueType.Null, ShibaValueType.Boolean ->
                ShibaBinding("", Singleton.get<RawConverter>(), value.value)
        }
    }

}

private class ShibaFunctionVisitor(override val type: Class<*> = ShibaFunction::class.java) : AbsValueVisitor<ShibaFunction, ShibaBinding>() {
    override fun parse(tree: ShibaFunction, context: IShibaContext): ShibaBinding {
        val function = parseFunction(tree, context)
        val bindings = getBindings(function)
        if (!bindings.any()) {
            return ShibaBinding("", Singleton.get<RawConverter>(), Singleton.get<ShibaFunctionExecutor>().execute(function, null))
        }

        if (bindings.size == 1) {
            return ShibaBinding(bindings.first().path, Singleton.get<SingleBindingFunctionConverter>(), function)
        }

        if (bindings.size > 1) {
            return ShibaMultiBinding(bindings.map { it.path }, Singleton.get<FunctionConverter>(), function)
        }

        throw IllegalArgumentException()
    }

    private fun getBindings(function: ShibaFunction): List<ShibaBinding> {
        return function.paramter.map {
            when (it) {
                is ShibaBinding -> arrayListOf(it)
                is ShibaFunction -> getBindings(it)
                else -> null
            }
        }.filter { it != null }.flatMap { it!! }
    }

    private fun parseFunction(function: ShibaFunction, context: IShibaContext) : ShibaFunction {
        return function.apply {
            paramter = paramter.map {
                        when (it) {
                            is ShibaFunction -> parseFunction(it, context)
                            else -> {
                                val result = it.visit<Any>(context)
                                if (result is ShibaBinding && result.converter is RawConverter) {
                                    result.parameter
                                }
                                result
                            }
                        }
                    }.filter { it != null }.map { it!! }
        }
    }
}

private class BasicValueVisitor(override val type: Class<*> = BasicValue::class.java) : AbsValueVisitor<BasicValue, Any?>() {
    override fun parse(tree: BasicValue, context: IShibaContext): Any? {
        return tree.value
    }
}