import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStreams;
// import org.antlr.v4.semantics.SymbolCollector;

import Semantic.AST.ASTBuilder;
import Semantic.AST.Node.RootNode;
import Semantic.Grammar.*;
// import Semantic.AST.Utils.globalScope;


public class Main
{
    public static void main(String[] args) throws Exception
    {
        String name = "test.mx";
        InputStream input = new FileInputStream(name);
        // InputStream input = System.in;

        // try
        // {
            
            // GlobalScope gscope = new globalScope(null);

            MxstarLexer lexer = new MxstarLexer(CharStreams.fromStream(input));
            MxstarParser parser = new MxstarParser(new CommonTokenStream(lexer));
            ParseTree parse_tree_root = parser.program();
            ASTBuilder ast_builder = new ASTBuilder();
            RootNode ast_root = (RootNode)ast_builder.visit(parse_tree_root);
            // new SymbolCollector(gscope).visit(ast_root);
            // new SemanticChecker(gscope).visit(ast_root);
        // }
        // catch (error err) 
        // {
        //     System.err.println(err.toString());
        //     throw new RuntimeException();
        // }
        
    }
}