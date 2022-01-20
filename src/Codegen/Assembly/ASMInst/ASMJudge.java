package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMJudge extends ASMInst
{
    public enum judge_op_type {seqz, snez, sltz, sgtz}

    public judge_op_type op;
    public ASMReg rd, rs;

    public ASMJudge(judge_op_type op, ASMReg rd, ASMReg rs)
    {
        super();
        this.op = op;
        this.rd = rd;
        this.rs = rs;

        read_reg.add(rs);
        write_reg.add(rd);
    }

    public void change(VirReg vir, PhyReg phy) 
    {
        if (rd == vir)
            rd = phy;
        if (rs == vir)
            rs = phy;
    }

    public String toString()
    {
        return op.toString() + "\t" + rd.toString() + ", " + rs.toString();
    }
}
