package Codegen.Assembly.ASMBlock;

import java.util.ArrayList;

import Codegen.Assembly.ASMInst.ASMInst;

public class ASMBlock
{
    public String tag;
    public ArrayList<ASMInst> asm_ins = new ArrayList<>();
    public ArrayList<ASMBlock> succ = new ArrayList<>();

    public ASMBlock(String tag)
    {
        this.tag = tag;
    } 

    public String toString()
    {
        return tag;
    }
}