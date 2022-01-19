package Codegen.Assembly.ASMBlock;

import java.util.ArrayList;

import Codegen.Assembly.ASMValue.ASMHeapAddr;

public class ASMGlobal 
{
    public ArrayList<ASMFunc> funcs = new ArrayList<>();
    public ArrayList<ASMHeapAddr> vars = new ArrayList<>();
}
