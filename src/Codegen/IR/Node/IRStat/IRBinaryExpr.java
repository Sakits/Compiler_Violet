package Codegen.IR.Node.IRStat;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.Node.IRValue.IRValue;

public class IRBinaryExpr extends IRStat
{
    public IRValue dest;
    public enum binary_op_type {add, sub, mul, sdiv, srem, shl, ashr, and, or, xor};
    public binary_op_type op;
    public IRValue lhs, rhs;

    public IRBinaryExpr(IRValue dest, binary_op_type op, IRValue lhs, IRValue rhs)
    {
        super();
        this.dest = dest;
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override public String toString()
    {
        return dest.toString() + " = " 
             + op.toString() + " " + this.lhs.type.toString() + " "
             + this.lhs.toString() + ", " + this.rhs.toString();
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
