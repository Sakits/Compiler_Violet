package Codegen.Assembly.ASMInst;

import java.util.ArrayList;

import Codegen.Assembly.ASMValue.ASMReg;

abstract public class ASMInst 
{
    public ArrayList<ASMReg> read_reg = new ArrayList<>();
    public ArrayList<ASMReg> write_reg = new ArrayList<>();

    public ASMInst() {}

    public String toString() {return null;}
}
