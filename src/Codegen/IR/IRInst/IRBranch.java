package Codegen.IR.IRInst;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.IRBlock.IRBlock;
import Codegen.IR.IRValue.IRValue;

public class IRBranch extends IRInst
{
    public IRValue cond;
    public IRBlock true_block, false_block;

    public IRBranch (IRValue cond, IRBlock true_block, IRBlock false_block)
    {
        super();
        this.cond = cond;
        this.true_block = true_block;
        this.false_block = false_block;
    }

    @Override public String toString()
    {
        return "br " + this.cond.toString() + ", "
             + this.true_block.tag + ", "
             + this.false_block.tag;
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
