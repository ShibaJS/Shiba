package moe.tlaster.shiba.parser

import moe.tlaster.shiba.View
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

class ShibaParserWrapper {
    private fun parseGrammarTree(input: String): ParseTree {
        val stream = CharStreams.fromString(input)
        val lexer = ShibaLexer(stream)
        val tokens =  CommonTokenStream(lexer)
        val parser = ShibaParser(tokens).apply {
            buildParseTree = true
        }
        return parser.root()
    }

    public fun parse(input: String): View {
        val tree = parseGrammarTree(input)
        return buildViewTree(tree)
    }

    private fun buildViewTree(tree: ParseTree): View {
        if (tree is ShibaParser.RootContext) {
            return buildViewTree(tree.obj())
        } else if (tree is ShibaParser.ObjContext) {
            
        }
    }

    private fun findTypes(name: String): List<Class<Any>> {
        throw NotImplementedError()
    }
}


annotation class ExportView {

}