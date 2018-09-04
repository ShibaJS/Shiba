package moe.tlaster.shiba

import java.util.concurrent.ConcurrentHashMap

object Singleton {
    val Instances = ConcurrentHashMap<Class<*>, Any>()
    inline fun <reified T> get() : T =
            Instances.getOrPut(T::class.java) {T::class.java.newInstance()} as T
} 