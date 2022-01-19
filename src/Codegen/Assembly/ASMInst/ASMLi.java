package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.Immediate;

public class ASMLi extends ASMInst
{
    public ASMReg rd;
    public Immediate imm;

    public ASMLi(ASMReg rd, Immediate imm)
    {
        super();
        this.rd = rd;
        this.imm = imm;

        write_reg.add(rd);
    }

    public String toString()
    {
        return "li\t" + rd.toString() + ", " + imm.toString();
    }
}
