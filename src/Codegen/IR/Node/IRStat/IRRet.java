package Codegen.IR.Node.IRStat;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.Node.IRValue.IRValue;

public class IRRet extends IRStat
{
    public IRValue val;

    public IRRet(IRValue val)
    {
        super();
        this.val = val;
    }

    public String toString()
    {
        return "ret " + val.toString();
    }

    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
