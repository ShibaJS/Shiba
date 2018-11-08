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
		T__0=1, Script=2, Null=3, String=4, Boolean=5, Identifier=6, Number=7, 
		Arrow=8, Comma=9, Colon=10, Assign=11, LeftParen=12, RightParen=13, LeftBracket=14, 
		RightBracket=15, LeftBrace=16, RightBrace=17, Hws=18, Vws=19, DocComment=20, 
		BlockComment=21, LineComment=22, LineCommentExt=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "Script", "Null", "String", "Boolean", "Identifier", "Number", 
		"Arrow", "Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "INT", "EXP", "Hws", "Vws", 
		"DocComment", "BlockComment", "LineComment", "LineCommentExt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'$'", null, "'null'", null, null, null, null, "'->'", "','", "':'", 
		"'='", "'('", "')'", "'['", "']'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "Script", "Null", "String", "Boolean", "Identifier", "Number", 
		"Arrow", "Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u00ee\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\3\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\7\5J\n\5\f\5\16\5M\13\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6Z\n\6\3\7\6\7]\n\7\r\7\16\7^\3\7\7\7b"+
		"\n\7\f\7\16\7e\13\7\3\b\5\bh\n\b\3\b\3\b\3\b\6\bm\n\b\r\b\16\bn\5\bq\n"+
		"\b\3\b\5\bt\n\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23\7\23\u008e\n\23"+
		"\f\23\16\23\u0091\13\23\5\23\u0093\n\23\3\24\3\24\5\24\u0097\n\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\7\27\u00a8\n\27\f\27\16\27\u00ab\13\27\3\27\3\27\3\27\5\27\u00b0\n\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\7\30\u00b8\n\30\f\30\16\30\u00bb\13\30"+
		"\3\30\3\30\3\30\5\30\u00c0\n\30\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u00c8"+
		"\n\31\f\31\16\31\u00cb\13\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u00d3"+
		"\n\32\f\32\16\32\u00d6\13\32\3\32\3\32\7\32\u00da\n\32\f\32\16\32\u00dd"+
		"\13\32\3\32\3\32\3\32\3\32\7\32\u00e3\n\32\f\32\16\32\u00e6\13\32\7\32"+
		"\u00e8\n\32\f\32\16\32\u00eb\13\32\3\32\3\32\5=\u00a9\u00b9\2\33\3\3\5"+
		"\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\2\'\2)\24+\25-\26/\27\61\30\63\31\3\2\r\3\2$$\5\2C\\aac|\7\2"+
		"\60\60\62;C\\aac|\3\2\62;\3\2\63;\4\2GGgg\4\2--//\4\2\13\13\"\"\4\2\f"+
		"\f\16\17\4\2\f\f\17\17\3\2\f\f\2\u0100\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\5\67\3\2\2\2\7"+
		"B\3\2\2\2\tG\3\2\2\2\13Y\3\2\2\2\r\\\3\2\2\2\17g\3\2\2\2\21u\3\2\2\2\23"+
		"x\3\2\2\2\25z\3\2\2\2\27|\3\2\2\2\31~\3\2\2\2\33\u0080\3\2\2\2\35\u0082"+
		"\3\2\2\2\37\u0084\3\2\2\2!\u0086\3\2\2\2#\u0088\3\2\2\2%\u0092\3\2\2\2"+
		"\'\u0094\3\2\2\2)\u009a\3\2\2\2+\u009e\3\2\2\2-\u00a2\3\2\2\2/\u00b3\3"+
		"\2\2\2\61\u00c3\3\2\2\2\63\u00ce\3\2\2\2\65\66\7&\2\2\66\4\3\2\2\2\67"+
		"8\7&\2\289\7}\2\29=\3\2\2\2:<\13\2\2\2;:\3\2\2\2<?\3\2\2\2=>\3\2\2\2="+
		";\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7\177\2\2A\6\3\2\2\2BC\7p\2\2CD\7w\2\2"+
		"DE\7n\2\2EF\7n\2\2F\b\3\2\2\2GK\7$\2\2HJ\n\2\2\2IH\3\2\2\2JM\3\2\2\2K"+
		"I\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NO\7$\2\2O\n\3\2\2\2PQ\7v\2\2Q"+
		"R\7t\2\2RS\7w\2\2SZ\7g\2\2TU\7h\2\2UV\7c\2\2VW\7n\2\2WX\7u\2\2XZ\7g\2"+
		"\2YP\3\2\2\2YT\3\2\2\2Z\f\3\2\2\2[]\t\3\2\2\\[\3\2\2\2]^\3\2\2\2^\\\3"+
		"\2\2\2^_\3\2\2\2_c\3\2\2\2`b\t\4\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3"+
		"\2\2\2d\16\3\2\2\2ec\3\2\2\2fh\7/\2\2gf\3\2\2\2gh\3\2\2\2hi\3\2\2\2ip"+
		"\5%\23\2jl\7\60\2\2km\t\5\2\2lk\3\2\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2"+
		"oq\3\2\2\2pj\3\2\2\2pq\3\2\2\2qs\3\2\2\2rt\5\'\24\2sr\3\2\2\2st\3\2\2"+
		"\2t\20\3\2\2\2uv\7/\2\2vw\7@\2\2w\22\3\2\2\2xy\7.\2\2y\24\3\2\2\2z{\7"+
		"<\2\2{\26\3\2\2\2|}\7?\2\2}\30\3\2\2\2~\177\7*\2\2\177\32\3\2\2\2\u0080"+
		"\u0081\7+\2\2\u0081\34\3\2\2\2\u0082\u0083\7]\2\2\u0083\36\3\2\2\2\u0084"+
		"\u0085\7_\2\2\u0085 \3\2\2\2\u0086\u0087\7}\2\2\u0087\"\3\2\2\2\u0088"+
		"\u0089\7\177\2\2\u0089$\3\2\2\2\u008a\u0093\7\62\2\2\u008b\u008f\t\6\2"+
		"\2\u008c\u008e\t\5\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0092"+
		"\u008a\3\2\2\2\u0092\u008b\3\2\2\2\u0093&\3\2\2\2\u0094\u0096\t\7\2\2"+
		"\u0095\u0097\t\b\2\2\u0096\u0095\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\u0099\5%\23\2\u0099(\3\2\2\2\u009a\u009b\t\t\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009d\b\25\2\2\u009d*\3\2\2\2\u009e\u009f\t\n\2\2"+
		"\u009f\u00a0\3\2\2\2\u00a0\u00a1\b\26\2\2\u00a1,\3\2\2\2\u00a2\u00a3\7"+
		"\61\2\2\u00a3\u00a4\7,\2\2\u00a4\u00a5\7,\2\2\u00a5\u00a9\3\2\2\2\u00a6"+
		"\u00a8\13\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00aa\3"+
		"\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00af\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac"+
		"\u00ad\7,\2\2\u00ad\u00b0\7\61\2\2\u00ae\u00b0\7\2\2\3\u00af\u00ac\3\2"+
		"\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\b\27\2\2\u00b2"+
		".\3\2\2\2\u00b3\u00b4\7\61\2\2\u00b4\u00b5\7,\2\2\u00b5\u00b9\3\2\2\2"+
		"\u00b6\u00b8\13\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9\u00ba"+
		"\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bf\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bc"+
		"\u00bd\7,\2\2\u00bd\u00c0\7\61\2\2\u00be\u00c0\7\2\2\3\u00bf\u00bc\3\2"+
		"\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\b\30\2\2\u00c2"+
		"\60\3\2\2\2\u00c3\u00c4\7\61\2\2\u00c4\u00c5\7\61\2\2\u00c5\u00c9\3\2"+
		"\2\2\u00c6\u00c8\n\13\2\2\u00c7\u00c6\3\2\2\2\u00c8\u00cb\3\2\2\2\u00c9"+
		"\u00c7\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb\u00c9\3\2"+
		"\2\2\u00cc\u00cd\b\31\2\2\u00cd\62\3\2\2\2\u00ce\u00cf\7\61\2\2\u00cf"+
		"\u00d0\7\61\2\2\u00d0\u00d4\3\2\2\2\u00d1\u00d3\n\f\2\2\u00d2\u00d1\3"+
		"\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5"+
		"\u00e9\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00db\7\f\2\2\u00d8\u00da\5)"+
		"\25\2\u00d9\u00d8\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00db"+
		"\u00dc\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd\u00db\3\2\2\2\u00de\u00df\7\61"+
		"\2\2\u00df\u00e0\7\61\2\2\u00e0\u00e4\3\2\2\2\u00e1\u00e3\n\f\2\2\u00e2"+
		"\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2"+
		"\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00d7\3\2\2\2\u00e8"+
		"\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2"+
		"\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\b\32\2\2\u00ed\64\3\2\2\2\32\2=K"+
		"Y\\^acgnps\u008f\u0092\u0096\u00a9\u00af\u00b9\u00bf\u00c9\u00d4\u00db"+
		"\u00e4\u00e9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}