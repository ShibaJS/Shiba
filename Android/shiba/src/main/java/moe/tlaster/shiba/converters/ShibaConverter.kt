package moe.tlaster.shiba.converters

import moe.tlaster.shiba.ShibaFunction
import moe.tlaster.shiba.ShibaFunctionExecutor
import moe.tlaster.shiba.SingleBindingShibaFunctionExecutor
import moe.tlaster.shiba.Singleton

abstract class ShibaConverter {
    abstract fun convert(value: Any?, parameter: Any?) : Any?
}

class RawConverter : ShibaConverter() {
    override fun convert(value: Any?, parameter: Any?) : Any? {
        return value
    }
}

open class FunctionConverter : ShibaConverter() {
    open val Executor = Singleton.get<ShibaFunctionExecutor>()

    override fun convert(value: Any?, parameter: Any?): Any? {
        if (parameter !is ShibaFunction) {
            throw IllegalArgumentException()
        }
        return Executor.execute(parameter, value)
    }
}

class SingleBindingFunctionConverter : FunctionConverter() {
    override val Executor = Singleton.get<SingleBindingShibaFunctionExecutor>()
}