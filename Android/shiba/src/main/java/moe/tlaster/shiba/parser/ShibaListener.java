// Generated from Shiba.g4 by ANTLR 4.7.1
package moe.tlaster.shiba.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShibaParser}.
 */
public interface ShibaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ShibaParser#root}.
	 * @param ctx the parse tree
	 */
	void enterRoot(ShibaParser.RootContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#root}.
	 * @param ctx the parse tree
	 */
	void exitRoot(ShibaParser.RootContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#obj}.
	 * @param ctx the parse tree
	 */
	void enterObj(ShibaParser.ObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#obj}.
	 * @param ctx the parse tree
	 */
	void exitObj(ShibaParser.ObjContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(ShibaParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(ShibaParser.PairContext ctx);
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
	 * Enter a parse tree produced by {@link ShibaParser#percent}.
	 * @param ctx the parse tree
	 */
	void enterPercent(ShibaParser.PercentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#percent}.
	 * @param ctx the parse tree
	 */
	void exitPercent(ShibaParser.PercentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#thickness}.
	 * @param ctx the parse tree
	 */
	void enterThickness(ShibaParser.ThicknessContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#thickness}.
	 * @param ctx the parse tree
	 */
	void exitThickness(ShibaParser.ThicknessContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#comput}.
	 * @param ctx the parse tree
	 */
	void enterComput(ShibaParser.ComputContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#comput}.
	 * @param ctx the parse tree
	 */
	void exitComput(ShibaParser.ComputContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#binding}.
	 * @param ctx the parse tree
	 */
	void enterBinding(ShibaParser.BindingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#binding}.
	 * @param ctx the parse tree
	 */
	void exitBinding(ShibaParser.BindingContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#native}.
	 * @param ctx the parse tree
	 */
	void enterNative(ShibaParser.NativeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#native}.
	 * @param ctx the parse tree
	 */
	void exitNative(ShibaParser.NativeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#jsonpath}.
	 * @param ctx the parse tree
	 */
	void enterJsonpath(ShibaParser.JsonpathContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#jsonpath}.
	 * @param ctx the parse tree
	 */
	void exitJsonpath(ShibaParser.JsonpathContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#dic}.
	 * @param ctx the parse tree
	 */
	void enterDic(ShibaParser.DicContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#dic}.
	 * @param ctx the parse tree
	 */
	void exitDic(ShibaParser.DicContext ctx);
}