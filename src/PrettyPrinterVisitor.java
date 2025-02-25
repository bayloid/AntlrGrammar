import java.util.HashMap;
import java.util.Map;

public class PrettyPrinterVisitor extends exprBaseVisitor<Integer>{
    private final Map<String, Integer> symbolTable = new HashMap<>();
    int indentLevel = 0;
    private void indent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("  ");
        }
    }

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

    @Override
    public Integer visitDeclaration(exprParser.DeclarationContext ctx) {
        String varName = ctx.ID().getText();
        symbolTable.put(varName, 0);
        indent();
        System.out.println("Declaration: var " + varName);
        return null;
    }

    @Override
    public Integer visitAssignment(exprParser.AssignmentContext ctx) {
        // Evaluate the expression and assign its value to the variable
        indent();
        System.out.println("Assignment: " + ctx.ID().getText() + " = ");
        indentLevel++;
        String varName = ctx.ID().getText();
        int value = visit(ctx.expression());
        symbolTable.put(varName, value);
        return value;
    }

    @Override
    public Integer visitExpression(exprParser.ExpressionContext ctx) {
        return visit(ctx.logicalOrExpression());
    }

    @Override
    public Integer visitLogicalOrExpression(exprParser.LogicalOrExpressionContext ctx) {
        if(ctx.OR().isEmpty()){
            return visit(ctx.logicalAndExpression());
        }
        indent();
        System.out.println("LogicalOrExpression: ");
        indentLevel++;
        int left = visit(ctx.logicalAndExpression().getChild(0));
        for (int i = 0; i < ctx.logicalAndExpression().AND().size(); i++) {
            int right = visit(ctx.logicalAndExpression().getChild(i+1));
            left = (left != 0 || right != 0) ? 1 : 0;
        }
        if (ctx.logicalOrExpression() != null) {
            indentLevel--;
        }
        return left;
    }

    @Override
    public Integer visitLogicalAndExpression(exprParser.LogicalAndExpressionContext ctx) {
        if(ctx.AND().isEmpty()){
            return visit(ctx.equalityExpression(0));
        }
        int left = visit(ctx.equalityExpression(0));
        // If there are AND operators, evaluate the right operands
        for (int i = 1; i < ctx.equalityExpression().size(); i++) {
            int right = visit(ctx.equalityExpression(i));
            left = (left != 0 && right != 0) ? 1 : 0; // Logical AND
        }
        return left;
    }

    @Override
    public Integer visitEqualityExpression(exprParser.EqualityExpressionContext ctx) {
        if(ctx.EQ().isEmpty()){
            return visit(ctx.relationalExpression(0));
        }
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
        if (ctx.LT().isEmpty() && ctx.GT().isEmpty() && ctx.LE().isEmpty() && ctx.GE().isEmpty()) {
            return visit(ctx.additiveExpression(0));
        }
        indent();
        System.out.println("RelationalExpression: ");
        indentLevel++;
        int left = visit(ctx.additiveExpression(0));
        // If there are relational operators, evaluate the right operands
        for (int i = 0; i < ctx.LT().size() + ctx.GT().size() + ctx.LE().size() + ctx.GE().size(); i++) {
            int right = visit(ctx.additiveExpression(i+1));
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
        if(ctx.ADD().isEmpty() && ctx.SUB().isEmpty()){
            return visit(ctx.multiplicativeExpression(0));
        }
        indent();
        System.out.println("AdditiveExpression: ");
        indentLevel++;
        int left = visit(ctx.multiplicativeExpression().get(0));
        // If there are additive operators, evaluate the right operands
        for (int i = 1; i < ctx.multiplicativeExpression().size() + ctx.SUB().size(); i++) {
            int right = visit(ctx.multiplicativeExpression(i));
            if (ctx.ADD(i-1) != null) {
                left += right; // Addition
            } else if (ctx.SUB(i-1) != null) {
                left -= right; // Subtraction
            }
        }
        indentLevel--;
        return left;
    }

    @Override
    public Integer visitMultiplicativeExpression(exprParser.MultiplicativeExpressionContext ctx) {
        if(ctx.MUL().isEmpty() && ctx.DIV().isEmpty()){
            return visit(ctx.unaryExpression(0));
        }
        indent();
        System.out.println("MultiplicativeExpression: ");
        indentLevel++;
        // Evaluate the left operand
        int left = visit(ctx.unaryExpression(0));
        // If there are multiplicative operators, evaluate the right operands
        for (int i = 1; i < ctx.unaryExpression().size(); i++) {
            int right = visit(ctx.unaryExpression(i));
            if (ctx.MUL(i - 1) != null) {
                left *= right; // Multiplication
            } else if (ctx.DIV(i - 1) != null) {
                if (right == 0) {
                    throw new RuntimeException("Division by zero");
                }
                left /= right; // Division
            }
        }
        indentLevel--;
        return left;
    }

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
            return (value == 0) ? 1 : 0; // Logical NOT
        } else {
            return visit(ctx.primaryExpression());
        }
    }

    @Override
    public Integer visitPrimaryExpression(exprParser.PrimaryExpressionContext ctx) {
        if (ctx.INT() != null) {
            indent();
            System.out.println("Number: "+ctx.INT().getText());
            return Integer.parseInt(ctx.INT().getText()); // Integer literal
        } else if (ctx.ID() != null) {
            String varName = ctx.ID().getText();
            if (!symbolTable.containsKey(varName)) {
                throw new RuntimeException("Error: Variable '" + varName + "' is not declared.");
            }
            indent();
            System.out.println("Identifier: "+ctx.ID().getText());
            return symbolTable.getOrDefault(varName, 0); // Variable lookup
        } else if (ctx.TRUE() != null) {
            indent();
            System.out.println("Boolean: true");
            return 1; // Boolean true
        } else if (ctx.FALSE() != null) {
            indent();
            System.out.println("Boolean: false");
            return 0; // Boolean false
        } else if (ctx.LPAREN() != null) {
            return visit(ctx.expression()); // Parenthesized expression
        }
        throw new RuntimeException("Invalid primary expression");
    }
}
