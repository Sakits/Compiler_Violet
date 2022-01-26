package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMMv extends ASMInst
{
    public ASMReg rd, rs;

    public ASMMv(ASMReg rd, ASMReg rs)
    {
        super();
        this.rd = rd;
        this.rs = rs;

        def.add(rd);
        use.add(rs);
    }

    public void change(VirReg vir, ASMReg phy)
    {
        if (rd == vir)
        {
            rd = phy;
            def.remove(vir);
            def.add(phy);
        }
        if (rs == vir)
        {
            rs = phy;
            use.remove(vir);
            use.add(phy);
        }
    }

    public String toString()
    {
        return "mv\t\t" + rd.toString() + ", " + rs.toString();
    }
}
