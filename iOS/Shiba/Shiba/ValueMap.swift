//
//  ValueMap.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/16.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation
import UIKit

protocol IValueMap {
    var name: String { get }
    var setter: (UIView, Any?) -> Void { get }
}


public class PropertyMap: IValueMap {
    var name: String
    var setter: (UIView, Any?) -> Void

    init(name: String, setter: @escaping (UIView, Any?) -> Void) {
        self.name = name
        self.setter = setter
    }
}

class TwoWayPropertyMap: PropertyMap {
    var twowayInitializer: (UIView, (Any?) -> Void) -> Void

    init(name: String, setter: @escaping (UIView, Any?) -> Void, twowayInitializer: @escaping (UIView, (Any?) -> Void) -> Void) {
        self.twowayInitializer = twowayInitializer
        super.init(name: name, setter: setter)
    }
}
