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

internal class Visitors {
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
        return Visitors.visitors.first {
            $0.type == type(of: self)
        }?.visit(tree: self) as! T
    }
}

internal class ShibaVisitor<T, K>: IShibaVisitor where T: ParseTree {

    func visit(tree: ParseTree) -> Any {
        return parse(tree: tree as! T) as Any
    }

    func parse(tree: T) -> K? {
        return nil
    }

    var type: Any.Type = T.Type.self
}


internal class ViewVisitor: ShibaVisitor<ShibaParser.ViewContext, View> {
    override func parse(tree: ShibaParser.ViewContext) -> View {
        let viewName: ShibaToken = (tree.identifier()?.visit())!
        let view = View(viewName: viewName)
        let defaultValue: Any? = tree.value()?.visit()
        if defaultValue != nil {
            view.defaultValue = defaultValue
        }
        if tree.property() != nil {
            
        }
        return view
    }
}


internal class PropertyVisitor: ShibaVisitor<ShibaParser.PropertyContext, Property> {
    override func parse(tree: ShibaParser.PropertyContext) -> Property {
        return super.parse(tree: tree)!
    }
}

internal class ValueVisitor: ShibaVisitor<ShibaParser.ValueContext, Any> {
    override func parse(tree: ShibaParser.ValueContext) -> Any {
        return super.parse(tree: tree)!
    }
}

internal class ShibaTokenVisitor: ShibaVisitor<ShibaParser.IdentifierContext, ShibaToken> {
    override func parse(tree: ShibaParser.IdentifierContext) -> ShibaToken {
        return super.parse(tree: tree)!
    }
}

internal class ShibaMapVisitor: ShibaVisitor<ShibaParser.MapContext, [String: Any]> {
    override func parse(tree: ShibaParser.MapContext) -> [String: Any] {
        return super.parse(tree: tree)!
    }
}

internal class BasicValueVisitor: ShibaVisitor<ShibaParser.BasicValueContext, BasicValue> {
    override func parse(tree: ShibaParser.BasicValueContext) -> BasicValue {
        return super.parse(tree: tree)!
    }
}

internal class FunctionVisitor: ShibaVisitor<ShibaParser.FunctionCallContext, ShibaFunction> {
    override func parse(tree: ShibaParser.FunctionCallContext) -> ShibaFunction {
        return super.parse(tree: tree)!
    }
}

internal class ArrayVisitor: ShibaVisitor<ShibaParser.ArrayContext, [Any]> {
    override func parse(tree: ShibaParser.ArrayContext) -> [Any] {
        return super.parse(tree: tree)!
    }
}

internal class ShibaExtensionVisitor: ShibaVisitor<ShibaParser.ShibaExtensionContext, ShibaExtension> {
    override func parse(tree: ShibaParser.ShibaExtensionContext) -> ShibaExtension {
        return super.parse(tree: tree)!
    }
}

public class ShibaParserWrapper {

}
