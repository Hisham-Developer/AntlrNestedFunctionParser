// Generated from com\functionvalidate\validate\MyFunctionsParser.g4 by ANTLR 4.7.2
package com.functionvalidate.validate;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MyFunctionsParser}.
 */
public interface MyFunctionsParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MyFunctionsParser#functions}.
	 * @param ctx the parse tree
	 */
	void enterFunctions(MyFunctionsParser.FunctionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyFunctionsParser#functions}.
	 * @param ctx the parse tree
	 */
	void exitFunctions(MyFunctionsParser.FunctionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyFunctionsParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MyFunctionsParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyFunctionsParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MyFunctionsParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MyFunctionsParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(MyFunctionsParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MyFunctionsParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(MyFunctionsParser.ArgumentContext ctx);
}