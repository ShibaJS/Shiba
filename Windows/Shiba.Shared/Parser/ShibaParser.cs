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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, NAMESPACE=6, STRING=7, BOOLEAN=8, 
		TOKEN=9, NUMBER=10, Hws=11, Vws=12, DocComment=13, BlockComment=14, LineComment=15, 
		LineCommentExt=16;
	public const int
		RULE_root = 0, RULE_namespaces = 1, RULE_obj = 2, RULE_pair = 3, RULE_value = 4;
	public static readonly string[] ruleNames = {
		"root", "namespaces", "obj", "pair", "value"
	};

	private static readonly string[] _LiteralNames = {
		null, "'{'", "','", "'}'", "':'", "'null'"
	};
	private static readonly string[] _SymbolicNames = {
		null, null, null, null, null, null, "NAMESPACE", "STRING", "BOOLEAN", 
		"TOKEN", "NUMBER", "Hws", "Vws", "DocComment", "BlockComment", "LineComment", 
		"LineCommentExt"
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
	public partial class RootContext : ParserRuleContext {
		public NamespacesContext namespaces() {
			return GetRuleContext<NamespacesContext>(0);
		}
		public ObjContext obj() {
			return GetRuleContext<ObjContext>(0);
		}
		public RootContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_root; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterRoot(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitRoot(this);
		}
	}

	[RuleVersion(0)]
	public RootContext root() {
		RootContext _localctx = new RootContext(Context, State);
		EnterRule(_localctx, 0, RULE_root);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 10; namespaces();
			State = 11; obj();
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

	public partial class NamespacesContext : ParserRuleContext {
		public ITerminalNode[] NAMESPACE() { return GetTokens(ShibaParser.NAMESPACE); }
		public ITerminalNode NAMESPACE(int i) {
			return GetToken(ShibaParser.NAMESPACE, i);
		}
		public NamespacesContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_namespaces; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterNamespaces(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitNamespaces(this);
		}
	}

	[RuleVersion(0)]
	public NamespacesContext namespaces() {
		NamespacesContext _localctx = new NamespacesContext(Context, State);
		EnterRule(_localctx, 2, RULE_namespaces);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 16;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			while (_la==NAMESPACE) {
				{
				{
				State = 13; Match(NAMESPACE);
				}
				}
				State = 18;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
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

	public partial class ObjContext : ParserRuleContext {
		public ITerminalNode TOKEN() { return GetToken(ShibaParser.TOKEN, 0); }
		public PairContext[] pair() {
			return GetRuleContexts<PairContext>();
		}
		public PairContext pair(int i) {
			return GetRuleContext<PairContext>(i);
		}
		public ObjContext[] obj() {
			return GetRuleContexts<ObjContext>();
		}
		public ObjContext obj(int i) {
			return GetRuleContext<ObjContext>(i);
		}
		public ObjContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_obj; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterObj(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitObj(this);
		}
	}

	[RuleVersion(0)]
	public ObjContext obj() {
		ObjContext _localctx = new ObjContext(Context, State);
		EnterRule(_localctx, 4, RULE_obj);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 19; Match(TOKEN);
			State = 36;
			ErrorHandler.Sync(this);
			_la = TokenStream.LA(1);
			if (_la==T__0) {
				{
				State = 20; Match(T__0);
				State = 21; pair();
				State = 31;
				ErrorHandler.Sync(this);
				_la = TokenStream.LA(1);
				while (_la==T__1 || _la==TOKEN) {
					{
					{
					State = 23;
					ErrorHandler.Sync(this);
					_la = TokenStream.LA(1);
					if (_la==T__1) {
						{
						State = 22; Match(T__1);
						}
					}

					State = 27;
					ErrorHandler.Sync(this);
					switch ( Interpreter.AdaptivePredict(TokenStream,2,Context) ) {
					case 1:
						{
						State = 25; pair();
						}
						break;
					case 2:
						{
						State = 26; obj();
						}
						break;
					}
					}
					}
					State = 33;
					ErrorHandler.Sync(this);
					_la = TokenStream.LA(1);
				}
				State = 34; Match(T__2);
				}
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

	public partial class PairContext : ParserRuleContext {
		public ITerminalNode TOKEN() { return GetToken(ShibaParser.TOKEN, 0); }
		public ValueContext value() {
			return GetRuleContext<ValueContext>(0);
		}
		public PairContext(ParserRuleContext parent, int invokingState)
			: base(parent, invokingState)
		{
		}
		public override int RuleIndex { get { return RULE_pair; } }
		public override void EnterRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.EnterPair(this);
		}
		public override void ExitRule(IParseTreeListener listener) {
			IShibaListener typedListener = listener as IShibaListener;
			if (typedListener != null) typedListener.ExitPair(this);
		}
	}

	[RuleVersion(0)]
	public PairContext pair() {
		PairContext _localctx = new PairContext(Context, State);
		EnterRule(_localctx, 6, RULE_pair);
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 38; Match(TOKEN);
			State = 39; Match(T__3);
			State = 40; value();
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
		public ITerminalNode STRING() { return GetToken(ShibaParser.STRING, 0); }
		public ITerminalNode NUMBER() { return GetToken(ShibaParser.NUMBER, 0); }
		public ITerminalNode BOOLEAN() { return GetToken(ShibaParser.BOOLEAN, 0); }
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
		EnterRule(_localctx, 8, RULE_value);
		int _la;
		try {
			EnterOuterAlt(_localctx, 1);
			{
			State = 42;
			_la = TokenStream.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << STRING) | (1L << BOOLEAN) | (1L << NUMBER))) != 0)) ) {
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

	private static char[] _serializedATN = {
		'\x3', '\x608B', '\xA72A', '\x8133', '\xB9ED', '\x417C', '\x3BE7', '\x7786', 
		'\x5964', '\x3', '\x12', '/', '\x4', '\x2', '\t', '\x2', '\x4', '\x3', 
		'\t', '\x3', '\x4', '\x4', '\t', '\x4', '\x4', '\x5', '\t', '\x5', '\x4', 
		'\x6', '\t', '\x6', '\x3', '\x2', '\x3', '\x2', '\x3', '\x2', '\x3', '\x3', 
		'\a', '\x3', '\x11', '\n', '\x3', '\f', '\x3', '\xE', '\x3', '\x14', '\v', 
		'\x3', '\x3', '\x4', '\x3', '\x4', '\x3', '\x4', '\x3', '\x4', '\x5', 
		'\x4', '\x1A', '\n', '\x4', '\x3', '\x4', '\x3', '\x4', '\x5', '\x4', 
		'\x1E', '\n', '\x4', '\a', '\x4', ' ', '\n', '\x4', '\f', '\x4', '\xE', 
		'\x4', '#', '\v', '\x4', '\x3', '\x4', '\x3', '\x4', '\x5', '\x4', '\'', 
		'\n', '\x4', '\x3', '\x5', '\x3', '\x5', '\x3', '\x5', '\x3', '\x5', '\x3', 
		'\x6', '\x3', '\x6', '\x3', '\x6', '\x2', '\x2', '\a', '\x2', '\x4', '\x6', 
		'\b', '\n', '\x2', '\x3', '\x5', '\x2', '\a', '\a', '\t', '\n', '\f', 
		'\f', '\x2', '.', '\x2', '\f', '\x3', '\x2', '\x2', '\x2', '\x4', '\x12', 
		'\x3', '\x2', '\x2', '\x2', '\x6', '\x15', '\x3', '\x2', '\x2', '\x2', 
		'\b', '(', '\x3', '\x2', '\x2', '\x2', '\n', ',', '\x3', '\x2', '\x2', 
		'\x2', '\f', '\r', '\x5', '\x4', '\x3', '\x2', '\r', '\xE', '\x5', '\x6', 
		'\x4', '\x2', '\xE', '\x3', '\x3', '\x2', '\x2', '\x2', '\xF', '\x11', 
		'\a', '\b', '\x2', '\x2', '\x10', '\xF', '\x3', '\x2', '\x2', '\x2', '\x11', 
		'\x14', '\x3', '\x2', '\x2', '\x2', '\x12', '\x10', '\x3', '\x2', '\x2', 
		'\x2', '\x12', '\x13', '\x3', '\x2', '\x2', '\x2', '\x13', '\x5', '\x3', 
		'\x2', '\x2', '\x2', '\x14', '\x12', '\x3', '\x2', '\x2', '\x2', '\x15', 
		'&', '\a', '\v', '\x2', '\x2', '\x16', '\x17', '\a', '\x3', '\x2', '\x2', 
		'\x17', '!', '\x5', '\b', '\x5', '\x2', '\x18', '\x1A', '\a', '\x4', '\x2', 
		'\x2', '\x19', '\x18', '\x3', '\x2', '\x2', '\x2', '\x19', '\x1A', '\x3', 
		'\x2', '\x2', '\x2', '\x1A', '\x1D', '\x3', '\x2', '\x2', '\x2', '\x1B', 
		'\x1E', '\x5', '\b', '\x5', '\x2', '\x1C', '\x1E', '\x5', '\x6', '\x4', 
		'\x2', '\x1D', '\x1B', '\x3', '\x2', '\x2', '\x2', '\x1D', '\x1C', '\x3', 
		'\x2', '\x2', '\x2', '\x1E', ' ', '\x3', '\x2', '\x2', '\x2', '\x1F', 
		'\x19', '\x3', '\x2', '\x2', '\x2', ' ', '#', '\x3', '\x2', '\x2', '\x2', 
		'!', '\x1F', '\x3', '\x2', '\x2', '\x2', '!', '\"', '\x3', '\x2', '\x2', 
		'\x2', '\"', '$', '\x3', '\x2', '\x2', '\x2', '#', '!', '\x3', '\x2', 
		'\x2', '\x2', '$', '%', '\a', '\x5', '\x2', '\x2', '%', '\'', '\x3', '\x2', 
		'\x2', '\x2', '&', '\x16', '\x3', '\x2', '\x2', '\x2', '&', '\'', '\x3', 
		'\x2', '\x2', '\x2', '\'', '\a', '\x3', '\x2', '\x2', '\x2', '(', ')', 
		'\a', '\v', '\x2', '\x2', ')', '*', '\a', '\x6', '\x2', '\x2', '*', '+', 
		'\x5', '\n', '\x6', '\x2', '+', '\t', '\x3', '\x2', '\x2', '\x2', ',', 
		'-', '\t', '\x2', '\x2', '\x2', '-', '\v', '\x3', '\x2', '\x2', '\x2', 
		'\a', '\x12', '\x19', '\x1D', '!', '&',
	};

	public static readonly ATN _ATN =
		new ATNDeserializer().Deserialize(_serializedATN);


}
} // namespace Shiba.Parser
