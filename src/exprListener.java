// Generated from /Users/jamesbayley/Documents/uni/y3/CE305/Assignment1/src/expr.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link exprParser}.
 */
public interface exprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link exprParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(exprParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(exprParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(exprParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(exprParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(exprParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(exprParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(exprParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(exprParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(exprParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(exprParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(exprParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#logicalOrExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(exprParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(exprParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#logicalAndExpression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(exprParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(exprParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#equalityExpression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(exprParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(exprParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#relationalExpression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(exprParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(exprParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#additiveExpression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(exprParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(exprParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#multiplicativeExpression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(exprParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpression(exprParser.UnaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#unaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpression(exprParser.UnaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link exprParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(exprParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link exprParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(exprParser.PrimaryExpressionContext ctx);
}