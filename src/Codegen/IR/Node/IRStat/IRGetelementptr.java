package Codegen.IR.Node.IRStat;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.Node.IRType.IRPointer;
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
        assert ptr.type instanceof IRPointer;
    }

    public String toString()
    {
        IRPointer tmp = (IRPointer) ptr.type;
        return dest.toString() + " = getelementptr "
             + (new IRPointer(tmp.dim - 1, tmp.basic_type)).toString() + ", "
             + ptr.type.toString() + " " + ptr.toString() + ", "
             + (is_class ? "i32 0, " : "")
             + "i32 " + offset.toString();
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
