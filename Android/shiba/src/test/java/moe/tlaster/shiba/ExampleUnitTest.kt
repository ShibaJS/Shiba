package moe.tlaster.shiba

import org.junit.Test

import org.junit.Assert.*
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    @Test
    @Throws(Exception::class)
    fun test() {
        val input = "<stack xmlns:uwp=\"UWP\">\n" +
                "    <text uwp:text=\"UWPText\" other=\"other\" />\n" +
                "    <uwp:text uwp:text=\"UWPText\" other=\"other\"/>\n" +
                "</stack>"
        val dom = DocumentBuilderFactory.newInstance().apply {
            isNamespaceAware = true
        }.newDocumentBuilder().parse(input)
        val nodeName = dom.documentElement.childNodes.item(1).nodeName
        val localName = dom.documentElement.childNodes.item(1).localName
        val namespaceURI = dom.documentElement.childNodes.item(1).namespaceURI
        assertNotNull(namespaceURI)
    }

}


