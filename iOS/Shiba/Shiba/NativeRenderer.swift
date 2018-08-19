//
//  NativeRenderer.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/16.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation
import UIKit

protocol IShibaContext {
    var propertyChangedSubscription: [UIView: [ISubscription]] { get set }
    func twowayToDataContext(path: String, value: Any?)
    func addPropertyChangedSubscription(view: UIView, list: [ISubscription])
}

