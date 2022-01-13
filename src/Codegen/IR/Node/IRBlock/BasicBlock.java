package Codegen.IR.Node.IRBlock;

import java.util.ArrayList;

import Codegen.IR.IRVisitor;
import Codegen.IR.Node.IRStat.IRStat;

public class BasicBlock 
{
    public ArrayList<IRStat> irst = new ArrayList<>();
    public String tag;
    public boolean is_returned = false;

    public BasicBlock(String tag)
    {
        this.tag = tag;
    }

    public String toString()
    {
        return this.tag + ":";
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
