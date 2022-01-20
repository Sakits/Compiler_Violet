import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import Codegen.Assembly.ASMBuilder;
import Codegen.Assembly.ASMPrinter;
import Codegen.Assembly.RegAlloc;
import Codegen.IR.IRBuilder;
import Codegen.IR.IRPrinter;

import org.antlr.v4.runtime.CharStreams;
// import org.antlr.v4.semantics.SymbolCollector;

import Semantic.AST.ASTBuilder;
import Semantic.AST.Node.RootNode;
import Semantic.Checker.SemanticChecker;
import Semantic.Checker.SymbolCollector;
import Semantic.Checker.Symbols;
import Semantic.Grammar.*;
// import Semantic.AST.Utils.globalScope;
import Utils.MxStarErrorListener;


public class Main
{
    public static void main(String[] args) throws Exception
    {
        String name = "test.mx";
        InputStream input = new FileInputStream(name);
        // InputStream input = System.in;

        try
        {
            boolean Semantic = false;
            if (args.length > 0) 
            {
                for (String arg : args)
                if (arg.equals("-Semantic"))
                    Semantic = true;
            }

            MxstarLexer lexer = new MxstarLexer(CharStreams.fromStream(input));
            lexer.removeErrorListeners();
            lexer.addErrorListener(new MxStarErrorListener());

            MxstarParser parser = new MxstarParser(new CommonTokenStream(lexer));
            parser.removeErrorListeners();
            parser.addErrorListener(new MxStarErrorListener());
            ParseTree parse_tree_root = parser.program();

            ASTBuilder ast_builder = new ASTBuilder();
            RootNode ast_root = (RootNode)ast_builder.visit(parse_tree_root);

            Symbols symbols = new Symbols();
            new SymbolCollector(symbols).visit(ast_root);
            new SemanticChecker(symbols).visit(ast_root);

            if (Semantic) return;

            IRBuilder ir_builder = new IRBuilder();
            ir_builder.visit(ast_root);
            // new IRPrinter("test.ll", ir_builder.global);

            ASMBuilder asm_builder = new ASMBuilder();
            asm_builder.visit(ir_builder.global);
            // new ASMPrinter("test.lts", asm_builder.global);

            new RegAlloc(asm_builder.global);
            new ASMPrinter("test.s", asm_builder.global);
        }
        catch (Error err) 
        {
            System.err.println(err.toString());
            throw new RuntimeException();
        }
    }
}