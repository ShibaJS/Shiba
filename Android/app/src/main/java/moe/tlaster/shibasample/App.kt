package moe.tlaster.shibasample

import android.app.Application
import moe.tlaster.shiba.Shiba

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Shiba.configuration.addConverter("function awesome(value) { return value + \" is awesome!\" }")
        Shiba.configuration.addConverter("function reverse(value) { return value.split(\"\").reverse().join(\"\"); }")
    }
}