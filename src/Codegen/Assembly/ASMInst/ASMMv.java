package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMMv extends ASMInst
{
    public ASMReg rd, rs;

    public ASMMv(ASMReg rd, ASMReg rs)
    {
        super();
        this.rd = rd;
        this.rs = rs;

        write_reg.add(rd);
        read_reg.add(rs);
    }

    public void change(VirReg vir, PhyReg phy) 
    {
        if (rd == vir)
            rd = phy;
        if (rs == vir)
            rs = phy;
    }

    public String toString()
    {
        return "mv\t\t" + rd.toString() + ", " + rs.toString();
    }
}
