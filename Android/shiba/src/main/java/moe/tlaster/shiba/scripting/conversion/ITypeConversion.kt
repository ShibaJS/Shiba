package moe.tlaster.shiba.scripting.conversion

interface ITypeConversion {
    val objectType: Class<*>
    fun convert(value: Any): Any?
}