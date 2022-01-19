package Codegen.Assembly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import Codegen.Assembly.ASMBlock.ASMBlock;
import Codegen.Assembly.ASMBlock.ASMFunc;
import Codegen.Assembly.ASMBlock.ASMGlobal;
import Codegen.Assembly.ASMValue.ASMHeapAddr.var_type;

public class ASMPrinter 
{
    public OutputStream fout;
    public PrintWriter printer;

    public ASMPrinter(String file_name, ASMGlobal global)
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

    public void visit(ASMGlobal global)
    {
        printer.println("\t.text");
        printer.println("");

        global.funcs.forEach(i -> visit(i));

        printer.println("");
        printer.println("\t.section\t.sdata,\"aw\",@progbits");

        global.vars.forEach(i ->
        {
            if (!(i.type.equals(var_type.glo_string)))
            {
                printer.println("\t.global\t" + i.name);
                printer.println("\t.p2align\t2");
            }

            printer.println("\t" + i.name + ":");
            printer.println("\t" + i.toString());
        });
    }

    public void visit(ASMFunc func)
    {
        printer.println("\t.globl\t" + func.name);
        printer.println("\t.p2align\t2");
        printer.println("\t.type\t" + func.name +",@function");

        printer.println(func.toString());

        func.blocks.forEach(i -> visit(i));

        printer.println("\t.size\t" + func.name + ", " + ".-" + func.name);
        // printer.println("");
    }

    public void visit(ASMBlock block)
    {
        printer.println(block.toString());
        block.asm_ins.forEach(i -> {
            printer.println("\t" + i.toString());
        });
    }
}
