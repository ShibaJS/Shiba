//
// Created by 高垣朝陽 on 2018/8/20.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation
import UIKit

public class StackMapper : ViewMapper<UIStackView> {
    override func createNativeView(context: IShibaContext) -> UIStackView {
        let view = UIStackView(frame: context.frame)
        view.axis = NSLayoutConstraint.Axis.vertical
        return view
    }

    public override func addSubView(view: UIView, child: UIView) {
        if let stack = view as? UIStackView {
            stack.addArrangedSubview(child)
        }
    }
}
