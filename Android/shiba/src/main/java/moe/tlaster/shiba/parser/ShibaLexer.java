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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, NULL=15, STRING=16, BOOLEAN=17, 
		TOKEN=18, NUMBER=19, Hws=20, Vws=21, DocComment=22, BlockComment=23, LineComment=24, 
		LineCommentExt=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "NULL", "STRING", "BOOLEAN", 
		"TOKEN", "NUMBER", "INT", "EXP", "Hws", "Vws", "DocComment", "BlockComment", 
		"LineComment", "LineCommentExt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "','", "'}'", "'->'", "':'", "'='", "'%'", "'['", "']'", 
		"'('", "')'", "'$bind'", "'$res'", "'$json'", "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, "NULL", "STRING", "BOOLEAN", "TOKEN", "NUMBER", "Hws", 
		"Vws", "DocComment", "BlockComment", "LineComment", "LineCommentExt"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00f8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\7\21i\n\21\f\21\16\21l\13\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22y\n\22\3\23\6\23"+
		"|\n\23\r\23\16\23}\3\23\7\23\u0081\n\23\f\23\16\23\u0084\13\23\3\24\5"+
		"\24\u0087\n\24\3\24\3\24\3\24\6\24\u008c\n\24\r\24\16\24\u008d\5\24\u0090"+
		"\n\24\3\24\5\24\u0093\n\24\3\25\3\25\3\25\7\25\u0098\n\25\f\25\16\25\u009b"+
		"\13\25\5\25\u009d\n\25\3\26\3\26\5\26\u00a1\n\26\3\26\3\26\3\27\3\27\3"+
		"\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\7\31\u00b2\n\31"+
		"\f\31\16\31\u00b5\13\31\3\31\3\31\3\31\5\31\u00ba\n\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\7\32\u00c2\n\32\f\32\16\32\u00c5\13\32\3\32\3\32\3\32"+
		"\5\32\u00ca\n\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u00d2\n\33\f\33\16"+
		"\33\u00d5\13\33\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00dd\n\34\f\34\16"+
		"\34\u00e0\13\34\3\34\3\34\7\34\u00e4\n\34\f\34\16\34\u00e7\13\34\3\34"+
		"\3\34\3\34\3\34\7\34\u00ed\n\34\f\34\16\34\u00f0\13\34\7\34\u00f2\n\34"+
		"\f\34\16\34\u00f5\13\34\3\34\3\34\4\u00b3\u00c3\2\35\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\2+\2-\26/\27\61\30\63\31\65\32\67\33\3\2\r\3\2$$\5\2C\\aac|\7\2\60"+
		"\60\62;C\\aac|\3\2\62;\3\2\63;\4\2GGgg\4\2--//\4\2\13\13\"\"\4\2\f\f\16"+
		"\17\4\2\f\f\17\17\3\2\f\f\2\u0109\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2"+
		"\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3"+
		"\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2"+
		"\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\39\3"+
		"\2\2\2\5;\3\2\2\2\7=\3\2\2\2\t?\3\2\2\2\13B\3\2\2\2\rD\3\2\2\2\17F\3\2"+
		"\2\2\21H\3\2\2\2\23J\3\2\2\2\25L\3\2\2\2\27N\3\2\2\2\31P\3\2\2\2\33V\3"+
		"\2\2\2\35[\3\2\2\2\37a\3\2\2\2!f\3\2\2\2#x\3\2\2\2%{\3\2\2\2\'\u0086\3"+
		"\2\2\2)\u009c\3\2\2\2+\u009e\3\2\2\2-\u00a4\3\2\2\2/\u00a8\3\2\2\2\61"+
		"\u00ac\3\2\2\2\63\u00bd\3\2\2\2\65\u00cd\3\2\2\2\67\u00d8\3\2\2\29:\7"+
		"}\2\2:\4\3\2\2\2;<\7.\2\2<\6\3\2\2\2=>\7\177\2\2>\b\3\2\2\2?@\7/\2\2@"+
		"A\7@\2\2A\n\3\2\2\2BC\7<\2\2C\f\3\2\2\2DE\7?\2\2E\16\3\2\2\2FG\7\'\2\2"+
		"G\20\3\2\2\2HI\7]\2\2I\22\3\2\2\2JK\7_\2\2K\24\3\2\2\2LM\7*\2\2M\26\3"+
		"\2\2\2NO\7+\2\2O\30\3\2\2\2PQ\7&\2\2QR\7d\2\2RS\7k\2\2ST\7p\2\2TU\7f\2"+
		"\2U\32\3\2\2\2VW\7&\2\2WX\7t\2\2XY\7g\2\2YZ\7u\2\2Z\34\3\2\2\2[\\\7&\2"+
		"\2\\]\7l\2\2]^\7u\2\2^_\7q\2\2_`\7p\2\2`\36\3\2\2\2ab\7p\2\2bc\7w\2\2"+
		"cd\7n\2\2de\7n\2\2e \3\2\2\2fj\7$\2\2gi\n\2\2\2hg\3\2\2\2il\3\2\2\2jh"+
		"\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7$\2\2n\"\3\2\2\2op\7v\2\2pq"+
		"\7t\2\2qr\7w\2\2ry\7g\2\2st\7h\2\2tu\7c\2\2uv\7n\2\2vw\7u\2\2wy\7g\2\2"+
		"xo\3\2\2\2xs\3\2\2\2y$\3\2\2\2z|\t\3\2\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2\2"+
		"}~\3\2\2\2~\u0082\3\2\2\2\177\u0081\t\4\2\2\u0080\177\3\2\2\2\u0081\u0084"+
		"\3\2\2\2\u0082\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083&\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0085\u0087\7/\2\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2"+
		"\2\2\u0087\u0088\3\2\2\2\u0088\u008f\5)\25\2\u0089\u008b\7\60\2\2\u008a"+
		"\u008c\t\5\2\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u0089\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u0093\5+\26\2\u0092\u0091\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093(\3\2\2\2\u0094\u009d\7\62\2\2\u0095\u0099"+
		"\t\6\2\2\u0096\u0098\t\5\2\2\u0097\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099"+
		"\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2"+
		"\2\2\u009c\u0094\3\2\2\2\u009c\u0095\3\2\2\2\u009d*\3\2\2\2\u009e\u00a0"+
		"\t\7\2\2\u009f\u00a1\t\b\2\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a3\5)\25\2\u00a3,\3\2\2\2\u00a4\u00a5\t\t\2\2"+
		"\u00a5\u00a6\3\2\2\2\u00a6\u00a7\b\27\2\2\u00a7.\3\2\2\2\u00a8\u00a9\t"+
		"\n\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\b\30\2\2\u00ab\60\3\2\2\2\u00ac"+
		"\u00ad\7\61\2\2\u00ad\u00ae\7,\2\2\u00ae\u00af\7,\2\2\u00af\u00b3\3\2"+
		"\2\2\u00b0\u00b2\13\2\2\2\u00b1\u00b0\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b9\3\2\2\2\u00b5\u00b3\3\2"+
		"\2\2\u00b6\u00b7\7,\2\2\u00b7\u00ba\7\61\2\2\u00b8\u00ba\7\2\2\3\u00b9"+
		"\u00b6\3\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\b\31"+
		"\2\2\u00bc\62\3\2\2\2\u00bd\u00be\7\61\2\2\u00be\u00bf\7,\2\2\u00bf\u00c3"+
		"\3\2\2\2\u00c0\u00c2\13\2\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2"+
		"\u00c3\u00c4\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c9\3\2\2\2\u00c5\u00c3"+
		"\3\2\2\2\u00c6\u00c7\7,\2\2\u00c7\u00ca\7\61\2\2\u00c8\u00ca\7\2\2\3\u00c9"+
		"\u00c6\3\2\2\2\u00c9\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cc\b\32"+
		"\2\2\u00cc\64\3\2\2\2\u00cd\u00ce\7\61\2\2\u00ce\u00cf\7\61\2\2\u00cf"+
		"\u00d3\3\2\2\2\u00d0\u00d2\n\13\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3"+
		"\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5"+
		"\u00d3\3\2\2\2\u00d6\u00d7\b\33\2\2\u00d7\66\3\2\2\2\u00d8\u00d9\7\61"+
		"\2\2\u00d9\u00da\7\61\2\2\u00da\u00de\3\2\2\2\u00db\u00dd\n\f\2\2\u00dc"+
		"\u00db\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2"+
		"\2\2\u00df\u00f3\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e5\7\f\2\2\u00e2"+
		"\u00e4\5-\27\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2"+
		"\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8"+
		"\u00e9\7\61\2\2\u00e9\u00ea\7\61\2\2\u00ea\u00ee\3\2\2\2\u00eb\u00ed\n"+
		"\f\2\2\u00ec\u00eb\3\2\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee"+
		"\u00ef\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00e1\3\2"+
		"\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4"+
		"\u00f6\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f7\b\34\2\2\u00f78\3\2\2\2"+
		"\31\2jx{}\u0080\u0082\u0086\u008d\u008f\u0092\u0099\u009c\u00a0\u00b3"+
		"\u00b9\u00c3\u00c9\u00d3\u00de\u00e5\u00ee\u00f3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}