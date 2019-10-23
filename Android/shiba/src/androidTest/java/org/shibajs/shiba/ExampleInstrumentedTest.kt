package org.shibajs.shiba

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.liquidplayer.javascript.JSContext
import org.liquidplayer.javascript.JSFunction

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
//        val runtime = JSContext()
//
//        runtime.evaluateScript("function resolveAfter2Seconds() {\n" +
//                "  return new Promise(resolve => {\n" +
//                "    setTimeout(() => {\n" +
//                "      resolve('resolved');\n" +
//                "    }, 2000);\n" +
//                "  });\n" +
//                "}\n" +
//                "\n" +
//                "async function asyncCall() {\n" +
//                "  var result = await resolveAfter2Seconds();\n" +
//                "  return result;\n" +
//                "  // expected output: 'resolved'\n" +
//                "}")
//
//        val obj = runtime.property("asyncCall").toFunction()
//        if (obj is JSFunction) {
//            val result = obj.call()
//            assert(result != null)
//        }
    }
}
