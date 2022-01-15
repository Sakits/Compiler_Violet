package Codegen.IR.Node.IRStat;

import Codegen.IR.Node.IRType.IRPointer;
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
        IRPointer type = (IRPointer)ptr.type;
        type = new IRPointer(type.dim - 1, type.basic_type);
        return "store " + type.toString() + " " + val.toString() + ", "
             + ptr.type.toString() + " " + ptr.toString();
    }
    
}
