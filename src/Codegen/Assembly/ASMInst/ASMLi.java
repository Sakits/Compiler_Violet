package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.Immediate;
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

        def.add(rd);
    }

    public void change(VirReg vir, ASMReg phy)
    {
        if (rd == vir)
        {
            rd = phy;
            def.remove(vir);
            def.add(phy);
        }
    }

    public String toString()
    {
        return "li\t\t" + rd.toString() + ", " + imm.toString();
    }
}
