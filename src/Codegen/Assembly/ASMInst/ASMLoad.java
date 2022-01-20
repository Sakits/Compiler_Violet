package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMLoad extends ASMInst
{
    public enum load_op_type {lb, lw}
    
    public load_op_type op;
    public ASMReg rd;
    public ASMAddr addr;

    public ASMLoad(load_op_type op, ASMReg rd, ASMAddr addr)
    {
        super();
        this.op = op;
        this.rd = rd;
        this.addr = addr;

        write_reg.add(rd);
        read_reg.add(addr.base);
    }

    public void change(VirReg vir, PhyReg phy) 
    {
        if (rd == vir)
            rd = phy;
        if (addr.base == vir)
            addr.base = phy;
    }

    public String toString()
    {
        return op.toString() + "\t\t" + rd.toString() + ", " + addr.toString();
    }
}
