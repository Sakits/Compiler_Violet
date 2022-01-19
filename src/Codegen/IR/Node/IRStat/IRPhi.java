package Codegen.IR.Node.IRStat;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.Node.IRBlock.IRBlock;
import Codegen.IR.Node.IRValue.IRValue;

public class IRPhi extends IRStat
{
    public IRValue dest, src1, src2;
    public IRBlock block1, block2;

    public IRPhi(IRValue dest, IRValue src1, IRBlock block1, IRValue src2, IRBlock block2)
    {
        super();
        this.dest = dest;
        this.src1 = src1;
        this.block1 = block1;
        this.src2 = src2;
        this.block2 = block2;
    }

    public String toString()
    {
        return dest + " = phi i1 " 
             + "[" + src1.toString() + ", %" + block1.tag + "], "
             + "[" + src2.toString() + ", %" + block2.tag + "]"; 
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
