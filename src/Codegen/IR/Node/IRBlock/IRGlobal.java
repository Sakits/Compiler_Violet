package Codegen.IR.Node.IRBlock;

import java.util.ArrayList;

import Codegen.IR.Node.IRType.IRClass;
import Codegen.IR.Node.IRValue.ConstString;
import Codegen.IR.Node.IRValue.Register;

public class IRGlobal 
{
    public ArrayList<Register> vars = new ArrayList<>();
    public ArrayList<ConstString> strs = new ArrayList<>();
    public ArrayList<Function> funcs = new ArrayList<>();
    public ArrayList<IRClass> classes = new ArrayList<>();
    
    public IRGlobal()
    {

    }
}
