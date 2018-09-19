package moe.tlaster.shiba

open class ShibaFunctionExecutor {
    fun execute(function: ShibaFunction, dataContext: Any?) : Any? {
        return Shiba.configuration.converterExecutor.execute(function.name, function.parameter.map { getParameterValue(it, dataContext) }.toTypedArray())
    }

    private fun getParameterValue(parameter: Any, dataContext: Any?) : Any? {
        return when (parameter) {
            is ShibaFunction -> execute(parameter, dataContext)
            is ShibaExtension -> getValueFromDataContext(parameter, dataContext)
            else -> parameter
        }
    }

    protected open fun getValueFromDataContext(extension: ShibaExtension, dataContext: Any?) : Any? {
        return Shiba.configuration.extensionExecutors.firstOrNull { it.name == extension.type }?.provideValue(null, extension)
    }
}

class SingleBindingShibaFunctionExecutor : ShibaFunctionExecutor() {
    override fun getValueFromDataContext(extension: ShibaExtension, dataContext: Any?): Any? {
        return dataContext
    }
}

