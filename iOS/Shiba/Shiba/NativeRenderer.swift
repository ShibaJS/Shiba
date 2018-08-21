//
//  NativeRenderer.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/16.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation
import UIKit

public protocol IShibaContext {
    var frame: CGRect { get set }
    var propertyChangedSubscription: [UIView: [ISubscription]] { get set }
    func twowayToDataContext(path: String, value: Any?)
    func addPropertyChangedSubscription(view: UIView, list: [ISubscription])
}

class NativeRenderer {
    static func render(layout: String, context: IShibaContext) -> UIView {
        let viewTree = ShibaParserWrapper.parse(input: layout)
        if viewTree == nil {
            fatalError()
        }
        let view = ShibaValueVisitor.getValue(item: viewTree!, context: context) as? UIView
        if view == nil {
            fatalError()
        }
        return view!
    }
}
