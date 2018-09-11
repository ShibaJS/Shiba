//
// Created by 高垣朝陽 on 2018/8/16.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

class ShibaFunctionExecutor {

    func execute(function: ShibaFunction, dataContext: Any?) -> Any? {
        return Shiba.instance.configuration.converterExecutor.execute(name: function.name, parameters: function.parameter.map { it in
            getParameterValue(parameter: it, dataContext: dataContext)
        })
    }

    private func getParameterValue(parameter: Any, dataContext: Any?) -> Any? {
        switch parameter {
        case is ShibaFunction:
            return execute(function: parameter as! ShibaFunction, dataContext: dataContext)
        case is ShibaBinding:
            return getValueFromDataContext(binding: parameter as! ShibaBinding, dataContext: dataContext)
        default:
            return parameter
        }
    }

    func getValueFromDataContext(binding: ShibaBinding, dataContext: Any?) -> Any? {
        return Shiba.instance.configuration.bindingValueResolver.getValue(dataContext: dataContext, name: binding.path)
    }
}

class SingleBindingShibaFunctionExecutor: ShibaFunctionExecutor {
    override func getValueFromDataContext(binding: ShibaBinding, dataContext: Any?) -> Any? {
        return dataContext;
    }
}
