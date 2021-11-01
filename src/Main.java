import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;
// import org.antlr.v4.semantics.SymbolCollector;

import Semantic.AST.ASTBuilder;
import Semantic.AST.Node.RootNode;
import Semantic.Frontend.SemanticChecker;
import Semantic.Frontend.SymbolCollector;
import Semantic.Frontend.Symbols;
import Semantic.Grammar.*;
// import Semantic.AST.Utils.globalScope;


public class Main
{
    public static void main(String[] args) throws Exception
    {
        // String name = "test.mx";
        // InputStream input = new FileInputStream(name);
        InputStream input = System.in;

        try
        {
            MxstarLexer lexer = new MxstarLexer(CharStreams.fromStream(input));
            MxstarParser parser = new MxstarParser(new CommonTokenStream(lexer));
            ParseTree parse_tree_root = parser.program();
            ASTBuilder ast_builder = new ASTBuilder();
            RootNode ast_root = (RootNode)ast_builder.visit(parse_tree_root);
            Symbols symbols = new Symbols();
            new SymbolCollector(symbols).visit(ast_root);
            new SemanticChecker(symbols).visit(ast_root);
        }
        catch (Error err) 
        {
            System.err.println(err.toString());
            throw new RuntimeException();
        }
    }
}