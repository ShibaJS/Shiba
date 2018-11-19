package moe.tlaster.shiba.common

import java.util.concurrent.ConcurrentHashMap

internal object Singleton {
    val Instances = ConcurrentHashMap<Class<*>, Any>()
    inline fun <reified T> get() : T =
            Instances.getOrPut(T::class.java) {T::class.java.newInstance()} as T
} 