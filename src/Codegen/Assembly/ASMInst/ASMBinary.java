package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.ASMValue;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMBinary extends ASMInst
{
    public enum binary_op_type 
    {
        add, sub, mul, div, rem, 
        sll, sra, and, or, xor, slt, 
        addi, slli, srai, andi, ori, xori, slti
    }

    public binary_op_type op;
    public ASMReg rd, rs1;
    public ASMValue rs2;

    public ASMBinary(binary_op_type op, ASMReg rd, ASMReg rs1, ASMValue rs2)
    {
        super();
        this.op = op;
        this.rd = rd;
        this.rs1 = rs1;
        this.rs2 = rs2;

        def.add(rd);
        use.add(rs1);
        if (rs2 instanceof ASMReg)
            use.add((ASMReg)rs2);
    }

    public void change(VirReg vir, ASMReg phy)
    {
        if (rd == vir)
        {
            rd = phy;
            def.remove(vir);
            def.add(phy);
        }
        if (rs1 == vir)
        {
            rs1 = phy;
            use.remove(vir);
            use.add(phy);
        }
        if (rs2 == vir)
        {
            rs2 = phy;
            use.remove(vir);
            use.add(phy);
        }
    }

    public String toString()
    {
        return op.toString() + "\t" + rd.toString() + ", " + rs1.toString() + ", " + rs2.toString();
    }
}
