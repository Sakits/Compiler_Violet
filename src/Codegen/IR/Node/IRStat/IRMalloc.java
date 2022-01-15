package Codegen.IR.Node.IRStat;

import Codegen.IR.Node.IRValue.IRValue;

public class IRMalloc extends IRStat
{
    public IRValue dest, size;

    public IRMalloc(IRValue dest, IRValue size)
    {
        super();
        this.dest = dest;
        this.size = size;
    }

    public String toString()
    {
        return dest.toString() + " = call noalias i8* @malloc("
             + size.type.toString() + " " + size.toString() + ")";
    }

}
