//
//  Shiba.swift
//  Shiba
//
//  Created by 高垣朝陽 on 2018/8/15.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import Foundation

public class ShibaConfiguration {
    var platformType: String = "iOS"
}


public final class Shiba {
    let configuration = ShibaConfiguration()
    
    private init() {
        
    }
    
    static let instance = Shiba()
}
