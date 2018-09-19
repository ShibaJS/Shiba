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
        val list = "dataContext.dsadsa".split('.')
        val a = list.indexOfFirst { it == "dsadsa" }
        val c = list.drop(a)
        val tree = ShibaParserWrapper().parse("stack { text -> awesome(reverse(\$bind UWPText)) text { color = \"#ffff00\" text = \"fdsafdsaf\" size = 20 } input -> \$bind UWPText }")
        assert(tree != null)
    }

}


