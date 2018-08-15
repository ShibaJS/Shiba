//
// Created by 高垣朝陽 on 2018/8/14.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

public class View {
    public var children: [View] = []
    public var defaultValue: Any? = nil
    public let viewName: ShibaToken
    public var properties: [Property] = []

    init(viewName: ShibaToken) {
        self.viewName = viewName
    }
}
