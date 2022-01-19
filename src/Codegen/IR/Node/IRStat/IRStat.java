package Codegen.IR.Node.IRStat;

import Codegen.Assembly.ASMBuilder;

abstract public class IRStat 
{
    public IRStat()
    {

    }

    public String toString()
    {
        return null;
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
