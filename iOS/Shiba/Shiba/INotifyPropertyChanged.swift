//
// Created by 高垣朝陽 on 2018/8/16.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

//public class PropertyChangedHandler {
//    private var listeners: [(_ sender: Any, _ propertyName: String) -> Void] = []
//
//    static func +=(handler: PropertyChangedHandler, propertyChanged: (Any, String) -> Void) {
//        handler.listeners += propertyChanged;
//    }
//
//    static func -=(handler: PropertyChangedHandler, propertyChanged: (Any, String) -> Void) {
//        handler.listeners -= propertyChanged
//    }
//
//    func clear() {
//        self.listeners.removeAll()
//    }
//}

public protocol INotifyPropertyChanged {
    var propertyChanged: [(_ sender: Any, _ propertyName: String) -> Void] { get set }
}

public class NotifyObject: INotifyPropertyChanged {
    public var propertyChanged: [(_ sender: Any, _ propertyName: String) -> Void] = []
}
