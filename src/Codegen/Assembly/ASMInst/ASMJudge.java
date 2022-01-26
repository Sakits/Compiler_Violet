package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
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

        use.add(rs);
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
        if (rs == vir)
        {
            rs = phy;
            use.remove(vir);
            use.add(phy);
        }
    }

    public String toString()
    {
        return op.toString() + "\t" + rd.toString() + ", " + rs.toString();
    }
}
