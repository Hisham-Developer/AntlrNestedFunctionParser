// Generated from MyFunctionsParser.g4 by ANTLR 4.4
package com.functionvalidate.validate;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyFunctionsParser}.
 */
public interface MyFunctionsParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyFunctionsParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(@NotNull MyFunctionsParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyFunctionsParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(@NotNull MyFunctionsParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyFunctionsParser#functions}.
	 * @param ctx the parse tree
	 */
	void enterFunctions(@NotNull MyFunctionsParser.FunctionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyFunctionsParser#functions}.
	 * @param ctx the parse tree
	 */
	void exitFunctions(@NotNull MyFunctionsParser.FunctionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyFunctionsParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(@NotNull MyFunctionsParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyFunctionsParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(@NotNull MyFunctionsParser.FunctionContext ctx);
}