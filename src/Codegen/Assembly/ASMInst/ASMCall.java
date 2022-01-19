package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMBlock.ASMFunc;

public class ASMCall extends ASMInst
{
    public ASMFunc func;

    public ASMCall(ASMFunc func)
    {
        super();
        this.func = func;

        
        // TODO: save caller
    }

    public String toString()
    {
        return "call\t" + (func.toString().equals("__builtin_malloc") ? "malloc" : func.toString());
    }
}
