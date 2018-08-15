//
// Created by 高垣朝陽 on 2018/8/14.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

public enum ShibaValueType {
    case String
    case Token
    case Number
    case Null
    case Boolean
}

public class BasicValue {
    public let typeCode: ShibaValueType
    public let value: Any?

    init(typeCode: ShibaValueType, value: Any?) {
        self.typeCode = typeCode;
        self.value = value;
    }
}
