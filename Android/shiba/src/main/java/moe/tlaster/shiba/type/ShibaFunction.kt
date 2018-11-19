package moe.tlaster.shiba.type

data class ShibaFunction(val name: String) {
    var parameter: List<Any> = ArrayList()
        internal set

    override fun toString(): String {
        return "$name(${parameter.joinToString(separator = ",") { it.toString() }})"
    }
}