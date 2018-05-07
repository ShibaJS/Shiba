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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, STRING=16, 
		BOOLEAN=17, TOKEN=18, NUMBER=19, Hws=20, Vws=21, DocComment=22, BlockComment=23, 
		LineComment=24, LineCommentExt=25;
	public static final int
		RULE_root = 0, RULE_obj = 1, RULE_pair = 2, RULE_value = 3, RULE_staticvalue = 4, 
		RULE_percent = 5, RULE_thickness = 6, RULE_calc = 7, RULE_func = 8, RULE_paramter = 9, 
		RULE_binding = 10, RULE_resource = 11, RULE_jsonpath = 12, RULE_dic = 13;
	public static final String[] ruleNames = {
		"root", "obj", "pair", "value", "staticvalue", "percent", "thickness", 
		"calc", "func", "paramter", "binding", "resource", "jsonpath", "dic"
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
			setState(28);
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
			setState(30);
			match(TOKEN);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(31);
				match(T__0);
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TOKEN) {
					{
					setState(34);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(32);
						pair();
						}
						break;
					case 2:
						{
						setState(33);
						obj();
						}
						break;
					}
					setState(45);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__1 || _la==TOKEN) {
						{
						{
						setState(37);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==T__1) {
							{
							setState(36);
							match(T__1);
							}
						}

						setState(41);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
						case 1:
							{
							setState(39);
							pair();
							}
							break;
						case 2:
							{
							setState(40);
							obj();
							}
							break;
						}
						}
						}
						setState(47);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(50);
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
			setState(53);
			match(TOKEN);
			setState(54);
			_la = _input.LA(1);
			if ( !(_la==T__3 || _la==T__4) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(55);
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
		public CalcContext calc() {
			return getRuleContext(CalcContext.class,0);
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
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case STRING:
			case BOOLEAN:
			case TOKEN:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				staticvalue();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				binding();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				resource();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 4);
				{
				setState(60);
				jsonpath();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(61);
				calc();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 6);
				{
				setState(62);
				dic();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
			setState(72);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				match(STRING);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				match(NUMBER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				match(BOOLEAN);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(68);
				match(T__5);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(69);
				match(TOKEN);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(70);
				percent();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(71);
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
			setState(74);
			match(NUMBER);
			setState(75);
			match(T__6);
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
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(77);
				match(NUMBER);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(78);
				match(NUMBER);
				setState(79);
				match(T__1);
				setState(80);
				match(NUMBER);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(81);
				match(NUMBER);
				setState(82);
				match(T__1);
				setState(83);
				match(NUMBER);
				setState(84);
				match(T__1);
				setState(85);
				match(NUMBER);
				setState(86);
				match(T__1);
				setState(87);
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

	public static class CalcContext extends ParserRuleContext {
		public FuncContext func() {
			return getRuleContext(FuncContext.class,0);
		}
		public CalcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterCalc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitCalc(this);
		}
	}

	public final CalcContext calc() throws RecognitionException {
		CalcContext _localctx = new CalcContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_calc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(T__7);
			setState(91);
			func();
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
		enterRule(_localctx, 16, RULE_func);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			match(TOKEN);
			setState(94);
			match(T__8);
			setState(95);
			paramter();
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(96);
				match(T__1);
				setState(97);
				paramter();
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
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
		enterRule(_localctx, 18, RULE_paramter);
		try {
			setState(107);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(105);
				value();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(106);
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
		enterRule(_localctx, 20, RULE_binding);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(T__10);
			setState(110);
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
		enterRule(_localctx, 22, RULE_resource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__11);
			setState(113);
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
		enterRule(_localctx, 24, RULE_jsonpath);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115);
			match(T__12);
			setState(116);
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
		enterRule(_localctx, 26, RULE_dic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(T__13);
			setState(119);
			pair();
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==TOKEN) {
				{
				{
				setState(121);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(120);
					match(T__1);
					}
				}

				setState(123);
				pair();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
			match(T__14);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u0086\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\3\3\3\5\3"+
		"%\n\3\3\3\5\3(\n\3\3\3\3\3\5\3,\n\3\7\3.\n\3\f\3\16\3\61\13\3\5\3\63\n"+
		"\3\3\3\5\3\66\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5B\n\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6K\n\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\5\b[\n\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\7\ne\n\n"+
		"\f\n\16\nh\13\n\3\n\3\n\3\13\3\13\5\13n\n\13\3\f\3\f\3\f\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\5\17|\n\17\3\17\7\17\177\n\17\f\17\16\17"+
		"\u0082\13\17\3\17\3\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\2\3\3\2\6\7\2\u008e\2\36\3\2\2\2\4 \3\2\2\2\6\67\3\2\2\2\bA\3\2\2\2\n"+
		"J\3\2\2\2\fL\3\2\2\2\16Z\3\2\2\2\20\\\3\2\2\2\22_\3\2\2\2\24m\3\2\2\2"+
		"\26o\3\2\2\2\30r\3\2\2\2\32u\3\2\2\2\34x\3\2\2\2\36\37\5\4\3\2\37\3\3"+
		"\2\2\2 \65\7\24\2\2!\62\7\3\2\2\"%\5\6\4\2#%\5\4\3\2$\"\3\2\2\2$#\3\2"+
		"\2\2%/\3\2\2\2&(\7\4\2\2\'&\3\2\2\2\'(\3\2\2\2(+\3\2\2\2),\5\6\4\2*,\5"+
		"\4\3\2+)\3\2\2\2+*\3\2\2\2,.\3\2\2\2-\'\3\2\2\2.\61\3\2\2\2/-\3\2\2\2"+
		"/\60\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\62$\3\2\2\2\62\63\3\2\2\2\63\64"+
		"\3\2\2\2\64\66\7\5\2\2\65!\3\2\2\2\65\66\3\2\2\2\66\5\3\2\2\2\678\7\24"+
		"\2\289\t\2\2\29:\5\b\5\2:\7\3\2\2\2;B\5\n\6\2<B\5\26\f\2=B\5\30\r\2>B"+
		"\5\32\16\2?B\5\20\t\2@B\5\34\17\2A;\3\2\2\2A<\3\2\2\2A=\3\2\2\2A>\3\2"+
		"\2\2A?\3\2\2\2A@\3\2\2\2B\t\3\2\2\2CK\7\22\2\2DK\7\25\2\2EK\7\23\2\2F"+
		"K\7\b\2\2GK\7\24\2\2HK\5\f\7\2IK\5\16\b\2JC\3\2\2\2JD\3\2\2\2JE\3\2\2"+
		"\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\13\3\2\2\2LM\7\25\2\2MN\7"+
		"\t\2\2N\r\3\2\2\2O[\7\25\2\2PQ\7\25\2\2QR\7\4\2\2R[\7\25\2\2ST\7\25\2"+
		"\2TU\7\4\2\2UV\7\25\2\2VW\7\4\2\2WX\7\25\2\2XY\7\4\2\2Y[\7\25\2\2ZO\3"+
		"\2\2\2ZP\3\2\2\2ZS\3\2\2\2[\17\3\2\2\2\\]\7\n\2\2]^\5\22\n\2^\21\3\2\2"+
		"\2_`\7\24\2\2`a\7\13\2\2af\5\24\13\2bc\7\4\2\2ce\5\24\13\2db\3\2\2\2e"+
		"h\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2\2\2hf\3\2\2\2ij\7\f\2\2j\23\3\2\2"+
		"\2kn\5\b\5\2ln\5\22\n\2mk\3\2\2\2ml\3\2\2\2n\25\3\2\2\2op\7\r\2\2pq\5"+
		"\n\6\2q\27\3\2\2\2rs\7\16\2\2st\5\n\6\2t\31\3\2\2\2uv\7\17\2\2vw\5\n\6"+
		"\2w\33\3\2\2\2xy\7\20\2\2y\u0080\5\6\4\2z|\7\4\2\2{z\3\2\2\2{|\3\2\2\2"+
		"|}\3\2\2\2}\177\5\6\4\2~{\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7\21"+
		"\2\2\u0084\35\3\2\2\2\17$\'+/\62\65AJZfm{\u0080";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}