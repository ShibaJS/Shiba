// Generated from Shiba.g4 by ANTLR 4.7.1
package moe.tlaster.shiba.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ShibaParser}.
 */
interface ShibaListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by {@link ShibaParser#staticvalue}.
	 * @param ctx the parse tree
	 */
	void enterStaticvalue(ShibaParser.StaticvalueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#staticvalue}.
	 * @param ctx the parse tree
	 */
	void exitStaticvalue(ShibaParser.StaticvalueContext ctx);
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
	 * Enter a parse tree produced by {@link ShibaParser#func}.
	 * @param ctx the parse tree
	 */
	void enterFunc(ShibaParser.FuncContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#func}.
	 * @param ctx the parse tree
	 */
	void exitFunc(ShibaParser.FuncContext ctx);
	/**
	 * Enter a parse tree produced by {@link ShibaParser#paramter}.
	 * @param ctx the parse tree
	 */
	void enterParamter(ShibaParser.ParamterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#paramter}.
	 * @param ctx the parse tree
	 */
	void exitParamter(ShibaParser.ParamterContext ctx);
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
	 * Enter a parse tree produced by {@link ShibaParser#resource}.
	 * @param ctx the parse tree
	 */
	void enterResource(ShibaParser.ResourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#resource}.
	 * @param ctx the parse tree
	 */
	void exitResource(ShibaParser.ResourceContext ctx);
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