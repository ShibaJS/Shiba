package moe.tlaster.shiba.extensionExecutor

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import moe.tlaster.shiba.IShibaContext
import moe.tlaster.shiba.common.Singleton
import moe.tlaster.shiba.converters.ShibaConverter
import moe.tlaster.shiba.dataBinding.ShibaBinding
import moe.tlaster.shiba.type.ShibaExtension

private const val dataContextPath = "dataContext"
class JsonExecutor(override val name: String = "json") : IExtensionExecutor {
    override fun provideValue(context: IShibaContext?, extension: ShibaExtension): Any? {
        val path = extension.value
        val bindingPath = dataContextPath
        return ShibaBinding(bindingPath).apply {
            source = context
            parameter = path
            converter = Singleton.get<JsonConverter>()
        }
    }
}

class JsonConverter : ShibaConverter() {
    override fun convert(value: Any?, parameter: Any?): Any? {
        if (value !is JsonNode) {
            return null
        }

        if (parameter !is String || parameter.isEmpty()) {
            return toNative(value)
        }

        var token = value as JsonNode
        parameter.split('.').forEach {
            token = token[it]
        }

        return toNative(token)
    }


    private fun toNative(jsonNode: JsonNode): Any? {

        return when {
            jsonNode.isNull -> null
            jsonNode.isBoolean -> jsonNode.booleanValue()
            jsonNode.isTextual -> jsonNode.textValue()
            jsonNode.isShort -> jsonNode.shortValue()
            jsonNode.isInt -> jsonNode.intValue()
            jsonNode.isLong -> jsonNode.longValue()
            jsonNode.isDouble -> jsonNode.doubleValue()
            jsonNode.isFloat -> jsonNode.floatValue()
            jsonNode.isBigDecimal -> jsonNode.decimalValue()
            jsonNode.isBigInteger -> jsonNode.bigIntegerValue()
            jsonNode.isBinary -> jsonNode.binaryValue()
            jsonNode.isNumber -> jsonNode.numberValue()
            else -> jsonNode
        }
    }

}