package moe.tlaster.shiba

import moe.tlaster.shiba.parser.ShibaParserWrapper
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
        val parserWrapper = ShibaParserWrapper()
        val tree = parserWrapper.parse("stackLayout {orientation: horizontal, padding: [8, 0], alpha: 50%, etgsa: \$bind aaa, input{content: fdsafds(dsafs(\$bind dasfd, dfsa), dfsafs)}}")
        Assert.assertNotNull(tree)
    }
}