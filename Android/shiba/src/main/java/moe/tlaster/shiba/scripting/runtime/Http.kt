package moe.tlaster.shiba.scripting.runtime

import awaitString
import awaitStringResponse
import com.github.kittinunf.fuel.httpGet
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import moe.tlaster.shiba.scripting.conversion.toJSValue
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSObject
import org.liquidplayer.javascript.JSValue

class Http(ctx: JSContext?) : JSObject(ctx) {
    @jsexport
    public fun get(url: String, cookie: String): JSValue {
        return GlobalScope.async {
            val result = url.httpGet().header(Pair("Cookie", cookie)).awaitString()
            return@async result
        }.toJSValue(context)
    }
}