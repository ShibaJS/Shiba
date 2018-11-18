package moe.tlaster.shiba.type

data class ShibaExtension(val type: String, val value: BasicValue?, val script: String?) {
    override fun toString(): String {
        return if (value != null) "\$$type $value $script" else "\$$type $script"
    }
}