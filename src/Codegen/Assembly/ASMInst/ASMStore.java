package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMReg;

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
    }

    public String toString()
    {
        return op.toString() + "\t" + rs.toString() + ", " + addr.toString();
    }
}
