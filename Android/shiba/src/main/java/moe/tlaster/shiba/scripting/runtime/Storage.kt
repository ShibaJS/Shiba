package moe.tlaster.shiba.scripting.runtime

import android.app.Application
import android.content.Context
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSObject

class Storage(val application: Application, context: JSContext) : JSObject(context) {
    private val sharedPreferencesKey = "Shiba"

    @jsexport
    public fun save(key: String, value: Any) {
        val sharedPreferences = application.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE).edit()
        when (value) {
            is String -> sharedPreferences.putString(key, value)
            is Int -> sharedPreferences.putInt(key, value)
            is Long -> sharedPreferences.putLong(key, value)
            is Number -> sharedPreferences.putFloat(key, value.toFloat())
            is Boolean -> sharedPreferences.putBoolean(key, value)
        }
        sharedPreferences.apply()
    }

    @jsexport
    public fun load(key: String, defaultValue: Any) : Any? {
        val sharedPreferences = application.getSharedPreferences(sharedPreferencesKey, Context.MODE_PRIVATE)
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue)
            is Int -> sharedPreferences.getInt(key, defaultValue)
            is Long -> sharedPreferences.getLong(key, defaultValue)
            is Number -> sharedPreferences.getFloat(key, defaultValue.toFloat())
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue)
            else -> null
        }
    }
}