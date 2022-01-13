package Codegen.IR.Node.IRStat;

import Codegen.IR.IRVisitor;
import Codegen.IR.Node.IRType.IRType;
import Codegen.IR.Node.IRValue.IRValue;

public class IRLoad extends IRStat
{
    public IRType type;
    public IRValue dest, ptr;

    public IRLoad(IRValue dest, IRValue ptr)
    {
        super();
        this.dest = dest;
        this.type = dest.type;
        this.ptr = ptr;
    }

    public String toString()
    {
        return dest.toString() + " = load "
             + type.toString() + ", "
             + ptr.type.toString() + " " + ptr.toString();
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
