package Codegen.IR.Node.IRStat;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.Node.IRValue.IRValue;

public class IRMove extends IRStat
{
    public IRValue dest, src;

    public IRMove(IRValue dest, IRValue src)
    {
        super();
        this.dest = dest;
        this.src = src;
    }

    public String toString()
    {
        return "move " + dest.toString() + ", " + src.toString();
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
