package Codegen.IR.Node.IRStat;

import Codegen.IR.Node.IRBlock.BasicBlock;
import Codegen.IR.Node.IRValue.IRValue;

public class IRBranch extends IRStat
{
    public IRValue cond;
    public BasicBlock true_block, false_block;

    public IRBranch (IRValue cond, BasicBlock true_block, BasicBlock false_block)
    {
        super();
        this.cond = cond;
        this.true_block = true_block;
        this.false_block = false_block;
    }

    @Override public String toString()
    {
        return "br i1 " + this.cond.toString() + ", "
             + "label %" + this.true_block.tag + ", "
             + "label %" + this.false_block.tag;
    }
}
