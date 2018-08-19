//
// Created by 高垣朝陽 on 2018/8/16.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation
import UIKit

public class ShibaHost : UIView, IShibaContext {
    func addPropertyChangedSubscription(view: UIView, list: [ISubscription]) {
        propertyChangedSubscription[view] = list
    }
    
    
    func twowayToDataContext(path: String, value: Any?) {
        
    }
    
    var propertyChangedSubscription: [UIView : [ISubscription]] = [:]
    
    
}
