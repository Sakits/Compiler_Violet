package Codegen.IR.IRBlock;

import java.util.ArrayList;

import Codegen.Assembly.ASMBuilder;
import Codegen.Assembly.ASMBlock.ASMFunc;
import Codegen.IR.IRType.IRType;
import Codegen.IR.IRValue.IRValue;
import Codegen.IR.IRValue.Register;

public class IRFunc 
{
    public ArrayList<IRBlock> blocks = new ArrayList<>();
    public ArrayList<IRValue> para = new ArrayList<>();
    public String name;
    public IRType return_type;
    public Register thisptr = null;
    public Boolean is_builtin = false;

    // Codegen
    public ASMFunc func = null;

    public IRFunc(IRType return_type, String name)
    {
        this.return_type = return_type;
        this.name = name;
    }

    public String toString()
    {
        String s = return_type.toString() + " @" + name;
        s += "(";
        for (int i = 0; i < para.size(); i++)
            s += para.get(i).type.toString() + " " 
            + para.get(i).toString() 
            + (i == para.size() - 1 ? "" : ", ");
        s += ")";
        return s;
    }

    public String declare(boolean type)
    {
        String s = type ? "declare " : "define ";
        s += return_type.toString() + " @";
        s += name + "(";
        for (int i = 0; i < para.size(); i++)
            s += para.get(i).type.toString() + " " 
              + para.get(i).toString() 
              + (i != para.size() - 1 ? ", " : "");
        s += ")";
        return s;
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
