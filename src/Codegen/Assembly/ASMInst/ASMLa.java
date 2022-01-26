package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMHeapAddr;
import Codegen.Assembly.ASMValue.ASMReg;
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
        return "la\t\t" + rd.toString() + ", " + rs.name;
    }
}
