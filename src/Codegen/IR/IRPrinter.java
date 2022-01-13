package Codegen.IR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import Codegen.IR.Node.IRBlock.BasicBlock;
import Codegen.IR.Node.IRBlock.Function;
import Codegen.IR.Node.IRBlock.IRGlobal;
import Codegen.IR.Node.IRStat.*;

public class IRPrinter implements IRVisitor
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
                i.accept(this);
        });
    }

    public void visit(Function func)
    {
        printer.println(func.declare(false));
        printer.println("{");

        func.blocks.forEach(i -> i.accept(this));

        printer.println("}");
    }

    public void visit(BasicBlock block)
    {
        printer.println(block.toString());
        block.irst.forEach(i -> i.accept(this));
    }

    public void visit(IRStat now){}

    public void visit(IRAlloca now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRBinaryExpr now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRBitcast now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRBranch now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRCall now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRCmp now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRGetelementptr now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRJump now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRLoad now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRMalloc now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRPhi now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRRet now)
    {
        printer.println("\t" + now.toString());
    }
    public void visit(IRStore now)
    {
        printer.println("\t" + now.toString());
    }
}
