//
//  ViewMapper.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/17.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation
import UIKit

protocol IViewMapper {
    func map(view: View, context: IShibaContext) -> UIView
    func createNativeView(context: IShibaContext) -> UIView
}


public class ViewMapper : IViewMapper {
    typealias T = UIView
    
    func map(view: View, context: IShibaContext) -> UIView {
        let target = createNativeView(context: context)
        
        var subs: [ISubscription] = []
        
        func setValue(context: IShibaContext, value: Any, propertyMap: PropertyMap, target: UIView) {
            let targetValue = ShibaValueVisitor.getValue(item: value, context: context)
            switch targetValue {
            case is ShibaMultiBinding:
                if propertyMap is TwoWayPropertyMap {
                    fatalError("two way multi binding is not supported")
                }
                subs.append(MultiSubscription(binding: targetValue as! ShibaBinding, setter: propertyMap.setter))
            case is ShibaBinding:
                switch propertyMap {
                case is TwoWayPropertyMap:
                    let sub = TwoWaySubscription(binding: targetValue as! ShibaBinding, setter: propertyMap.setter)
                default:
                    subs.append(SingleSubscription(binding: targetValue as! ShibaBinding, setter: propertyMap.setter))
                }
            default:
                break
            }
            
        }
        
        
        return target
    }
    
    func createNativeView(context: IShibaContext) -> UIView {
        return UIView()
    }
    
    
}
