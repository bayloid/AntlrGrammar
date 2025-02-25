import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        boolean run = true;
        while (run) {
            try {
                System.out.println("Enter File Name: ");
                BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
                String fileName = r.readLine();
                CharStream charStream = CharStreams.fromFileName(fileName);

                exprLexer lexer = new exprLexer(charStream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                exprParser parser = new exprParser(tokens);

                ParseTree tree = parser.program();

                PrettyPrinterVisitor visitor = new PrettyPrinterVisitor();
                visitor.visit(tree);
                run = false;
            } catch (IOException e) {
                System.out.println("Error: Invalid file path, please try again. ");
            }
        }
    }
}
