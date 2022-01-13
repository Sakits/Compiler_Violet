package Codegen.IR.Node.IRValue;

import Codegen.IR.Node.IRType.IRType;

abstract public class IRValue 
{
    public IRType type;

    public IRValue (IRType type) 
    {
        this.type = type;
    }

    public String toString()
    {
        return null;
    }
}
