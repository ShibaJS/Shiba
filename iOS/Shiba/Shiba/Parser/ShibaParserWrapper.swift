//
// Created ?by 高垣朝陽 on 2018/8/9.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation
import Antlr4

protocol IShibaVisitor {
    var type: Any.Type { get }
    func visit(tree: ParseTree) -> Any
}

private class Visitors {
    internal static let visitors: [IShibaVisitor] = [
        ViewVisitor(),
        PropertyVisitor(),
        ValueVisitor(),
        ShibaTokenVisitor(),
        ShibaMapVisitor(),
        BasicValueVisitor(),
        FunctionVisitor(),
        ArrayVisitor(),
        ShibaExtensionVisitor(),
    ]
}

extension ParseTree {
    func visit<T>() -> T {
        let visitor = Visitors.visitors.first { it in
            it.type == type(of: self)
        };
        return visitor?.visit(tree: self) as! T
    }
}

private class ShibaVisitor<T, K>: IShibaVisitor where T: ParseTree {

    func visit(tree: ParseTree) -> Any {
        return parse(tree: tree as! T) as Any
    }

    func parse(tree: T) -> K? {
        return nil
    }

    var type: Any.Type = T.self
}


private class ViewVisitor: ShibaVisitor<ShibaParser.ViewContext, View> {
    override func parse(tree: ShibaParser.ViewContext) -> View {
        let viewName: ShibaToken = tree.identifier()!.visit()
        let view = View(viewName: viewName)
        let defaultValue: Any? = tree.value()?.visit()
        if defaultValue != nil {
            view.defaultValue = defaultValue
        }
        if tree.property() != nil {
            view.properties += tree.property().map { it in
                it.visit()
            }
        }
        if tree.view() != nil {
            view.children += tree.view().map { it in
                it.visit()
            }
        }

        return view
    }
}


private class PropertyVisitor: ShibaVisitor<ShibaParser.PropertyContext, Property> {
    override func parse(tree: ShibaParser.PropertyContext) -> Property {
        let propertyName: ShibaToken = tree.identifier()!.visit()
        let value: Any = tree.value()!.visit()
        return Property(name: propertyName, value: value)
    }
}

private class ValueVisitor: ShibaVisitor<ShibaParser.ValueContext, Any> {
    override func parse(tree: ShibaParser.ValueContext) -> Any {
        return tree.children!.first!.visit()
    }
}

private class ShibaTokenVisitor: ShibaVisitor<ShibaParser.IdentifierContext, ShibaToken> {
    override func parse(tree: ShibaParser.IdentifierContext) -> ShibaToken {
        switch tree.Identifier().count {
        case 1:
            return ShibaToken(prefix: "", value: tree.Identifier().first!.getText())
        case 2:
            return ShibaToken(prefix: tree.Identifier().first!.getText(), value: tree.Identifier()[1].getText())
        default:
            return ShibaToken(prefix: "", value: "")
        }
    }
}

private class ShibaMapVisitor: ShibaVisitor<ShibaParser.MapContext, ShibaMap> {
    override func parse(tree: ShibaParser.MapContext) -> ShibaMap {
        let properties: [Property] = tree.property().map { it in
            it.visit()
        }
        return ShibaMap(items: properties)
    }
}

private class BasicValueVisitor: ShibaVisitor<ShibaParser.BasicValueContext, BasicValue> {
    override func parse(tree: ShibaParser.BasicValueContext) -> BasicValue {
        var type = ShibaValueType.Token
        var targetValue: Any? = nil
        let token = tree.getChild(0) as! TerminalNode
        switch token.getSymbol()!.getType() {
        case ShibaParser.Tokens.String.rawValue:
            type = ShibaValueType.String
            targetValue = token.getText().trimmingCharacters(in: CharacterSet(charactersIn: "\""))
        case ShibaParser.Tokens.Number.rawValue:
            type = ShibaValueType.Number
            targetValue = NSDecimalNumber(string: token.getText())
        case ShibaParser.Tokens.Boolean.rawValue:
            type = ShibaValueType.Boolean
            targetValue = NSString(string: token.getText()).boolValue
        case ShibaParser.Tokens.Null.rawValue:
            type = ShibaValueType.Null
            targetValue = nil
        case ShibaParser.Tokens.Identifier.rawValue:
            type = ShibaValueType.Token
            targetValue = token.getText()
        default:
            break
        }

        return BasicValue(typeCode: type, value: targetValue)
    }
}

private class FunctionVisitor: ShibaVisitor<ShibaParser.FunctionCallContext, ShibaFunction> {
    override func parse(tree: ShibaParser.FunctionCallContext) -> ShibaFunction {
        let name = tree.Identifier()!.getText()
        let function = ShibaFunction(name: name)
        if tree.value() != nil {
            function.parameter += tree.value().map { it in
                it.visit()
            }
        }
        return function
    }
}

private class ArrayVisitor: ShibaVisitor<ShibaParser.ArrayContext, [Any]> {
    override func parse(tree: ShibaParser.ArrayContext) -> [Any] {
        return tree.value().map { it in
            it.visit()
        }
    }
}

private class ShibaExtensionVisitor: ShibaVisitor<ShibaParser.ShibaExtensionContext, ShibaExtension> {
    override func parse(tree: ShibaParser.ShibaExtensionContext) -> ShibaExtension {
        let name = tree.Identifier()!.getText()
        let value: BasicValue = tree.basicValue()!.visit()
        return ShibaExtension(type: name, value: value)
    }
}

public class ShibaParserWrapper {
    public static func parse(input: String) -> View? {
        do {
            let input = ANTLRInputStream(input)
            let lexer = ShibaLexer(input)
            let tokens = CommonTokenStream(lexer)
            let parser = try ShibaParser(tokens)
            parser.setBuildParseTree(true)
            let tree = try parser.view()
            return tree.visit()
        } catch {
            return nil
        }
    }
}
