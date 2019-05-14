package moe.tlaster.shiba.type

import moe.tlaster.shiba.Shiba
//
//data class ShibaToken(val prefix: String, val value: String) {
//    override fun toString(): String {
//        return if (prefix.isEmpty()) value else "$prefix:$value"
//    }
//
//    fun isCurrentPlatformAndCheckValue(value: String): Boolean {
//        return isCurrentPlatform() && value == this.value
//    }
//
//    fun isCurrentPlatform(): Boolean {
//        return (prefix == Shiba.configuration.platformType || prefix.isEmpty())
//    }
//}