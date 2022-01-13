package Codegen.IR.Node.IRStat;

import Codegen.IR.IRVisitor;
import Codegen.IR.Node.IRValue.IRValue;

public class IRGetelementptr extends IRStat
{
    public IRValue dest, ptr;
    public IRValue offset;
    public Boolean is_class = false;

    public IRGetelementptr(IRValue dest, IRValue ptr, IRValue offset, Boolean is_class)
    {
        super();
        this.dest = dest;
        this.ptr = ptr;
        this.offset = offset;
        this.is_class = is_class;
    }

    public String toString()
    {
        return dest.toString() + " = getelementptr "
             + ptr.toString() + ", "
             + (is_class ? "i32 0, " : "")
             + "i32 " + offset.toString();
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
