//
// Created by 高垣朝陽 on 2018/8/14.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

public class View {
    public let children: [View] = []
    public var defaultValue: Any? = nil
    public let viewName: ShibaToken
    public let properties: [Property] = []

    init(viewName: ShibaToken) {
        self.viewName = viewName
    }
}
