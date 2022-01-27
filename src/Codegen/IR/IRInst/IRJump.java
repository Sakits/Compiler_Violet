package Codegen.IR.IRInst;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.IRBlock.IRBlock;

public class IRJump extends IRInst
{
    public IRBlock dest_block;

    public IRJump(IRBlock dest_block)
    {
        super();
        this.dest_block = dest_block;
    }

    public String toString()
    {
        return "jp " + dest_block.tag;
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
