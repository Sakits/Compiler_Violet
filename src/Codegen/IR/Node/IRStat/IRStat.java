package Codegen.IR.Node.IRStat;

import Codegen.IR.IRVisitor;

abstract public class IRStat 
{
    public IRStat()
    {

    }

    public String toString()
    {
        return null;
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
