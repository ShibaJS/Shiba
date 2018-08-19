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
}


public class ViewMapper<T> : IViewMapper where T: UIView {
    
    var defaultPropertyMap: PropertyMap? = nil
    var hasDefaultProperty: Bool = false
    private var _propertyCache: [PropertyMap] = [
        PropertyMap(name: "visiable", setter: { view, data in
            if let value = data as? Bool {
                view.isVisible(visible: value)
            }
        }),
        PropertyMap(name: "alpha", setter: { view, data in
            if let value = data as? Decimal {
                view.alpha = CGFloat(Double(value.description)!)
            }
        })
    ]
    
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
            case let binding as ShibaBinding:
                switch propertyMap {
                case is TwoWayPropertyMap:
                    let sub = TwoWaySubscription(binding: binding, setter: propertyMap.setter)
                    (propertyMap as! TwoWayPropertyMap).twowayInitializer(target, { it in
                        if !sub.isChanging {
                            sub.isChanging = true
                            context.twowayToDataContext(path: binding.path, value: it)
                            sub.isChanging = false
                        }
                    })
                    subs.append(sub)
                default:
                    subs.append(SingleSubscription(binding: targetValue as! ShibaBinding, setter: propertyMap.setter))
                }
            default:
                propertyMap.setter(target, targetValue)
            }
        }
        
        if view.defaultValue != nil && defaultPropertyMap != nil && hasDefaultProperty {
            setValue(context: context, value: view.defaultValue!, propertyMap: defaultPropertyMap!, target: target)
        }
        
        view.properties.forEach { (property) in
            if property.name.isCurrentPlatform() {
                let cache = _propertyCache.first { it in it.name == property.name.value }
                if cache != nil {
                    setValue(context: context, value: property.value, propertyMap: cache!, target: target)
                }
            }
        }
        
        context.addPropertyChangedSubscription(view: target, list: subs)
        
        return target
    }
    
    func createNativeView(context: IShibaContext) -> T {
        return UIView() as! T
    }
}
