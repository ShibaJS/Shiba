//
// Created by 高垣朝陽 on 2018/8/16.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

public class ShibaConverter {
    func convert(value: Any?, parameter: Any?) -> Any? {
        fatalError("convert(value:parameters:) has not been implemented")
    }

    static let Raw = RawConverter()
    static let Function = FunctionConverter()
    static let SingleBindingFunction = SingleBindingFunctionConverter()
}

public class RawConverter: ShibaConverter {
    override func convert(value: Any?, parameter: Any?) -> Any? {
        return value
    }
}

public class FunctionConverter: ShibaConverter {
    private let executor = ShibaFunctionExecutor()

    func getExecutor() -> ShibaFunctionExecutor {
        return executor
    }

    override func convert(value: Any?, parameter: Any?) -> Any? {
        if parameter is ShibaFunction {
            return getExecutor().execute(function: parameter as! ShibaFunction, dataContext: value)
        }
        fatalError()
    }
}

public class SingleBindingFunctionConverter: FunctionConverter {
    private let executor = SingleBindingShibaFunctionExecutor()

    override func getExecutor() -> ShibaFunctionExecutor {
        return executor;
    }
}
