package moe.tlaster.shiba.type

/**
 * Created by SE on 3/5.
 */

data class Property(val name: ShibaToken, val value: Any) {
    override fun toString(): String {
        return "$name = $value"
    }

}