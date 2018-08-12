// Generated from Shiba.g4 by ANTLR 4.7.1
import Antlr4

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShibaParser}.
 */
public protocol ShibaListener: ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShibaParser#view}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterView(_ ctx: ShibaParser.ViewContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#view}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitView(_ ctx: ShibaParser.ViewContext)
	/**
	 * Enter a parse tree produced by {@link ShibaParser#property}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterProperty(_ ctx: ShibaParser.PropertyContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#property}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitProperty(_ ctx: ShibaParser.PropertyContext)
	/**
	 * Enter a parse tree produced by {@link ShibaParser#value}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterValue(_ ctx: ShibaParser.ValueContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#value}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitValue(_ ctx: ShibaParser.ValueContext)
	/**
	 * Enter a parse tree produced by {@link ShibaParser#map}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterMap(_ ctx: ShibaParser.MapContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#map}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitMap(_ ctx: ShibaParser.MapContext)
	/**
	 * Enter a parse tree produced by {@link ShibaParser#basicValue}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterBasicValue(_ ctx: ShibaParser.BasicValueContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#basicValue}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitBasicValue(_ ctx: ShibaParser.BasicValueContext)
	/**
	 * Enter a parse tree produced by {@link ShibaParser#functionCall}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterFunctionCall(_ ctx: ShibaParser.FunctionCallContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#functionCall}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitFunctionCall(_ ctx: ShibaParser.FunctionCallContext)
	/**
	 * Enter a parse tree produced by {@link ShibaParser#shibaExtension}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterShibaExtension(_ ctx: ShibaParser.ShibaExtensionContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#shibaExtension}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitShibaExtension(_ ctx: ShibaParser.ShibaExtensionContext)
	/**
	 * Enter a parse tree produced by {@link ShibaParser#array}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterArray(_ ctx: ShibaParser.ArrayContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#array}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitArray(_ ctx: ShibaParser.ArrayContext)
	/**
	 * Enter a parse tree produced by {@link ShibaParser#identifier}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func enterIdentifier(_ ctx: ShibaParser.IdentifierContext)
	/**
	 * Exit a parse tree produced by {@link ShibaParser#identifier}.
	 - Parameters:
	   - ctx: the parse tree
	 */
	func exitIdentifier(_ ctx: ShibaParser.IdentifierContext)
}