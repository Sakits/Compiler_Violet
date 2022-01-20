package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMHeapAddr;
import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMLa extends ASMInst
{
    public ASMReg rd;
    public ASMHeapAddr rs;
    
    public ASMLa(ASMReg rd, ASMHeapAddr rs)
    {
        super();
        this.rd = rd;
        this.rs = rs;

        write_reg.add(rd);
    }

    public void change(VirReg vir, PhyReg phy) 
    {
        if (rd == vir)
            rd = phy;
    }

    public String toString()
    {
        return "la\t\t" + rd.toString() + ", " + rs.name;
    }
}
