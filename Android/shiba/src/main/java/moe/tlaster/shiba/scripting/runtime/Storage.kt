package moe.tlaster.shiba.scripting.runtime

import android.app.Application
import android.content.Context
import moe.tlaster.shiba.scripting.conversion.toNative
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSObject
import org.liquidplayer.javascript.JSValue

class Storage(val application: Context, context: JSContext) : JSObject(context) {
    private val sharedPreferencesKey = "Shiba"

    @jsexport
    public fun save(key: String, value: JSValue) {
        val target = value.toNative()
        val sharedPreferences = application.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE).edit()
        when (target) {
            is Int -> sharedPreferences.putInt(key, target)
            is Long -> sharedPreferences.putLong(key, target)
            is Number -> sharedPreferences.putFloat(key, target.toFloat())
            is Boolean -> sharedPreferences.putBoolean(key, target)
            is String? -> sharedPreferences.putString(key, target)
        }
        sharedPreferences.apply()
    }

    @jsexport
    public fun load(key: String, defaultValue: JSValue) : Any? {
        val target = defaultValue.toNative()
        val sharedPreferences = application.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
        return when (target) {
            is Int -> sharedPreferences.getInt(key, target)
            is Long -> sharedPreferences.getLong(key, target)
            is Number -> sharedPreferences.getFloat(key, target.toFloat())
            is Boolean -> sharedPreferences.getBoolean(key, target)
            is String? -> sharedPreferences.getString(key, target)
            else -> null
        }
    }
}