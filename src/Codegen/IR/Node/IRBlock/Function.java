package Codegen.IR.Node.IRBlock;

import java.util.ArrayList;

import Codegen.IR.IRVisitor;
import Codegen.IR.Node.IRType.IRType;
import Codegen.IR.Node.IRValue.IRValue;
import Codegen.IR.Node.IRValue.Register;

public class Function 
{
    public ArrayList<BasicBlock> blocks = new ArrayList<>();
    public ArrayList<IRValue> para = new ArrayList<>();
    public String name;
    public IRType return_type;
    public BasicBlock init_block;
    public Register thisptr = null;
    public Boolean is_builtin = false;

    public Function(IRType return_type, String name)
    {
        this.return_type = return_type;
        this.name = name;
        this.init_block = new BasicBlock(name + "_init");
        blocks.add(this.init_block);
    }

    public String toString()
    {
        String s = return_type.toString() + " @" + name;
        s += "(";
        for (int i = 0; i < para.size() - 1; i++)
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
            s += para.get(i).type.toString() + " " + para.get(i).toString() + (i != para.size() - 1 ? ", " : "");
        s += ")";
        return s;
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
