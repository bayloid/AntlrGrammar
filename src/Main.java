import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SymbolTable symbolTable = new SymbolTable();

        CharStream charStream = CharStreams.fromFileName("src/test.txt");

        exprLexer lexer = new exprLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        exprParser parser = new exprParser(tokens);

        ParseTree tree = parser.program();
        ParseTreeWalker walker = new ParseTreeWalker();

        PrettyPrintListener printer = new PrettyPrintListener(symbolTable);

        walker.walk(printer,tree);
        symbolTable.print();
    }
}
