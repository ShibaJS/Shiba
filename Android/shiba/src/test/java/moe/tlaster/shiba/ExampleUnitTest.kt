package moe.tlaster.shiba

import moe.tlaster.shiba.parser.ShibaParser
import moe.tlaster.shiba.parser.ShibaParserWrapper
import org.antlr.v4.runtime.tree.ParseTree
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun test() {

    }

    fun check(tree: ParseTree) : Boolean {
        return tree.javaClass == type
    }

    val type: Class<*> = ShibaParser.ViewContext::class.java
}


