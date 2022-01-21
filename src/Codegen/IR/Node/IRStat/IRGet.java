package Codegen.IR.Node.IRStat;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.Node.IRType.IRPointer;
import Codegen.IR.Node.IRValue.IRValue;

public class IRGet extends IRStat
{
    public IRValue dest, ptr;
    public IRValue offset;
    public Boolean is_class = false;

    public IRGet(IRValue dest, IRValue ptr, IRValue offset, Boolean is_class)
    {
        super();
        this.dest = dest;
        this.ptr = ptr;
        this.offset = offset;
        this.is_class = is_class;
        assert ptr.type instanceof IRPointer;
    }

    public String toString()
    {
        return dest.toString() + " = get "
             + ptr.toString() + ", "
             + offset.toString();
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
