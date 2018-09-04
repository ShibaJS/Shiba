// Generated from Shiba.g4 by ANTLR 4.7.1
import Antlr4

open class ShibaLexer: Lexer {

	internal static var _decisionToDFA: [DFA] = {
          var decisionToDFA = [DFA]()
          let length = ShibaLexer._ATN.getNumberOfDecisions()
          for i in 0..<length {
          	    decisionToDFA.append(DFA(ShibaLexer._ATN.getDecisionState(i)!, i))
          }
           return decisionToDFA
     }()

	internal static let _sharedContextCache = PredictionContextCache()

	public
	static let T__0=1, Null=2, String=3, Boolean=4, Identifier=5, Number=6, 
            Arrow=7, Comma=8, Colon=9, Assign=10, LeftParen=11, RightParen=12, 
            LeftBracket=13, RightBracket=14, LeftBrace=15, RightBrace=16, 
            Hws=17, Vws=18, DocComment=19, BlockComment=20, LineComment=21, 
            LineCommentExt=22

	public
	static let channelNames: [String] = [
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	]

	public
	static let modeNames: [String] = [
		"DEFAULT_MODE"
	]

	public
	static let ruleNames: [String] = [
		"T__0", "Null", "String", "Boolean", "Identifier", "Number", "Arrow", 
		"Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "INT", "EXP", "Hws", "Vws", 
		"DocComment", "BlockComment", "LineComment", "LineCommentExt"
	]

	private static let _LITERAL_NAMES: [String?] = [
		nil, "'$'", "'null'", nil, nil, nil, nil, "'->'", "','", "':'", "'='", 
		"'('", "')'", "'['", "']'", "'{'", "'}'"
	]
	private static let _SYMBOLIC_NAMES: [String?] = [
		nil, nil, "Null", "String", "Boolean", "Identifier", "Number", "Arrow", 
		"Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "Hws", "Vws", "DocComment", 
		"BlockComment", "LineComment", "LineCommentExt"
	]
	public
	static let VOCABULARY = Vocabulary(_LITERAL_NAMES, _SYMBOLIC_NAMES)


	override open
	func getVocabulary() -> Vocabulary {
		return ShibaLexer.VOCABULARY
	}

	public
	required init(_ input: CharStream) {
	    RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION)
		super.init(input)
		_interp = LexerATNSimulator(self, ShibaLexer._ATN, ShibaLexer._decisionToDFA, ShibaLexer._sharedContextCache)
	}

	override open
	func getGrammarFileName() -> String { return "Shiba.g4" }

	override open
	func getRuleNames() -> [String] { return ShibaLexer.ruleNames }

	override open
	func getSerializedATN() -> String { return ShibaLexer._serializedATN }

	override open
	func getChannelNames() -> [String] { return ShibaLexer.channelNames }

	override open
	func getModeNames() -> [String] { return ShibaLexer.modeNames }

	override open
	func getATN() -> ATN { return ShibaLexer._ATN }


	public
	static let _serializedATN: String = ShibaLexerATN().jsonString

	public
	static let _ATN: ATN = ATNDeserializer().deserializeFromJson(_serializedATN)
}