//
//  ViewController.swift
//  ShibaSample
//
//  Created by 高垣朝陽 on 2018/8/9.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import UIKit
import Shiba

class ViewController: UIViewController {


    override func viewDidLoad() {
        super.viewDidLoad()
        let shibaHost = ShibaHost(frame: self.view.frame)
        shibaHost.load(layout: "stack { text -> \"hello\" text -> \" world!\" }", dataContext: nil)
        self.view.addSubview(shibaHost)
    }
}
