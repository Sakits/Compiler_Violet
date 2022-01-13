package Codegen.IR.Node.IRStat;

import Codegen.IR.Node.IRType.IRType;
import Codegen.IR.Node.IRValue.IRValue;

public class IRTrunc extends IRStat
{
    public IRType from, to;
    public IRValue dest, val;    

    public IRTrunc(IRValue dest, IRValue val, IRType from, IRType to)
    {
        super();
        this.dest = dest;
        this.val = val;
        this.from = from;
        this.to = to;
    }

    @Override public String toString()
    {
        return dest.toString() + " = trunc "
             + this.from.toString() + " "
             + this.val.toString() + " to "
             + this.to.toString();
    }
}