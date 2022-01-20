package Codegen.Assembly.ASMInst;

import java.util.ArrayList;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

abstract public class ASMInst 
{
    public ArrayList<ASMReg> read_reg = new ArrayList<>();
    public ArrayList<ASMReg> write_reg = new ArrayList<>();

    public ASMInst() {}
    
    public void change(VirReg vir, PhyReg phy) {}

    public String toString() {return null;}
}
