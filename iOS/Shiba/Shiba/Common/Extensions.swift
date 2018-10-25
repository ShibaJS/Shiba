//
// Created by 高垣朝陽 on 2018/8/19.
// Copyright (c) 2018 Tlaster. All rights reserved.
//

import Foundation
import UIKit

extension UIView {
    func isVisible(visible: Bool, dimension: CGFloat = 0.0, attribute: NSLayoutConstraint.Attribute = .height) -> Void {
        if let constraint = (self.constraints.filter {
            $0.firstAttribute == attribute
        }.first) {
            constraint.constant = visible ? 0.0 : dimension
            self.isHidden = visible
            self.layoutIfNeeded()
        }
    }
}
