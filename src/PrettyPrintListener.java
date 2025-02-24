import java.util.ArrayDeque;
import java.util.Deque;

public class PrettyPrintListener extends exprBaseListener {
    private int indentLevel = 0;
    private final SymbolTable symbolTable;
    private final Deque<Integer> stack = new ArrayDeque<>();

    public PrettyPrintListener(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    private void indent() {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("  ");
        }
    }

    @Override
    public void enterProgram(exprParser.ProgramContext ctx) {
        System.out.println("=== Parse Tree Pretty Printer ===");
    }

    @Override
    public void enterDeclaration(exprParser.DeclarationContext ctx) {
        String varName = ctx.ID().getText();
        symbolTable.put(varName, 0);
        indent();
        System.out.println("Declaration: " + ctx.VAR().getText() + " " + varName);
    }

    @Override
    public void enterAssignment(exprParser.AssignmentContext ctx) {
        String ID = ctx.ID().getText();
        indent();
        System.out.println("Assignment: " + ID + " = ");
        indentLevel++;
    }

    @Override
    public void exitAssignment(exprParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        Object value = stack.pop();
        symbolTable.put(varName, value);
        indentLevel--;
    }

    @Override
    public void enterPrimaryExpression(exprParser.PrimaryExpressionContext ctx) {
        indent();
        if (ctx.INT() != null) {
            System.out.println("Number: " + ctx.INT().getText());
        } else if (ctx.ID() != null) {
            System.out.println("Identifier: " + ctx.ID().getText());
        } else if (ctx.TRUE() != null || ctx.FALSE() != null) {
            System.out.println("Boolean: " + (ctx.TRUE() != null ? ctx.TRUE().getText() : ctx.FALSE().getText()));
        } else if (ctx.LPAREN() != null) {
            System.out.println("Parenthesized Expression: ");
            indentLevel++;
        }
    }

    @Override
    public void exitPrimaryExpression(exprParser.PrimaryExpressionContext ctx) {
        if (ctx.INT() != null) {
            int value = Integer.parseInt(ctx.INT().getText());
            stack.push(value);
        } else if (ctx.TRUE() != null) {
            stack.push(1);
        } else if (ctx.FALSE() != null) {
            stack.push(0);
        } else if (ctx.ID() != null) {
            String varName = ctx.ID().getText();
            stack.push(symbolTable.getOrDefault(varName, 0));
        }else if (ctx.LPAREN() != null) {
            indentLevel--;
        }
    }

    @Override
    public void enterUnaryExpression(exprParser.UnaryExpressionContext ctx) {
        if (ctx.NOT() != null) {
            indent();
            System.out.println("UnaryExpression: " + ctx.NOT().getText());
            indentLevel++;
        }
    }

    @Override
    public void exitUnaryExpression(exprParser.UnaryExpressionContext ctx) {
        if (ctx.NOT() != null) {
            int value = stack.pop();
            stack.push(value == 1 ? 0 : 1);
            indentLevel--;
        }
    }

    @Override
    public void exitMultiplicativeExpression(exprParser.MultiplicativeExpressionContext ctx) {
        if (ctx.MUL() != null || ctx.DIV() != null) {
            int result = stack.pop();
            for (int i = 0; i < ctx.MUL().size() + ctx.DIV().size(); i++) {
                int nextOperand = stack.pop();
                if (ctx.MUL(i) != null) {
                    result *= nextOperand;
                } else if (ctx.DIV(i) != null) {
                    if (nextOperand == 0) {
                        throw new RuntimeException("Division by zero");
                    }
                    result /= nextOperand;
                }
            }
            stack.push(result);
        }
    }

    @Override
    public void exitAdditiveExpression(exprParser.AdditiveExpressionContext ctx) {
        if (ctx.ADD() != null || ctx.SUB() != null) {
            int result = stack.pop();
            for (int i = 0; i < ctx.ADD().size() + ctx.SUB().size(); i++) {
                int nextOperand = stack.pop();
                if (ctx.ADD(i) != null) {
                    result += nextOperand;
                } else if (ctx.SUB(i) != null) {
                    result -= nextOperand;
                }
            }
            stack.push(result);
            System.out.println(stack);
        }
    }

    @Override
    public void exitEqualityExpression(exprParser.EqualityExpressionContext ctx) {
        if (ctx.EQ() != null || ctx.NEQ() != null) {
            int right = stack.pop();
            int left = stack.pop();
            if (ctx.EQ() != null) stack.push(left == right ? 1 : 0);
            if (ctx.NEQ() != null) stack.push(left != right ? 1 : 0);
        }
    }

    @Override
    public void exitRelationalExpression(exprParser.RelationalExpressionContext ctx) {
        if (ctx.LT() != null || ctx.GT() != null || ctx.LE() != null || ctx.GE() != null) {
            int right = stack.pop();
            int left = stack.pop();
            if (ctx.LT() != null) stack.push(left < right ? 1 : 0);
            if (ctx.GT() != null) stack.push(left > right ? 1 : 0);
            if (ctx.LE() != null) stack.push(left <= right ? 1 : 0);
            if (ctx.GE() != null) stack.push(left >= right ? 1 : 0);
        }
    }

    @Override
    public void exitLogicalOrExpression(exprParser.LogicalOrExpressionContext ctx) {
        if (ctx.OR() != null) {
            int right = stack.pop();
            int left = stack.pop();
            stack.push((left != 0 || right != 0) ? 1 : 0);
        }
    }

    @Override
    public void exitLogicalAndExpression(exprParser.LogicalAndExpressionContext ctx) {
        if (ctx.AND() != null) {
            int right = stack.pop();
            int left = stack.pop();
            stack.push((left != 0 && right != 0) ? 1 : 0);
        }
    }

}