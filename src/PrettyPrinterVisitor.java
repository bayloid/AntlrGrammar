import java.util.HashMap;
import java.util.Map;

public class PrettyPrinterVisitor extends exprBaseVisitor<Integer>{
    private final Map<String, Integer> symbolTable = new HashMap<>();
    int indentLevel = 0;

    /**
     * Provides indents based on indent level. Used for pretty output.
     */
    private void indent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("  ");
        }
    }
    /**
     * Iterates over input statements, recursively calls visit on statements.
     * @param ctx the parse tree
     * @return null
     */
    @Override
    public Integer visitProgram(exprParser.ProgramContext ctx) {
        System.out.println("=== Parse Tree Pretty Printer ===");
        System.out.println("Program");
        indentLevel++;
        for (exprParser.StatementContext statement : ctx.statement()) {
            visit(statement);
        }
        indentLevel--;
        System.out.println("\n=== Symbol Table After Evaluation ===");
        System.out.println(symbolTable);
        return null;
    }

    /**
     * Visits statement, decides which statement type, throws RuntimeException
     * if no statement type is matched.
     * @param ctx the parse tree
     * @return output of visit(tree)
     */
    @Override
    public Integer visitStatement(exprParser.StatementContext ctx) {
        if (ctx.declaration() != null) {
            return visit(ctx.declaration());
        } else if (ctx.assignment() != null) {
            return visit(ctx.assignment());
        } else if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        throw new RuntimeException("Invalid statement");
    }

    /**
     * Visits declaration statement, puts variable name into the symbol tree
     * with default value (0).
     * @param ctx the parse tree
     * @return null
     */

    @Override
    public Integer visitDeclaration(exprParser.DeclarationContext ctx) {
        String varName = ctx.ID().getText();
        symbolTable.put(varName, 0);
        indent();
        System.out.println("Declaration: var " + varName);
        return null;
    }

    /**
     * Visits variable assignment statement. Throws RuntimeException if variable is not
     * defined previously.
     * @param ctx the parse tree
     * @return value of variable.
     */

    @Override
    public Integer visitAssignment(exprParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        if(!(symbolTable.containsKey(varName))){
            throw new RuntimeException("Variable "+varName+" not declared.");
        }
        indent();
        System.out.println("Assignment: " + ctx.ID().getText() + " = ");
        indentLevel++;
        int value = visit(ctx.expression());
        symbolTable.put(varName, value);
        return value;
    }

    /**
     * Visits expression. visits logicalOrExpression().
     * @param ctx the parse tree
     * @return returns output of visit(ctx.logicalOrExpression())
     */

    @Override
    public Integer visitExpression(exprParser.ExpressionContext ctx) {
        return visit(ctx.logicalOrExpression());
    }

    /**
     * Visits logicalOrExpression,
     * @param ctx the parse tree
     * @return output of visit(ctx.logicalAndExpression(0)) if no or operator present
     * else returns result ot left and right or operation.
     */
    @Override
    public Integer visitLogicalOrExpression(exprParser.LogicalOrExpressionContext ctx) {
        if(ctx.OR().isEmpty()){
            return visit(ctx.logicalAndExpression(0));
        }
        indent();
        System.out.println("LogicalOrExpression: ");
        indentLevel++;
        int left = visit(ctx.logicalAndExpression(0));
        for (int i = 1; i < ctx.logicalAndExpression().size(); i++) {
            int right = visit(ctx.logicalAndExpression(i));
            left = (left != 0 || right != 0) ? 1 : 0;
        }
        indentLevel--;
        return left;
    }

    /**
     * Visits logical and expression.
     * @param ctx the parse tree
     * @return output of visit(ctx.equalityExpression(0)) if no AND operator present,
     * else returns result or AND operation of left and right operands.
     */

    @Override
    public Integer visitLogicalAndExpression(exprParser.LogicalAndExpressionContext ctx) {
        if(ctx.AND().isEmpty()){
            return visit(ctx.equalityExpression(0));
        }
        int left = visit(ctx.equalityExpression(0));
        for (int i = 1; i < ctx.equalityExpression().size(); i++) {
            int right = visit(ctx.equalityExpression(i));
            left = (left != 0 && right != 0) ? 1 : 0;
        }
        indentLevel--;
        return left;
    }

    /**
     * Visits equality expression
     * @param ctx the parse tree
     * @return output of visit(ctx.relationalExpression(0)) if no equality operator present,
     * else returns result of left and right operand equality operation.
     */

    @Override
    public Integer visitEqualityExpression(exprParser.EqualityExpressionContext ctx) {
        if(ctx.EQ().isEmpty() && ctx.NEQ().isEmpty()){
            return visit(ctx.relationalExpression(0));
        }
        int left = visit(ctx.relationalExpression().get(0));
        for (int i = 0; i < ctx.EQ().size() + ctx.NEQ().size(); i++) {
            int right = visit(ctx.relationalExpression(i));
            if (ctx.EQ(i) != null) {
                left = (left == right) ? 1 : 0;
            } else if (ctx.NEQ(i) != null) {
                left = (left != right) ? 1 : 0;
            }
        }
        return left;
    }

    /**
     * Visits relation expression.
     * @param ctx the parse tree
     * @return output of visit(ctx.additiveExpression(0)) if no relational operator is present, else
     * returns result of left and right operand relational operation.
     */

    @Override
    public Integer visitRelationalExpression(exprParser.RelationalExpressionContext ctx) {
        if (ctx.LT().isEmpty() && ctx.GT().isEmpty() && ctx.LE().isEmpty() && ctx.GE().isEmpty()) {
            return visit(ctx.additiveExpression(0));
        }
        indent();
        System.out.println("RelationalExpression: ");
        indentLevel++;
        int left = visit(ctx.additiveExpression(0));
        for (int i = 0; i < ctx.LT().size() + ctx.GT().size() + ctx.LE().size() + ctx.GE().size(); i++) {
            int right = visit(ctx.additiveExpression(i+1));
            if (ctx.LT(i) != null) {
                left = (left < right) ? 1 : 0;
            } else if (ctx.GT(i) != null) {
                left = (left > right) ? 1 : 0;
            } else if (ctx.LE(i) != null) {
                left = (left <= right) ? 1 : 0;
            } else if (ctx.GE(i) != null) {
                left = (left >= right) ? 1 : 0;
            }
        }
        indentLevel--;
        return left;
    }

    /**
     * Visits additive expression
     * @param ctx the parse tree
     * @return result of visit(ctx.multiplicativeExpression(0)) if no additive operator present, else
     * returns result of left and right operand additive operation.
     */

    @Override
    public Integer visitAdditiveExpression(exprParser.AdditiveExpressionContext ctx) {
        if(ctx.ADD().isEmpty() && ctx.SUB().isEmpty()){
            return visit(ctx.multiplicativeExpression(0));
        }
        indent();
        System.out.println("AdditiveExpression: ");
        indentLevel++;
        int left = visit(ctx.multiplicativeExpression().get(0));
        for (int i = 1; i < ctx.multiplicativeExpression().size() + ctx.SUB().size(); i++) {
            int right = visit(ctx.multiplicativeExpression(i));
            if (ctx.ADD(i-1) != null) {
                left += right;
            } else if (ctx.SUB(i-1) != null) {
                left -= right;
            }
        }
        indentLevel--;
        return left;
    }

    /**
     * Visits multiplicative expression.
     * @param ctx the parse tree
     * @return result of visit(ctx.unaryExpression(0) if no multiplicative operator present,
     * else returns the result of left and right operand multiplicative operation.
     */

    @Override
    public Integer visitMultiplicativeExpression(exprParser.MultiplicativeExpressionContext ctx) {
        if(ctx.MUL().isEmpty() && ctx.DIV().isEmpty()){
            return visit(ctx.unaryExpression(0));
        }
        indent();
        System.out.println("MultiplicativeExpression: ");
        indentLevel++;
        int left = visit(ctx.unaryExpression(0));
        for (int i = 1; i < ctx.unaryExpression().size(); i++) {
            int right = visit(ctx.unaryExpression(i));
            if (ctx.MUL(i - 1) != null) {
                left *= right;
            } else if (ctx.DIV(i - 1) != null) {
                if (right == 0) {
                    throw new RuntimeException("Division by zero");
                }
                left /= right;
            }
        }
        indentLevel--;
        return left;
    }

    /**
     * Visits unary expression.
     * @param ctx the parse tree
     * @return result of visit(ctx.primaryExpression() if no unary operator present, else returns
     * result of unary operation.
     */
    @Override
    public Integer visitUnaryExpression(exprParser.UnaryExpressionContext ctx) {
        if(ctx.NOT()==null){
            return visit(ctx.primaryExpression());
        }
        indent();
        System.out.println("UnaryExpression: " + (ctx.NOT() != null ? "!" : ""));
        indentLevel++;
        if (ctx.NOT() != null) {
            int value = visit(ctx.unaryExpression());
            return (value == 0) ? 1 : 0;
        } else {
            return visit(ctx.primaryExpression());
        }
    }

    /**
     * visits primary expression
     * @param ctx the parse tree
     * @return value of primary expression based on type. If left parenthesis is found, the expression inside
     * is visited and value is returned.
     */
    @Override
    public Integer visitPrimaryExpression(exprParser.PrimaryExpressionContext ctx) {
        if (ctx.INT() != null) {
            indent();
            System.out.println("Number: "+ctx.INT().getText());
            return Integer.parseInt(ctx.INT().getText());
        } else if (ctx.ID() != null) {
            String varName = ctx.ID().getText();
            if (!symbolTable.containsKey(varName)) {
                throw new RuntimeException("Error: Variable '" + varName + "' is not declared.");
            }
            indent();
            System.out.println("Identifier: "+ctx.ID().getText());
            return symbolTable.getOrDefault(varName, 0);
        } else if (ctx.TRUE() != null) {
            indent();
            System.out.println("Boolean: true");
            return 1;
        } else if (ctx.FALSE() != null) {
            indent();
            System.out.println("Boolean: false");
            return 0;
        } else if (ctx.LPAREN() != null) {
            return visit(ctx.expression());
        }
        throw new RuntimeException("Invalid primary expression");
    }
}
