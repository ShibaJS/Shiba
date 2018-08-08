// Generated from Shiba.g4 by ANTLR 4.7.1
package moe.tlaster.shiba.parser;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ShibaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Null=2, String=3, Boolean=4, Identifier=5, Number=6, Arrow=7, 
		Comma=8, Colon=9, Assign=10, LeftParen=11, RightParen=12, LeftBracket=13, 
		RightBracket=14, LeftBrace=15, RightBrace=16, Hws=17, Vws=18, DocComment=19, 
		BlockComment=20, LineComment=21, LineCommentExt=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "Null", "String", "Boolean", "Identifier", "Number", "Arrow", 
		"Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "INT", "EXP", "Hws", "Vws", 
		"DocComment", "BlockComment", "LineComment", "LineCommentExt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'$'", "'null'", null, null, null, null, "'->'", "','", "':'", "'='", 
		"'('", "')'", "'['", "']'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "Null", "String", "Boolean", "Identifier", "Number", "Arrow", 
		"Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "Hws", "Vws", "DocComment", 
		"BlockComment", "LineComment", "LineCommentExt"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ShibaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Shiba.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u00e1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\7\4=\n\4\f\4\16\4@\13\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5M\n\5\3\6\6\6P\n\6\r\6\16"+
		"\6Q\3\6\7\6U\n\6\f\6\16\6X\13\6\3\7\5\7[\n\7\3\7\3\7\3\7\6\7`\n\7\r\7"+
		"\16\7a\5\7d\n\7\3\7\5\7g\n\7\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\22\7"+
		"\22\u0081\n\22\f\22\16\22\u0084\13\22\5\22\u0086\n\22\3\23\3\23\5\23\u008a"+
		"\n\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\7\26\u009b\n\26\f\26\16\26\u009e\13\26\3\26\3\26\3\26\5\26"+
		"\u00a3\n\26\3\26\3\26\3\27\3\27\3\27\3\27\7\27\u00ab\n\27\f\27\16\27\u00ae"+
		"\13\27\3\27\3\27\3\27\5\27\u00b3\n\27\3\27\3\27\3\30\3\30\3\30\3\30\7"+
		"\30\u00bb\n\30\f\30\16\30\u00be\13\30\3\30\3\30\3\31\3\31\3\31\3\31\7"+
		"\31\u00c6\n\31\f\31\16\31\u00c9\13\31\3\31\3\31\7\31\u00cd\n\31\f\31\16"+
		"\31\u00d0\13\31\3\31\3\31\3\31\3\31\7\31\u00d6\n\31\f\31\16\31\u00d9\13"+
		"\31\7\31\u00db\n\31\f\31\16\31\u00de\13\31\3\31\3\31\4\u009c\u00ac\2\32"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\2%\2\'\23)\24+\25-\26/\27\61\30\3\2\r\3\2$$\5\2C\\aac|\7\2"+
		"\60\60\62;C\\aac|\3\2\62;\3\2\63;\4\2GGgg\4\2--//\4\2\13\13\"\"\4\2\f"+
		"\f\16\17\4\2\f\f\17\17\3\2\f\f\2\u00f2\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\65\3\2\2\2\7:\3\2\2\2\t"+
		"L\3\2\2\2\13O\3\2\2\2\rZ\3\2\2\2\17h\3\2\2\2\21k\3\2\2\2\23m\3\2\2\2\25"+
		"o\3\2\2\2\27q\3\2\2\2\31s\3\2\2\2\33u\3\2\2\2\35w\3\2\2\2\37y\3\2\2\2"+
		"!{\3\2\2\2#\u0085\3\2\2\2%\u0087\3\2\2\2\'\u008d\3\2\2\2)\u0091\3\2\2"+
		"\2+\u0095\3\2\2\2-\u00a6\3\2\2\2/\u00b6\3\2\2\2\61\u00c1\3\2\2\2\63\64"+
		"\7&\2\2\64\4\3\2\2\2\65\66\7p\2\2\66\67\7w\2\2\678\7n\2\289\7n\2\29\6"+
		"\3\2\2\2:>\7$\2\2;=\n\2\2\2<;\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A"+
		"\3\2\2\2@>\3\2\2\2AB\7$\2\2B\b\3\2\2\2CD\7v\2\2DE\7t\2\2EF\7w\2\2FM\7"+
		"g\2\2GH\7h\2\2HI\7c\2\2IJ\7n\2\2JK\7u\2\2KM\7g\2\2LC\3\2\2\2LG\3\2\2\2"+
		"M\n\3\2\2\2NP\t\3\2\2ON\3\2\2\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2RV\3\2\2"+
		"\2SU\t\4\2\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2W\f\3\2\2\2XV\3\2"+
		"\2\2Y[\7/\2\2ZY\3\2\2\2Z[\3\2\2\2[\\\3\2\2\2\\c\5#\22\2]_\7\60\2\2^`\t"+
		"\5\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2c]\3\2\2\2cd\3"+
		"\2\2\2df\3\2\2\2eg\5%\23\2fe\3\2\2\2fg\3\2\2\2g\16\3\2\2\2hi\7/\2\2ij"+
		"\7@\2\2j\20\3\2\2\2kl\7.\2\2l\22\3\2\2\2mn\7<\2\2n\24\3\2\2\2op\7?\2\2"+
		"p\26\3\2\2\2qr\7*\2\2r\30\3\2\2\2st\7+\2\2t\32\3\2\2\2uv\7]\2\2v\34\3"+
		"\2\2\2wx\7_\2\2x\36\3\2\2\2yz\7}\2\2z \3\2\2\2{|\7\177\2\2|\"\3\2\2\2"+
		"}\u0086\7\62\2\2~\u0082\t\6\2\2\177\u0081\t\5\2\2\u0080\177\3\2\2\2\u0081"+
		"\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0086\3\2"+
		"\2\2\u0084\u0082\3\2\2\2\u0085}\3\2\2\2\u0085~\3\2\2\2\u0086$\3\2\2\2"+
		"\u0087\u0089\t\7\2\2\u0088\u008a\t\b\2\2\u0089\u0088\3\2\2\2\u0089\u008a"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\5#\22\2\u008c&\3\2\2\2\u008d"+
		"\u008e\t\t\2\2\u008e\u008f\3\2\2\2\u008f\u0090\b\24\2\2\u0090(\3\2\2\2"+
		"\u0091\u0092\t\n\2\2\u0092\u0093\3\2\2\2\u0093\u0094\b\25\2\2\u0094*\3"+
		"\2\2\2\u0095\u0096\7\61\2\2\u0096\u0097\7,\2\2\u0097\u0098\7,\2\2\u0098"+
		"\u009c\3\2\2\2\u0099\u009b\13\2\2\2\u009a\u0099\3\2\2\2\u009b\u009e\3"+
		"\2\2\2\u009c\u009d\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u00a2\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009f\u00a0\7,\2\2\u00a0\u00a3\7\61\2\2\u00a1\u00a3\7\2"+
		"\2\3\u00a2\u009f\3\2\2\2\u00a2\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a5\b\26\2\2\u00a5,\3\2\2\2\u00a6\u00a7\7\61\2\2\u00a7\u00a8\7,\2\2"+
		"\u00a8\u00ac\3\2\2\2\u00a9\u00ab\13\2\2\2\u00aa\u00a9\3\2\2\2\u00ab\u00ae"+
		"\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00b2\3\2\2\2\u00ae"+
		"\u00ac\3\2\2\2\u00af\u00b0\7,\2\2\u00b0\u00b3\7\61\2\2\u00b1\u00b3\7\2"+
		"\2\3\u00b2\u00af\3\2\2\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b5\b\27\2\2\u00b5.\3\2\2\2\u00b6\u00b7\7\61\2\2\u00b7\u00b8\7\61\2"+
		"\2\u00b8\u00bc\3\2\2\2\u00b9\u00bb\n\13\2\2\u00ba\u00b9\3\2\2\2\u00bb"+
		"\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2"+
		"\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\b\30\2\2\u00c0\60\3\2\2\2\u00c1\u00c2"+
		"\7\61\2\2\u00c2\u00c3\7\61\2\2\u00c3\u00c7\3\2\2\2\u00c4\u00c6\n\f\2\2"+
		"\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8"+
		"\3\2\2\2\u00c8\u00dc\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00ce\7\f\2\2\u00cb"+
		"\u00cd\5\'\24\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3"+
		"\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1"+
		"\u00d2\7\61\2\2\u00d2\u00d3\7\61\2\2\u00d3\u00d7\3\2\2\2\u00d4\u00d6\n"+
		"\f\2\2\u00d5\u00d4\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00ca\3\2"+
		"\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd"+
		"\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\b\31\2\2\u00e0\62\3\2\2"+
		"\2\31\2>LOQTVZacf\u0082\u0085\u0089\u009c\u00a2\u00ac\u00b2\u00bc\u00c7"+
		"\u00ce\u00d7\u00dc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}