// Generated from com\functionvalidate\validate\MyFunctionsParser.g4 by ANTLR 4.7.2
package com.functionvalidate.validate;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MyFunctionsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MyFunctionsParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MyFunctionsParser#functions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctions(MyFunctionsParser.FunctionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyFunctionsParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(MyFunctionsParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MyFunctionsParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(MyFunctionsParser.ArgumentContext ctx);
}