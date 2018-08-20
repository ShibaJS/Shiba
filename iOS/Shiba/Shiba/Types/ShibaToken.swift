//
// Created by 高垣朝陽 on 2018/8/14.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

public class ShibaToken {
    public let prefix: String
    public let value: String

    init(prefix: String, value: String) {
        self.prefix = prefix
        self.value = value
    }

    func isCurrentPlatform(value: String) -> Bool {
        return isCurrentPlatform() && value == self.value;
    }

    func isCurrentPlatform() -> Bool {
        return prefix.isEmpty || prefix == Shiba.instance.configuration.platformType
    }

}
