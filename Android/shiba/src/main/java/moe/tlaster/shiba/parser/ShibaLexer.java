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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, STRING=6, BOOLEAN=7, TOKEN=8, 
		NUMBER=9, Hws=10, Vws=11, DocComment=12, BlockComment=13, LineComment=14, 
		LineCommentExt=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "STRING", "BOOLEAN", "TOKEN", 
		"NUMBER", "INT", "EXP", "Hws", "Vws", "DocComment", "BlockComment", "LineComment", 
		"LineCommentExt"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'{'", "','", "'}'", "':'", "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "STRING", "BOOLEAN", "TOKEN", "NUMBER", 
		"Hws", "Vws", "DocComment", "BlockComment", "LineComment", "LineCommentExt"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u00c4\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\7\7"+
		"\65\n\7\f\7\16\78\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b"+
		"E\n\b\3\t\6\tH\n\t\r\t\16\tI\3\t\7\tM\n\t\f\t\16\tP\13\t\3\n\5\nS\n\n"+
		"\3\n\3\n\3\n\6\nX\n\n\r\n\16\nY\5\n\\\n\n\3\n\5\n_\n\n\3\13\3\13\3\13"+
		"\7\13d\n\13\f\13\16\13g\13\13\5\13i\n\13\3\f\3\f\5\fm\n\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\7\17~\n\17\f"+
		"\17\16\17\u0081\13\17\3\17\3\17\3\17\5\17\u0086\n\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\7\20\u008e\n\20\f\20\16\20\u0091\13\20\3\20\3\20\3\20\5"+
		"\20\u0096\n\20\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u009e\n\21\f\21\16\21"+
		"\u00a1\13\21\3\21\3\21\3\22\3\22\3\22\3\22\7\22\u00a9\n\22\f\22\16\22"+
		"\u00ac\13\22\3\22\3\22\7\22\u00b0\n\22\f\22\16\22\u00b3\13\22\3\22\3\22"+
		"\3\22\3\22\7\22\u00b9\n\22\f\22\16\22\u00bc\13\22\7\22\u00be\n\22\f\22"+
		"\16\22\u00c1\13\22\3\22\3\22\4\177\u008f\2\23\3\3\5\4\7\5\t\6\13\7\r\b"+
		"\17\t\21\n\23\13\25\2\27\2\31\f\33\r\35\16\37\17!\20#\21\3\2\r\3\2$$\5"+
		"\2C\\aac|\6\2\62;C\\aac|\3\2\62;\3\2\63;\4\2GGgg\4\2--//\4\2\13\13\"\""+
		"\4\2\f\f\16\17\4\2\f\f\17\17\3\2\f\f\2\u00d5\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2"+
		"\2!\3\2\2\2\2#\3\2\2\2\3%\3\2\2\2\5\'\3\2\2\2\7)\3\2\2\2\t+\3\2\2\2\13"+
		"-\3\2\2\2\r\62\3\2\2\2\17D\3\2\2\2\21G\3\2\2\2\23R\3\2\2\2\25h\3\2\2\2"+
		"\27j\3\2\2\2\31p\3\2\2\2\33t\3\2\2\2\35x\3\2\2\2\37\u0089\3\2\2\2!\u0099"+
		"\3\2\2\2#\u00a4\3\2\2\2%&\7}\2\2&\4\3\2\2\2\'(\7.\2\2(\6\3\2\2\2)*\7\177"+
		"\2\2*\b\3\2\2\2+,\7<\2\2,\n\3\2\2\2-.\7p\2\2./\7w\2\2/\60\7n\2\2\60\61"+
		"\7n\2\2\61\f\3\2\2\2\62\66\7$\2\2\63\65\n\2\2\2\64\63\3\2\2\2\658\3\2"+
		"\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66\3\2\2\29:\7$\2\2:\16"+
		"\3\2\2\2;<\7v\2\2<=\7t\2\2=>\7w\2\2>E\7g\2\2?@\7h\2\2@A\7c\2\2AB\7n\2"+
		"\2BC\7u\2\2CE\7g\2\2D;\3\2\2\2D?\3\2\2\2E\20\3\2\2\2FH\t\3\2\2GF\3\2\2"+
		"\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JN\3\2\2\2KM\t\4\2\2LK\3\2\2\2MP\3\2\2"+
		"\2NL\3\2\2\2NO\3\2\2\2O\22\3\2\2\2PN\3\2\2\2QS\7/\2\2RQ\3\2\2\2RS\3\2"+
		"\2\2ST\3\2\2\2T[\5\25\13\2UW\7\60\2\2VX\t\5\2\2WV\3\2\2\2XY\3\2\2\2YW"+
		"\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[U\3\2\2\2[\\\3\2\2\2\\^\3\2\2\2]_\5\27\f"+
		"\2^]\3\2\2\2^_\3\2\2\2_\24\3\2\2\2`i\7\62\2\2ae\t\6\2\2bd\t\5\2\2cb\3"+
		"\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fi\3\2\2\2ge\3\2\2\2h`\3\2\2\2ha\3"+
		"\2\2\2i\26\3\2\2\2jl\t\7\2\2km\t\b\2\2lk\3\2\2\2lm\3\2\2\2mn\3\2\2\2n"+
		"o\5\25\13\2o\30\3\2\2\2pq\t\t\2\2qr\3\2\2\2rs\b\r\2\2s\32\3\2\2\2tu\t"+
		"\n\2\2uv\3\2\2\2vw\b\16\2\2w\34\3\2\2\2xy\7\61\2\2yz\7,\2\2z{\7,\2\2{"+
		"\177\3\2\2\2|~\13\2\2\2}|\3\2\2\2~\u0081\3\2\2\2\177\u0080\3\2\2\2\177"+
		"}\3\2\2\2\u0080\u0085\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7,\2\2\u0083"+
		"\u0086\7\61\2\2\u0084\u0086\7\2\2\3\u0085\u0082\3\2\2\2\u0085\u0084\3"+
		"\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\b\17\2\2\u0088\36\3\2\2\2\u0089"+
		"\u008a\7\61\2\2\u008a\u008b\7,\2\2\u008b\u008f\3\2\2\2\u008c\u008e\13"+
		"\2\2\2\u008d\u008c\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u0090\3\2\2\2\u008f"+
		"\u008d\3\2\2\2\u0090\u0095\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0093\7,"+
		"\2\2\u0093\u0096\7\61\2\2\u0094\u0096\7\2\2\3\u0095\u0092\3\2\2\2\u0095"+
		"\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\b\20\2\2\u0098 \3\2\2\2"+
		"\u0099\u009a\7\61\2\2\u009a\u009b\7\61\2\2\u009b\u009f\3\2\2\2\u009c\u009e"+
		"\n\13\2\2\u009d\u009c\3\2\2\2\u009e\u00a1\3\2\2\2\u009f\u009d\3\2\2\2"+
		"\u009f\u00a0\3\2\2\2\u00a0\u00a2\3\2\2\2\u00a1\u009f\3\2\2\2\u00a2\u00a3"+
		"\b\21\2\2\u00a3\"\3\2\2\2\u00a4\u00a5\7\61\2\2\u00a5\u00a6\7\61\2\2\u00a6"+
		"\u00aa\3\2\2\2\u00a7\u00a9\n\f\2\2\u00a8\u00a7\3\2\2\2\u00a9\u00ac\3\2"+
		"\2\2\u00aa\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00bf\3\2\2\2\u00ac"+
		"\u00aa\3\2\2\2\u00ad\u00b1\7\f\2\2\u00ae\u00b0\5\31\r\2\u00af\u00ae\3"+
		"\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7\61\2\2\u00b5\u00b6\7"+
		"\61\2\2\u00b6\u00ba\3\2\2\2\u00b7\u00b9\n\f\2\2\u00b8\u00b7\3\2\2\2\u00b9"+
		"\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00be\3\2"+
		"\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00ad\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c2\u00c3\b\22\2\2\u00c3$\3\2\2\2\31\2\66DGILNRY[^ehl\177\u0085"+
		"\u008f\u0095\u009f\u00aa\u00b1\u00ba\u00bf\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}