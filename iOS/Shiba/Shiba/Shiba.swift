//
//  Shiba.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/15.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation

public class ShibaConfiguration {
    var jsonValueResolver: IJsonValueResolver = DefaultJsonValueResolver()
    var resourceValueResolver: IValueResolver = DefaultResourceValueResolver()
    var bindingValueResolver: IBindingValueResolver = DefaultBindingValueResolver()
    var converterExecutor: IConverterExecutor = DefaultConverterExecutor()
    var platformType: String = "iOS"
}

protocol IConverterExecutor {
    func execute(name: String, parameters: [Any?]) -> Any?
}

protocol IBindingValueResolver {
    func getValue(dataContext: Any?, name: String) -> Any?
    func setValue(dataContext: Any?, name: String, value: Any?)
}

protocol IJsonValueResolver {
    func getValue(dataContext: Any?, name: String) -> Any?
}

protocol IValueResolver {
    func getValue(value: Any?) -> Any?
}

class DefaultConverterExecutor : IConverterExecutor {
    func execute(name: String, parameters: [Any?]) -> Any? {
        fatalError("execute(name:parameters:) has not been implemented")
    }
}

class DefaultJsonValueResolver : IJsonValueResolver {
    func getValue(dataContext: Any?, name: String) -> Any? {
        fatalError("getValue(dataContext:name:) has not been implemented")
    }
}

class DefaultBindingValueResolver : IBindingValueResolver {
    func getValue(dataContext: Any?, name: String) -> Any? {
        fatalError("getValue(dataContext:name:) has not been implemented")
    }

    func setValue(dataContext: Any?, name: String, value: Any?) {
        fatalError("getValue(dataContext:name:) has not been implemented")
    }
}

class DefaultResourceValueResolver: IValueResolver {
    func getValue(value: Any?) -> Any? {
        fatalError("getValue(value:) has not been implemented")
    }
}

public final class Shiba {
    let configuration = ShibaConfiguration()
    var viewMapper: [String: IViewMapper] = [:]
    
    private init() {
        
    }
    
    static let instance = Shiba()
}
