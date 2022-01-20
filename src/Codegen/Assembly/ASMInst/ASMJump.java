package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMBlock.ASMBlock;

public class ASMJump extends ASMInst
{
    public ASMBlock dest_block;

    public ASMJump(ASMBlock dest_block)
    {
        super();
        this.dest_block = dest_block;
    }

    public String toString()
    {
        return "j\t\t" + dest_block.toString();
    }
}
