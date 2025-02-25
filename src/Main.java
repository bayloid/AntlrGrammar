import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
        CharStream charStream = CharStreams.fromFileName("src/test.txt");

        exprLexer lexer = new exprLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        exprParser parser = new exprParser(tokens);

        ParseTree tree = parser.program();

        PrettyPrinterVisitor visitor = new PrettyPrinterVisitor();
        visitor.visit(tree);
    }
}
