//
// Created by 高垣朝陽 on 2018/8/20.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation
import UIKit

public class TextMapper : ViewMapper<UILabel> {
    override var hasDefaultProperty: Bool {
        get {
            return true;
        }
    }
    override var defaultPropertyMap: PropertyMap? {
        get {
            return PropertyMap(name: "text", setter: { view, data in
                if let value = data as? String {
                    if let uilabel = view as? UILabel {
                        uilabel.text = value
                    }
                }
            })
        }
    }

    override func createNativeView(context: IShibaContext) -> UILabel {
        return UILabel(frame: context.frame)
    }
}
