//
// Created by 高垣朝陽 on 2018/8/16.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation
import UIKit

public class ShibaHost: UIView, IShibaContext {
    public func addPropertyChangedSubscription(view: UIView, list: [ISubscription]) {
        propertyChangedSubscription[view] = list
    }


    public func twowayToDataContext(path: String, value: Any?) {
        if dataContext != nil {
            Shiba.instance.configuration.bindingValueResolver.setValue(dataContext: dataContext, name: path, value: value)
        }
    }

    public var propertyChangedSubscription: [UIView: [ISubscription]] = [:]

    public var layout: String? = nil {
        willSet(newValue) {
            self.removeSubView()
        }
        didSet {
            if layout != nil {
                addSubview(NativeRenderer.render(layout: layout!, context: self))
            }
        }
    }

    public var dataContext: Any? = nil {
        willSet(newValue) {
            onDataContextChanged(dataContext, newValue)
        }
    }

    private func onDataContextChanged(_ oldValue: Any?, _ newValue: Any?) {
        if var oldModel = oldValue as? INotifyPropertyChanged {
            oldModel.propertyChanged.removeAll()
        }
        
        if var newModel = newValue as? INotifyPropertyChanged {
            newModel.propertyChanged.append({ sender, name in
                self.onModelPropertyChanged(sender, name)
            })
            propertyChangedSubscription.forEach({ view, sub in
                for var it in sub {
                    it.isChanging = true
                    setValueToView(newValue, it, view)
                    it.isChanging = false
                }
            })
        }
    }

    private func setValueToView(_ dataContext: Any?, _ sub: ISubscription, _ view: UIView) {
        let value = Shiba.instance.configuration.bindingValueResolver.getValue(dataContext: dataContext, name: sub.binding.path)
        if sub.binding.converter != nil {
            sub.setter(view, sub.binding.converter!.convert(value: value, parameter: sub.binding.parameter))
        } else {
            sub.setter(view, value)
        }
    }

    private func onModelPropertyChanged(_ sender: Any, _ name: String) {
        propertyChangedSubscription.forEach({ view, subs in
            subs.forEach({ it in
                if it.binding.path == name {
                    setValueToView(sender, it, view)
                }
            })
        })
    }

    public func load(layout: String?, dataContext: Any?) {
        self.layout = layout;
        self.dataContext = dataContext;
    }

    private func removeSubView() {
        for subView in self.subviews as [UIView] {
            subView.removeFromSuperview()
        }
        self.propertyChangedSubscription.removeAll()
    }

}
