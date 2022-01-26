package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMReg;
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

        def.add(rd);
        use.add(addr.base);
    }

    public void change(VirReg vir, ASMReg phy)
    {
        if (rd == vir)
        {
            rd = phy;
            def.remove(vir);
            def.add(phy);
        }
        if (addr.base == vir)
        {
            addr.base = phy;
            use.remove(vir);
            use.add(phy);
        }
    }

    public String toString()
    {
        return op.toString() + "\t\t" + rd.toString() + ", " + addr.toString();
    }
}
