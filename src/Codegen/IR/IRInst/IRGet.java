package Codegen.IR.IRInst;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.IRType.IRPointer;
import Codegen.IR.IRValue.IRValue;

public class IRGet extends IRInst
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
