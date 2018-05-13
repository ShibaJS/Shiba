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
		T__9=10, T__10=11, T__11=12, T__12=13, NULL=14, STRING=15, BOOLEAN=16, 
		TOKEN=17, NUMBER=18, Hws=19, Vws=20, DocComment=21, BlockComment=22, LineComment=23, 
		LineCommentExt=24;
	public static final int
		RULE_root = 0, RULE_obj = 1, RULE_pair = 2, RULE_value = 3, RULE_staticvalue = 4, 
		RULE_percent = 5, RULE_thickness = 6, RULE_func = 7, RULE_paramter = 8, 
		RULE_binding = 9, RULE_resource = 10, RULE_jsonpath = 11, RULE_dic = 12;
	public static final String[] ruleNames = {
		"root", "obj", "pair", "value", "staticvalue", "percent", "thickness", 
		"func", "paramter", "binding", "resource", "jsonpath", "dic"
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
			setState(26);
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
			setState(28);
			match(TOKEN);
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(29);
				match(T__0);
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TOKEN) {
					{
					setState(32);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(30);
						pair();
						}
						break;
					case 2:
						{
						setState(31);
						obj();
						}
						break;
					}
					setState(43);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1 || _la==TOKEN) {
						{
						{
						setState(35);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__1) {
							{
							setState(34);
							match(T__1);
							}
						}

						setState(39);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
						case 1:
							{
							setState(37);
							pair();
							}
							break;
						case 2:
							{
							setState(38);
							obj();
							}
							break;
						}
						}
						}
						setState(45);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(48);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(TOKEN);
			setState(52);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(53);
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
		public StaticvalueContext staticvalue() {
			return getRuleContext(StaticvalueContext.class,0);
		}
		public BindingContext binding() {
			return getRuleContext(BindingContext.class,0);
		}
		public ResourceContext resource() {
			return getRuleContext(ResourceContext.class,0);
		}
		public JsonpathContext jsonpath() {
			return getRuleContext(JsonpathContext.class,0);
		}
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public DicContext dic() {
			return getRuleContext(DicContext.class,0);
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
			setState(61);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				staticvalue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(56);
				binding();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				resource();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(58);
				jsonpath();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(59);
				func();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(60);
				dic();
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

	public static class StaticvalueContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(ShibaParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(ShibaParser.NUMBER, 0); }
		public TerminalNode BOOLEAN() { return getToken(ShibaParser.BOOLEAN, 0); }
		public TerminalNode NULL() { return getToken(ShibaParser.NULL, 0); }
		public TerminalNode TOKEN() { return getToken(ShibaParser.TOKEN, 0); }
		public PercentContext percent() {
			return getRuleContext(PercentContext.class,0);
		}
		public ThicknessContext thickness() {
			return getRuleContext(ThicknessContext.class,0);
		}
		public StaticvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staticvalue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterStaticvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitStaticvalue(this);
		}
	}

	public final StaticvalueContext staticvalue() throws RecognitionException {
		StaticvalueContext _localctx = new StaticvalueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_staticvalue);
		try {
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				match(STRING);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				match(NUMBER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				match(BOOLEAN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				match(NULL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(67);
				match(TOKEN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(68);
				percent();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(69);
				thickness();
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
		enterRule(_localctx, 10, RULE_percent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(NUMBER);
			setState(73);
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
		enterRule(_localctx, 12, RULE_thickness);
		try {
			setState(92);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(75);
				match(T__6);
				setState(76);
				match(NUMBER);
				setState(77);
				match(T__7);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				match(T__6);
				setState(79);
				match(NUMBER);
				setState(80);
				match(T__1);
				setState(81);
				match(NUMBER);
				setState(82);
				match(T__7);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				match(T__6);
				setState(84);
				match(NUMBER);
				setState(85);
				match(T__1);
				setState(86);
				match(NUMBER);
				setState(87);
				match(T__1);
				setState(88);
				match(NUMBER);
				setState(89);
				match(T__1);
				setState(90);
				match(NUMBER);
				setState(91);
				match(T__7);
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
		public List<ParamterContext> paramter() {
			return getRuleContexts(ParamterContext.class);
		}
		public ParamterContext paramter(int i) {
			return getRuleContext(ParamterContext.class,i);
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
			setState(94);
			match(TOKEN);
			setState(95);
			match(T__8);
			setState(96);
			paramter();
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(97);
				match(T__1);
				setState(98);
				paramter();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
			match(T__9);
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

	public static class ParamterContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public ParamterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterParamter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitParamter(this);
		}
	}

	public final ParamterContext paramter() throws RecognitionException {
		ParamterContext _localctx = new ParamterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_paramter);
		try {
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				func();
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

	public static class BindingContext extends ParserRuleContext {
		public StaticvalueContext staticvalue() {
			return getRuleContext(StaticvalueContext.class,0);
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
		enterRule(_localctx, 18, RULE_binding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__10);
			setState(111);
			staticvalue();
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

	public static class ResourceContext extends ParserRuleContext {
		public StaticvalueContext staticvalue() {
			return getRuleContext(StaticvalueContext.class,0);
		}
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitResource(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_resource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__11);
			setState(114);
			staticvalue();
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
		public StaticvalueContext staticvalue() {
			return getRuleContext(StaticvalueContext.class,0);
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
		enterRule(_localctx, 22, RULE_jsonpath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(T__12);
			setState(117);
			staticvalue();
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
		enterRule(_localctx, 24, RULE_dic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119);
			match(T__6);
			setState(120);
			pair();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==TOKEN) {
				{
				{
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(121);
					match(T__1);
					}
				}

				setState(124);
				pair();
				}
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(130);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\32\u0087\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\3\3\3\5\3#\n\3\3\3\5"+
		"\3&\n\3\3\3\3\3\5\3*\n\3\7\3,\n\3\f\3\16\3/\13\3\5\3\61\n\3\3\3\5\3\64"+
		"\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5@\n\5\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\5\6I\n\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b_\n\b\3\t\3\t\3\t\3\t\3\t\7\tf\n\t"+
		"\f\t\16\ti\13\t\3\t\3\t\3\n\3\n\5\no\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\5\16}\n\16\3\16\7\16\u0080\n\16\f\16\16\16\u0083"+
		"\13\16\3\16\3\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30\32\2\3\3\2"+
		"\6\7\2\u0090\2\34\3\2\2\2\4\36\3\2\2\2\6\65\3\2\2\2\b?\3\2\2\2\nH\3\2"+
		"\2\2\fJ\3\2\2\2\16^\3\2\2\2\20`\3\2\2\2\22n\3\2\2\2\24p\3\2\2\2\26s\3"+
		"\2\2\2\30v\3\2\2\2\32y\3\2\2\2\34\35\5\4\3\2\35\3\3\2\2\2\36\63\7\23\2"+
		"\2\37\60\7\3\2\2 #\5\6\4\2!#\5\4\3\2\" \3\2\2\2\"!\3\2\2\2#-\3\2\2\2$"+
		"&\7\4\2\2%$\3\2\2\2%&\3\2\2\2&)\3\2\2\2\'*\5\6\4\2(*\5\4\3\2)\'\3\2\2"+
		"\2)(\3\2\2\2*,\3\2\2\2+%\3\2\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\61\3\2"+
		"\2\2/-\3\2\2\2\60\"\3\2\2\2\60\61\3\2\2\2\61\62\3\2\2\2\62\64\7\5\2\2"+
		"\63\37\3\2\2\2\63\64\3\2\2\2\64\5\3\2\2\2\65\66\7\23\2\2\66\67\t\2\2\2"+
		"\678\5\b\5\28\7\3\2\2\29@\5\n\6\2:@\5\24\13\2;@\5\26\f\2<@\5\30\r\2=@"+
		"\5\20\t\2>@\5\32\16\2?9\3\2\2\2?:\3\2\2\2?;\3\2\2\2?<\3\2\2\2?=\3\2\2"+
		"\2?>\3\2\2\2@\t\3\2\2\2AI\7\21\2\2BI\7\24\2\2CI\7\22\2\2DI\7\20\2\2EI"+
		"\7\23\2\2FI\5\f\7\2GI\5\16\b\2HA\3\2\2\2HB\3\2\2\2HC\3\2\2\2HD\3\2\2\2"+
		"HE\3\2\2\2HF\3\2\2\2HG\3\2\2\2I\13\3\2\2\2JK\7\24\2\2KL\7\b\2\2L\r\3\2"+
		"\2\2MN\7\t\2\2NO\7\24\2\2O_\7\n\2\2PQ\7\t\2\2QR\7\24\2\2RS\7\4\2\2ST\7"+
		"\24\2\2T_\7\n\2\2UV\7\t\2\2VW\7\24\2\2WX\7\4\2\2XY\7\24\2\2YZ\7\4\2\2"+
		"Z[\7\24\2\2[\\\7\4\2\2\\]\7\24\2\2]_\7\n\2\2^M\3\2\2\2^P\3\2\2\2^U\3\2"+
		"\2\2_\17\3\2\2\2`a\7\23\2\2ab\7\13\2\2bg\5\22\n\2cd\7\4\2\2df\5\22\n\2"+
		"ec\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\7\f\2\2"+
		"k\21\3\2\2\2lo\5\b\5\2mo\5\20\t\2nl\3\2\2\2nm\3\2\2\2o\23\3\2\2\2pq\7"+
		"\r\2\2qr\5\n\6\2r\25\3\2\2\2st\7\16\2\2tu\5\n\6\2u\27\3\2\2\2vw\7\17\2"+
		"\2wx\5\n\6\2x\31\3\2\2\2yz\7\t\2\2z\u0081\5\6\4\2{}\7\4\2\2|{\3\2\2\2"+
		"|}\3\2\2\2}~\3\2\2\2~\u0080\5\6\4\2\177|\3\2\2\2\u0080\u0083\3\2\2\2\u0081"+
		"\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081\3\2\2"+
		"\2\u0084\u0085\7\n\2\2\u0085\33\3\2\2\2\17\"%)-\60\63?H^gn|\u0081";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}