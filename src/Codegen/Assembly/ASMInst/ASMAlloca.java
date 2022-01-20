package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMReg;

public class ASMAlloca extends ASMInst
{
    public ASMReg dest;
    public ASMAddr addr;

    public ASMAlloca(ASMReg dest, ASMAddr addr)
    {
        super();
        this.dest = dest;
        this.addr = addr;

        write_reg.add(dest);
    }
}
