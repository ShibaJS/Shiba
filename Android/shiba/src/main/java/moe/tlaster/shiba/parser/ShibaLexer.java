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
		T__9=10, T__10=11, T__11=12, T__12=13, STRING=14, BOOLEAN=15, TOKEN=16, 
		NUMBER=17, Hws=18, Vws=19, DocComment=20, BlockComment=21, LineComment=22, 
		LineCommentExt=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "STRING", "BOOLEAN", "TOKEN", "NUMBER", 
		"INT", "EXP", "Hws", "Vws", "DocComment", "BlockComment", "LineComment", 
		"LineCommentExt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "','", "'}'", "':'", "'null'", "'%'", "'('", "')'", "'$bind'", 
		"'$res'", "'$json'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "STRING", "BOOLEAN", "TOKEN", "NUMBER", "Hws", "Vws", "DocComment", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\31\u00ef\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\7\17`\n\17\f\17\16"+
		"\17c\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"p\n\20\3\21\6\21s\n\21\r\21\16\21t\3\21\7\21x\n\21\f\21\16\21{\13\21\3"+
		"\22\5\22~\n\22\3\22\3\22\3\22\6\22\u0083\n\22\r\22\16\22\u0084\5\22\u0087"+
		"\n\22\3\22\5\22\u008a\n\22\3\23\3\23\3\23\7\23\u008f\n\23\f\23\16\23\u0092"+
		"\13\23\5\23\u0094\n\23\3\24\3\24\5\24\u0098\n\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\7\27\u00a9\n\27"+
		"\f\27\16\27\u00ac\13\27\3\27\3\27\3\27\5\27\u00b1\n\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\7\30\u00b9\n\30\f\30\16\30\u00bc\13\30\3\30\3\30\3\30"+
		"\5\30\u00c1\n\30\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u00c9\n\31\f\31\16"+
		"\31\u00cc\13\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u00d4\n\32\f\32\16"+
		"\32\u00d7\13\32\3\32\3\32\7\32\u00db\n\32\f\32\16\32\u00de\13\32\3\32"+
		"\3\32\3\32\3\32\7\32\u00e4\n\32\f\32\16\32\u00e7\13\32\7\32\u00e9\n\32"+
		"\f\32\16\32\u00ec\13\32\3\32\3\32\4\u00aa\u00ba\2\33\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\2\'"+
		"\2)\24+\25-\26/\27\61\30\63\31\3\2\r\3\2$$\5\2C\\aac|\7\2\60\60\62;C\\"+
		"aac|\3\2\62;\3\2\63;\4\2GGgg\4\2--//\4\2\13\13\"\"\4\2\f\f\16\17\4\2\f"+
		"\f\17\17\3\2\f\f\2\u0100\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2"+
		"\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\2#\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2"+
		"\2\61\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\5\67\3\2\2\2\79\3\2\2\2\t;\3\2"+
		"\2\2\13=\3\2\2\2\rB\3\2\2\2\17D\3\2\2\2\21F\3\2\2\2\23H\3\2\2\2\25N\3"+
		"\2\2\2\27S\3\2\2\2\31Y\3\2\2\2\33[\3\2\2\2\35]\3\2\2\2\37o\3\2\2\2!r\3"+
		"\2\2\2#}\3\2\2\2%\u0093\3\2\2\2\'\u0095\3\2\2\2)\u009b\3\2\2\2+\u009f"+
		"\3\2\2\2-\u00a3\3\2\2\2/\u00b4\3\2\2\2\61\u00c4\3\2\2\2\63\u00cf\3\2\2"+
		"\2\65\66\7}\2\2\66\4\3\2\2\2\678\7.\2\28\6\3\2\2\29:\7\177\2\2:\b\3\2"+
		"\2\2;<\7<\2\2<\n\3\2\2\2=>\7p\2\2>?\7w\2\2?@\7n\2\2@A\7n\2\2A\f\3\2\2"+
		"\2BC\7\'\2\2C\16\3\2\2\2DE\7*\2\2E\20\3\2\2\2FG\7+\2\2G\22\3\2\2\2HI\7"+
		"&\2\2IJ\7d\2\2JK\7k\2\2KL\7p\2\2LM\7f\2\2M\24\3\2\2\2NO\7&\2\2OP\7t\2"+
		"\2PQ\7g\2\2QR\7u\2\2R\26\3\2\2\2ST\7&\2\2TU\7l\2\2UV\7u\2\2VW\7q\2\2W"+
		"X\7p\2\2X\30\3\2\2\2YZ\7]\2\2Z\32\3\2\2\2[\\\7_\2\2\\\34\3\2\2\2]a\7$"+
		"\2\2^`\n\2\2\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2"+
		"\2\2de\7$\2\2e\36\3\2\2\2fg\7v\2\2gh\7t\2\2hi\7w\2\2ip\7g\2\2jk\7h\2\2"+
		"kl\7c\2\2lm\7n\2\2mn\7u\2\2np\7g\2\2of\3\2\2\2oj\3\2\2\2p \3\2\2\2qs\t"+
		"\3\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3\2\2\2uy\3\2\2\2vx\t\4\2\2wv\3"+
		"\2\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z\"\3\2\2\2{y\3\2\2\2|~\7/\2\2}|\3"+
		"\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0086\5%\23\2\u0080\u0082\7\60\2\2\u0081"+
		"\u0083\t\5\2\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2"+
		"\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0080\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u008a\5\'\24\2\u0089\u0088\3"+
		"\2\2\2\u0089\u008a\3\2\2\2\u008a$\3\2\2\2\u008b\u0094\7\62\2\2\u008c\u0090"+
		"\t\6\2\2\u008d\u008f\t\5\2\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2"+
		"\2\2\u0093\u008b\3\2\2\2\u0093\u008c\3\2\2\2\u0094&\3\2\2\2\u0095\u0097"+
		"\t\7\2\2\u0096\u0098\t\b\2\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u009a\5%\23\2\u009a(\3\2\2\2\u009b\u009c\t\t\2\2"+
		"\u009c\u009d\3\2\2\2\u009d\u009e\b\25\2\2\u009e*\3\2\2\2\u009f\u00a0\t"+
		"\n\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\b\26\2\2\u00a2,\3\2\2\2\u00a3\u00a4"+
		"\7\61\2\2\u00a4\u00a5\7,\2\2\u00a5\u00a6\7,\2\2\u00a6\u00aa\3\2\2\2\u00a7"+
		"\u00a9\13\2\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00ab\3"+
		"\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00b0\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad"+
		"\u00ae\7,\2\2\u00ae\u00b1\7\61\2\2\u00af\u00b1\7\2\2\3\u00b0\u00ad\3\2"+
		"\2\2\u00b0\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\b\27\2\2\u00b3"+
		".\3\2\2\2\u00b4\u00b5\7\61\2\2\u00b5\u00b6\7,\2\2\u00b6\u00ba\3\2\2\2"+
		"\u00b7\u00b9\13\2\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00bb"+
		"\3\2\2\2\u00ba\u00b8\3\2\2\2\u00bb\u00c0\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd"+
		"\u00be\7,\2\2\u00be\u00c1\7\61\2\2\u00bf\u00c1\7\2\2\3\u00c0\u00bd\3\2"+
		"\2\2\u00c0\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\b\30\2\2\u00c3"+
		"\60\3\2\2\2\u00c4\u00c5\7\61\2\2\u00c5\u00c6\7\61\2\2\u00c6\u00ca\3\2"+
		"\2\2\u00c7\u00c9\n\13\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca"+
		"\u00c8\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00ca\3\2"+
		"\2\2\u00cd\u00ce\b\31\2\2\u00ce\62\3\2\2\2\u00cf\u00d0\7\61\2\2\u00d0"+
		"\u00d1\7\61\2\2\u00d1\u00d5\3\2\2\2\u00d2\u00d4\n\f\2\2\u00d3\u00d2\3"+
		"\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6"+
		"\u00ea\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00dc\7\f\2\2\u00d9\u00db\5)"+
		"\25\2\u00da\u00d9\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc"+
		"\u00dd\3\2\2\2\u00dd\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\7\61"+
		"\2\2\u00e0\u00e1\7\61\2\2\u00e1\u00e5\3\2\2\2\u00e2\u00e4\n\f\2\2\u00e3"+
		"\u00e2\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2"+
		"\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00d8\3\2\2\2\u00e9"+
		"\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3\2"+
		"\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\b\32\2\2\u00ee\64\3\2\2\2\31\2ao"+
		"rtwy}\u0084\u0086\u0089\u0090\u0093\u0097\u00aa\u00b0\u00ba\u00c0\u00ca"+
		"\u00d5\u00dc\u00e5\u00ea\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}