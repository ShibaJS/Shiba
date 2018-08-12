// Generated from Shiba.g4 by ANTLR 4.7.1
package moe.tlaster.shiba.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShibaParser}.
 */
public interface ShibaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShibaParser#view}.
	 * @param ctx the parse tree
	 */
	void enterView(ShibaParser.ViewContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#view}.
	 * @param ctx the parse tree
	 */
	void exitView(ShibaParser.ViewContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#property}.
	 * @param ctx the parse tree
	 */
	void enterProperty(ShibaParser.PropertyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#property}.
	 * @param ctx the parse tree
	 */
	void exitProperty(ShibaParser.PropertyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ShibaParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ShibaParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(ShibaParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(ShibaParser.MapContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#basicValue}.
	 * @param ctx the parse tree
	 */
	void enterBasicValue(ShibaParser.BasicValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#basicValue}.
	 * @param ctx the parse tree
	 */
	void exitBasicValue(ShibaParser.BasicValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(ShibaParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(ShibaParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#shibaExtension}.
	 * @param ctx the parse tree
	 */
	void enterShibaExtension(ShibaParser.ShibaExtensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#shibaExtension}.
	 * @param ctx the parse tree
	 */
	void exitShibaExtension(ShibaParser.ShibaExtensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(ShibaParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(ShibaParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(ShibaParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(ShibaParser.IdentifierContext ctx);
}