package moe.tlaster.shiba

import moe.tlaster.shiba.converters.ShibaConverter

open class ShibaBinding(val path: String, val converter: ShibaConverter? = null, val parameter: Any? = null) {
    open fun execute(value: Any?) : Any? {
        return converter?.convert(value, parameter) ?: value
    }
}

class ShibaMultiBinding(val paths: List<String>, converter: ShibaConverter?, parameter: Any?) : ShibaBinding("", converter, parameter)

