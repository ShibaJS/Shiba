package moe.tlaster.shiba.scripting

import moe.tlaster.shiba.scripting.ITypeConversion

interface IObjectConversion : ITypeConversion {
    fun convert(value: Any): Map<String, Any?>
}