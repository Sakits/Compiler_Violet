package Codegen.IR.IRInst;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.IRType.IRType;
import Codegen.IR.IRValue.IRValue;

public class IRLoad extends IRInst
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
        return dest.toString() + " = load " + ptr.toString();
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
