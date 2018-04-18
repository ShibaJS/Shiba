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
	 * Enter a parse tree produced by {@link ShibaParser#namespaces}.
	 * @param ctx the parse tree
	 */
	void enterNamespaces(ShibaParser.NamespacesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ShibaParser#namespaces}.
	 * @param ctx the parse tree
	 */
	void exitNamespaces(ShibaParser.NamespacesContext ctx);
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
}