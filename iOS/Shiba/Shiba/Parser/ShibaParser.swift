// Generated from Shiba.g4 by ANTLR 4.7.1
import Antlr4

open class ShibaParser: Parser {

	internal static var _decisionToDFA: [DFA] = {
          var decisionToDFA = [DFA]()
          let length = ShibaParser._ATN.getNumberOfDecisions()
          for i in 0..<length {
            decisionToDFA.append(DFA(ShibaParser._ATN.getDecisionState(i)!, i))
           }
           return decisionToDFA
     }()

	internal static let _sharedContextCache = PredictionContextCache()

	public
	enum Tokens: Int {
		case EOF = -1, T__0 = 1, Script = 2, Null = 3, String = 4, Boolean = 5, 
                 Identifier = 6, Number = 7, Arrow = 8, Comma = 9, Colon = 10, 
                 Assign = 11, LeftParen = 12, RightParen = 13, LeftBracket = 14, 
                 RightBracket = 15, LeftBrace = 16, RightBrace = 17, Hws = 18, 
                 Vws = 19, DocComment = 20, BlockComment = 21, LineComment = 22, 
                 LineCommentExt = 23
	}

	public
	static let RULE_view = 0, RULE_property = 1, RULE_value = 2, RULE_map = 3, 
            RULE_basicValue = 4, RULE_functionCall = 5, RULE_shibaExtension = 6, 
            RULE_array = 7, RULE_identifier = 8

	public
	static let ruleNames: [String] = [
		"view", "property", "value", "map", "basicValue", "functionCall", "shibaExtension", 
		"array", "identifier"
	]

	private static let _LITERAL_NAMES: [String?] = [
		nil, "'$'", nil, "'null'", nil, nil, nil, nil, "'->'", "','", "':'", "'='", 
		"'('", "')'", "'['", "']'", "'{'", "'}'"
	]
	private static let _SYMBOLIC_NAMES: [String?] = [
		nil, nil, "Script", "Null", "String", "Boolean", "Identifier", "Number", 
		"Arrow", "Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "Hws", "Vws", "DocComment", 
		"BlockComment", "LineComment", "LineCommentExt"
	]
	public
	static let VOCABULARY = Vocabulary(_LITERAL_NAMES, _SYMBOLIC_NAMES)

	override open
	func getGrammarFileName() -> String { return "Shiba.g4" }

	override open
	func getRuleNames() -> [String] { return ShibaParser.ruleNames }

	override open
	func getSerializedATN() -> String { return ShibaParser._serializedATN }

	override open
	func getATN() -> ATN { return ShibaParser._ATN }

	override open
	func getVocabulary() -> Vocabulary {
	    return ShibaParser.VOCABULARY
	}

	override public
	init(_ input:TokenStream) throws {
	    RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION)
		try super.init(input)
		_interp = ParserATNSimulator(self,ShibaParser._ATN,ShibaParser._decisionToDFA, ShibaParser._sharedContextCache)
	}

	public class ViewContext: ParserRuleContext {
			open
			func identifier() -> IdentifierContext? {
				return getRuleContext(IdentifierContext.self, 0)
			}
			open
			func LeftBrace() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.LeftBrace.rawValue, 0)
			}
			open
			func RightBrace() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.RightBrace.rawValue, 0)
			}
			open
			func Arrow() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Arrow.rawValue, 0)
			}
			open
			func value() -> ValueContext? {
				return getRuleContext(ValueContext.self, 0)
			}
			open
			func property() -> [PropertyContext] {
				return getRuleContexts(PropertyContext.self)
			}
			open
			func property(_ i: Int) -> PropertyContext? {
				return getRuleContext(PropertyContext.self, i)
			}
			open
			func view() -> [ViewContext] {
				return getRuleContexts(ViewContext.self)
			}
			open
			func view(_ i: Int) -> ViewContext? {
				return getRuleContext(ViewContext.self, i)
			}
			open
			func Comma() -> [TerminalNode] {
				return getTokens(ShibaParser.Tokens.Comma.rawValue)
			}
			open
			func Comma(_ i:Int) -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Comma.rawValue, i)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_view
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterView(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitView(self)
			}
		}
	}
	@discardableResult
	 open func view() throws -> ViewContext {
		var _localctx: ViewContext = ViewContext(_ctx, getState())
		try enterRule(_localctx, 0, ShibaParser.RULE_view)
		var _la: Int = 0
		defer {
	    		try! exitRule()
	    }
		do {
		 	try enterOuterAlt(_localctx, 1)
		 	setState(18)
		 	try identifier()
		 	setState(38)
		 	try _errHandler.sync(self)
		 	switch (ShibaParser.Tokens(rawValue: try _input.LA(1))!) {
		 	case .LeftBrace:
		 	 	setState(19)
		 	 	try match(ShibaParser.Tokens.LeftBrace.rawValue)
		 	 	setState(29)
		 	 	try _errHandler.sync(self)
		 	 	_la = try _input.LA(1)
		 	 	while (//closure
		 	 	 { () -> Bool in
		 	 	      let testSet: Bool = _la == ShibaParser.Tokens.Identifier.rawValue
		 	 	      return testSet
		 	 	 }()) {
		 	 		setState(22)
		 	 		try _errHandler.sync(self)
		 	 		switch(try getInterpreter().adaptivePredict(_input,0, _ctx)) {
		 	 		case 1:
		 	 			setState(20)
		 	 			try property()

		 	 			break
		 	 		case 2:
		 	 			setState(21)
		 	 			try view()

		 	 			break
		 	 		default: break
		 	 		}
		 	 		setState(25)
		 	 		try _errHandler.sync(self)
		 	 		_la = try _input.LA(1)
		 	 		if (//closure
		 	 		 { () -> Bool in
		 	 		      let testSet: Bool = _la == ShibaParser.Tokens.Comma.rawValue
		 	 		      return testSet
		 	 		 }()) {
		 	 			setState(24)
		 	 			try match(ShibaParser.Tokens.Comma.rawValue)

		 	 		}



		 	 		setState(31)
		 	 		try _errHandler.sync(self)
		 	 		_la = try _input.LA(1)
		 	 	}
		 	 	setState(32)
		 	 	try match(ShibaParser.Tokens.RightBrace.rawValue)

		 		break

		 	case .Arrow:
		 	 	setState(33)
		 	 	try match(ShibaParser.Tokens.Arrow.rawValue)
		 	 	setState(36)
		 	 	try _errHandler.sync(self)
		 	 	switch(try getInterpreter().adaptivePredict(_input,3, _ctx)) {
		 	 	case 1:
		 	 		setState(34)
		 	 		try value()

		 	 		break
		 	 	case 2:
		 	 		setState(35)
		 	 		try property()

		 	 		break
		 	 	default: break
		 	 	}

		 		break
		 	case .T__0:fallthrough
		 	case .Null:fallthrough
		 	case .String:fallthrough
		 	case .Boolean:fallthrough
		 	case .Identifier:fallthrough
		 	case .Number:fallthrough
		 	case .Comma:fallthrough
		 	case .RightParen:fallthrough
		 	case .LeftBracket:fallthrough
		 	case .RightBracket:fallthrough
		 	case .RightBrace:
		 		break
		 	default:
		 		break
		 	}

		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}

	public class PropertyContext: ParserRuleContext {
			open
			func identifier() -> IdentifierContext? {
				return getRuleContext(IdentifierContext.self, 0)
			}
			open
			func Assign() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Assign.rawValue, 0)
			}
			open
			func value() -> ValueContext? {
				return getRuleContext(ValueContext.self, 0)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_property
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterProperty(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitProperty(self)
			}
		}
	}
	@discardableResult
	 open func property() throws -> PropertyContext {
		var _localctx: PropertyContext = PropertyContext(_ctx, getState())
		try enterRule(_localctx, 2, ShibaParser.RULE_property)
		defer {
	    		try! exitRule()
	    }
		do {
		 	try enterOuterAlt(_localctx, 1)
		 	setState(40)
		 	try identifier()
		 	setState(41)
		 	try match(ShibaParser.Tokens.Assign.rawValue)
		 	setState(42)
		 	try value()

		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}

	public class ValueContext: ParserRuleContext {
			open
			func basicValue() -> BasicValueContext? {
				return getRuleContext(BasicValueContext.self, 0)
			}
			open
			func array() -> ArrayContext? {
				return getRuleContext(ArrayContext.self, 0)
			}
			open
			func map() -> MapContext? {
				return getRuleContext(MapContext.self, 0)
			}
			open
			func functionCall() -> FunctionCallContext? {
				return getRuleContext(FunctionCallContext.self, 0)
			}
			open
			func shibaExtension() -> ShibaExtensionContext? {
				return getRuleContext(ShibaExtensionContext.self, 0)
			}
			open
			func view() -> ViewContext? {
				return getRuleContext(ViewContext.self, 0)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_value
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterValue(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitValue(self)
			}
		}
	}
	@discardableResult
	 open func value() throws -> ValueContext {
		var _localctx: ValueContext = ValueContext(_ctx, getState())
		try enterRule(_localctx, 4, ShibaParser.RULE_value)
		defer {
	    		try! exitRule()
	    }
		do {
		 	setState(50)
		 	try _errHandler.sync(self)
		 	switch(try getInterpreter().adaptivePredict(_input,5, _ctx)) {
		 	case 1:
		 		try enterOuterAlt(_localctx, 1)
		 		setState(44)
		 		try basicValue()

		 		break
		 	case 2:
		 		try enterOuterAlt(_localctx, 2)
		 		setState(45)
		 		try array()

		 		break
		 	case 3:
		 		try enterOuterAlt(_localctx, 3)
		 		setState(46)
		 		try map()

		 		break
		 	case 4:
		 		try enterOuterAlt(_localctx, 4)
		 		setState(47)
		 		try functionCall()

		 		break
		 	case 5:
		 		try enterOuterAlt(_localctx, 5)
		 		setState(48)
		 		try shibaExtension()

		 		break
		 	case 6:
		 		try enterOuterAlt(_localctx, 6)
		 		setState(49)
		 		try view()

		 		break
		 	default: break
		 	}
		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}

	public class MapContext: ParserRuleContext {
			open
			func LeftBracket() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.LeftBracket.rawValue, 0)
			}
			open
			func RightBracket() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.RightBracket.rawValue, 0)
			}
			open
			func property() -> [PropertyContext] {
				return getRuleContexts(PropertyContext.self)
			}
			open
			func property(_ i: Int) -> PropertyContext? {
				return getRuleContext(PropertyContext.self, i)
			}
			open
			func Comma() -> [TerminalNode] {
				return getTokens(ShibaParser.Tokens.Comma.rawValue)
			}
			open
			func Comma(_ i:Int) -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Comma.rawValue, i)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_map
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterMap(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitMap(self)
			}
		}
	}
	@discardableResult
	 open func map() throws -> MapContext {
		var _localctx: MapContext = MapContext(_ctx, getState())
		try enterRule(_localctx, 6, ShibaParser.RULE_map)
		var _la: Int = 0
		defer {
	    		try! exitRule()
	    }
		do {
		 	try enterOuterAlt(_localctx, 1)
		 	setState(52)
		 	try match(ShibaParser.Tokens.LeftBracket.rawValue)
		 	setState(59)
		 	try _errHandler.sync(self)
		 	_la = try _input.LA(1)
		 	while (//closure
		 	 { () -> Bool in
		 	      let testSet: Bool = _la == ShibaParser.Tokens.Identifier.rawValue
		 	      return testSet
		 	 }()) {
		 		setState(53)
		 		try property()
		 		setState(55)
		 		try _errHandler.sync(self)
		 		_la = try _input.LA(1)
		 		if (//closure
		 		 { () -> Bool in
		 		      let testSet: Bool = _la == ShibaParser.Tokens.Comma.rawValue
		 		      return testSet
		 		 }()) {
		 			setState(54)
		 			try match(ShibaParser.Tokens.Comma.rawValue)

		 		}



		 		setState(61)
		 		try _errHandler.sync(self)
		 		_la = try _input.LA(1)
		 	}
		 	setState(62)
		 	try match(ShibaParser.Tokens.RightBracket.rawValue)

		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}

	public class BasicValueContext: ParserRuleContext {
			open
			func String() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.String.rawValue, 0)
			}
			open
			func Number() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Number.rawValue, 0)
			}
			open
			func Boolean() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Boolean.rawValue, 0)
			}
			open
			func Null() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Null.rawValue, 0)
			}
			open
			func Identifier() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Identifier.rawValue, 0)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_basicValue
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterBasicValue(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitBasicValue(self)
			}
		}
	}
	@discardableResult
	 open func basicValue() throws -> BasicValueContext {
		var _localctx: BasicValueContext = BasicValueContext(_ctx, getState())
		try enterRule(_localctx, 8, ShibaParser.RULE_basicValue)
		var _la: Int = 0
		defer {
	    		try! exitRule()
	    }
		do {
		 	try enterOuterAlt(_localctx, 1)
		 	setState(64)
		 	_la = try _input.LA(1)
		 	if (!(//closure
		 	 { () -> Bool in
		 	      let testSet: Bool = {  () -> Bool in
		 	   let testArray: [Int] = [_la, ShibaParser.Tokens.Null.rawValue,ShibaParser.Tokens.String.rawValue,ShibaParser.Tokens.Boolean.rawValue,ShibaParser.Tokens.Identifier.rawValue,ShibaParser.Tokens.Number.rawValue]
		 	    return  Utils.testBitLeftShiftArray(testArray, 0)
		 	}()
		 	      return testSet
		 	 }())) {
		 	try _errHandler.recoverInline(self)
		 	}
		 	else {
		 		_errHandler.reportMatch(self)
		 		try consume()
		 	}

		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}

	public class FunctionCallContext: ParserRuleContext {
			open
			func Identifier() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Identifier.rawValue, 0)
			}
			open
			func LeftParen() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.LeftParen.rawValue, 0)
			}
			open
			func RightParen() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.RightParen.rawValue, 0)
			}
			open
			func value() -> [ValueContext] {
				return getRuleContexts(ValueContext.self)
			}
			open
			func value(_ i: Int) -> ValueContext? {
				return getRuleContext(ValueContext.self, i)
			}
			open
			func Comma() -> [TerminalNode] {
				return getTokens(ShibaParser.Tokens.Comma.rawValue)
			}
			open
			func Comma(_ i:Int) -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Comma.rawValue, i)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_functionCall
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterFunctionCall(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitFunctionCall(self)
			}
		}
	}
	@discardableResult
	 open func functionCall() throws -> FunctionCallContext {
		var _localctx: FunctionCallContext = FunctionCallContext(_ctx, getState())
		try enterRule(_localctx, 10, ShibaParser.RULE_functionCall)
		var _la: Int = 0
		defer {
	    		try! exitRule()
	    }
		do {
		 	try enterOuterAlt(_localctx, 1)
		 	setState(66)
		 	try match(ShibaParser.Tokens.Identifier.rawValue)
		 	setState(67)
		 	try match(ShibaParser.Tokens.LeftParen.rawValue)
		 	setState(74)
		 	try _errHandler.sync(self)
		 	_la = try _input.LA(1)
		 	while (//closure
		 	 { () -> Bool in
		 	      let testSet: Bool = {  () -> Bool in
		 	   let testArray: [Int] = [_la, ShibaParser.Tokens.T__0.rawValue,ShibaParser.Tokens.Null.rawValue,ShibaParser.Tokens.String.rawValue,ShibaParser.Tokens.Boolean.rawValue,ShibaParser.Tokens.Identifier.rawValue,ShibaParser.Tokens.Number.rawValue,ShibaParser.Tokens.LeftBracket.rawValue]
		 	    return  Utils.testBitLeftShiftArray(testArray, 0)
		 	}()
		 	      return testSet
		 	 }()) {
		 		setState(68)
		 		try value()
		 		setState(70)
		 		try _errHandler.sync(self)
		 		_la = try _input.LA(1)
		 		if (//closure
		 		 { () -> Bool in
		 		      let testSet: Bool = _la == ShibaParser.Tokens.Comma.rawValue
		 		      return testSet
		 		 }()) {
		 			setState(69)
		 			try match(ShibaParser.Tokens.Comma.rawValue)

		 		}



		 		setState(76)
		 		try _errHandler.sync(self)
		 		_la = try _input.LA(1)
		 	}
		 	setState(77)
		 	try match(ShibaParser.Tokens.RightParen.rawValue)

		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}

	public class ShibaExtensionContext: ParserRuleContext {
			open
			func Identifier() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Identifier.rawValue, 0)
			}
			open
			func basicValue() -> BasicValueContext? {
				return getRuleContext(BasicValueContext.self, 0)
			}
			open
			func Script() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Script.rawValue, 0)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_shibaExtension
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterShibaExtension(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitShibaExtension(self)
			}
		}
	}
	@discardableResult
	 open func shibaExtension() throws -> ShibaExtensionContext {
		var _localctx: ShibaExtensionContext = ShibaExtensionContext(_ctx, getState())
		try enterRule(_localctx, 12, ShibaParser.RULE_shibaExtension)
		var _la: Int = 0
		defer {
	    		try! exitRule()
	    }
		do {
		 	try enterOuterAlt(_localctx, 1)
		 	setState(79)
		 	try match(ShibaParser.Tokens.T__0.rawValue)
		 	setState(80)
		 	try match(ShibaParser.Tokens.Identifier.rawValue)
		 	setState(82)
		 	try _errHandler.sync(self)
		 	switch (try getInterpreter().adaptivePredict(_input,10,_ctx)) {
		 	case 1:
		 		setState(81)
		 		try basicValue()

		 		break
		 	default: break
		 	}
		 	setState(85)
		 	try _errHandler.sync(self)
		 	_la = try _input.LA(1)
		 	if (//closure
		 	 { () -> Bool in
		 	      let testSet: Bool = _la == ShibaParser.Tokens.Script.rawValue
		 	      return testSet
		 	 }()) {
		 		setState(84)
		 		try match(ShibaParser.Tokens.Script.rawValue)

		 	}


		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}

	public class ArrayContext: ParserRuleContext {
			open
			func LeftBracket() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.LeftBracket.rawValue, 0)
			}
			open
			func RightBracket() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.RightBracket.rawValue, 0)
			}
			open
			func value() -> [ValueContext] {
				return getRuleContexts(ValueContext.self)
			}
			open
			func value(_ i: Int) -> ValueContext? {
				return getRuleContext(ValueContext.self, i)
			}
			open
			func Comma() -> [TerminalNode] {
				return getTokens(ShibaParser.Tokens.Comma.rawValue)
			}
			open
			func Comma(_ i:Int) -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Comma.rawValue, i)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_array
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterArray(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitArray(self)
			}
		}
	}
	@discardableResult
	 open func array() throws -> ArrayContext {
		var _localctx: ArrayContext = ArrayContext(_ctx, getState())
		try enterRule(_localctx, 14, ShibaParser.RULE_array)
		var _la: Int = 0
		defer {
	    		try! exitRule()
	    }
		do {
		 	try enterOuterAlt(_localctx, 1)
		 	setState(87)
		 	try match(ShibaParser.Tokens.LeftBracket.rawValue)
		 	setState(94)
		 	try _errHandler.sync(self)
		 	_la = try _input.LA(1)
		 	while (//closure
		 	 { () -> Bool in
		 	      let testSet: Bool = {  () -> Bool in
		 	   let testArray: [Int] = [_la, ShibaParser.Tokens.T__0.rawValue,ShibaParser.Tokens.Null.rawValue,ShibaParser.Tokens.String.rawValue,ShibaParser.Tokens.Boolean.rawValue,ShibaParser.Tokens.Identifier.rawValue,ShibaParser.Tokens.Number.rawValue,ShibaParser.Tokens.LeftBracket.rawValue]
		 	    return  Utils.testBitLeftShiftArray(testArray, 0)
		 	}()
		 	      return testSet
		 	 }()) {
		 		setState(88)
		 		try value()
		 		setState(90)
		 		try _errHandler.sync(self)
		 		_la = try _input.LA(1)
		 		if (//closure
		 		 { () -> Bool in
		 		      let testSet: Bool = _la == ShibaParser.Tokens.Comma.rawValue
		 		      return testSet
		 		 }()) {
		 			setState(89)
		 			try match(ShibaParser.Tokens.Comma.rawValue)

		 		}



		 		setState(96)
		 		try _errHandler.sync(self)
		 		_la = try _input.LA(1)
		 	}
		 	setState(97)
		 	try match(ShibaParser.Tokens.RightBracket.rawValue)

		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}

	public class IdentifierContext: ParserRuleContext {
			open
			func Identifier() -> [TerminalNode] {
				return getTokens(ShibaParser.Tokens.Identifier.rawValue)
			}
			open
			func Identifier(_ i:Int) -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Identifier.rawValue, i)
			}
			open
			func Colon() -> TerminalNode? {
				return getToken(ShibaParser.Tokens.Colon.rawValue, 0)
			}
		override open
		func getRuleIndex() -> Int {
			return ShibaParser.RULE_identifier
		}
		override open
		func enterRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.enterIdentifier(self)
			}
		}
		override open
		func exitRule(_ listener: ParseTreeListener) {
			if let listener = listener as? ShibaListener {
				listener.exitIdentifier(self)
			}
		}
	}
	@discardableResult
	 open func identifier() throws -> IdentifierContext {
		var _localctx: IdentifierContext = IdentifierContext(_ctx, getState())
		try enterRule(_localctx, 16, ShibaParser.RULE_identifier)
		defer {
	    		try! exitRule()
	    }
		do {
		 	try enterOuterAlt(_localctx, 1)
		 	setState(101)
		 	try _errHandler.sync(self)
		 	switch (try getInterpreter().adaptivePredict(_input,14,_ctx)) {
		 	case 1:
		 		setState(99)
		 		try match(ShibaParser.Tokens.Identifier.rawValue)
		 		setState(100)
		 		try match(ShibaParser.Tokens.Colon.rawValue)

		 		break
		 	default: break
		 	}
		 	setState(103)
		 	try match(ShibaParser.Tokens.Identifier.rawValue)

		}
		catch ANTLRException.recognition(let re) {
			_localctx.exception = re
			_errHandler.reportError(self, re)
			try _errHandler.recover(self, re)
		}

		return _localctx
	}


	public
	static let _serializedATN = ShibaParserATN().jsonString

	public
	static let _ATN = ATNDeserializer().deserializeFromJson(_serializedATN)
}