package Codegen.IR.Node.IRStat;

import Codegen.IR.IRVisitor;
import Codegen.IR.Node.IRType.IRType;
import Codegen.IR.Node.IRValue.IRValue;

public class IRBitcast extends IRStat
{
    public IRType from, to;
    public IRValue dest, val;    

    public IRBitcast(IRValue dest, IRValue val)
    {
        super();
        this.dest = dest;
        this.val = val;
        this.from = dest.type;
        this.to = val.type;
    }

    @Override public String toString()
    {
        return dest.toString() + " = bitcast "
             + this.from.toString() + " "
             + this.val.toString() + " to "
             + this.to.toString();
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
