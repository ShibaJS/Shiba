//
//  ShibaMap.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/15.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation

public class ShibaMap {
    private var _items: [Property] = []

    init(items: [Property]) {
        self._items = items
    }

    subscript(key: String) -> Any? {
        let value = _items.first(where: { it in it.name.isCurrentPlatform(value: key) })
        if value == nil {
            return nil
        }
        return ShibaValueVisitor.getValue(item: value!, context: nil)
    }
}
