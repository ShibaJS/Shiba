package moe.tlaster.shiba.scripting

import moe.tlaster.shiba.scripting.ITypeConversion

interface IArrayConversion : ITypeConversion {
    fun convert(value: Any): List<Any?>
}