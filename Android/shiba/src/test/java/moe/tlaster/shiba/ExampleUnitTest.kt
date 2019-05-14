package moe.tlaster.shiba

import com.fasterxml.jackson.databind.ObjectMapper
import moe.tlaster.shiba.visitors.ValueVisitor
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
        val json = "{\n" +
                "    \"children\": [\n" +
                "        {\n" +
                "            \"children\": [\n" +
                "                \"dsafdsa\",\n" +
                "                {\n" +
                "                    \"children\": [],\n" +
                "                    \"className\": \"IView\",\n" +
                "                    \"name\": \"haha\",\n" +
                "                    \"properties\": []\n" +
                "                }\n" +
                "            ],\n" +
                "            \"className\": \"IView\",\n" +
                "            \"name\": \"text\",\n" +
                "            \"properties\": [\n" +
                "                {\n" +
                "                    \"className\": \"IProperty\",\n" +
                "                    \"name\": \"aaa\",\n" +
                "                    \"value\": {\n" +
                "                        \"className\": \"IShibaExtension\",\n" +
                "                        \"scriptName\": \"dsads\",\n" +
                "                        \"target\": \"haha\",\n" +
                "                        \"type\": \"binding\"\n" +
                "                    },\n" +
                "                    \"valueType\": 0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"className\": \"IProperty\",\n" +
                "                    \"name\": \"bbb\",\n" +
                "                    \"value\": {\n" +
                "                        \"className\": \"IShibaExtension\",\n" +
                "                        \"scriptName\": \"_a49638d8022f7aab03c73c958414cc898744f0e50048337a42fbe3adf38c46a3\",\n" +
                "                        \"target\": \"haha\",\n" +
                "                        \"type\": \"binding\"\n" +
                "                    },\n" +
                "                    \"valueType\": 0\n" +
                "                },\n" +
                "                {\n" +
                "                    \"className\": \"IProperty\",\n" +
                "                    \"name\": \"text\",\n" +
                "                    \"value\": \"\\\"sadsadsa\\\"\",\n" +
                "                    \"valueType\": 3\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"className\": \"IView\",\n" +
                "    \"name\": \"stack\",\n" +
                "    \"properties\": []\n" +
                "}"
        val result = ValueVisitor.visit(ObjectMapper().readTree(json), null)
        assert(result != null)
    }

}


