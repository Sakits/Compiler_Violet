package Codegen.IR.Node.IRBlock;

import java.util.ArrayList;

import Codegen.Assembly.ASMBuilder;
import Codegen.Assembly.ASMBlock.ASMBlock;
import Codegen.IR.Node.IRStat.IRStat;

public class IRBlock 
{
    public ArrayList<IRStat> irst = new ArrayList<>();
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
