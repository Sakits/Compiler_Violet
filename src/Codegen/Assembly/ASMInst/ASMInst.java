package Codegen.Assembly.ASMInst;

import java.util.ArrayList;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.VirReg;

abstract public class ASMInst 
{
    public ArrayList<ASMReg> use = new ArrayList<>();
    public ArrayList<ASMReg> def = new ArrayList<>();

    public ASMInst() {}
    
    public void change(VirReg vir, ASMReg phy) {}

    public String toString() {return null;}
}
