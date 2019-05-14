package moe.tlaster.shiba.commonProperty

import android.view.ViewGroup
import moe.tlaster.shiba.NativeView

interface ICommonProperty {
    val name: String
    fun handle(targetValue: Any?, nativeView: NativeView, parent: ViewGroup)
}

abstract class AbsCommonProperty<T> : ICommonProperty {
    override fun handle(targetValue: Any?, nativeView: NativeView, parent: ViewGroup) {
        val value = targetValue as? T
        if (value != null) {
            setValue(value, nativeView, parent)
        }
    }

    abstract fun setValue(value: T, nativeView: NativeView, parent: ViewGroup)
}