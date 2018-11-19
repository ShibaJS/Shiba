package moe.tlaster.shiba.parser

import moe.tlaster.shiba.type.*
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.TerminalNode

interface IShibaVisitor {
    val type: Class<*>
    fun visit(tree: ParseTree): Any
}

private val visitors = ArrayList<IShibaVisitor>().apply {
    add(ViewVisitor())
    add(PropertyVisitor())
    add(ValueVisitor())
    add(ShibaTokenVisitor())
    add(ShibaMapVisitor())
    add(BasicValueVisitor())
    add(FunctionVisitor())
    add(ArrayVisitor())
    add(ShibaExtensionVisitor())
}

private fun <T> ParseTree.visit(): T? {
    return visitors.find { it.type == this.javaClass }?.visit(this) as T?
}

private abstract class AbsVisitor<T : ParseTree, K : Any> : IShibaVisitor {
    override fun visit(tree: ParseTree): Any {
        return parse(tree as T)
    }

    abstract fun parse(tree: T): K
}

private final class ViewVisitor(override val type: Class<*> = ShibaParser.ViewContext::class.java) : AbsVisitor<ShibaParser.ViewContext, View>() {
    override fun parse(tree: ShibaParser.ViewContext): View {
        val viewName = tree.identifier().visit<ShibaToken>()
                ?: throw IllegalArgumentException("view name can not be null at line:${tree.start.line} column:${tree.start.startIndex}")
        val view = View(viewName)
        val defaultValue = tree.value()?.visit<Any>()
        if (defaultValue != null) {
            view.defaultValue = defaultValue
        }
        if (tree.property() != null) {
            view.properties += tree.property().map { it.visit<Property>() }.filter { it != null }.map { it!! }
        }
        if (tree.view() != null) {
            view.children += tree.view()
                    .map { it.visit<View>() }
                    .filter { it != null }
                    .map { it!! }
                    .map { it.apply { it.parent = view } }
        }
        return view
    }
}

private final class PropertyVisitor(override val type: Class<*> = ShibaParser.PropertyContext::class.java) : AbsVisitor<ShibaParser.PropertyContext, Property>() {
    override fun parse(tree: ShibaParser.PropertyContext): Property {
        val tokenName = tree.identifier().visit<ShibaToken>()
        val value = tree.value().visit<Any>()
        if (tokenName != null && value != null) {
            return Property(tokenName, value)
        }
        throw IllegalArgumentException("propertyPath can not contain null at line:${tree.start.line} column: ${tree.start.startIndex}")
    }
}

private final class ValueVisitor(override val type: Class<*> = ShibaParser.ValueContext::class.java) : AbsVisitor<ShibaParser.ValueContext, Any>() {
    override fun parse(tree: ShibaParser.ValueContext): Any {
        val result = tree.children.first().visit<Any>()
        if (result == null){
            throw NotImplementedError("${tree.children.first().text} is not implement")
        } else {
            return result
        }
    }
}

private final class ShibaExtensionVisitor(override val type: Class<*> = ShibaParser.ShibaExtensionContext::class.java) : AbsVisitor<ShibaParser.ShibaExtensionContext, ShibaExtension>() {
    override fun parse(tree: ShibaParser.ShibaExtensionContext): ShibaExtension {
        val name = tree.Identifier().text
        val value = tree.basicValue()?.visit<BasicValue>()
        val script = tree.Script()?.text
        return ShibaExtension(name, value, script)
    }

}

private final class ShibaTokenVisitor(override val type: Class<*> = ShibaParser.IdentifierContext::class.java) : AbsVisitor<ShibaParser.IdentifierContext, ShibaToken>() {
    override fun parse(tree: ShibaParser.IdentifierContext): ShibaToken {
        when(tree.Identifier().size) {
            1 -> return ShibaToken("", tree.Identifier().first().text)
            2 -> return ShibaToken(tree.Identifier().first().text, tree.Identifier().last().text)
        }
        throw IllegalArgumentException("ShibaToken can not be null at line ${tree.start.line} column: ${tree.start.startIndex}")
    }
}

private final class ShibaMapVisitor(override val type: Class<*> = ShibaParser.MapContext::class.java) : AbsVisitor<ShibaParser.MapContext, ShibaMap>() {
    override fun parse(tree: ShibaParser.MapContext): ShibaMap {
        return ShibaMap(tree.property().map { it.visit<Property>() }.filter { it != null }.map { it!! })
    }
}

private final class BasicValueVisitor(override val type: Class<*> = ShibaParser.BasicValueContext::class.java) : AbsVisitor<ShibaParser.BasicValueContext, BasicValue>() {
    override fun parse(tree: ShibaParser.BasicValueContext): BasicValue {
        var type = ShibaValueType.Token
        var targetValue: Any? = null
        val token = tree.getChild(0) as TerminalNode
        when (token.symbol.type) {
            ShibaParser.String -> {
                type = ShibaValueType.String
                targetValue = token.text.trim('"')
            }
            ShibaParser.Number -> {
                type = ShibaValueType.Number
                targetValue = token.text.toBigDecimal()
            }
            ShibaParser.Boolean -> {
                type = ShibaValueType.Boolean
                targetValue = token.text?.toBoolean()
            }
            ShibaParser.Null -> {
                type = ShibaValueType.Null
                targetValue = null
            }
            ShibaParser.Identifier -> {
                type = ShibaValueType.Token
                targetValue = token.text
            }
        }
        if (targetValue == null && type != ShibaValueType.Null) {
            throw IllegalArgumentException("basic value can not be null at line ${tree.start.line} column: ${tree.start.startIndex}")
        }
        return BasicValue(type, targetValue)
    }
}

private final class FunctionVisitor(override val type: Class<*> = ShibaParser.FunctionCallContext::class.java) : AbsVisitor<ShibaParser.FunctionCallContext, ShibaFunction>() {
    override fun parse(tree: ShibaParser.FunctionCallContext): ShibaFunction {
        val name = tree.Identifier().text
        if (tree.value() != null && tree.value().any()) {
            return ShibaFunction(name).apply {
                parameter = tree.value().map { it.visit<Any>() }.filter { it != null }.map { it!! }
            }
        }
        return ShibaFunction(name)
    }
}

private final class ArrayVisitor(override val type: Class<*> = ShibaParser.ArrayContext::class.java) : AbsVisitor<ShibaParser.ArrayContext, ShibaArray>() {
    override fun parse(tree: ShibaParser.ArrayContext): ShibaArray {
        return ShibaArray().apply {
            addAll(tree.value().map { it.visit<Any>() }.filter { it != null }.map { it!! })
        }
    }
}

class ShibaParserWrapper {
    private fun parseGrammarTree(input: String): ParseTree {
        val stream = CharStreams.fromString(input)
        val lexer = ShibaLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = ShibaParser(tokens).apply {
            buildParseTree = true
        }
        return parser.view()
    }
    fun parse(input: String): View? {
        val tree = parseGrammarTree(input)
        return tree.visit<View>()
    }
}
