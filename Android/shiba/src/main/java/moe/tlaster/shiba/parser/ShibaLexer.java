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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, STRING=16, 
		BOOLEAN=17, TOKEN=18, NUMBER=19, Hws=20, Vws=21, DocComment=22, BlockComment=23, 
		LineComment=24, LineCommentExt=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "STRING", "BOOLEAN", 
		"TOKEN", "NUMBER", "INT", "EXP", "Hws", "Vws", "DocComment", "BlockComment", 
		"LineComment", "LineCommentExt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "','", "'}'", "':'", "'='", "'null'", "'%'", "'$calc'", "'('", 
		"')'", "'$bind'", "'$res'", "'$json'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "STRING", "BOOLEAN", "TOKEN", "NUMBER", "Hws", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\33\u00fb\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\7\21l\n\21\f\21\16\21o\13\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22|\n\22\3\23"+
		"\6\23\177\n\23\r\23\16\23\u0080\3\23\7\23\u0084\n\23\f\23\16\23\u0087"+
		"\13\23\3\24\5\24\u008a\n\24\3\24\3\24\3\24\6\24\u008f\n\24\r\24\16\24"+
		"\u0090\5\24\u0093\n\24\3\24\5\24\u0096\n\24\3\25\3\25\3\25\7\25\u009b"+
		"\n\25\f\25\16\25\u009e\13\25\5\25\u00a0\n\25\3\26\3\26\5\26\u00a4\n\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31"+
		"\3\31\7\31\u00b5\n\31\f\31\16\31\u00b8\13\31\3\31\3\31\3\31\5\31\u00bd"+
		"\n\31\3\31\3\31\3\32\3\32\3\32\3\32\7\32\u00c5\n\32\f\32\16\32\u00c8\13"+
		"\32\3\32\3\32\3\32\5\32\u00cd\n\32\3\32\3\32\3\33\3\33\3\33\3\33\7\33"+
		"\u00d5\n\33\f\33\16\33\u00d8\13\33\3\33\3\33\3\34\3\34\3\34\3\34\7\34"+
		"\u00e0\n\34\f\34\16\34\u00e3\13\34\3\34\3\34\7\34\u00e7\n\34\f\34\16\34"+
		"\u00ea\13\34\3\34\3\34\3\34\3\34\7\34\u00f0\n\34\f\34\16\34\u00f3\13\34"+
		"\7\34\u00f5\n\34\f\34\16\34\u00f8\13\34\3\34\3\34\4\u00b6\u00c6\2\35\3"+
		"\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37"+
		"\21!\22#\23%\24\'\25)\2+\2-\26/\27\61\30\63\31\65\32\67\33\3\2\r\3\2$"+
		"$\5\2C\\aac|\7\2\60\60\62;C\\aac|\3\2\62;\3\2\63;\4\2GGgg\4\2--//\4\2"+
		"\13\13\"\"\4\2\f\f\16\17\4\2\f\f\17\17\3\2\f\f\2\u010c\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2"+
		"\2\2\67\3\2\2\2\39\3\2\2\2\5;\3\2\2\2\7=\3\2\2\2\t?\3\2\2\2\13A\3\2\2"+
		"\2\rC\3\2\2\2\17H\3\2\2\2\21J\3\2\2\2\23P\3\2\2\2\25R\3\2\2\2\27T\3\2"+
		"\2\2\31Z\3\2\2\2\33_\3\2\2\2\35e\3\2\2\2\37g\3\2\2\2!i\3\2\2\2#{\3\2\2"+
		"\2%~\3\2\2\2\'\u0089\3\2\2\2)\u009f\3\2\2\2+\u00a1\3\2\2\2-\u00a7\3\2"+
		"\2\2/\u00ab\3\2\2\2\61\u00af\3\2\2\2\63\u00c0\3\2\2\2\65\u00d0\3\2\2\2"+
		"\67\u00db\3\2\2\29:\7}\2\2:\4\3\2\2\2;<\7.\2\2<\6\3\2\2\2=>\7\177\2\2"+
		">\b\3\2\2\2?@\7<\2\2@\n\3\2\2\2AB\7?\2\2B\f\3\2\2\2CD\7p\2\2DE\7w\2\2"+
		"EF\7n\2\2FG\7n\2\2G\16\3\2\2\2HI\7\'\2\2I\20\3\2\2\2JK\7&\2\2KL\7e\2\2"+
		"LM\7c\2\2MN\7n\2\2NO\7e\2\2O\22\3\2\2\2PQ\7*\2\2Q\24\3\2\2\2RS\7+\2\2"+
		"S\26\3\2\2\2TU\7&\2\2UV\7d\2\2VW\7k\2\2WX\7p\2\2XY\7f\2\2Y\30\3\2\2\2"+
		"Z[\7&\2\2[\\\7t\2\2\\]\7g\2\2]^\7u\2\2^\32\3\2\2\2_`\7&\2\2`a\7l\2\2a"+
		"b\7u\2\2bc\7q\2\2cd\7p\2\2d\34\3\2\2\2ef\7]\2\2f\36\3\2\2\2gh\7_\2\2h"+
		" \3\2\2\2im\7$\2\2jl\n\2\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2n"+
		"p\3\2\2\2om\3\2\2\2pq\7$\2\2q\"\3\2\2\2rs\7v\2\2st\7t\2\2tu\7w\2\2u|\7"+
		"g\2\2vw\7h\2\2wx\7c\2\2xy\7n\2\2yz\7u\2\2z|\7g\2\2{r\3\2\2\2{v\3\2\2\2"+
		"|$\3\2\2\2}\177\t\3\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0085\3\2\2\2\u0082\u0084\t\4\2\2\u0083\u0082\3\2"+
		"\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086"+
		"&\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u008a\7/\2\2\u0089\u0088\3\2\2\2\u0089"+
		"\u008a\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0092\5)\25\2\u008c\u008e\7\60"+
		"\2\2\u008d\u008f\t\5\2\2\u008e\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u008c\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0096\5+\26\2\u0095"+
		"\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096(\3\2\2\2\u0097\u00a0\7\62\2\2"+
		"\u0098\u009c\t\6\2\2\u0099\u009b\t\5\2\2\u009a\u0099\3\2\2\2\u009b\u009e"+
		"\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u00a0\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009f\u0097\3\2\2\2\u009f\u0098\3\2\2\2\u00a0*\3\2\2\2"+
		"\u00a1\u00a3\t\7\2\2\u00a2\u00a4\t\b\2\2\u00a3\u00a2\3\2\2\2\u00a3\u00a4"+
		"\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\5)\25\2\u00a6,\3\2\2\2\u00a7"+
		"\u00a8\t\t\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\b\27\2\2\u00aa.\3\2\2\2"+
		"\u00ab\u00ac\t\n\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\b\30\2\2\u00ae\60"+
		"\3\2\2\2\u00af\u00b0\7\61\2\2\u00b0\u00b1\7,\2\2\u00b1\u00b2\7,\2\2\u00b2"+
		"\u00b6\3\2\2\2\u00b3\u00b5\13\2\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3"+
		"\2\2\2\u00b6\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00bc\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b9\u00ba\7,\2\2\u00ba\u00bd\7\61\2\2\u00bb\u00bd\7\2"+
		"\2\3\u00bc\u00b9\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"\u00bf\b\31\2\2\u00bf\62\3\2\2\2\u00c0\u00c1\7\61\2\2\u00c1\u00c2\7,\2"+
		"\2\u00c2\u00c6\3\2\2\2\u00c3\u00c5\13\2\2\2\u00c4\u00c3\3\2\2\2\u00c5"+
		"\u00c8\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00cc\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7,\2\2\u00ca\u00cd\7\61\2\2\u00cb"+
		"\u00cd\7\2\2\3\u00cc\u00c9\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2"+
		"\2\2\u00ce\u00cf\b\32\2\2\u00cf\64\3\2\2\2\u00d0\u00d1\7\61\2\2\u00d1"+
		"\u00d2\7\61\2\2\u00d2\u00d6\3\2\2\2\u00d3\u00d5\n\13\2\2\u00d4\u00d3\3"+
		"\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7"+
		"\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\b\33\2\2\u00da\66\3\2\2"+
		"\2\u00db\u00dc\7\61\2\2\u00dc\u00dd\7\61\2\2\u00dd\u00e1\3\2\2\2\u00de"+
		"\u00e0\n\f\2\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2"+
		"\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00f6\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e4"+
		"\u00e8\7\f\2\2\u00e5\u00e7\5-\27\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2"+
		"\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00ec\7\61\2\2\u00ec\u00ed\7\61\2\2\u00ed\u00f1\3"+
		"\2\2\2\u00ee\u00f0\n\f\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2"+
		"\2\2\u00f4\u00e4\3\2\2\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6"+
		"\u00f7\3\2\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa\b\34"+
		"\2\2\u00fa8\3\2\2\2\31\2m{~\u0080\u0083\u0085\u0089\u0090\u0092\u0095"+
		"\u009c\u009f\u00a3\u00b6\u00bc\u00c6\u00cc\u00d6\u00e1\u00e8\u00f1\u00f6"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}