//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     ANTLR Version: 4.7.1
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// Generated from Shiba.g4 by ANTLR 4.7.1

// Unreachable code detected
#pragma warning disable 0162
// The variable '...' is assigned but its value is never used
#pragma warning disable 0219
// Missing XML comment for publicly visible type or member '...'
#pragma warning disable 1591
// Ambiguous reference in cref attribute
#pragma warning disable 419

namespace Shiba.Parser {
using System;
using System.IO;
using System.Text;
using System.Diagnostics;
using System.Collections.Generic;
using Antlr4.Runtime;
using Antlr4.Runtime.Atn;
using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using DFA = Antlr4.Runtime.Dfa.DFA;

[System.CodeDom.Compiler.GeneratedCode("ANTLR", "4.7.1")]
[System.CLSCompliant(false)]
public partial class ShibaParser : Parser {
	protected static DFA[] decisionToDFA;
	protected static PredictionContextCache sharedContextCache = new PredictionContextCache();
	public const int
		T__0=1, Null=2, String=3, Boolean=4, Identifier=5, Number=6, Arrow=7, 
		Comma=8, Colon=9, Assign=10, LeftParen=11, RightParen=12, LeftBracket=13, 
		RightBracket=14, LeftBrace=15, RightBrace=16, Hws=17, Vws=18, DocComment=19, 
		BlockComment=20, LineComment=21, LineCommentExt=22;
	public const int
		RULE_view = 0, RULE_property = 1, RULE_value = 2, RULE_map = 3, RULE_basicValue = 4, 
		RULE_function = 5, RULE_shibaExtension = 6, RULE_array = 7, RULE_identifier = 8;
	public static readonly string[] ruleNames = {
		"view", "property", "value", "map", "basicValue", "function", "shibaExtension", 
		"array", "identifier"
	};

	private static readonly string[] _LiteralNames = {
		null, "'$'", "'null'", null, null, null, null, "'->'", "','", "':'", "'='", 
		"'('", "')'", "'['", "']'", "'{'", "'}'"
	};
	private static readonly string[] _SymbolicNames = {
		null, null, "Null", "String", "Boolean", "Identifier", "Number", "Arrow", 
		"Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "Hws", "Vws", "DocComment", 
		"BlockComment", "LineComment", "LineCommentExt"
	};
	public static readonly IVocabulary DefaultVocabulary = new Vocabulary(_LiteralNames, _SymbolicNames);

	[NotNull]
	public override IVocabulary Vocabulary
	{
		get
		{
			return DefaultVocabulary;
		}
	}

	public override string GrammarFileName { get { return "Shiba.g4"; } }

	public override string[] RuleNames { get { return ruleNames; } }

	public override string SerializedAtn { get { return new string(_serializedATN); } }

	static ShibaParser() {
		decisionToDFA = new DFA[_ATN.NumberOfDecisions];
		for (int i = 0; i < _ATN.NumberOfDecisions; i++) {
			decisionToDFA[i] = new DFA(_ATN.GetDecisionState(i), i);
		}
	}

		public ShibaParser(ITokenStream input) : this(input, Console.Out, Console.Error) { }

		public ShibaParser(ITokenStream input, TextWriter output, TextWriter errorOutput)
		: base(input, output, errorOutput)
	{
		Interpreter = new ParserATNSimulator(this, _ATN, decisionToDFA, sharedContextCache);
	}
	public partial class ViewContext : ParserRuleContext {
		public IdentifierContext identifier() {
			return GetRuleContext<IdentifierContext>(0);
		}
		public ITerminalNode LeftBrace() { return GetToken(ShibaParser.LeftBrace, 0); }
		public ITerminalNode RightBrace() { return GetToken(ShibaParser.RightBrace, 0); }
		public ITerminalNode Arrow() { return GetToken(ShibaParser.Arrow, 0); }
		public ValueContext value() {
			return GetRuleContext<ValueContext>(0);
		}
		public PropertyContext[] property() {
			return GetRuleContexts<PropertyContext>();
		}
		public PropertyContext property(int i) {
			return GetRuleContext<PropertyContext>(i);
		}
		public ViewContext[] view() {
			return GetRuleContexts<ViewContext>();
		}
		public ViewContext view(int i) {
			return GetRuleContext<ViewContext>(i);
		}
		public ITerminalNode[] Comma() { return GetTokens(ShibaParser.Comma); }
		public ITerminalNode Comma(int i) {
			return GetToken(ShibaParser.Comma, i);
		}
		public ViewContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_view; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterView(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitView(this);
		}
	}

	[RuleVersion(0)]
	public ViewContext view() {
		ViewContext _localctx = new ViewContext(Context, State);
		EnterRule(_localctx, 0, RULE_view);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 18; identifier();
			State = 38;
			ErrorHandler.Sync(this);
			switch (TokenStream.LA(1)) {
			case LeftBrace:
				{
				State = 19; Match(LeftBrace);
				State = 29;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
				while (_la==Identifier) {
					{
					{
					State = 22;
					ErrorHandler.Sync(this);
					switch ( Interpreter.AdaptivePredict(TokenStream,0,Context) ) {
					case 1:
						{
						State = 20; property();
						}
						break;
					case 2:
						{
						State = 21; view();
						}
						break;
					}
					State = 25;
					ErrorHandler.Sync(this);
					_la = TokenStream.LA(1);
					if (_la==Comma) {
						{
						State = 24; Match(Comma);
						}
					}

					}
					}
					State = 31;
					ErrorHandler.Sync(this);
					_la = TokenStream.LA(1);
				}
				State = 32; Match(RightBrace);
				}
				break;
			case Arrow:
				{
				State = 33; Match(Arrow);
				State = 36;
				ErrorHandler.Sync(this);
				switch ( Interpreter.AdaptivePredict(TokenStream,3,Context) ) {
				case 1:
					{
					State = 34; value();
					}
					break;
				case 2:
					{
					State = 35; property();
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
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class PropertyContext : ParserRuleContext {
		public IdentifierContext identifier() {
			return GetRuleContext<IdentifierContext>(0);
		}
		public ITerminalNode Assign() { return GetToken(ShibaParser.Assign, 0); }
		public ValueContext value() {
			return GetRuleContext<ValueContext>(0);
		}
		public PropertyContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_property; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterProperty(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitProperty(this);
		}
	}

	[RuleVersion(0)]
	public PropertyContext property() {
		PropertyContext _localctx = new PropertyContext(Context, State);
		EnterRule(_localctx, 2, RULE_property);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 40; identifier();
			State = 41; Match(Assign);
			State = 42; value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class ValueContext : ParserRuleContext {
		public BasicValueContext basicValue() {
			return GetRuleContext<BasicValueContext>(0);
		}
		public ArrayContext array() {
			return GetRuleContext<ArrayContext>(0);
		}
		public MapContext map() {
			return GetRuleContext<MapContext>(0);
		}
		public FunctionContext function() {
			return GetRuleContext<FunctionContext>(0);
		}
		public ShibaExtensionContext shibaExtension() {
			return GetRuleContext<ShibaExtensionContext>(0);
		}
		public ViewContext view() {
			return GetRuleContext<ViewContext>(0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_value; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterValue(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitValue(this);
		}
	}

	[RuleVersion(0)]
	public ValueContext value() {
		ValueContext _localctx = new ValueContext(Context, State);
		EnterRule(_localctx, 4, RULE_value);
		try {
			State = 50;
			ErrorHandler.Sync(this);
			switch ( Interpreter.AdaptivePredict(TokenStream,5,Context) ) {
			case 1:
				EnterOuterAlt(_localctx, 1);
				{
				State = 44; basicValue();
				}
				break;
			case 2:
				EnterOuterAlt(_localctx, 2);
				{
				State = 45; array();
				}
				break;
			case 3:
				EnterOuterAlt(_localctx, 3);
				{
				State = 46; map();
				}
				break;
			case 4:
				EnterOuterAlt(_localctx, 4);
				{
				State = 47; function();
				}
				break;
			case 5:
				EnterOuterAlt(_localctx, 5);
				{
				State = 48; shibaExtension();
				}
				break;
			case 6:
				EnterOuterAlt(_localctx, 6);
				{
				State = 49; view();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class MapContext : ParserRuleContext {
		public ITerminalNode LeftBracket() { return GetToken(ShibaParser.LeftBracket, 0); }
		public ITerminalNode RightBracket() { return GetToken(ShibaParser.RightBracket, 0); }
		public PropertyContext[] property() {
			return GetRuleContexts<PropertyContext>();
		}
		public PropertyContext property(int i) {
			return GetRuleContext<PropertyContext>(i);
		}
		public ITerminalNode[] Comma() { return GetTokens(ShibaParser.Comma); }
		public ITerminalNode Comma(int i) {
			return GetToken(ShibaParser.Comma, i);
		}
		public MapContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_map; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterMap(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitMap(this);
		}
	}

	[RuleVersion(0)]
	public MapContext map() {
		MapContext _localctx = new MapContext(Context, State);
		EnterRule(_localctx, 6, RULE_map);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 52; Match(LeftBracket);
			State = 59;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			while (_la==Identifier) {
				{
				{
				State = 53; property();
				State = 55;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
				if (_la==Comma) {
					{
					State = 54; Match(Comma);
					}
				}

				}
				}
				State = 61;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
			}
			State = 62; Match(RightBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class BasicValueContext : ParserRuleContext {
		public ITerminalNode String() { return GetToken(ShibaParser.String, 0); }
		public ITerminalNode Number() { return GetToken(ShibaParser.Number, 0); }
		public ITerminalNode Boolean() { return GetToken(ShibaParser.Boolean, 0); }
		public ITerminalNode Null() { return GetToken(ShibaParser.Null, 0); }
		public ITerminalNode Identifier() { return GetToken(ShibaParser.Identifier, 0); }
		public BasicValueContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_basicValue; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterBasicValue(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitBasicValue(this);
		}
	}

	[RuleVersion(0)]
	public BasicValueContext basicValue() {
		BasicValueContext _localctx = new BasicValueContext(Context, State);
		EnterRule(_localctx, 8, RULE_basicValue);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 64;
			_la = TokenStream.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << String) | (1L << Boolean) | (1L << Identifier) | (1L << Number))) != 0)) ) {
			ErrorHandler.RecoverInline(this);
			}
			else {
				ErrorHandler.ReportMatch(this);
			    Consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class FunctionContext : ParserRuleContext {
		public ITerminalNode Identifier() { return GetToken(ShibaParser.Identifier, 0); }
		public ITerminalNode LeftParen() { return GetToken(ShibaParser.LeftParen, 0); }
		public ITerminalNode RightParen() { return GetToken(ShibaParser.RightParen, 0); }
		public ValueContext[] value() {
			return GetRuleContexts<ValueContext>();
		}
		public ValueContext value(int i) {
			return GetRuleContext<ValueContext>(i);
		}
		public ITerminalNode[] Comma() { return GetTokens(ShibaParser.Comma); }
		public ITerminalNode Comma(int i) {
			return GetToken(ShibaParser.Comma, i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_function; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterFunction(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitFunction(this);
		}
	}

	[RuleVersion(0)]
	public FunctionContext function() {
		FunctionContext _localctx = new FunctionContext(Context, State);
		EnterRule(_localctx, 10, RULE_function);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 66; Match(Identifier);
			State = 67; Match(LeftParen);
			State = 74;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << Null) | (1L << String) | (1L << Boolean) | (1L << Identifier) | (1L << Number) | (1L << LeftBracket))) != 0)) {
				{
				{
				State = 68; value();
				State = 70;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
				if (_la==Comma) {
					{
					State = 69; Match(Comma);
					}
				}

				}
				}
				State = 76;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
			}
			State = 77; Match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class ShibaExtensionContext : ParserRuleContext {
		public ITerminalNode Identifier() { return GetToken(ShibaParser.Identifier, 0); }
		public BasicValueContext basicValue() {
			return GetRuleContext<BasicValueContext>(0);
		}
		public ShibaExtensionContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_shibaExtension; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterShibaExtension(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitShibaExtension(this);
		}
	}

	[RuleVersion(0)]
	public ShibaExtensionContext shibaExtension() {
		ShibaExtensionContext _localctx = new ShibaExtensionContext(Context, State);
		EnterRule(_localctx, 12, RULE_shibaExtension);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 79; Match(T__0);
			State = 80; Match(Identifier);
			State = 81; basicValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class ArrayContext : ParserRuleContext {
		public ITerminalNode LeftBracket() { return GetToken(ShibaParser.LeftBracket, 0); }
		public ITerminalNode RightBracket() { return GetToken(ShibaParser.RightBracket, 0); }
		public ValueContext[] value() {
			return GetRuleContexts<ValueContext>();
		}
		public ValueContext value(int i) {
			return GetRuleContext<ValueContext>(i);
		}
		public ITerminalNode[] Comma() { return GetTokens(ShibaParser.Comma); }
		public ITerminalNode Comma(int i) {
			return GetToken(ShibaParser.Comma, i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_array; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterArray(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitArray(this);
		}
	}

	[RuleVersion(0)]
	public ArrayContext array() {
		ArrayContext _localctx = new ArrayContext(Context, State);
		EnterRule(_localctx, 14, RULE_array);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 83; Match(LeftBracket);
			State = 90;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << Null) | (1L << String) | (1L << Boolean) | (1L << Identifier) | (1L << Number) | (1L << LeftBracket))) != 0)) {
				{
				{
				State = 84; value();
				State = 86;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
				if (_la==Comma) {
					{
					State = 85; Match(Comma);
					}
				}

				}
				}
				State = 92;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
			}
			State = 93; Match(RightBracket);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	public partial class IdentifierContext : ParserRuleContext {
		public ITerminalNode[] Identifier() { return GetTokens(ShibaParser.Identifier); }
		public ITerminalNode Identifier(int i) {
			return GetToken(ShibaParser.Identifier, i);
		}
		public ITerminalNode Colon() { return GetToken(ShibaParser.Colon, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_identifier; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterIdentifier(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitIdentifier(this);
		}
	}

	[RuleVersion(0)]
	public IdentifierContext identifier() {
		IdentifierContext _localctx = new IdentifierContext(Context, State);
		EnterRule(_localctx, 16, RULE_identifier);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 97;
			ErrorHandler.Sync(this);
			switch ( Interpreter.AdaptivePredict(TokenStream,12,Context) ) {
			case 1:
				{
				State = 95; Match(Identifier);
				State = 96; Match(Colon);
				}
				break;
			}
			State = 99; Match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			ErrorHandler.ReportError(this, re);
			ErrorHandler.Recover(this, re);
		}
		finally {
			ExitRule();
		}
		return _localctx;
	}

	private static char[] _serializedATN = {
		'\x3', '\x608B', '\xA72A', '\x8133', '\xB9ED', '\x417C', '\x3BE7', '\x7786', 
		'\x5964', '\x3', '\x18', 'h', '\x4', '\x2', '\t', '\x2', '\x4', '\x3', 
		'\t', '\x3', '\x4', '\x4', '\t', '\x4', '\x4', '\x5', '\t', '\x5', '\x4', 
		'\x6', '\t', '\x6', '\x4', '\a', '\t', '\a', '\x4', '\b', '\t', '\b', 
		'\x4', '\t', '\t', '\t', '\x4', '\n', '\t', '\n', '\x3', '\x2', '\x3', 
		'\x2', '\x3', '\x2', '\x3', '\x2', '\x5', '\x2', '\x19', '\n', '\x2', 
		'\x3', '\x2', '\x5', '\x2', '\x1C', '\n', '\x2', '\a', '\x2', '\x1E', 
		'\n', '\x2', '\f', '\x2', '\xE', '\x2', '!', '\v', '\x2', '\x3', '\x2', 
		'\x3', '\x2', '\x3', '\x2', '\x3', '\x2', '\x5', '\x2', '\'', '\n', '\x2', 
		'\x5', '\x2', ')', '\n', '\x2', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', 
		'\x3', '\x3', '\x3', '\x4', '\x3', '\x4', '\x3', '\x4', '\x3', '\x4', 
		'\x3', '\x4', '\x3', '\x4', '\x5', '\x4', '\x35', '\n', '\x4', '\x3', 
		'\x5', '\x3', '\x5', '\x3', '\x5', '\x5', '\x5', ':', '\n', '\x5', '\a', 
		'\x5', '<', '\n', '\x5', '\f', '\x5', '\xE', '\x5', '?', '\v', '\x5', 
		'\x3', '\x5', '\x3', '\x5', '\x3', '\x6', '\x3', '\x6', '\x3', '\a', '\x3', 
		'\a', '\x3', '\a', '\x3', '\a', '\x5', '\a', 'I', '\n', '\a', '\a', '\a', 
		'K', '\n', '\a', '\f', '\a', '\xE', '\a', 'N', '\v', '\a', '\x3', '\a', 
		'\x3', '\a', '\x3', '\b', '\x3', '\b', '\x3', '\b', '\x3', '\b', '\x3', 
		'\t', '\x3', '\t', '\x3', '\t', '\x5', '\t', 'Y', '\n', '\t', '\a', '\t', 
		'[', '\n', '\t', '\f', '\t', '\xE', '\t', '^', '\v', '\t', '\x3', '\t', 
		'\x3', '\t', '\x3', '\n', '\x3', '\n', '\x5', '\n', '\x64', '\n', '\n', 
		'\x3', '\n', '\x3', '\n', '\x3', '\n', '\x2', '\x2', '\v', '\x2', '\x4', 
		'\x6', '\b', '\n', '\f', '\xE', '\x10', '\x12', '\x2', '\x3', '\x3', '\x2', 
		'\x4', '\b', '\x2', 'p', '\x2', '\x14', '\x3', '\x2', '\x2', '\x2', '\x4', 
		'*', '\x3', '\x2', '\x2', '\x2', '\x6', '\x34', '\x3', '\x2', '\x2', '\x2', 
		'\b', '\x36', '\x3', '\x2', '\x2', '\x2', '\n', '\x42', '\x3', '\x2', 
		'\x2', '\x2', '\f', '\x44', '\x3', '\x2', '\x2', '\x2', '\xE', 'Q', '\x3', 
		'\x2', '\x2', '\x2', '\x10', 'U', '\x3', '\x2', '\x2', '\x2', '\x12', 
		'\x63', '\x3', '\x2', '\x2', '\x2', '\x14', '(', '\x5', '\x12', '\n', 
		'\x2', '\x15', '\x1F', '\a', '\x11', '\x2', '\x2', '\x16', '\x19', '\x5', 
		'\x4', '\x3', '\x2', '\x17', '\x19', '\x5', '\x2', '\x2', '\x2', '\x18', 
		'\x16', '\x3', '\x2', '\x2', '\x2', '\x18', '\x17', '\x3', '\x2', '\x2', 
		'\x2', '\x19', '\x1B', '\x3', '\x2', '\x2', '\x2', '\x1A', '\x1C', '\a', 
		'\n', '\x2', '\x2', '\x1B', '\x1A', '\x3', '\x2', '\x2', '\x2', '\x1B', 
		'\x1C', '\x3', '\x2', '\x2', '\x2', '\x1C', '\x1E', '\x3', '\x2', '\x2', 
		'\x2', '\x1D', '\x18', '\x3', '\x2', '\x2', '\x2', '\x1E', '!', '\x3', 
		'\x2', '\x2', '\x2', '\x1F', '\x1D', '\x3', '\x2', '\x2', '\x2', '\x1F', 
		' ', '\x3', '\x2', '\x2', '\x2', ' ', '\"', '\x3', '\x2', '\x2', '\x2', 
		'!', '\x1F', '\x3', '\x2', '\x2', '\x2', '\"', ')', '\a', '\x12', '\x2', 
		'\x2', '#', '&', '\a', '\t', '\x2', '\x2', '$', '\'', '\x5', '\x6', '\x4', 
		'\x2', '%', '\'', '\x5', '\x4', '\x3', '\x2', '&', '$', '\x3', '\x2', 
		'\x2', '\x2', '&', '%', '\x3', '\x2', '\x2', '\x2', '\'', ')', '\x3', 
		'\x2', '\x2', '\x2', '(', '\x15', '\x3', '\x2', '\x2', '\x2', '(', '#', 
		'\x3', '\x2', '\x2', '\x2', '(', ')', '\x3', '\x2', '\x2', '\x2', ')', 
		'\x3', '\x3', '\x2', '\x2', '\x2', '*', '+', '\x5', '\x12', '\n', '\x2', 
		'+', ',', '\a', '\f', '\x2', '\x2', ',', '-', '\x5', '\x6', '\x4', '\x2', 
		'-', '\x5', '\x3', '\x2', '\x2', '\x2', '.', '\x35', '\x5', '\n', '\x6', 
		'\x2', '/', '\x35', '\x5', '\x10', '\t', '\x2', '\x30', '\x35', '\x5', 
		'\b', '\x5', '\x2', '\x31', '\x35', '\x5', '\f', '\a', '\x2', '\x32', 
		'\x35', '\x5', '\xE', '\b', '\x2', '\x33', '\x35', '\x5', '\x2', '\x2', 
		'\x2', '\x34', '.', '\x3', '\x2', '\x2', '\x2', '\x34', '/', '\x3', '\x2', 
		'\x2', '\x2', '\x34', '\x30', '\x3', '\x2', '\x2', '\x2', '\x34', '\x31', 
		'\x3', '\x2', '\x2', '\x2', '\x34', '\x32', '\x3', '\x2', '\x2', '\x2', 
		'\x34', '\x33', '\x3', '\x2', '\x2', '\x2', '\x35', '\a', '\x3', '\x2', 
		'\x2', '\x2', '\x36', '=', '\a', '\xF', '\x2', '\x2', '\x37', '\x39', 
		'\x5', '\x4', '\x3', '\x2', '\x38', ':', '\a', '\n', '\x2', '\x2', '\x39', 
		'\x38', '\x3', '\x2', '\x2', '\x2', '\x39', ':', '\x3', '\x2', '\x2', 
		'\x2', ':', '<', '\x3', '\x2', '\x2', '\x2', ';', '\x37', '\x3', '\x2', 
		'\x2', '\x2', '<', '?', '\x3', '\x2', '\x2', '\x2', '=', ';', '\x3', '\x2', 
		'\x2', '\x2', '=', '>', '\x3', '\x2', '\x2', '\x2', '>', '@', '\x3', '\x2', 
		'\x2', '\x2', '?', '=', '\x3', '\x2', '\x2', '\x2', '@', '\x41', '\a', 
		'\x10', '\x2', '\x2', '\x41', '\t', '\x3', '\x2', '\x2', '\x2', '\x42', 
		'\x43', '\t', '\x2', '\x2', '\x2', '\x43', '\v', '\x3', '\x2', '\x2', 
		'\x2', '\x44', '\x45', '\a', '\a', '\x2', '\x2', '\x45', 'L', '\a', '\r', 
		'\x2', '\x2', '\x46', 'H', '\x5', '\x6', '\x4', '\x2', 'G', 'I', '\a', 
		'\n', '\x2', '\x2', 'H', 'G', '\x3', '\x2', '\x2', '\x2', 'H', 'I', '\x3', 
		'\x2', '\x2', '\x2', 'I', 'K', '\x3', '\x2', '\x2', '\x2', 'J', '\x46', 
		'\x3', '\x2', '\x2', '\x2', 'K', 'N', '\x3', '\x2', '\x2', '\x2', 'L', 
		'J', '\x3', '\x2', '\x2', '\x2', 'L', 'M', '\x3', '\x2', '\x2', '\x2', 
		'M', 'O', '\x3', '\x2', '\x2', '\x2', 'N', 'L', '\x3', '\x2', '\x2', '\x2', 
		'O', 'P', '\a', '\xE', '\x2', '\x2', 'P', '\r', '\x3', '\x2', '\x2', '\x2', 
		'Q', 'R', '\a', '\x3', '\x2', '\x2', 'R', 'S', '\a', '\a', '\x2', '\x2', 
		'S', 'T', '\x5', '\n', '\x6', '\x2', 'T', '\xF', '\x3', '\x2', '\x2', 
		'\x2', 'U', '\\', '\a', '\xF', '\x2', '\x2', 'V', 'X', '\x5', '\x6', '\x4', 
		'\x2', 'W', 'Y', '\a', '\n', '\x2', '\x2', 'X', 'W', '\x3', '\x2', '\x2', 
		'\x2', 'X', 'Y', '\x3', '\x2', '\x2', '\x2', 'Y', '[', '\x3', '\x2', '\x2', 
		'\x2', 'Z', 'V', '\x3', '\x2', '\x2', '\x2', '[', '^', '\x3', '\x2', '\x2', 
		'\x2', '\\', 'Z', '\x3', '\x2', '\x2', '\x2', '\\', ']', '\x3', '\x2', 
		'\x2', '\x2', ']', '_', '\x3', '\x2', '\x2', '\x2', '^', '\\', '\x3', 
		'\x2', '\x2', '\x2', '_', '`', '\a', '\x10', '\x2', '\x2', '`', '\x11', 
		'\x3', '\x2', '\x2', '\x2', '\x61', '\x62', '\a', '\a', '\x2', '\x2', 
		'\x62', '\x64', '\a', '\v', '\x2', '\x2', '\x63', '\x61', '\x3', '\x2', 
		'\x2', '\x2', '\x63', '\x64', '\x3', '\x2', '\x2', '\x2', '\x64', '\x65', 
		'\x3', '\x2', '\x2', '\x2', '\x65', '\x66', '\a', '\a', '\x2', '\x2', 
		'\x66', '\x13', '\x3', '\x2', '\x2', '\x2', '\xF', '\x18', '\x1B', '\x1F', 
		'&', '(', '\x34', '\x39', '=', 'H', 'L', 'X', '\\', '\x63',
	};

	public static readonly ATN _ATN =
		new ATNDeserializer().Deserialize(_serializedATN);


}
} // namespace Shiba.Parser
