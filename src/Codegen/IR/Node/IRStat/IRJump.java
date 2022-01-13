package Codegen.IR.Node.IRStat;

import Codegen.IR.IRVisitor;
import Codegen.IR.Node.IRBlock.BasicBlock;

public class IRJump extends IRStat
{
    public BasicBlock dest_block;

    public IRJump(BasicBlock dest_block)
    {
        super();
        this.dest_block = dest_block;
    }

    public String toString()
    {
        return "br lable %" + dest_block.tag;
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
