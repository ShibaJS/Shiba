//
//  Subscription.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/16.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation
import UIKit

internal protocol ISubscription {
    var isChanging: Bool { get }
    var binding: ShibaBinding { get }
    var setter: (UIView, Any?) -> Void { get }
}

public final class SingleSubscription : ISubscription {
    var isChanging: Bool
    var binding: ShibaBinding
    var setter: (UIView, Any?) -> Void
    init(binding: ShibaBinding, setter: @escaping (UIView, Any?) -> Void) {
        self.binding = binding
        self.setter = setter
        self.isChanging = false
    }
}

public final class MultiSubscription : ISubscription {
    var isChanging: Bool
    var binding: ShibaBinding
    var setter: (UIView, Any?) -> Void
    init(binding: ShibaBinding, setter: @escaping (UIView, Any?) -> Void) {
        self.binding = binding
        self.setter = setter
        self.isChanging = false
    }
}

public final class TwoWaySubscription : ISubscription {
    var isChanging: Bool
    var binding: ShibaBinding
    var setter: (UIView, Any?) -> Void
    init(binding: ShibaBinding, setter: @escaping (UIView, Any?) -> Void) {
        self.binding = binding
        self.setter = setter
        self.isChanging = false
    }
}

