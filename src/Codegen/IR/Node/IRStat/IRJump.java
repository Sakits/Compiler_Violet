package Codegen.IR.Node.IRStat;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.Node.IRBlock.IRBlock;

public class IRJump extends IRStat
{
    public IRBlock dest_block;

    public IRJump(IRBlock dest_block)
    {
        super();
        this.dest_block = dest_block;
    }

    public String toString()
    {
        return "br label %" + dest_block.tag;
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
