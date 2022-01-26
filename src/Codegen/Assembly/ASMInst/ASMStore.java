package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMReg;
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

        use.add(rs);
        use.add(addr.base);
    }

    public void change(VirReg vir, ASMReg phy)
    {
        if (rs == vir)
            rs = phy;
        if (addr.base == vir)
            addr.base = phy;

        use.remove(vir);
        use.add(phy);
    }

    public String toString()
    {
        return op.toString() + "\t\t" + rs.toString() + ", " + addr.toString();
    }
}
