package Codegen.IR.IRBlock;

import java.util.ArrayList;

import Codegen.Assembly.ASMBuilder;
import Codegen.Assembly.ASMBlock.ASMBlock;
import Codegen.IR.IRInst.IRInst;

public class IRBlock 
{
    public ArrayList<IRInst> irst = new ArrayList<>();
    public String tag;
    public boolean is_returned = false;

    // Codegen
    public ASMBlock block;

    public IRBlock(String tag)
    {
        this.tag = tag;
    }

    public String toString()
    {
        return this.tag + ":";
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
