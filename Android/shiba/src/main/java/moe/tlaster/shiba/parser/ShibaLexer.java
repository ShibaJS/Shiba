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
		T__9=10, T__10=11, T__11=12, T__12=13, NULL=14, STRING=15, BOOLEAN=16, 
		TOKEN=17, NUMBER=18, Hws=19, Vws=20, DocComment=21, BlockComment=22, LineComment=23, 
		LineCommentExt=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "NULL", "STRING", "BOOLEAN", "TOKEN", 
		"NUMBER", "INT", "EXP", "Hws", "Vws", "DocComment", "BlockComment", "LineComment", 
		"LineCommentExt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "','", "'}'", "':'", "'='", "'%'", "'['", "']'", "'('", "')'", 
		"'$bind'", "'$res'", "'$json'", "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "NULL", "STRING", "BOOLEAN", "TOKEN", "NUMBER", "Hws", "Vws", 
		"DocComment", "BlockComment", "LineComment", "LineCommentExt"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u00f3\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\7\20d\n\20\f\20\16\20g\13\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\5\21t\n\21\3\22\6\22w\n\22\r\22\16\22x\3\22\7"+
		"\22|\n\22\f\22\16\22\177\13\22\3\23\5\23\u0082\n\23\3\23\3\23\3\23\6\23"+
		"\u0087\n\23\r\23\16\23\u0088\5\23\u008b\n\23\3\23\5\23\u008e\n\23\3\24"+
		"\3\24\3\24\7\24\u0093\n\24\f\24\16\24\u0096\13\24\5\24\u0098\n\24\3\25"+
		"\3\25\5\25\u009c\n\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\30\3\30\3\30\3\30\3\30\7\30\u00ad\n\30\f\30\16\30\u00b0\13\30\3\30"+
		"\3\30\3\30\5\30\u00b5\n\30\3\30\3\30\3\31\3\31\3\31\3\31\7\31\u00bd\n"+
		"\31\f\31\16\31\u00c0\13\31\3\31\3\31\3\31\5\31\u00c5\n\31\3\31\3\31\3"+
		"\32\3\32\3\32\3\32\7\32\u00cd\n\32\f\32\16\32\u00d0\13\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\33\7\33\u00d8\n\33\f\33\16\33\u00db\13\33\3\33\3\33\7"+
		"\33\u00df\n\33\f\33\16\33\u00e2\13\33\3\33\3\33\3\33\3\33\7\33\u00e8\n"+
		"\33\f\33\16\33\u00eb\13\33\7\33\u00ed\n\33\f\33\16\33\u00f0\13\33\3\33"+
		"\3\33\4\u00ae\u00be\2\34\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\2)\2+\25-\26/\27\61\30\63"+
		"\31\65\32\3\2\r\3\2$$\5\2C\\aac|\7\2\60\60\62;C\\aac|\3\2\62;\3\2\63;"+
		"\4\2GGgg\4\2--//\4\2\13\13\"\"\4\2\f\f\16\17\4\2\f\f\17\17\3\2\f\f\2\u0104"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2"+
		"\2\2\2\65\3\2\2\2\3\67\3\2\2\2\59\3\2\2\2\7;\3\2\2\2\t=\3\2\2\2\13?\3"+
		"\2\2\2\rA\3\2\2\2\17C\3\2\2\2\21E\3\2\2\2\23G\3\2\2\2\25I\3\2\2\2\27K"+
		"\3\2\2\2\31Q\3\2\2\2\33V\3\2\2\2\35\\\3\2\2\2\37a\3\2\2\2!s\3\2\2\2#v"+
		"\3\2\2\2%\u0081\3\2\2\2\'\u0097\3\2\2\2)\u0099\3\2\2\2+\u009f\3\2\2\2"+
		"-\u00a3\3\2\2\2/\u00a7\3\2\2\2\61\u00b8\3\2\2\2\63\u00c8\3\2\2\2\65\u00d3"+
		"\3\2\2\2\678\7}\2\28\4\3\2\2\29:\7.\2\2:\6\3\2\2\2;<\7\177\2\2<\b\3\2"+
		"\2\2=>\7<\2\2>\n\3\2\2\2?@\7?\2\2@\f\3\2\2\2AB\7\'\2\2B\16\3\2\2\2CD\7"+
		"]\2\2D\20\3\2\2\2EF\7_\2\2F\22\3\2\2\2GH\7*\2\2H\24\3\2\2\2IJ\7+\2\2J"+
		"\26\3\2\2\2KL\7&\2\2LM\7d\2\2MN\7k\2\2NO\7p\2\2OP\7f\2\2P\30\3\2\2\2Q"+
		"R\7&\2\2RS\7t\2\2ST\7g\2\2TU\7u\2\2U\32\3\2\2\2VW\7&\2\2WX\7l\2\2XY\7"+
		"u\2\2YZ\7q\2\2Z[\7p\2\2[\34\3\2\2\2\\]\7p\2\2]^\7w\2\2^_\7n\2\2_`\7n\2"+
		"\2`\36\3\2\2\2ae\7$\2\2bd\n\2\2\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2"+
		"\2\2fh\3\2\2\2ge\3\2\2\2hi\7$\2\2i \3\2\2\2jk\7v\2\2kl\7t\2\2lm\7w\2\2"+
		"mt\7g\2\2no\7h\2\2op\7c\2\2pq\7n\2\2qr\7u\2\2rt\7g\2\2sj\3\2\2\2sn\3\2"+
		"\2\2t\"\3\2\2\2uw\t\3\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y}\3"+
		"\2\2\2z|\t\4\2\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~$\3\2\2\2"+
		"\177}\3\2\2\2\u0080\u0082\7/\2\2\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2"+
		"\2\u0082\u0083\3\2\2\2\u0083\u008a\5\'\24\2\u0084\u0086\7\60\2\2\u0085"+
		"\u0087\t\5\2\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a\u0084\3\2\2\2\u008a"+
		"\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008e\5)\25\2\u008d\u008c\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e&\3\2\2\2\u008f\u0098\7\62\2\2\u0090\u0094"+
		"\t\6\2\2\u0091\u0093\t\5\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0097\u008f\3\2\2\2\u0097\u0090\3\2\2\2\u0098(\3\2\2\2\u0099\u009b"+
		"\t\7\2\2\u009a\u009c\t\b\2\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009d\3\2\2\2\u009d\u009e\5\'\24\2\u009e*\3\2\2\2\u009f\u00a0\t\t\2\2"+
		"\u00a0\u00a1\3\2\2\2\u00a1\u00a2\b\26\2\2\u00a2,\3\2\2\2\u00a3\u00a4\t"+
		"\n\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\b\27\2\2\u00a6.\3\2\2\2\u00a7\u00a8"+
		"\7\61\2\2\u00a8\u00a9\7,\2\2\u00a9\u00aa\7,\2\2\u00aa\u00ae\3\2\2\2\u00ab"+
		"\u00ad\13\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00af\3"+
		"\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b4\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1"+
		"\u00b2\7,\2\2\u00b2\u00b5\7\61\2\2\u00b3\u00b5\7\2\2\3\u00b4\u00b1\3\2"+
		"\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\b\30\2\2\u00b7"+
		"\60\3\2\2\2\u00b8\u00b9\7\61\2\2\u00b9\u00ba\7,\2\2\u00ba\u00be\3\2\2"+
		"\2\u00bb\u00bd\13\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c4\3\2\2\2\u00c0\u00be\3\2"+
		"\2\2\u00c1\u00c2\7,\2\2\u00c2\u00c5\7\61\2\2\u00c3\u00c5\7\2\2\3\u00c4"+
		"\u00c1\3\2\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\b\31"+
		"\2\2\u00c7\62\3\2\2\2\u00c8\u00c9\7\61\2\2\u00c9\u00ca\7\61\2\2\u00ca"+
		"\u00ce\3\2\2\2\u00cb\u00cd\n\13\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3"+
		"\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0"+
		"\u00ce\3\2\2\2\u00d1\u00d2\b\32\2\2\u00d2\64\3\2\2\2\u00d3\u00d4\7\61"+
		"\2\2\u00d4\u00d5\7\61\2\2\u00d5\u00d9\3\2\2\2\u00d6\u00d8\n\f\2\2\u00d7"+
		"\u00d6\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2"+
		"\2\2\u00da\u00ee\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00e0\7\f\2\2\u00dd"+
		"\u00df\5+\26\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3"+
		"\u00e4\7\61\2\2\u00e4\u00e5\7\61\2\2\u00e5\u00e9\3\2\2\2\u00e6\u00e8\n"+
		"\f\2\2\u00e7\u00e6\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9"+
		"\u00ea\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00dc\3\2"+
		"\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef"+
		"\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f2\b\33\2\2\u00f2\66\3\2\2"+
		"\2\31\2esvx{}\u0081\u0088\u008a\u008d\u0094\u0097\u009b\u00ae\u00b4\u00be"+
		"\u00c4\u00ce\u00d9\u00e0\u00e9\u00ee\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}