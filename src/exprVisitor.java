// Generated from /Users/jamesbayley/Documents/uni/y3/CE305/Assignment1/src/expr.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link exprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface exprVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link exprParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(exprParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(exprParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(exprParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(exprParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(exprParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExpression(exprParser.LogicalOrExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExpression(exprParser.LogicalAndExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#equalityExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExpression(exprParser.EqualityExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#relationalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExpression(exprParser.RelationalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#additiveExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExpression(exprParser.AdditiveExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExpression(exprParser.MultiplicativeExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(exprParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link exprParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(exprParser.PrimaryExpressionContext ctx);
}