package Codegen.Assembly.ASMInst;

import Codegen.Assembly.ASMValue.ASMHeapAddr;
import Codegen.Assembly.ASMValue.ASMReg;

public class ASMLa extends ASMInst
{
    public ASMReg rd;
    public ASMHeapAddr rs;
    
    public ASMLa(ASMReg rd, ASMHeapAddr rs)
    {
        super();
        this.rd = rd;
        this.rs = rs;

        write_reg.add(rd);
    }

    public String toString()
    {
        return "la\t" + rd.toString() + ", " + rs.name;
    }
}
