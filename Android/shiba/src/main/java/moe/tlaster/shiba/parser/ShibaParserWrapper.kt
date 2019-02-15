package moe.tlaster.shiba.parser

import moe.tlaster.shiba.type.*
import org.w3c.dom.Attr
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.*

private val Node.name
    get() = this.localName
private val Node.namespaceName
    get() = this.namespaceURI

private fun Node.hasChildElement(): Boolean {
    if (!this.hasChildNodes()) {
        return false
    }
    for (index in 0 until this.childNodes.length) {
        val item = this.childNodes.item(index)
        if (item is Element) {
            return true
        }
    }
    return false
}

private fun Node.childElements(): List<Element> {
    if (!this.hasChildElement()) {
        return listOf()
    }
    return (0 until childNodes.length).map { index ->
        childNodes.item(index)
                ?.takeIf {
                    it is Element
                }?.let {
                    it as Element
                }
    }.filter {
        it != null
    }.map {
        it!!
    }
}

interface IShibaVisitor {
    val type: Class<*>
    val returnType: Class<*>
    fun visit(tree: Any): Any
}

private val visitors = ArrayList<IShibaVisitor>().apply {
    add(ViewVisitor())
    add(ElementPropertyVisitor())
    add(PropertyVisitor())
    add(ShibaTokenVisitor())
    add(ShibaMapVisitor())
    add(FunctionVisitor())
    add(ShibaExtensionVisitor())
}

private inline fun <reified T> Any.visit(): T? {
    return visitors.find { it.type == this.javaClass && it.returnType == T::class.java }?.visit(this) as T?
}

private abstract class AbsVisitor<T : Any, K : Any> : IShibaVisitor {
    override fun visit(tree: Any): Any {
        return parse(tree as T)
    }

    abstract fun parse(tree: T): K
}

private final class ViewVisitor(override val type: Class<*> = Element::class.java, override val returnType: Class<*> = View::class.java) : AbsVisitor<Element, View>() {
    override fun parse(tree: Element): View {
        val viewName = (tree as Node).visit<ShibaToken>()
                ?: throw IllegalArgumentException("view name can not be null at $tree")
        val view = View(viewName)
        for (index in 0 until tree.attributes.length) {
            tree.attributes.item(index)?.let {
                it as Attr
            }?.let {
                it.visit<Property>()
            }?.let {
                view.properties += it
            }
        }
        if (!tree.hasChildElement()) {
            // TODO: default property
        } else {
            tree.childElements().forEach { element ->
                if (element.localName.contains('.')
                        && element.localName.split('.').firstOrNull() == viewName.value
                        && element.namespaceName == viewName.prefix) {
                    element.visit<Property>()?.let {
                        view.properties += it
                    }
                } else {
                    element.visit<View>()?.let {
                        view.children += it
                    }
                }
            }
        }

        return view
    }
}

private fun parsePropertyValue(propertyValue: String): Any {
    val OpenCurly = '{'
    val CloseCurly = '}'
    val OpenBracket = '['
    val CloseBracket = ']'
    val ExtensionStart = '$'
    val value = propertyValue.trim()
    when {
        value.startsWith(OpenCurly) && value.endsWith(CloseCurly) -> {
            val subValue = value.substring(1, value.length - 2)
            val function = subValue.visit<ShibaFunction>()
            if (function != null) {
                return function
            }
        }
        value.startsWith(OpenBracket) && value.endsWith(CloseBracket) -> {
            val subValue = value.substring(1, value.length - 2)
            val map = subValue.visit<ShibaMap>()
            if (map != null) {
                return map
            }
        }
        value.startsWith(ExtensionStart) -> {
            val extension = value.visit<ShibaExtension>()
            if (extension != null) {
                return extension
            }
        }
    }

    if (value == "true" || value == "false") {
        return value.toBoolean()
    }


    val numberValue = value.toBigDecimalOrNull()
    if (numberValue != null) {
        return numberValue
    }
    return value
}

private final class ElementPropertyVisitor(override val type: Class<*> = Element::class.java, override val returnType: Class<*> = Property::class.java) : AbsVisitor<Element, Property>() {
    override fun parse(tree: Element): Property {
        val name = (tree as Node).visit<ShibaToken>()
                ?: throw IllegalArgumentException("view name can not be null at $tree")
        if (tree.hasChildElement()) {
            tree.childElements().firstOrNull()?.visit<View>()?.let {
                return Property(name, it)
            }
        }
        return Property(name, parsePropertyValue(tree.textContent))
    }
}

private final class PropertyVisitor(override val type: Class<*> = Attr::class.java, override val returnType: Class<*> = Property::class.java) : AbsVisitor<Attr, Property>() {
    override fun parse(tree: Attr): Property {
        val tokenName = (tree as Node).visit<ShibaToken>()
                ?: throw IllegalArgumentException("view name can not be null at $tree")
        val value = parsePropertyValue(tree.value)
        return Property(tokenName, value)
    }
}


private final class ShibaExtensionVisitor(override val type: Class<*> = String::class.java, override val returnType: Class<*> = ShibaExtension::class.java) : AbsVisitor<String, ShibaExtension>() {
    private val ScriptStart = '{'
    private val ExtensionStart = '$'
    override fun parse(tree: String): ShibaExtension {
        if (!tree.startsWith(ExtensionStart)) {
            throw IllegalArgumentException("wrong shiba extension at $tree")
        }
        var value = tree.trimStart(ExtensionStart)
        var index = tree.indexOf(' ')
        val name = value.substring(0, index)
        value = value.substring(index + 1, value.length - index - 1)
        index = value.indexOf(ScriptStart)
        if (index == -1) {
            return ShibaExtension(name.trim(), value.trim(), null)
        }
        val bindingValue = value.substring(0, index).trim()
        value = value.substring(index + 1, value.length - index - 1).trim()
        val script = value.substring(1, value.length - 2)
        return ShibaExtension(name.trim(), bindingValue, script)
    }

}

private final class ShibaTokenVisitor(override val type: Class<*> = Node::class.java, override val returnType: Class<*> = ShibaToken::class.java) : AbsVisitor<Node, ShibaToken>() {
    override fun parse(tree: Node): ShibaToken {
        return if (tree.prefix.isNullOrEmpty()) {
            ShibaToken("", tree.name)
        } else {
            ShibaToken(tree.namespaceName, tree.name)
        }
    }
}

private final class ShibaMapVisitor(override val type: Class<*> = String::class.java, override val returnType: Class<*> = ShibaMap::class.java) : AbsVisitor<String, ShibaMap>() {
    private val EqualSign = '='
    private val Comma = ','
    override fun parse(tree: String): ShibaMap {
        return ShibaMap(tree
                .split(Comma)
                .map { it.trim() }
                .map { it.split(EqualSign) }
                .map { it.first() to it.last() }
                .toMap())
    }
}

private final class FunctionVisitor(override val type: Class<*> = String::class.java, override val returnType: Class<*> = ShibaFunction::class.java) : AbsVisitor<String, ShibaFunction>() {
    private val Comma = ','
    override fun parse(tree: String): ShibaFunction {
        val index = tree.indexOf('(')
        val name = tree.substring(0, index)
        val function = ShibaFunction(name.trim())
        val value = tree.substring(index + 1, tree.length - index - 2)
        val param = value.split(Comma).map {
            parsePropertyValue(it)
        }
        function.parameter += param
        return function
    }
}


class ShibaParserWrapper {
    private fun parseGrammarTree(input: String): Element? {
        val dom = DocumentBuilderFactory.newInstance().apply {
            isNamespaceAware = true
        }.newDocumentBuilder().parse(InputSource(StringReader(input)))
        return dom.documentElement
    }

    fun parse(input: String): View? {
        val tree = parseGrammarTree(input)
        return tree?.visit<View>()
    }
}
