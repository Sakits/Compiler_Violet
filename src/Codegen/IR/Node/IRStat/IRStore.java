package Codegen.IR.Node.IRStat;

import Codegen.IR.IRVisitor;
import Codegen.IR.Node.IRValue.IRValue;

public class IRStore extends IRStat
{
    public IRValue ptr, val;

    public IRStore(IRValue ptr, IRValue val)
    {
        super();
        this.ptr = ptr;
        this.val = val;
    }

    public String toString()
    {
        return "store " + val.type.toString() + " " + val.toString() + ", "
             + ptr.type.toString() + " " + ptr.toString();
    }
    
    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
