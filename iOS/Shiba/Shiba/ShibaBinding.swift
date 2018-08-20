//
// Created by 高垣朝陽 on 2018/8/16.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation

public class ShibaBinding {
    let path: String
    var converter: ShibaConverter? = nil
    var parameter: Any? = nil

    init(path: String) {
        self.path = path
    }
}

public class ShibaMultiBinding: ShibaBinding {
    let paths: [String]

    init(paths: [String]) {
        self.paths = paths
        super.init(path: "")
    }
}
