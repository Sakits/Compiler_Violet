package Codegen.IR.IRInst;

import Codegen.Assembly.ASMBuilder;
import Codegen.IR.IRValue.IRValue;

public class IRStore extends IRInst
{
    public IRValue ptr, val;

    public IRStore(IRValue ptr, IRValue val)
    {
        super();
        this.ptr = ptr;
        this.val = val;
    }

    public String toString()
    {
        return "store " + val.toString() + ", " + ptr.toString();
    }
    
    public void accept(ASMBuilder visitor)
    {
        visitor.visit(this);
    }
}
