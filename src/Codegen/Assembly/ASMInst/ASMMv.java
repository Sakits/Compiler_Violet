package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;

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

    public String toString()
    {
        return "mv\t" + rd.toString() + ", " + rs.toString();
    }
}
