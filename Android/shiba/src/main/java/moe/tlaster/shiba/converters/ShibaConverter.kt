package moe.tlaster.shiba.converters

import moe.tlaster.shiba.ShibaFunction
import moe.tlaster.shiba.ShibaFunctionExecutor
import moe.tlaster.shiba.SingleBindingShibaFunctionExecutor
import moe.tlaster.shiba.Singleton

abstract class ShibaConverter {
    abstract fun convert(value: Any?, parameter: Any?): Any?
}

class RawConverter : ShibaConverter() {
    override fun convert(value: Any?, parameter: Any?): Any? {
        return value
    }
}

open class FunctionConverter : ShibaConverter() {
    open val executor = Singleton.get<ShibaFunctionExecutor>()

    override fun convert(value: Any?, parameter: Any?): Any? {
        return when (parameter) {
            is ShibaFunction -> {
                executor.execute(parameter, value)
            }
            is ShibaConverterParameter -> {
                if (parameter.innerConverter != null) {
                    val innerResult = parameter.innerConverter.convert(value, parameter.innerParameter)
                    executor.execute(parameter.function, innerResult)
                } else {
                    executor.execute(parameter.function, value)
                }
            }
            else -> throw NotImplementedError()

        }
    }
}

class SingleBindingFunctionConverter : FunctionConverter() {
    override val executor = Singleton.get<SingleBindingShibaFunctionExecutor>()
}

internal data class ShibaConverterParameter(
        val innerConverter: ShibaConverter?,
        val innerParameter: Any?,
        val function: ShibaFunction
)