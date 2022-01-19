package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMReg;

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
    }

    public String toString()
    {
        return op.toString() + "\t" + rd.toString() + ", " + addr.toString();
    }
}
