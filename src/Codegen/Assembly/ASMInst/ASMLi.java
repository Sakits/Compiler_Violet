package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.Immediate;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

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

    public void change(VirReg vir, PhyReg phy) 
    {
        if (rd == vir)
            rd = phy;
    }

    public String toString()
    {
        return "li\t\t" + rd.toString() + ", " + imm.toString();
    }
}
