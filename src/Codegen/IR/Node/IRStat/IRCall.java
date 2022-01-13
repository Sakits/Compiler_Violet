package Codegen.IR.Node.IRStat;

import java.util.ArrayList;

import Codegen.IR.IRVisitor;
import Codegen.IR.Node.IRBlock.Function;
import Codegen.IR.Node.IRValue.IRValue;

public class IRCall extends IRStat
{
    public IRValue dest;
    public Function func;
    public ArrayList<IRValue> para = new ArrayList<>();
    String s;

    public IRCall(IRValue dest, Function func)
    {
        super();
        this.dest = dest;
        this.func = func;
    }

    @Override public String toString()
    {
        if (dest != null)
            s = dest.toString() + " = call ";
        else
            s = "call ";
        s += func.return_type.toString() + " @" + func.name;
        s += "(";
        for (int i = 0; i < para.size() - 1; i++)
            s += para.get(i).type.toString() + " " 
            + para.get(i).toString() 
            + (i == para.size() - 1 ? "" : ", ");
        s += ")";
        return s;
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
