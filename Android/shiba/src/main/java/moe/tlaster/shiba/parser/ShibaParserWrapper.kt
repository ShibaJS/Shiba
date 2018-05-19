package moe.tlaster.shiba.parser

import moe.tlaster.shiba.Percent
import moe.tlaster.shiba.Shiba
import moe.tlaster.shiba.Thickness
import moe.tlaster.shiba.View
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree

interface IToken {
    val line: Int
    val column: Int
    val value: Any?
}


fun IBindingValue.getTokenValue(): String {
    return value.value.toString()
}

interface IBindingValue {
    val value: IToken
}

abstract class TokenBase<T>(override val line: Int, override val column: Int, override val value: T) : IToken

class BindingToken(line: Int, column: Int, value: Binding) : TokenBase<Binding>(line, column, value)

class NativeResourceToken(line: Int, column: Int, value: NativeResource) : TokenBase<NativeResource>(line, column, value)

class JsonPathToken(line: Int, column: Int, value: JsonPath) : TokenBase<JsonPath>(line, column, value)

class FunctionToken(line: Int, column: Int, value: Function) : TokenBase<Function>(line, column, value)

class ThicknessToken(line: Int, column: Int, value: Thickness) : TokenBase<Thickness>(line, column, value)

class PercentToken(line: Int, column: Int, value: Percent) : TokenBase<Percent>(line, column, value)

class NullToken(override val line: Int, override val column: Int, override val value: Any? = null) : IToken

class StringToken(line: Int, column: Int, value: String) : TokenBase<String>(line, column, value)

class NumberToken(line: Int, column: Int, value: Number) : TokenBase<Number>(line, column, value)

class BoolToken(line: Int, column: Int, value: Boolean) : TokenBase<Boolean>(line, column, value)

class SimpleToken(line: Int, column: Int, value: String) : TokenBase<String>(line, column, value)

class Function(val name: String, val paramter: List<IParamter>) : IParamter

interface IParamter

class ValueParamter(val value: IToken) : IParamter

class Binding(override val value: IToken) : IBindingValue

class JsonPath(override val value: IToken) : IBindingValue

class NativeResource(override val value: IToken) : IBindingValue


class ShibaParserWrapper {
    private fun parseGrammarTree(input: String): ParseTree {
        val stream = CharStreams.fromString(input)
        val lexer = ShibaLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = ShibaParser(tokens).apply {
            buildParseTree = true
        }
        return parser.root()
    }

    fun parse(input: String): View {
        val tree = parseGrammarTree(input)
        return buildViewTree(tree)
    }

    private fun buildViewTree(tree: ParseTree): View {
        return when (tree) {
            is ShibaParser.RootContext -> buildViewTree(tree.obj())
            is ShibaParser.ObjContext -> {
                val view = View(viewName = tree.start.text, properties = pairToMap(tree.pair()))
                if (tree.obj() != null && tree.obj().any()) {
                    view.children.addAll(tree.obj().map { buildViewTree(it) })
                }
                return view
            }
            else -> throw IllegalArgumentException()
        }
    }

    private fun pairToMap(pair: List<ShibaParser.PairContext>): Map<String, IToken> {
        return pair.map { it.start.text to getValue(it.value()) }.toMap()
    }

    private fun getStaticValue(value: ShibaParser.StaticvalueContext?): IToken? {
        if (value == null) {
            return null;
        }

        if (value.NULL() != null) {
            return NullToken(value.NULL().symbol.line, value.NULL().symbol.charPositionInLine)
        }

        if (value.BOOLEAN() != null) {
            return BoolToken(value.BOOLEAN().symbol.line, value.BOOLEAN().symbol.charPositionInLine, value.BOOLEAN().text.toBoolean())
        }

        if (value.NUMBER() != null) {
            return NumberToken(value.NUMBER().symbol.line, value.NUMBER().symbol.charPositionInLine, value.NUMBER().text.toBigDecimal())
        }

        if (value.percent() != null) {
            return PercentToken(value.percent().start.line, value.percent().start.charPositionInLine, Percent(value.percent().start.text))
        }

        if (value.thickness() != null) {
            return ThicknessToken(value.thickness().start.line, value.thickness().start.charPositionInLine, Thickness(value.thickness().text))
        }

        if (value.STRING() != null) {
            return StringToken(value.STRING().symbol.line, value.STRING().symbol.charPositionInLine, value.STRING().text.trim('"'))
        }

        if (value.TOKEN() != null) {
            return SimpleToken(value.TOKEN().symbol.line, value.TOKEN().symbol.charPositionInLine, value.TOKEN().text)
        }

        return null
    }

    private fun getValue(value: ShibaParser.ValueContext?): IToken {
        if (value == null) {
            return NullToken(0, 0)
        }
        val staticValue = getStaticValue(value.staticvalue())
        if (staticValue != null) {
            return staticValue
        }

        if (value.binding() != null) {
            return BindingToken(value.binding().start.line, value.binding().start.charPositionInLine, Binding(getStaticValue(value.binding().staticvalue())
                    ?: throw IllegalStateException()))
        }

        if (value.resource() != null) {
            return NativeResourceToken(value.resource().start.line, value.resource().start.charPositionInLine, NativeResource(getStaticValue(value.resource().staticvalue())
                    ?: throw IllegalStateException()))
        }

        if (value.jsonpath() != null) {
            return JsonPathToken(value.jsonpath().start.line, value.jsonpath().start.charPositionInLine, JsonPath(getStaticValue(value.jsonpath().staticvalue())
                    ?: throw IllegalStateException()))
        }

        if (value.dic() != null) {
            val pair = value.dic().pair().firstOrNull { it.TOKEN().symbol.text == Shiba.configuration.platformType }
            return getValue(pair?.value())
        }

        if (value.func() != null) {
            return FunctionToken(value.func().start.line, value.func().start.charPositionInLine, getFunc(value.func()))
        }


        throw IllegalStateException()
    }

    private fun getFunc(func: ShibaParser.FuncContext): Function {
        return Function(func.TOKEN().symbol.text, func.paramter().map { if (it.func() == null) getValueParameter(it.value()) else getFunc(it.func()) }.toList())
    }

    private fun getValueParameter(value: ShibaParser.ValueContext): ValueParamter {
        return ValueParamter(getValue(value))
    }

}

