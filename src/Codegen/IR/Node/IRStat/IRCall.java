package Codegen.IR.Node.IRStat;

import java.util.ArrayList;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.Node.IRBlock.IRFunc;
import Codegen.IR.Node.IRValue.IRValue;

public class IRCall extends IRStat
{
    public IRValue dest = null;
    public IRFunc func = null;
    public ArrayList<IRValue> para = new ArrayList<>();

    public IRCall(IRValue dest, IRFunc func)
    {
        super();
        assert func != null;
        this.dest = dest;
        this.func = func;
    }

    @Override public String toString()
    {
        String s;
        if (dest != null && !func.return_type.name.equals("void"))
            s = dest.toString() + " = call ";
        else
            s = "call ";
        s += "@" + func.name;
        s += "(";
        for (int i = 0; i < para.size(); i++)
        {
            s += para.get(i).toString() 
            + (i == para.size() - 1 ? "" : ", ");
        }
        s += ")";
        return s;
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
