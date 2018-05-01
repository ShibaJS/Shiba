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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, STRING=14, BOOLEAN=15, TOKEN=16, 
		NUMBER=17, Hws=18, Vws=19, DocComment=20, BlockComment=21, LineComment=22, 
		LineCommentExt=23;
	public static final int
		RULE_root = 0, RULE_obj = 1, RULE_pair = 2, RULE_value = 3, RULE_percent = 4, 
		RULE_thickness = 5, RULE_comput = 6, RULE_func = 7, RULE_binding = 8, 
		RULE_native = 9, RULE_jsonpath = 10, RULE_dic = 11;
	public static final String[] ruleNames = {
		"root", "obj", "pair", "value", "percent", "thickness", "comput", "func", 
		"binding", "native", "jsonpath", "dic"
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
			setState(24);
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
			setState(26);
			match(TOKEN);
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(27);
				match(T__0);
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TOKEN) {
					{
					setState(30);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(28);
						pair();
						}
						break;
					case 2:
						{
						setState(29);
						obj();
						}
						break;
					}
					setState(41);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1 || _la==TOKEN) {
						{
						{
						setState(33);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__1) {
							{
							setState(32);
							match(T__1);
							}
						}

						setState(37);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
						case 1:
							{
							setState(35);
							pair();
							}
							break;
						case 2:
							{
							setState(36);
							obj();
							}
							break;
						}
						}
						}
						setState(43);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(46);
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
			setState(49);
			match(TOKEN);
			setState(50);
			match(T__3);
			setState(51);
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
		public TerminalNode TOKEN() { return getToken(ShibaParser.TOKEN, 0); }
		public PercentContext percent() {
			return getRuleContext(PercentContext.class,0);
		}
		public ThicknessContext thickness() {
			return getRuleContext(ThicknessContext.class,0);
		}
		public BindingContext binding() {
			return getRuleContext(BindingContext.class,0);
		}
		public NativeContext native() {
			return getRuleContext(NativeContext.class,0);
		}
		public DicContext dic() {
			return getRuleContext(DicContext.class,0);
		}
		public JsonpathContext jsonpath() {
			return getRuleContext(JsonpathContext.class,0);
		}
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
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				match(STRING);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(NUMBER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				match(BOOLEAN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(56);
				match(T__4);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(57);
				match(TOKEN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(58);
				percent();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(59);
				thickness();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(60);
				binding();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(61);
				native();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(62);
				dic();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(63);
				jsonpath();
				}
				break;
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

	public static class PercentContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(ShibaParser.NUMBER, 0); }
		public PercentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_percent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterPercent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitPercent(this);
		}
	}

	public final PercentContext percent() throws RecognitionException {
		PercentContext _localctx = new PercentContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_percent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(NUMBER);
			setState(67);
			match(T__5);
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

	public static class ThicknessContext extends ParserRuleContext {
		public List<TerminalNode> NUMBER() { return getTokens(ShibaParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(ShibaParser.NUMBER, i);
		}
		public ThicknessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thickness; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterThickness(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitThickness(this);
		}
	}

	public final ThicknessContext thickness() throws RecognitionException {
		ThicknessContext _localctx = new ThicknessContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_thickness);
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(NUMBER);
				setState(71);
				match(T__1);
				setState(72);
				match(NUMBER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				match(NUMBER);
				setState(74);
				match(T__1);
				setState(75);
				match(NUMBER);
				setState(76);
				match(T__1);
				setState(77);
				match(NUMBER);
				setState(78);
				match(T__1);
				setState(79);
				match(NUMBER);
				}
				break;
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

	public static class ComputContext extends ParserRuleContext {
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ComputContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comput; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterComput(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitComput(this);
		}
	}

	public final ComputContext comput() throws RecognitionException {
		ComputContext _localctx = new ComputContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_comput);
		try {
			setState(84);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				func();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				value();
				}
				break;
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

	public static class FuncContext extends ParserRuleContext {
		public TerminalNode TOKEN() { return getToken(ShibaParser.TOKEN, 0); }
		public ComputContext comput() {
			return getRuleContext(ComputContext.class,0);
		}
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitFunc(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(TOKEN);
			setState(87);
			match(T__6);
			setState(88);
			comput();
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(89);
				match(T__1);
				setState(90);
				value();
				}
			}

			setState(93);
			match(T__7);
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

	public static class BindingContext extends ParserRuleContext {
		public ComputContext comput() {
			return getRuleContext(ComputContext.class,0);
		}
		public BindingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binding; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterBinding(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitBinding(this);
		}
	}

	public final BindingContext binding() throws RecognitionException {
		BindingContext _localctx = new BindingContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_binding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__8);
			setState(96);
			comput();
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

	public static class NativeContext extends ParserRuleContext {
		public ComputContext comput() {
			return getRuleContext(ComputContext.class,0);
		}
		public NativeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_native; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterNative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitNative(this);
		}
	}

	public final NativeContext native() throws RecognitionException {
		NativeContext _localctx = new NativeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_native);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__9);
			setState(99);
			comput();
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

	public static class JsonpathContext extends ParserRuleContext {
		public ComputContext comput() {
			return getRuleContext(ComputContext.class,0);
		}
		public JsonpathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jsonpath; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterJsonpath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitJsonpath(this);
		}
	}

	public final JsonpathContext jsonpath() throws RecognitionException {
		JsonpathContext _localctx = new JsonpathContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_jsonpath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__10);
			setState(102);
			comput();
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

	public static class DicContext extends ParserRuleContext {
		public List<PairContext> pair() {
			return getRuleContexts(PairContext.class);
		}
		public PairContext pair(int i) {
			return getRuleContext(PairContext.class,i);
		}
		public DicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dic; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterDic(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitDic(this);
		}
	}

	public final DicContext dic() throws RecognitionException {
		DicContext _localctx = new DicContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_dic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__11);
			setState(105);
			pair();
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==TOKEN) {
				{
				{
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(106);
					match(T__1);
					}
				}

				setState(109);
				pair();
				}
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(115);
			match(T__12);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31x\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\3\3\3\5\3!\n\3\3\3\5\3$\n\3\3\3\3\3\5"+
		"\3(\n\3\7\3*\n\3\f\3\16\3-\13\3\5\3/\n\3\3\3\5\3\62\n\3\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5C\n\5\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7S\n\7\3\b\3\b\5\bW\n\b\3"+
		"\t\3\t\3\t\3\t\3\t\5\t^\n\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\5\rn\n\r\3\r\7\rq\n\r\f\r\16\rt\13\r\3\r\3\r\3\r\2\2"+
		"\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\2\u0081\2\32\3\2\2\2\4\34\3\2\2"+
		"\2\6\63\3\2\2\2\bB\3\2\2\2\nD\3\2\2\2\fR\3\2\2\2\16V\3\2\2\2\20X\3\2\2"+
		"\2\22a\3\2\2\2\24d\3\2\2\2\26g\3\2\2\2\30j\3\2\2\2\32\33\5\4\3\2\33\3"+
		"\3\2\2\2\34\61\7\22\2\2\35.\7\3\2\2\36!\5\6\4\2\37!\5\4\3\2 \36\3\2\2"+
		"\2 \37\3\2\2\2!+\3\2\2\2\"$\7\4\2\2#\"\3\2\2\2#$\3\2\2\2$\'\3\2\2\2%("+
		"\5\6\4\2&(\5\4\3\2\'%\3\2\2\2\'&\3\2\2\2(*\3\2\2\2)#\3\2\2\2*-\3\2\2\2"+
		"+)\3\2\2\2+,\3\2\2\2,/\3\2\2\2-+\3\2\2\2. \3\2\2\2./\3\2\2\2/\60\3\2\2"+
		"\2\60\62\7\5\2\2\61\35\3\2\2\2\61\62\3\2\2\2\62\5\3\2\2\2\63\64\7\22\2"+
		"\2\64\65\7\6\2\2\65\66\5\b\5\2\66\7\3\2\2\2\67C\7\20\2\28C\7\23\2\29C"+
		"\7\21\2\2:C\7\7\2\2;C\7\22\2\2<C\5\n\6\2=C\5\f\7\2>C\5\22\n\2?C\5\24\13"+
		"\2@C\5\30\r\2AC\5\26\f\2B\67\3\2\2\2B8\3\2\2\2B9\3\2\2\2B:\3\2\2\2B;\3"+
		"\2\2\2B<\3\2\2\2B=\3\2\2\2B>\3\2\2\2B?\3\2\2\2B@\3\2\2\2BA\3\2\2\2C\t"+
		"\3\2\2\2DE\7\23\2\2EF\7\b\2\2F\13\3\2\2\2GS\7\23\2\2HI\7\23\2\2IJ\7\4"+
		"\2\2JS\7\23\2\2KL\7\23\2\2LM\7\4\2\2MN\7\23\2\2NO\7\4\2\2OP\7\23\2\2P"+
		"Q\7\4\2\2QS\7\23\2\2RG\3\2\2\2RH\3\2\2\2RK\3\2\2\2S\r\3\2\2\2TW\5\20\t"+
		"\2UW\5\b\5\2VT\3\2\2\2VU\3\2\2\2W\17\3\2\2\2XY\7\22\2\2YZ\7\t\2\2Z]\5"+
		"\16\b\2[\\\7\4\2\2\\^\5\b\5\2][\3\2\2\2]^\3\2\2\2^_\3\2\2\2_`\7\n\2\2"+
		"`\21\3\2\2\2ab\7\13\2\2bc\5\16\b\2c\23\3\2\2\2de\7\f\2\2ef\5\16\b\2f\25"+
		"\3\2\2\2gh\7\r\2\2hi\5\16\b\2i\27\3\2\2\2jk\7\16\2\2kr\5\6\4\2ln\7\4\2"+
		"\2ml\3\2\2\2mn\3\2\2\2no\3\2\2\2oq\5\6\4\2pm\3\2\2\2qt\3\2\2\2rp\3\2\2"+
		"\2rs\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7\17\2\2v\31\3\2\2\2\16 #\'+.\61BR"+
		"V]mr";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}