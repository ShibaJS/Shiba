// Generated from Shiba.g4 by ANTLR 4.7.1
package moe.tlaster.shiba.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ShibaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, STRING=6, BOOLEAN=7, TOKEN=8, 
		NUMBER=9, Hws=10, Vws=11, DocComment=12, BlockComment=13, LineComment=14, 
		LineCommentExt=15;
	public static final int
		RULE_root = 0, RULE_obj = 1, RULE_pair = 2, RULE_value = 3;
	public static final String[] ruleNames = {
		"root", "obj", "pair", "value"
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

	@Override
	public String getGrammarFileName() { return "Shiba.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ShibaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public ObjContext obj() {
			return getRuleContext(ObjContext.class,0);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			obj();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjContext extends ParserRuleContext {
		public TerminalNode TOKEN() { return getToken(ShibaParser.TOKEN, 0); }
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public List<ObjContext> obj() {
			return getRuleContexts(ObjContext.class);
		}
		public ObjContext obj(int i) {
			return getRuleContext(ObjContext.class,i);
		}
		public ObjContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obj; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterObj(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitObj(this);
		}
	}

	public final ObjContext obj() throws RecognitionException {
		ObjContext _localctx = new ObjContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_obj);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(TOKEN);
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(11);
				match(T__0);
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TOKEN) {
					{
					setState(14);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(12);
						pair();
						}
						break;
					case 2:
						{
						setState(13);
						obj();
						}
						break;
					}
					setState(25);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1 || _la==TOKEN) {
						{
						{
						setState(17);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__1) {
							{
							setState(16);
							match(T__1);
							}
						}

						setState(21);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
						case 1:
							{
							setState(19);
							pair();
							}
							break;
						case 2:
							{
							setState(20);
							obj();
							}
							break;
						}
						}
						}
						setState(27);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(30);
				match(T__2);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PairContext extends ParserRuleContext {
		public TerminalNode TOKEN() { return getToken(ShibaParser.TOKEN, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public PairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterPair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitPair(this);
		}
	}

	public final PairContext pair() throws RecognitionException {
		PairContext _localctx = new PairContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(TOKEN);
			setState(34);
			match(T__3);
			setState(35);
			value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ShibaParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(ShibaParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(ShibaParser.BOOLEAN, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << STRING) | (1L << BOOLEAN) | (1L << NUMBER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21*\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\3\3\3\3\5\3\21\n\3\3\3\5\3\24\n\3\3"+
		"\3\3\3\5\3\30\n\3\7\3\32\n\3\f\3\16\3\35\13\3\5\3\37\n\3\3\3\5\3\"\n\3"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\2\2\6\2\4\6\b\2\3\4\2\7\t\13\13\2+\2\n\3"+
		"\2\2\2\4\f\3\2\2\2\6#\3\2\2\2\b\'\3\2\2\2\n\13\5\4\3\2\13\3\3\2\2\2\f"+
		"!\7\n\2\2\r\36\7\3\2\2\16\21\5\6\4\2\17\21\5\4\3\2\20\16\3\2\2\2\20\17"+
		"\3\2\2\2\21\33\3\2\2\2\22\24\7\4\2\2\23\22\3\2\2\2\23\24\3\2\2\2\24\27"+
		"\3\2\2\2\25\30\5\6\4\2\26\30\5\4\3\2\27\25\3\2\2\2\27\26\3\2\2\2\30\32"+
		"\3\2\2\2\31\23\3\2\2\2\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\37"+
		"\3\2\2\2\35\33\3\2\2\2\36\20\3\2\2\2\36\37\3\2\2\2\37 \3\2\2\2 \"\7\5"+
		"\2\2!\r\3\2\2\2!\"\3\2\2\2\"\5\3\2\2\2#$\7\n\2\2$%\7\6\2\2%&\5\b\5\2&"+
		"\7\3\2\2\2\'(\t\2\2\2(\t\3\2\2\2\b\20\23\27\33\36!";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}