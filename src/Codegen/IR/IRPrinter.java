package Codegen.IR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import Codegen.IR.Node.IRBlock.BasicBlock;
import Codegen.IR.Node.IRBlock.Function;
import Codegen.IR.Node.IRBlock.IRGlobal;

public class IRPrinter
{
    public OutputStream fout;
    public PrintWriter printer;

    public IRPrinter(String file_name, IRGlobal global)
    {
        try 
        {
            File file = new File(file_name);
            assert file.exists() || file.createNewFile();
            
            fout = new FileOutputStream(file_name, false);
            printer = new PrintWriter(fout);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        visit(global);

        try 
        {
            printer.close();
            fout.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public void visit(IRGlobal global)
    {
        // System.out.println(global.funcs.size());
        global.classes.forEach(i -> printer.println(i.declare()));
        global.strs.forEach(i -> printer.println(i.declare()));
        global.vars.forEach(i -> printer.println(i.declare()));
        global.funcs.forEach(i -> {
            if (i.is_builtin)
                printer.println(i.declare(true));
        });
        global.funcs.forEach(i -> {
            if (!i.is_builtin)
                visit(i);
        });
    }

    public void visit(Function func)
    {
        printer.println(func.declare(false));
        printer.println("{");

        func.blocks.forEach(i -> visit(i));

        printer.println("}");
    }

    public void visit(BasicBlock block)
    {
        printer.println(block.toString());
        block.irst.forEach(i -> {
            // System.out.println(i.toString());
            printer.println("\t" + i.toString());
        });
    }
}
