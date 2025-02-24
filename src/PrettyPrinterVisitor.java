import java.util.HashMap;
import java.util.Map;

public class PrettyPrinterVisitor extends exprBaseVisitor<Integer>{
    private final Map<String, Integer> symbolTable = new HashMap<>();

    @Override
    public Integer visitProgram(exprParser.ProgramContext ctx) {
        System.out.println("=== Parse Tree Pretty Printer ===");
        // Visit all statements in the program
        for (exprParser.StatementContext statement : ctx.statement()) {
            visit(statement);
        }
        return null; // No return value for the program
    }

    @Override
    public Integer visitStatement(exprParser.StatementContext ctx) {
        // Handle declaration, assignment, or expression
        if (ctx.declaration() != null) {
            return visit(ctx.declaration());
        } else if (ctx.assignment() != null) {
            return visit(ctx.assignment());
        } else if (ctx.expression() != null) {
            return visit(ctx.expression());
        }
        throw new RuntimeException("Invalid statement");
    }

    @Override
    public Integer visitDeclaration(exprParser.DeclarationContext ctx) {
        // Declare a variable and initialize it to 0
        String varName = ctx.ID().getText();
        symbolTable.put(varName, 0);
        System.out.println("Declaration: var " + varName);
        return null; // No return value for declaration
    }

    @Override
    public Integer visitAssignment(exprParser.AssignmentContext ctx) {
        // Evaluate the expression and assign its value to the variable
        String varName = ctx.ID().getText();
        int value = visit(ctx.expression());
        symbolTable.put(varName, value);
        System.out.println("Assignment: " + varName + " = " + value);
        return value;
    }

    @Override
    public Integer visitExpression(exprParser.ExpressionContext ctx) {
        // Evaluate the logical OR expression
        return visit(ctx.logicalOrExpression());
    }

    @Override
    public Integer visitLogicalOrExpression(exprParser.LogicalOrExpressionContext ctx) {
        // Evaluate the left operand
        int left = visit(ctx.logicalAndExpression());
        // If there are OR operators, evaluate the right operands
        for (int i = 0; i < ctx.OR().size(); i++) {
            int right = visit(ctx.logicalOrExpression(i));
            left = (left != 0 || right != 0) ? 1 : 0; // Logical OR
        }
        return left;
    }

    @Override
    public Integer visitLogicalAndExpression(exprParser.LogicalAndExpressionContext ctx) {
        // Evaluate the left operand
        int left = visit(ctx.equalityExpression().get(0));
        // If there are AND operators, evaluate the right operands
        for (int i = 0; i < ctx.AND().size(); i++) {
            int right = visit(ctx.equalityExpression(i));
            left = (left != 0 && right != 0) ? 1 : 0; // Logical AND
        }
        return left;
    }

    @Override
    public Integer visitEqualityExpression(exprParser.EqualityExpressionContext ctx) {
        // Evaluate the left operand
        int left = visit(ctx.relationalExpression().get(0));
        // If there are equality operators, evaluate the right operands
        for (int i = 0; i < ctx.EQ().size() + ctx.NEQ().size(); i++) {
            int right = visit(ctx.relationalExpression(i));
            if (ctx.EQ(i) != null) {
                left = (left == right) ? 1 : 0; // Equality
            } else if (ctx.NEQ(i) != null) {
                left = (left != right) ? 1 : 0; // Inequality
            }
        }
        return left;
    }

    @Override
    public Integer visitRelationalExpression(exprParser.RelationalExpressionContext ctx) {
        // Evaluate the left operand
        int left = visit(ctx.additiveExpression().get(0));
        // If there are relational operators, evaluate the right operands
        for (int i = 0; i < ctx.LT().size() + ctx.GT().size() + ctx.LE().size() + ctx.GE().size(); i++) {
            int right = visit(ctx.additiveExpression(i));
            if (ctx.LT(i) != null) {
                left = (left < right) ? 1 : 0; // Less than
            } else if (ctx.GT(i) != null) {
                left = (left > right) ? 1 : 0; // Greater than
            } else if (ctx.LE(i) != null) {
                left = (left <= right) ? 1 : 0; // Less than or equal
            } else if (ctx.GE(i) != null) {
                left = (left >= right) ? 1 : 0; // Greater than or equal
            }
        }
        return left;
    }

    @Override
    public Integer visitAdditiveExpression(exprParser.AdditiveExpressionContext ctx) {
        // Evaluate the left operand
        int left = visit(ctx.multiplicativeExpression().get(0));
        // If there are additive operators, evaluate the right operands
        for (int i = 1; i < ctx.multiplicativeExpression().size() + ctx.SUB().size(); i++) {
            int right = visit(ctx.multiplicativeExpression(i));
            if (ctx.ADD(i-1) != null) {
                System.out.println(left +" + "+ right);
                left += right; // Addition
            } else if (ctx.SUB(i-1) != null) {
                left -= right; // Subtraction
            }
        }
        return left;
    }

    @Override
    public Integer visitMultiplicativeExpression(exprParser.MultiplicativeExpressionContext ctx) {
        // Evaluate the left operand
        int left = visit(ctx.unaryExpression(0));
        // If there are multiplicative operators, evaluate the right operands
        for (int i = 1; i < ctx.unaryExpression().size(); i++) {
            int right = visit(ctx.unaryExpression(i));
            if (ctx.MUL(i - 1) != null) {
                System.out.println(left +" * "+right);
                left *= right; // Multiplication
            } else if (ctx.DIV(i - 1) != null) {
                if (right == 0) {
                    throw new RuntimeException("Division by zero");
                }
                left /= right; // Division
            }
        }
        return left;
    }

    @Override
    public Integer visitUnaryExpression(exprParser.UnaryExpressionContext ctx) {
        // Handle NOT operator or primary expression
        if (ctx.NOT() != null) {
            int value = visit(ctx.unaryExpression());
            return (value == 0) ? 1 : 0; // Logical NOT
        } else {
            return visit(ctx.primaryExpression());
        }
    }

    @Override
    public Integer visitPrimaryExpression(exprParser.PrimaryExpressionContext ctx) {
        // Handle literals, variables, or parenthesized expressions
        if (ctx.INT() != null) {
            return Integer.parseInt(ctx.INT().getText()); // Integer literal
        } else if (ctx.ID() != null) {
            String varName = ctx.ID().getText();
            return symbolTable.getOrDefault(varName, 0); // Variable lookup
        } else if (ctx.TRUE() != null) {
            return 1; // Boolean true
        } else if (ctx.FALSE() != null) {
            return 0; // Boolean false
        } else if (ctx.LPAREN() != null) {
            return visit(ctx.expression()); // Parenthesized expression
        }
        throw new RuntimeException("Invalid primary expression");
    }
}
