package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMBlock.ASMFunc;
import Codegen.Assembly.ASMValue.PhyReg;

public class ASMCall extends ASMInst
{
    public ASMFunc func;

    public ASMCall(ASMFunc func)
    {
        super();
        this.func = func;

        // Call 里可能用到所有 caller 寄存器，任何 liveness 跨过 call 的要么用 callee，要么 store
        for (int i = 0; i < 32; i++)
            if (PhyReg.type_list.get(i) == 0)
                def.add(PhyReg.phy_regs.get(PhyReg.name_list.get(i)));
    }

    public String toString()
    {
        return "call\t" + (func.toString().equals("__builtin_malloc") ? "malloc" : func.toString());
    }
}
