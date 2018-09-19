package moe.tlaster.shibasample

import android.app.Application
import moe.tlaster.shiba.Shiba

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Shiba.addConverter("function awesome(value) { return value + \" is awesome!\" }")
        Shiba.addConverter("function reverse(value) { if(value == null) return \"\"; return value.split(\"\").reverse().join(\"\"); }")
    }
}