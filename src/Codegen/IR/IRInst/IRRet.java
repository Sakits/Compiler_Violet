package Codegen.IR.IRInst;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.IRValue.IRValue;

public class IRRet extends IRInst
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
