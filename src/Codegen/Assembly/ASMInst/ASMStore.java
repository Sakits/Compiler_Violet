package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMStore extends ASMInst
{
    public enum store_op_type {sb, sw}
    
    public store_op_type op;
    public ASMReg rs;
    public ASMAddr addr;

    public ASMStore(store_op_type op, ASMReg rs, ASMAddr addr)
    {
        super();
        this.op = op;
        this.rs = rs;
        this.addr = addr;

        read_reg.add(rs);
        read_reg.add(addr.base);
    }

    public void change(VirReg vir, PhyReg phy) 
    {
        if (rs == vir)
            rs = phy;
        if (addr.base == vir)
            addr.base = phy;
    }

    public String toString()
    {
        return op.toString() + "\t\t" + rs.toString() + ", " + addr.toString();
    }
}
