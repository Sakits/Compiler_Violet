package Codegen.IR.IRInst;

import Codegen.Assembly.ASMBuilder;

abstract public class IRInst 
{
    public IRInst()
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
