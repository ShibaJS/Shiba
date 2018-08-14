//
// Created by 高垣朝陽 on 2018/8/14.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

public class Property {
    public let name: ShibaToken
    public let value: Any

    init(name: ShibaToken, value: Any) {
        self.name = name;
        self.value = value;
    }
}
