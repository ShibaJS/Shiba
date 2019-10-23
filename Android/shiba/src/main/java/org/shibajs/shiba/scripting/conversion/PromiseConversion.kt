package org.shibajs.shiba.scripting.conversion

import android.util.ArrayMap
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSFunction
import org.liquidplayer.javascript.JSValue
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


class PromiseConversion(override val objectType: Class<*> = Deferred::class.java) : ITypeConversion {
    override fun fromJSValue(value: JSValue): Any? {
        if (!value.isObject) {
            return null
        }
        val obj = value.toObject()
        return GlobalScope.async {
            suspendCoroutine<Any?> { cont ->
                obj.property("then").toFunction().call(obj,
                        object : JSFunction(obj.context, "then") {
                            fun then(value: JSValue) {
                                cont.resume(value.toNative())
                            }
                        },
                        object : JSFunction(obj.context, "reject") {
                            fun reject(value: JSValue) {
                                cont.resumeWithException(Throwable(value.toNative().toString()))
                            }
                        })

            }
        }
    }

    override fun toJSValue(context: JSContext, value: Any): JSValue {
        val deferred = value as Deferred<*>
        val promiseObj = context.evaluateScript(
                "(()=>{let promise = {}; promise.promise=new Promise((resolve,reject)=>{promise.resolve=resolve; promise.reject=reject}); return promise;})();"
        ).toObject()
        val resolve = promiseObj.property("resolve").toFunction()
        val reject = promiseObj.property("reject").toFunction()
        val promise = promiseObj.property("promise").toObject()
        GlobalScope.launch {
            kotlin.runCatching {
                deferred.await()
            }.onSuccess {
                resolve.call(promise, it.toJSValue(context))
            }.onFailure {
                reject.call(promise, JSValue(context, ArrayMap<String, String>().apply {
                    put("message", it.message ?: "error")
//                        put("stackTrace", exceptionOrNull()?.printStackTrace() ?: "stackTrace")
                }))
            }
        }
        return promise
    }

    override fun canConvert(value: JSValue): Boolean {
        if (!value.isObject) {
            return false
        }
        val prototype = value.toObject().prototype()
        if (!prototype.isObject) {
            return false
        }
        val obj = prototype.toObject()
        if (!obj.hasProperty("constructor")) {
            return false
        }
        val constructor = obj.property("constructor")
        if (!constructor.isObject) {
            return false
        }
        val constructorObject = constructor.toObject()
        if (!constructorObject.hasProperty("name")) {
            return false
        }
        val name = constructorObject.property("name")
        return name.isString && name.toString() == "Promise"
    }

}