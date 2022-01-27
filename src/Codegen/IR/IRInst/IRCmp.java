package Codegen.IR.IRInst;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.IRValue.IRValue;

public class IRCmp extends IRInst
{
    public enum cmp_op_type {eq, ne, sgt, sge, slt, sle};
    public cmp_op_type op;
    public IRValue dest, lhs, rhs;

    public IRCmp(IRValue dest, cmp_op_type op, IRValue lhs, IRValue rhs)
    {
        super();
        this.dest = dest;
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public String toString()
    {
        return dest.toString() + " = cmp "
             + op.toString() + " "
             + lhs.toString() + ", "
             + rhs.toString();
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
