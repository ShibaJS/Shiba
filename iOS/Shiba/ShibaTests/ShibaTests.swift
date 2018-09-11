//
//  ShibaTests.swift
//  ShibaTests
//
//  Created by 高垣朝陽 on 2018/8/9.
//  Copyright © 2018年 Tlaster. All rights reserved.
//

import XCTest
@testable import Shiba

class ShibaTests: XCTestCase {

    override func setUp() {
        super.setUp()
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }

    func testExample() {
        let tree = ShibaParserWrapper.parse(input: "stack { text -> awesome(reverse($bind UWPText)) text { color = \"#ffff00\" text = \"fdsafdsaf\" size = 20 } input -> $bind UWPText }")
        assert(tree != nil)
        assert(tree?.viewName.value == "stack")
        assert(tree?.children.count == 3)
        assert(tree?.children.first?.defaultValue != nil)
        assert(tree?.children.first?.defaultValue is ShibaFunction)
        assert((tree?.children.first?.defaultValue as! ShibaFunction).name == "awesome")
        assert((tree?.children.first?.defaultValue as! ShibaFunction).parameter.first is ShibaFunction)
        assert(((tree?.children.first?.defaultValue as! ShibaFunction).parameter.first as! ShibaFunction).name == "reverse")
        assert(((tree?.children.first?.defaultValue as! ShibaFunction).parameter.first as! ShibaFunction).parameter.first is ShibaExtension)
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}
