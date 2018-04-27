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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, STRING=7, BOOLEAN=8, TOKEN=9, 
		NUMBER=10, Hws=11, Vws=12, DocComment=13, BlockComment=14, LineComment=15, 
		LineCommentExt=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "STRING", "BOOLEAN", "TOKEN", 
		"NUMBER", "INT", "EXP", "Hws", "Vws", "DocComment", "BlockComment", "LineComment", 
		"LineCommentExt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "','", "'}'", "':'", "'null'", "'%'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "STRING", "BOOLEAN", "TOKEN", 
		"NUMBER", "Hws", "Vws", "DocComment", "BlockComment", "LineComment", "LineCommentExt"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u00c8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\7\b9\n\b\f\b\16\b<\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\tI\n\t\3\n\6\nL\n\n\r\n\16\nM\3\n\7\nQ\n\n\f\n\16\nT\13"+
		"\n\3\13\5\13W\n\13\3\13\3\13\3\13\6\13\\\n\13\r\13\16\13]\5\13`\n\13\3"+
		"\13\5\13c\n\13\3\f\3\f\3\f\7\fh\n\f\f\f\16\fk\13\f\5\fm\n\f\3\r\3\r\5"+
		"\rq\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u0082\n\20\f\20\16\20\u0085\13\20\3\20\3\20\3\20\5\20"+
		"\u008a\n\20\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u0092\n\21\f\21\16\21\u0095"+
		"\13\21\3\21\3\21\3\21\5\21\u009a\n\21\3\21\3\21\3\22\3\22\3\22\3\22\7"+
		"\22\u00a2\n\22\f\22\16\22\u00a5\13\22\3\22\3\22\3\23\3\23\3\23\3\23\7"+
		"\23\u00ad\n\23\f\23\16\23\u00b0\13\23\3\23\3\23\7\23\u00b4\n\23\f\23\16"+
		"\23\u00b7\13\23\3\23\3\23\3\23\3\23\7\23\u00bd\n\23\f\23\16\23\u00c0\13"+
		"\23\7\23\u00c2\n\23\f\23\16\23\u00c5\13\23\3\23\3\23\4\u0083\u0093\2\24"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\2\31\2\33\r\35\16\37"+
		"\17!\20#\21%\22\3\2\r\3\2$$\5\2C\\aac|\7\2\60\60\62;C\\aac|\3\2\62;\3"+
		"\2\63;\4\2GGgg\4\2--//\4\2\13\13\"\"\4\2\f\f\16\17\4\2\f\f\17\17\3\2\f"+
		"\f\2\u00d9\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\3"+
		"\'\3\2\2\2\5)\3\2\2\2\7+\3\2\2\2\t-\3\2\2\2\13/\3\2\2\2\r\64\3\2\2\2\17"+
		"\66\3\2\2\2\21H\3\2\2\2\23K\3\2\2\2\25V\3\2\2\2\27l\3\2\2\2\31n\3\2\2"+
		"\2\33t\3\2\2\2\35x\3\2\2\2\37|\3\2\2\2!\u008d\3\2\2\2#\u009d\3\2\2\2%"+
		"\u00a8\3\2\2\2\'(\7}\2\2(\4\3\2\2\2)*\7.\2\2*\6\3\2\2\2+,\7\177\2\2,\b"+
		"\3\2\2\2-.\7<\2\2.\n\3\2\2\2/\60\7p\2\2\60\61\7w\2\2\61\62\7n\2\2\62\63"+
		"\7n\2\2\63\f\3\2\2\2\64\65\7\'\2\2\65\16\3\2\2\2\66:\7$\2\2\679\n\2\2"+
		"\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;=\3\2\2\2<:\3\2\2\2=>\7$"+
		"\2\2>\20\3\2\2\2?@\7v\2\2@A\7t\2\2AB\7w\2\2BI\7g\2\2CD\7h\2\2DE\7c\2\2"+
		"EF\7n\2\2FG\7u\2\2GI\7g\2\2H?\3\2\2\2HC\3\2\2\2I\22\3\2\2\2JL\t\3\2\2"+
		"KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2NR\3\2\2\2OQ\t\4\2\2PO\3\2\2\2"+
		"QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2S\24\3\2\2\2TR\3\2\2\2UW\7/\2\2VU\3\2\2"+
		"\2VW\3\2\2\2WX\3\2\2\2X_\5\27\f\2Y[\7\60\2\2Z\\\t\5\2\2[Z\3\2\2\2\\]\3"+
		"\2\2\2][\3\2\2\2]^\3\2\2\2^`\3\2\2\2_Y\3\2\2\2_`\3\2\2\2`b\3\2\2\2ac\5"+
		"\31\r\2ba\3\2\2\2bc\3\2\2\2c\26\3\2\2\2dm\7\62\2\2ei\t\6\2\2fh\t\5\2\2"+
		"gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jm\3\2\2\2ki\3\2\2\2ld\3\2\2\2"+
		"le\3\2\2\2m\30\3\2\2\2np\t\7\2\2oq\t\b\2\2po\3\2\2\2pq\3\2\2\2qr\3\2\2"+
		"\2rs\5\27\f\2s\32\3\2\2\2tu\t\t\2\2uv\3\2\2\2vw\b\16\2\2w\34\3\2\2\2x"+
		"y\t\n\2\2yz\3\2\2\2z{\b\17\2\2{\36\3\2\2\2|}\7\61\2\2}~\7,\2\2~\177\7"+
		",\2\2\177\u0083\3\2\2\2\u0080\u0082\13\2\2\2\u0081\u0080\3\2\2\2\u0082"+
		"\u0085\3\2\2\2\u0083\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0089\3\2"+
		"\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7,\2\2\u0087\u008a\7\61\2\2\u0088"+
		"\u008a\7\2\2\3\u0089\u0086\3\2\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2"+
		"\2\2\u008b\u008c\b\20\2\2\u008c \3\2\2\2\u008d\u008e\7\61\2\2\u008e\u008f"+
		"\7,\2\2\u008f\u0093\3\2\2\2\u0090\u0092\13\2\2\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0095\3\2\2\2\u0093\u0094\3\2\2\2\u0093\u0091\3\2\2\2\u0094\u0099\3\2"+
		"\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7,\2\2\u0097\u009a\7\61\2\2\u0098"+
		"\u009a\7\2\2\3\u0099\u0096\3\2\2\2\u0099\u0098\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009c\b\21\2\2\u009c\"\3\2\2\2\u009d\u009e\7\61\2\2\u009e\u009f"+
		"\7\61\2\2\u009f\u00a3\3\2\2\2\u00a0\u00a2\n\13\2\2\u00a1\u00a0\3\2\2\2"+
		"\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6"+
		"\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\b\22\2\2\u00a7$\3\2\2\2\u00a8"+
		"\u00a9\7\61\2\2\u00a9\u00aa\7\61\2\2\u00aa\u00ae\3\2\2\2\u00ab\u00ad\n"+
		"\f\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00c3\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b5\7\f"+
		"\2\2\u00b2\u00b4\5\33\16\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5"+
		"\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2"+
		"\2\2\u00b8\u00b9\7\61\2\2\u00b9\u00ba\7\61\2\2\u00ba\u00be\3\2\2\2\u00bb"+
		"\u00bd\n\f\2\2\u00bc\u00bb\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2"+
		"\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1"+
		"\u00b1\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2"+
		"\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c7\b\23\2\2\u00c7"+
		"&\3\2\2\2\31\2:HKMPRV]_bilp\u0083\u0089\u0093\u0099\u00a3\u00ae\u00b5"+
		"\u00be\u00c3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}