package Codegen.Assembly.ASMValue;

import java.util.ArrayList;
import java.util.Arrays;

public class PhyReg extends ASMReg
{
    public static ArrayList<String> name_list = new ArrayList<>(Arrays.asList(
        "zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1",
        "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7",
        "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"
    ));

    public String name;

    public PhyReg(int pos)
    {
        super();
        this.name = name_list.get(pos);
    }
    
    public PhyReg(String name)
    {
        super();
        this.name = name;
    }

    public String toString() {return name;}
}
