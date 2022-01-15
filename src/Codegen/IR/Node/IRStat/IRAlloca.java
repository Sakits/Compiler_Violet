package Codegen.IR.Node.IRStat;

import Codegen.IR.Node.IRType.IRType;
import Codegen.IR.Node.IRValue.IRValue;

public class IRAlloca extends IRStat
{
    public IRValue dest;
    public IRType type;

    public IRAlloca(IRValue dest, IRType type)
    {
        super();
        this.dest = dest;
        this.type = type;
    }

    @Override public String toString()
    {
        return dest.toString() + " = alloca " + type.toString();
    }
}
