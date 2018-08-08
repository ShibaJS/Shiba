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
		T__0=1, Null=2, String=3, Boolean=4, Identifier=5, Number=6, Arrow=7, 
		Comma=8, Colon=9, Assign=10, LeftParen=11, RightParen=12, LeftBracket=13, 
		RightBracket=14, LeftBrace=15, RightBrace=16, Hws=17, Vws=18, DocComment=19, 
		BlockComment=20, LineComment=21, LineCommentExt=22;
	public static final int
		RULE_view = 0, RULE_property = 1, RULE_value = 2, RULE_map = 3, RULE_basicValue = 4, 
		RULE_function = 5, RULE_shibaExtension = 6, RULE_array = 7, RULE_identifier = 8;
	public static final String[] ruleNames = {
		"view", "property", "value", "map", "basicValue", "function", "shibaExtension", 
		"array", "identifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'$'", "'null'", null, null, null, null, "'->'", "','", "':'", "'='", 
		"'('", "')'", "'['", "']'", "'{'", "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "Null", "String", "Boolean", "Identifier", "Number", "Arrow", 
		"Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "Hws", "Vws", "DocComment", 
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
	public static class ViewContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode LeftBrace() { return getToken(ShibaParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(ShibaParser.RightBrace, 0); }
		public TerminalNode Arrow() { return getToken(ShibaParser.Arrow, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<ViewContext> view() {
			return getRuleContexts(ViewContext.class);
		}
		public ViewContext view(int i) {
			return getRuleContext(ViewContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(ShibaParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ShibaParser.Comma, i);
		}
		public ViewContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_view; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterView(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitView(this);
		}
	}

	public final ViewContext view() throws RecognitionException {
		ViewContext _localctx = new ViewContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_view);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			identifier();
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftBrace:
				{
				setState(19);
				match(LeftBrace);
				setState(29);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Identifier) {
					{
					{
					setState(22);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(20);
						property();
						}
						break;
					case 2:
						{
						setState(21);
						view();
						}
						break;
					}
					setState(25);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==Comma) {
						{
						setState(24);
						match(Comma);
						}
					}

					}
					}
					setState(31);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(32);
				match(RightBrace);
				}
				break;
			case Arrow:
				{
				setState(33);
				match(Arrow);
				setState(36);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(34);
					value();
					}
					break;
				case 2:
					{
					setState(35);
					property();
					}
					break;
				}
				}
				break;
			case T__0:
			case Null:
			case String:
			case Boolean:
			case Identifier:
			case Number:
			case Comma:
			case RightParen:
			case LeftBracket:
			case RightBracket:
			case RightBrace:
				break;
			default:
				break;
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

	public static class PropertyContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode Assign() { return getToken(ShibaParser.Assign, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterProperty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitProperty(this);
		}
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_property);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			identifier();
			setState(41);
			match(Assign);
			setState(42);
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
		public BasicValueContext basicValue() {
			return getRuleContext(BasicValueContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public MapContext map() {
			return getRuleContext(MapContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ShibaExtensionContext shibaExtension() {
			return getRuleContext(ShibaExtensionContext.class,0);
		}
		public ViewContext view() {
			return getRuleContext(ViewContext.class,0);
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
		enterRule(_localctx, 4, RULE_value);
		try {
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				basicValue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				array();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(46);
				map();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(47);
				function();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(48);
				shibaExtension();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(49);
				view();
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

	public static class MapContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(ShibaParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(ShibaParser.RightBracket, 0); }
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(ShibaParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ShibaParser.Comma, i);
		}
		public MapContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_map; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterMap(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitMap(this);
		}
	}

	public final MapContext map() throws RecognitionException {
		MapContext _localctx = new MapContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_map);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(LeftBracket);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Identifier) {
				{
				{
				setState(53);
				property();
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(54);
					match(Comma);
					}
				}

				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(RightBracket);
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

	public static class BasicValueContext extends ParserRuleContext {
		public TerminalNode String() { return getToken(ShibaParser.String, 0); }
		public TerminalNode Number() { return getToken(ShibaParser.Number, 0); }
		public TerminalNode Boolean() { return getToken(ShibaParser.Boolean, 0); }
		public TerminalNode Null() { return getToken(ShibaParser.Null, 0); }
		public TerminalNode Identifier() { return getToken(ShibaParser.Identifier, 0); }
		public BasicValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterBasicValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitBasicValue(this);
		}
	}

	public final BasicValueContext basicValue() throws RecognitionException {
		BasicValueContext _localctx = new BasicValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_basicValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << String) | (1L << Boolean) | (1L << Identifier) | (1L << Number))) != 0)) ) {
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

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ShibaParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(ShibaParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(ShibaParser.RightParen, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(ShibaParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ShibaParser.Comma, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitFunction(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(Identifier);
			setState(67);
			match(LeftParen);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << Null) | (1L << String) | (1L << Boolean) | (1L << Identifier) | (1L << Number) | (1L << LeftBracket))) != 0)) {
				{
				{
				setState(68);
				value();
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(69);
					match(Comma);
					}
				}

				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(RightParen);
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

	public static class ShibaExtensionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(ShibaParser.Identifier, 0); }
		public BasicValueContext basicValue() {
			return getRuleContext(BasicValueContext.class,0);
		}
		public ShibaExtensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shibaExtension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterShibaExtension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitShibaExtension(this);
		}
	}

	public final ShibaExtensionContext shibaExtension() throws RecognitionException {
		ShibaExtensionContext _localctx = new ShibaExtensionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_shibaExtension);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(T__0);
			setState(80);
			match(Identifier);
			setState(81);
			basicValue();
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

	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode LeftBracket() { return getToken(ShibaParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(ShibaParser.RightBracket, 0); }
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(ShibaParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ShibaParser.Comma, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(LeftBracket);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << Null) | (1L << String) | (1L << Boolean) | (1L << Identifier) | (1L << Number) | (1L << LeftBracket))) != 0)) {
				{
				{
				setState(84);
				value();
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Comma) {
					{
					setState(85);
					match(Comma);
					}
				}

				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(93);
			match(RightBracket);
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

	public static class IdentifierContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(ShibaParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(ShibaParser.Identifier, i);
		}
		public TerminalNode Colon() { return getToken(ShibaParser.Colon, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ShibaListener ) ((ShibaListener)listener).exitIdentifier(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(95);
				match(Identifier);
				setState(96);
				match(Colon);
				}
				break;
			}
			setState(99);
			match(Identifier);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\30h\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\5\2\31\n\2\3\2\5\2\34\n\2\7\2\36\n\2\f\2\16\2!\13\2\3\2\3\2\3\2\3"+
		"\2\5\2\'\n\2\5\2)\n\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\5\4\65\n"+
		"\4\3\5\3\5\3\5\5\5:\n\5\7\5<\n\5\f\5\16\5?\13\5\3\5\3\5\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\5\7I\n\7\7\7K\n\7\f\7\16\7N\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\5\tY\n\t\7\t[\n\t\f\t\16\t^\13\t\3\t\3\t\3\n\3\n\5\nd\n\n\3"+
		"\n\3\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\3\3\2\4\b\2p\2\24\3\2\2\2\4*"+
		"\3\2\2\2\6\64\3\2\2\2\b\66\3\2\2\2\nB\3\2\2\2\fD\3\2\2\2\16Q\3\2\2\2\20"+
		"U\3\2\2\2\22c\3\2\2\2\24(\5\22\n\2\25\37\7\21\2\2\26\31\5\4\3\2\27\31"+
		"\5\2\2\2\30\26\3\2\2\2\30\27\3\2\2\2\31\33\3\2\2\2\32\34\7\n\2\2\33\32"+
		"\3\2\2\2\33\34\3\2\2\2\34\36\3\2\2\2\35\30\3\2\2\2\36!\3\2\2\2\37\35\3"+
		"\2\2\2\37 \3\2\2\2 \"\3\2\2\2!\37\3\2\2\2\")\7\22\2\2#&\7\t\2\2$\'\5\6"+
		"\4\2%\'\5\4\3\2&$\3\2\2\2&%\3\2\2\2\')\3\2\2\2(\25\3\2\2\2(#\3\2\2\2("+
		")\3\2\2\2)\3\3\2\2\2*+\5\22\n\2+,\7\f\2\2,-\5\6\4\2-\5\3\2\2\2.\65\5\n"+
		"\6\2/\65\5\20\t\2\60\65\5\b\5\2\61\65\5\f\7\2\62\65\5\16\b\2\63\65\5\2"+
		"\2\2\64.\3\2\2\2\64/\3\2\2\2\64\60\3\2\2\2\64\61\3\2\2\2\64\62\3\2\2\2"+
		"\64\63\3\2\2\2\65\7\3\2\2\2\66=\7\17\2\2\679\5\4\3\28:\7\n\2\298\3\2\2"+
		"\29:\3\2\2\2:<\3\2\2\2;\67\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2\2>@\3\2"+
		"\2\2?=\3\2\2\2@A\7\20\2\2A\t\3\2\2\2BC\t\2\2\2C\13\3\2\2\2DE\7\7\2\2E"+
		"L\7\r\2\2FH\5\6\4\2GI\7\n\2\2HG\3\2\2\2HI\3\2\2\2IK\3\2\2\2JF\3\2\2\2"+
		"KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\16\2\2P\r\3\2\2"+
		"\2QR\7\3\2\2RS\7\7\2\2ST\5\n\6\2T\17\3\2\2\2U\\\7\17\2\2VX\5\6\4\2WY\7"+
		"\n\2\2XW\3\2\2\2XY\3\2\2\2Y[\3\2\2\2ZV\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\"+
		"]\3\2\2\2]_\3\2\2\2^\\\3\2\2\2_`\7\20\2\2`\21\3\2\2\2ab\7\7\2\2bd\7\13"+
		"\2\2ca\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\7\7\2\2f\23\3\2\2\2\17\30\33\37&"+
		"(\649=HLX\\c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}