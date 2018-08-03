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
using Antlr4.Runtime;
using Antlr4.Runtime.Atn;
using Antlr4.Runtime.Misc;
using DFA = Antlr4.Runtime.Dfa.DFA;

[System.CodeDom.Compiler.GeneratedCode("ANTLR", "4.7.1")]
[System.CLSCompliant(false)]
public partial class ShibaLexer : Lexer {
	protected static DFA[] decisionToDFA;
	protected static PredictionContextCache sharedContextCache = new PredictionContextCache();
	public const int
		T__0=1, Null=2, String=3, Boolean=4, Identifier=5, Number=6, Arrow=7, 
		Comma=8, Colon=9, Assign=10, LeftParen=11, RightParen=12, LeftBracket=13, 
		RightBracket=14, LeftBrace=15, RightBrace=16, Hws=17, Vws=18, DocComment=19, 
		BlockComment=20, LineComment=21, LineCommentExt=22;
	public static string[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static string[] modeNames = {
		"DEFAULT_MODE"
	};

	public static readonly string[] ruleNames = {
		"T__0", "Null", "String", "Boolean", "Identifier", "Number", "Arrow", 
		"Comma", "Colon", "Assign", "LeftParen", "RightParen", "LeftBracket", 
		"RightBracket", "LeftBrace", "RightBrace", "INT", "EXP", "Hws", "Vws", 
		"DocComment", "BlockComment", "LineComment", "LineCommentExt"
	};


	public ShibaLexer(ICharStream input)
	: this(input, Console.Out, Console.Error) { }

	public ShibaLexer(ICharStream input, TextWriter output, TextWriter errorOutput)
	: base(input, output, errorOutput)
	{
		Interpreter = new LexerATNSimulator(this, _ATN, decisionToDFA, sharedContextCache);
	}

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

	public override string[] ChannelNames { get { return channelNames; } }

	public override string[] ModeNames { get { return modeNames; } }

	public override string SerializedAtn { get { return new string(_serializedATN); } }

	static ShibaLexer() {
		decisionToDFA = new DFA[_ATN.NumberOfDecisions];
		for (int i = 0; i < _ATN.NumberOfDecisions; i++) {
			decisionToDFA[i] = new DFA(_ATN.GetDecisionState(i), i);
		}
	}
	private static char[] _serializedATN = {
		'\x3', '\x608B', '\xA72A', '\x8133', '\xB9ED', '\x417C', '\x3BE7', '\x7786', 
		'\x5964', '\x2', '\x18', '\xE1', '\b', '\x1', '\x4', '\x2', '\t', '\x2', 
		'\x4', '\x3', '\t', '\x3', '\x4', '\x4', '\t', '\x4', '\x4', '\x5', '\t', 
		'\x5', '\x4', '\x6', '\t', '\x6', '\x4', '\a', '\t', '\a', '\x4', '\b', 
		'\t', '\b', '\x4', '\t', '\t', '\t', '\x4', '\n', '\t', '\n', '\x4', '\v', 
		'\t', '\v', '\x4', '\f', '\t', '\f', '\x4', '\r', '\t', '\r', '\x4', '\xE', 
		'\t', '\xE', '\x4', '\xF', '\t', '\xF', '\x4', '\x10', '\t', '\x10', '\x4', 
		'\x11', '\t', '\x11', '\x4', '\x12', '\t', '\x12', '\x4', '\x13', '\t', 
		'\x13', '\x4', '\x14', '\t', '\x14', '\x4', '\x15', '\t', '\x15', '\x4', 
		'\x16', '\t', '\x16', '\x4', '\x17', '\t', '\x17', '\x4', '\x18', '\t', 
		'\x18', '\x4', '\x19', '\t', '\x19', '\x3', '\x2', '\x3', '\x2', '\x3', 
		'\x3', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', '\x3', 
		'\x4', '\x3', '\x4', '\a', '\x4', '=', '\n', '\x4', '\f', '\x4', '\xE', 
		'\x4', '@', '\v', '\x4', '\x3', '\x4', '\x3', '\x4', '\x3', '\x5', '\x3', 
		'\x5', '\x3', '\x5', '\x3', '\x5', '\x3', '\x5', '\x3', '\x5', '\x3', 
		'\x5', '\x3', '\x5', '\x3', '\x5', '\x5', '\x5', 'M', '\n', '\x5', '\x3', 
		'\x6', '\x6', '\x6', 'P', '\n', '\x6', '\r', '\x6', '\xE', '\x6', 'Q', 
		'\x3', '\x6', '\a', '\x6', 'U', '\n', '\x6', '\f', '\x6', '\xE', '\x6', 
		'X', '\v', '\x6', '\x3', '\a', '\x5', '\a', '[', '\n', '\a', '\x3', '\a', 
		'\x3', '\a', '\x3', '\a', '\x6', '\a', '`', '\n', '\a', '\r', '\a', '\xE', 
		'\a', '\x61', '\x5', '\a', '\x64', '\n', '\a', '\x3', '\a', '\x5', '\a', 
		'g', '\n', '\a', '\x3', '\b', '\x3', '\b', '\x3', '\b', '\x3', '\t', '\x3', 
		'\t', '\x3', '\n', '\x3', '\n', '\x3', '\v', '\x3', '\v', '\x3', '\f', 
		'\x3', '\f', '\x3', '\r', '\x3', '\r', '\x3', '\xE', '\x3', '\xE', '\x3', 
		'\xF', '\x3', '\xF', '\x3', '\x10', '\x3', '\x10', '\x3', '\x11', '\x3', 
		'\x11', '\x3', '\x12', '\x3', '\x12', '\x3', '\x12', '\a', '\x12', '\x81', 
		'\n', '\x12', '\f', '\x12', '\xE', '\x12', '\x84', '\v', '\x12', '\x5', 
		'\x12', '\x86', '\n', '\x12', '\x3', '\x13', '\x3', '\x13', '\x5', '\x13', 
		'\x8A', '\n', '\x13', '\x3', '\x13', '\x3', '\x13', '\x3', '\x14', '\x3', 
		'\x14', '\x3', '\x14', '\x3', '\x14', '\x3', '\x15', '\x3', '\x15', '\x3', 
		'\x15', '\x3', '\x15', '\x3', '\x16', '\x3', '\x16', '\x3', '\x16', '\x3', 
		'\x16', '\x3', '\x16', '\a', '\x16', '\x9B', '\n', '\x16', '\f', '\x16', 
		'\xE', '\x16', '\x9E', '\v', '\x16', '\x3', '\x16', '\x3', '\x16', '\x3', 
		'\x16', '\x5', '\x16', '\xA3', '\n', '\x16', '\x3', '\x16', '\x3', '\x16', 
		'\x3', '\x17', '\x3', '\x17', '\x3', '\x17', '\x3', '\x17', '\a', '\x17', 
		'\xAB', '\n', '\x17', '\f', '\x17', '\xE', '\x17', '\xAE', '\v', '\x17', 
		'\x3', '\x17', '\x3', '\x17', '\x3', '\x17', '\x5', '\x17', '\xB3', '\n', 
		'\x17', '\x3', '\x17', '\x3', '\x17', '\x3', '\x18', '\x3', '\x18', '\x3', 
		'\x18', '\x3', '\x18', '\a', '\x18', '\xBB', '\n', '\x18', '\f', '\x18', 
		'\xE', '\x18', '\xBE', '\v', '\x18', '\x3', '\x18', '\x3', '\x18', '\x3', 
		'\x19', '\x3', '\x19', '\x3', '\x19', '\x3', '\x19', '\a', '\x19', '\xC6', 
		'\n', '\x19', '\f', '\x19', '\xE', '\x19', '\xC9', '\v', '\x19', '\x3', 
		'\x19', '\x3', '\x19', '\a', '\x19', '\xCD', '\n', '\x19', '\f', '\x19', 
		'\xE', '\x19', '\xD0', '\v', '\x19', '\x3', '\x19', '\x3', '\x19', '\x3', 
		'\x19', '\x3', '\x19', '\a', '\x19', '\xD6', '\n', '\x19', '\f', '\x19', 
		'\xE', '\x19', '\xD9', '\v', '\x19', '\a', '\x19', '\xDB', '\n', '\x19', 
		'\f', '\x19', '\xE', '\x19', '\xDE', '\v', '\x19', '\x3', '\x19', '\x3', 
		'\x19', '\x4', '\x9C', '\xAC', '\x2', '\x1A', '\x3', '\x3', '\x5', '\x4', 
		'\a', '\x5', '\t', '\x6', '\v', '\a', '\r', '\b', '\xF', '\t', '\x11', 
		'\n', '\x13', '\v', '\x15', '\f', '\x17', '\r', '\x19', '\xE', '\x1B', 
		'\xF', '\x1D', '\x10', '\x1F', '\x11', '!', '\x12', '#', '\x2', '%', '\x2', 
		'\'', '\x13', ')', '\x14', '+', '\x15', '-', '\x16', '/', '\x17', '\x31', 
		'\x18', '\x3', '\x2', '\r', '\x3', '\x2', '$', '$', '\x5', '\x2', '\x43', 
		'\\', '\x61', '\x61', '\x63', '|', '\a', '\x2', '\x30', '\x30', '\x32', 
		';', '\x43', '\\', '\x61', '\x61', '\x63', '|', '\x3', '\x2', '\x32', 
		';', '\x3', '\x2', '\x33', ';', '\x4', '\x2', 'G', 'G', 'g', 'g', '\x4', 
		'\x2', '-', '-', '/', '/', '\x4', '\x2', '\v', '\v', '\"', '\"', '\x4', 
		'\x2', '\f', '\f', '\xE', '\xF', '\x4', '\x2', '\f', '\f', '\xF', '\xF', 
		'\x3', '\x2', '\f', '\f', '\x2', '\xF2', '\x2', '\x3', '\x3', '\x2', '\x2', 
		'\x2', '\x2', '\x5', '\x3', '\x2', '\x2', '\x2', '\x2', '\a', '\x3', '\x2', 
		'\x2', '\x2', '\x2', '\t', '\x3', '\x2', '\x2', '\x2', '\x2', '\v', '\x3', 
		'\x2', '\x2', '\x2', '\x2', '\r', '\x3', '\x2', '\x2', '\x2', '\x2', '\xF', 
		'\x3', '\x2', '\x2', '\x2', '\x2', '\x11', '\x3', '\x2', '\x2', '\x2', 
		'\x2', '\x13', '\x3', '\x2', '\x2', '\x2', '\x2', '\x15', '\x3', '\x2', 
		'\x2', '\x2', '\x2', '\x17', '\x3', '\x2', '\x2', '\x2', '\x2', '\x19', 
		'\x3', '\x2', '\x2', '\x2', '\x2', '\x1B', '\x3', '\x2', '\x2', '\x2', 
		'\x2', '\x1D', '\x3', '\x2', '\x2', '\x2', '\x2', '\x1F', '\x3', '\x2', 
		'\x2', '\x2', '\x2', '!', '\x3', '\x2', '\x2', '\x2', '\x2', '\'', '\x3', 
		'\x2', '\x2', '\x2', '\x2', ')', '\x3', '\x2', '\x2', '\x2', '\x2', '+', 
		'\x3', '\x2', '\x2', '\x2', '\x2', '-', '\x3', '\x2', '\x2', '\x2', '\x2', 
		'/', '\x3', '\x2', '\x2', '\x2', '\x2', '\x31', '\x3', '\x2', '\x2', '\x2', 
		'\x3', '\x33', '\x3', '\x2', '\x2', '\x2', '\x5', '\x35', '\x3', '\x2', 
		'\x2', '\x2', '\a', ':', '\x3', '\x2', '\x2', '\x2', '\t', 'L', '\x3', 
		'\x2', '\x2', '\x2', '\v', 'O', '\x3', '\x2', '\x2', '\x2', '\r', 'Z', 
		'\x3', '\x2', '\x2', '\x2', '\xF', 'h', '\x3', '\x2', '\x2', '\x2', '\x11', 
		'k', '\x3', '\x2', '\x2', '\x2', '\x13', 'm', '\x3', '\x2', '\x2', '\x2', 
		'\x15', 'o', '\x3', '\x2', '\x2', '\x2', '\x17', 'q', '\x3', '\x2', '\x2', 
		'\x2', '\x19', 's', '\x3', '\x2', '\x2', '\x2', '\x1B', 'u', '\x3', '\x2', 
		'\x2', '\x2', '\x1D', 'w', '\x3', '\x2', '\x2', '\x2', '\x1F', 'y', '\x3', 
		'\x2', '\x2', '\x2', '!', '{', '\x3', '\x2', '\x2', '\x2', '#', '\x85', 
		'\x3', '\x2', '\x2', '\x2', '%', '\x87', '\x3', '\x2', '\x2', '\x2', '\'', 
		'\x8D', '\x3', '\x2', '\x2', '\x2', ')', '\x91', '\x3', '\x2', '\x2', 
		'\x2', '+', '\x95', '\x3', '\x2', '\x2', '\x2', '-', '\xA6', '\x3', '\x2', 
		'\x2', '\x2', '/', '\xB6', '\x3', '\x2', '\x2', '\x2', '\x31', '\xC1', 
		'\x3', '\x2', '\x2', '\x2', '\x33', '\x34', '\a', '&', '\x2', '\x2', '\x34', 
		'\x4', '\x3', '\x2', '\x2', '\x2', '\x35', '\x36', '\a', 'p', '\x2', '\x2', 
		'\x36', '\x37', '\a', 'w', '\x2', '\x2', '\x37', '\x38', '\a', 'n', '\x2', 
		'\x2', '\x38', '\x39', '\a', 'n', '\x2', '\x2', '\x39', '\x6', '\x3', 
		'\x2', '\x2', '\x2', ':', '>', '\a', '$', '\x2', '\x2', ';', '=', '\n', 
		'\x2', '\x2', '\x2', '<', ';', '\x3', '\x2', '\x2', '\x2', '=', '@', '\x3', 
		'\x2', '\x2', '\x2', '>', '<', '\x3', '\x2', '\x2', '\x2', '>', '?', '\x3', 
		'\x2', '\x2', '\x2', '?', '\x41', '\x3', '\x2', '\x2', '\x2', '@', '>', 
		'\x3', '\x2', '\x2', '\x2', '\x41', '\x42', '\a', '$', '\x2', '\x2', '\x42', 
		'\b', '\x3', '\x2', '\x2', '\x2', '\x43', '\x44', '\a', 'v', '\x2', '\x2', 
		'\x44', '\x45', '\a', 't', '\x2', '\x2', '\x45', '\x46', '\a', 'w', '\x2', 
		'\x2', '\x46', 'M', '\a', 'g', '\x2', '\x2', 'G', 'H', '\a', 'h', '\x2', 
		'\x2', 'H', 'I', '\a', '\x63', '\x2', '\x2', 'I', 'J', '\a', 'n', '\x2', 
		'\x2', 'J', 'K', '\a', 'u', '\x2', '\x2', 'K', 'M', '\a', 'g', '\x2', 
		'\x2', 'L', '\x43', '\x3', '\x2', '\x2', '\x2', 'L', 'G', '\x3', '\x2', 
		'\x2', '\x2', 'M', '\n', '\x3', '\x2', '\x2', '\x2', 'N', 'P', '\t', '\x3', 
		'\x2', '\x2', 'O', 'N', '\x3', '\x2', '\x2', '\x2', 'P', 'Q', '\x3', '\x2', 
		'\x2', '\x2', 'Q', 'O', '\x3', '\x2', '\x2', '\x2', 'Q', 'R', '\x3', '\x2', 
		'\x2', '\x2', 'R', 'V', '\x3', '\x2', '\x2', '\x2', 'S', 'U', '\t', '\x4', 
		'\x2', '\x2', 'T', 'S', '\x3', '\x2', '\x2', '\x2', 'U', 'X', '\x3', '\x2', 
		'\x2', '\x2', 'V', 'T', '\x3', '\x2', '\x2', '\x2', 'V', 'W', '\x3', '\x2', 
		'\x2', '\x2', 'W', '\f', '\x3', '\x2', '\x2', '\x2', 'X', 'V', '\x3', 
		'\x2', '\x2', '\x2', 'Y', '[', '\a', '/', '\x2', '\x2', 'Z', 'Y', '\x3', 
		'\x2', '\x2', '\x2', 'Z', '[', '\x3', '\x2', '\x2', '\x2', '[', '\\', 
		'\x3', '\x2', '\x2', '\x2', '\\', '\x63', '\x5', '#', '\x12', '\x2', ']', 
		'_', '\a', '\x30', '\x2', '\x2', '^', '`', '\t', '\x5', '\x2', '\x2', 
		'_', '^', '\x3', '\x2', '\x2', '\x2', '`', '\x61', '\x3', '\x2', '\x2', 
		'\x2', '\x61', '_', '\x3', '\x2', '\x2', '\x2', '\x61', '\x62', '\x3', 
		'\x2', '\x2', '\x2', '\x62', '\x64', '\x3', '\x2', '\x2', '\x2', '\x63', 
		']', '\x3', '\x2', '\x2', '\x2', '\x63', '\x64', '\x3', '\x2', '\x2', 
		'\x2', '\x64', '\x66', '\x3', '\x2', '\x2', '\x2', '\x65', 'g', '\x5', 
		'%', '\x13', '\x2', '\x66', '\x65', '\x3', '\x2', '\x2', '\x2', '\x66', 
		'g', '\x3', '\x2', '\x2', '\x2', 'g', '\xE', '\x3', '\x2', '\x2', '\x2', 
		'h', 'i', '\a', '/', '\x2', '\x2', 'i', 'j', '\a', '@', '\x2', '\x2', 
		'j', '\x10', '\x3', '\x2', '\x2', '\x2', 'k', 'l', '\a', '.', '\x2', '\x2', 
		'l', '\x12', '\x3', '\x2', '\x2', '\x2', 'm', 'n', '\a', '<', '\x2', '\x2', 
		'n', '\x14', '\x3', '\x2', '\x2', '\x2', 'o', 'p', '\a', '?', '\x2', '\x2', 
		'p', '\x16', '\x3', '\x2', '\x2', '\x2', 'q', 'r', '\a', '*', '\x2', '\x2', 
		'r', '\x18', '\x3', '\x2', '\x2', '\x2', 's', 't', '\a', '+', '\x2', '\x2', 
		't', '\x1A', '\x3', '\x2', '\x2', '\x2', 'u', 'v', '\a', ']', '\x2', '\x2', 
		'v', '\x1C', '\x3', '\x2', '\x2', '\x2', 'w', 'x', '\a', '_', '\x2', '\x2', 
		'x', '\x1E', '\x3', '\x2', '\x2', '\x2', 'y', 'z', '\a', '}', '\x2', '\x2', 
		'z', ' ', '\x3', '\x2', '\x2', '\x2', '{', '|', '\a', '\x7F', '\x2', '\x2', 
		'|', '\"', '\x3', '\x2', '\x2', '\x2', '}', '\x86', '\a', '\x32', '\x2', 
		'\x2', '~', '\x82', '\t', '\x6', '\x2', '\x2', '\x7F', '\x81', '\t', '\x5', 
		'\x2', '\x2', '\x80', '\x7F', '\x3', '\x2', '\x2', '\x2', '\x81', '\x84', 
		'\x3', '\x2', '\x2', '\x2', '\x82', '\x80', '\x3', '\x2', '\x2', '\x2', 
		'\x82', '\x83', '\x3', '\x2', '\x2', '\x2', '\x83', '\x86', '\x3', '\x2', 
		'\x2', '\x2', '\x84', '\x82', '\x3', '\x2', '\x2', '\x2', '\x85', '}', 
		'\x3', '\x2', '\x2', '\x2', '\x85', '~', '\x3', '\x2', '\x2', '\x2', '\x86', 
		'$', '\x3', '\x2', '\x2', '\x2', '\x87', '\x89', '\t', '\a', '\x2', '\x2', 
		'\x88', '\x8A', '\t', '\b', '\x2', '\x2', '\x89', '\x88', '\x3', '\x2', 
		'\x2', '\x2', '\x89', '\x8A', '\x3', '\x2', '\x2', '\x2', '\x8A', '\x8B', 
		'\x3', '\x2', '\x2', '\x2', '\x8B', '\x8C', '\x5', '#', '\x12', '\x2', 
		'\x8C', '&', '\x3', '\x2', '\x2', '\x2', '\x8D', '\x8E', '\t', '\t', '\x2', 
		'\x2', '\x8E', '\x8F', '\x3', '\x2', '\x2', '\x2', '\x8F', '\x90', '\b', 
		'\x14', '\x2', '\x2', '\x90', '(', '\x3', '\x2', '\x2', '\x2', '\x91', 
		'\x92', '\t', '\n', '\x2', '\x2', '\x92', '\x93', '\x3', '\x2', '\x2', 
		'\x2', '\x93', '\x94', '\b', '\x15', '\x2', '\x2', '\x94', '*', '\x3', 
		'\x2', '\x2', '\x2', '\x95', '\x96', '\a', '\x31', '\x2', '\x2', '\x96', 
		'\x97', '\a', ',', '\x2', '\x2', '\x97', '\x98', '\a', ',', '\x2', '\x2', 
		'\x98', '\x9C', '\x3', '\x2', '\x2', '\x2', '\x99', '\x9B', '\v', '\x2', 
		'\x2', '\x2', '\x9A', '\x99', '\x3', '\x2', '\x2', '\x2', '\x9B', '\x9E', 
		'\x3', '\x2', '\x2', '\x2', '\x9C', '\x9D', '\x3', '\x2', '\x2', '\x2', 
		'\x9C', '\x9A', '\x3', '\x2', '\x2', '\x2', '\x9D', '\xA2', '\x3', '\x2', 
		'\x2', '\x2', '\x9E', '\x9C', '\x3', '\x2', '\x2', '\x2', '\x9F', '\xA0', 
		'\a', ',', '\x2', '\x2', '\xA0', '\xA3', '\a', '\x31', '\x2', '\x2', '\xA1', 
		'\xA3', '\a', '\x2', '\x2', '\x3', '\xA2', '\x9F', '\x3', '\x2', '\x2', 
		'\x2', '\xA2', '\xA1', '\x3', '\x2', '\x2', '\x2', '\xA3', '\xA4', '\x3', 
		'\x2', '\x2', '\x2', '\xA4', '\xA5', '\b', '\x16', '\x2', '\x2', '\xA5', 
		',', '\x3', '\x2', '\x2', '\x2', '\xA6', '\xA7', '\a', '\x31', '\x2', 
		'\x2', '\xA7', '\xA8', '\a', ',', '\x2', '\x2', '\xA8', '\xAC', '\x3', 
		'\x2', '\x2', '\x2', '\xA9', '\xAB', '\v', '\x2', '\x2', '\x2', '\xAA', 
		'\xA9', '\x3', '\x2', '\x2', '\x2', '\xAB', '\xAE', '\x3', '\x2', '\x2', 
		'\x2', '\xAC', '\xAD', '\x3', '\x2', '\x2', '\x2', '\xAC', '\xAA', '\x3', 
		'\x2', '\x2', '\x2', '\xAD', '\xB2', '\x3', '\x2', '\x2', '\x2', '\xAE', 
		'\xAC', '\x3', '\x2', '\x2', '\x2', '\xAF', '\xB0', '\a', ',', '\x2', 
		'\x2', '\xB0', '\xB3', '\a', '\x31', '\x2', '\x2', '\xB1', '\xB3', '\a', 
		'\x2', '\x2', '\x3', '\xB2', '\xAF', '\x3', '\x2', '\x2', '\x2', '\xB2', 
		'\xB1', '\x3', '\x2', '\x2', '\x2', '\xB3', '\xB4', '\x3', '\x2', '\x2', 
		'\x2', '\xB4', '\xB5', '\b', '\x17', '\x2', '\x2', '\xB5', '.', '\x3', 
		'\x2', '\x2', '\x2', '\xB6', '\xB7', '\a', '\x31', '\x2', '\x2', '\xB7', 
		'\xB8', '\a', '\x31', '\x2', '\x2', '\xB8', '\xBC', '\x3', '\x2', '\x2', 
		'\x2', '\xB9', '\xBB', '\n', '\v', '\x2', '\x2', '\xBA', '\xB9', '\x3', 
		'\x2', '\x2', '\x2', '\xBB', '\xBE', '\x3', '\x2', '\x2', '\x2', '\xBC', 
		'\xBA', '\x3', '\x2', '\x2', '\x2', '\xBC', '\xBD', '\x3', '\x2', '\x2', 
		'\x2', '\xBD', '\xBF', '\x3', '\x2', '\x2', '\x2', '\xBE', '\xBC', '\x3', 
		'\x2', '\x2', '\x2', '\xBF', '\xC0', '\b', '\x18', '\x2', '\x2', '\xC0', 
		'\x30', '\x3', '\x2', '\x2', '\x2', '\xC1', '\xC2', '\a', '\x31', '\x2', 
		'\x2', '\xC2', '\xC3', '\a', '\x31', '\x2', '\x2', '\xC3', '\xC7', '\x3', 
		'\x2', '\x2', '\x2', '\xC4', '\xC6', '\n', '\f', '\x2', '\x2', '\xC5', 
		'\xC4', '\x3', '\x2', '\x2', '\x2', '\xC6', '\xC9', '\x3', '\x2', '\x2', 
		'\x2', '\xC7', '\xC5', '\x3', '\x2', '\x2', '\x2', '\xC7', '\xC8', '\x3', 
		'\x2', '\x2', '\x2', '\xC8', '\xDC', '\x3', '\x2', '\x2', '\x2', '\xC9', 
		'\xC7', '\x3', '\x2', '\x2', '\x2', '\xCA', '\xCE', '\a', '\f', '\x2', 
		'\x2', '\xCB', '\xCD', '\x5', '\'', '\x14', '\x2', '\xCC', '\xCB', '\x3', 
		'\x2', '\x2', '\x2', '\xCD', '\xD0', '\x3', '\x2', '\x2', '\x2', '\xCE', 
		'\xCC', '\x3', '\x2', '\x2', '\x2', '\xCE', '\xCF', '\x3', '\x2', '\x2', 
		'\x2', '\xCF', '\xD1', '\x3', '\x2', '\x2', '\x2', '\xD0', '\xCE', '\x3', 
		'\x2', '\x2', '\x2', '\xD1', '\xD2', '\a', '\x31', '\x2', '\x2', '\xD2', 
		'\xD3', '\a', '\x31', '\x2', '\x2', '\xD3', '\xD7', '\x3', '\x2', '\x2', 
		'\x2', '\xD4', '\xD6', '\n', '\f', '\x2', '\x2', '\xD5', '\xD4', '\x3', 
		'\x2', '\x2', '\x2', '\xD6', '\xD9', '\x3', '\x2', '\x2', '\x2', '\xD7', 
		'\xD5', '\x3', '\x2', '\x2', '\x2', '\xD7', '\xD8', '\x3', '\x2', '\x2', 
		'\x2', '\xD8', '\xDB', '\x3', '\x2', '\x2', '\x2', '\xD9', '\xD7', '\x3', 
		'\x2', '\x2', '\x2', '\xDA', '\xCA', '\x3', '\x2', '\x2', '\x2', '\xDB', 
		'\xDE', '\x3', '\x2', '\x2', '\x2', '\xDC', '\xDA', '\x3', '\x2', '\x2', 
		'\x2', '\xDC', '\xDD', '\x3', '\x2', '\x2', '\x2', '\xDD', '\xDF', '\x3', 
		'\x2', '\x2', '\x2', '\xDE', '\xDC', '\x3', '\x2', '\x2', '\x2', '\xDF', 
		'\xE0', '\b', '\x19', '\x2', '\x2', '\xE0', '\x32', '\x3', '\x2', '\x2', 
		'\x2', '\x19', '\x2', '>', 'L', 'O', 'Q', 'T', 'V', 'Z', '\x61', '\x63', 
		'\x66', '\x82', '\x85', '\x89', '\x9C', '\xA2', '\xAC', '\xB2', '\xBC', 
		'\xC7', '\xCE', '\xD7', '\xDC', '\x3', '\b', '\x2', '\x2',
	};

	public static readonly ATN _ATN =
		new ATNDeserializer().Deserialize(_serializedATN);


}
} // namespace Shiba.Parser
