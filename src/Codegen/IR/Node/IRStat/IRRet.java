package Codegen.IR.Node.IRStat;

import Codegen.IR.IRVisitor;
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
        return "ret " + val.type.toString() + " " + val.toString();
    }

    public void accept(IRVisitor visitor)
    {
        visitor.visit(this);
    }
}
