//
//  ValueVisitor.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/17.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation
import UIKit

protocol IValueVisitor {
    var type: Any.Type { get }
    func visit(item: Any, context: IShibaContext?) -> Any?
}

class ShibaValueVisitor {
    static func getValue(item: Any, context: IShibaContext?) -> Any? {
        return Visitor.visit(item: item, context: context)
    }
}

private class Visitor {
    static func visit(item: Any, context: IShibaContext?) -> Any {
        let visitor = visitors.first { it in
            it.type == type(of: item)
        };
        if visitor == nil {
            return item
        }
        return visitor!.visit(item:item, context: context)
    }

    private static let visitors: [IValueVisitor] = [
        ViewVisitor(),
        ShibaExtensionVisitor(),
        ShibaFunctionVisitor(),
        BasicValueVisitor()
    ]
}

private class AbsValueVisitor<T, K>: IValueVisitor {
    var type: Any.Type = T.self

    func visit(item: Any, context: IShibaContext?) -> Any? {
        return parse(tree: item as! T, context: context) as Any?
    }

    func parse(tree: T, context: IShibaContext?) -> K? {
        fatalError()
    }
}

private class ViewVisitor: AbsValueVisitor<View, UIView> {
    override func parse(tree: View, context: IShibaContext?) -> UIView {
        let mapper = Shiba.instance.viewMapper.first { it in
            tree.viewName.isCurrentPlatform(value: it.key)
        }
        if mapper == nil {
            fatalError()
        }
        if context == nil {
            fatalError()
        }
        let target = mapper!.value.map(view: tree, context: context!)
        if tree.children.count > 0 {
            tree.children.forEach { it in
                mapper!.value.addSubView(view: target, child: parse(tree: it, context: context))
            }
        }

        return target
    }
}

private class ShibaExtensionVisitor: AbsValueVisitor<ShibaExtension, ShibaBinding> {
    override func parse(tree: ShibaExtension, context: IShibaContext?) -> ShibaBinding {
        switch tree.type {
        case "bind":
            return bindHandler(value: tree.value, context: context)
        default:
            fatalError()
        }
    }

    private func bindHandler(value: BasicValue, context: IShibaContext?) -> ShibaBinding {
        switch value.typeCode {
        case ShibaValueType.Token:
            return ShibaBinding(path: value.value as! String)
        default:
            let binding = ShibaBinding(path: "")
            binding.converter = ShibaConverter.Raw
            binding.parameter = value.value
            return binding
        }
    }
}

private class ShibaFunctionVisitor: AbsValueVisitor<ShibaFunction, ShibaBinding> {
    override func parse(tree: ShibaFunction, context: IShibaContext?) -> ShibaBinding {
        let function = parseFunction(function: tree, context: context)
        let bindings = getBindings(function: function)
        if bindings.count == 0 {
            let binding = ShibaBinding(path: "")
            binding.converter = ShibaConverter.Raw
            binding.parameter = ShibaConverter.Function.getExecutor().execute(function: function, dataContext: nil)
            return binding
        }

        if bindings.count == 1 {
            let binding = ShibaBinding(path: bindings.first!.path)
            binding.converter = ShibaConverter.SingleBindingFunction
            binding.parameter = function
            return binding
        }

        if bindings.count > 1 {
            let binding = ShibaMultiBinding(paths: bindings.map { it in
                it.path
            })
            binding.converter = ShibaConverter.Function
            binding.parameter = function
            return binding
        }

        fatalError()
    }

    private func getBindings(function: ShibaFunction) -> [ShibaBinding] {
        let item: [[ShibaBinding]] = function.parameter.map { it in
            var ar: [ShibaBinding] = []
            switch it {
            case is ShibaBinding:
                ar = [it as! ShibaBinding]
            case is ShibaFunction:
                ar = getBindings(function: it as! ShibaFunction)
            default: break
            }
            return ar
        }
        return item.flatMap { it in
            it
        }
    }

    private func parseFunction(function: ShibaFunction, context: IShibaContext?) -> ShibaFunction {
        function.parameter = function.parameter.map({ it in
            switch it {
            case is ShibaFunction:
                return parseFunction(function: it as! ShibaFunction, context: context)
            default:
                let result = Visitor.visit(item: it, context: context)
                if result is ShibaBinding {
                    let binding = result as! ShibaBinding
                    if binding.converter is RawConverter {
                        return binding.parameter!
                    }
                }
                return result
            }
        })
        return function
    }
}

private class BasicValueVisitor: AbsValueVisitor<BasicValue, Any> {
    override func parse(tree: BasicValue, context: IShibaContext?) -> Any? {
        return tree.value
    }
}
