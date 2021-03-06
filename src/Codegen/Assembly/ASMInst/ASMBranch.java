package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMBlock.ASMBlock;
import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMBranch extends ASMInst
{
    public enum branch_op_type
    {
        beq, bne, blt, bge, ble, bgt,
        beqz, bnez, bltz, bgez, blez, bgtz
    }

    public branch_op_type op;
    public ASMReg rs1, rs2;
    public ASMBlock dest_block;

    public ASMBranch(branch_op_type op, ASMReg rs1, ASMReg rs2, ASMBlock dest_block)
    {
        super();
        this.op = op;
        this.rs1 = rs1;
        this.rs2 = rs2;
        this.dest_block = dest_block;

        use.add(rs1);
        if (rs2 != null)
            use.add(rs2);
    }

    public void change(VirReg vir, ASMReg phy)
    {
        if (rs1 == vir)
            rs1 = phy;
        if (rs2 == vir)
            rs2 = phy;

        use.remove(vir);
        use.add(phy);
    }

    public String toString()
    {
        return op.toString() + "\t" 
             + rs1.toString() + ", "
             + (rs2 != null ? rs2.toString() + ", " : "")
             + dest_block.toString();
    }
}
