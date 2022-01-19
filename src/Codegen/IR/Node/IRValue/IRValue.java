package Codegen.IR.Node.IRValue;

import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.IR.Node.IRType.IRType;

abstract public class IRValue 
{
    public IRType type;

    // Codegen
    public ASMReg val = null;

    public IRValue (IRType type) 
    {
        this.type = type;
    }

    public String toString()
    {
        return null;
    }
}
