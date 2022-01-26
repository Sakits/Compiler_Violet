package Codegen.Assembly.ASMBlock;

import java.util.ArrayList;

import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.VirReg;

public class ASMFunc 
{
    public String name;
    public ArrayList<ASMBlock> blocks = new ArrayList<>();  
    public VirReg ra = null;
    public ArrayList<VirReg> callees = new ArrayList<>();

    // StackFrame
    public ArrayList<ASMAddr> para = new ArrayList<>();
    public ArrayList< ArrayList<ASMAddr> > call = new ArrayList<>();
    public ArrayList<ASMAddr> alloca = new ArrayList<>();
    
    public ASMFunc(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return name;
    }
}
