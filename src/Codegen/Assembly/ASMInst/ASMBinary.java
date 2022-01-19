package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.ASMValue;

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

        write_reg.add(rd);
        read_reg.add(rs1);
        if (rs2 instanceof ASMReg)
            read_reg.add((ASMReg)rs2);
    }

    public String toString()
    {
        return op.toString() + "\t" + rd.toString() + ", " + rs1.toString() + ", " + rs2.toString();
    }
}
