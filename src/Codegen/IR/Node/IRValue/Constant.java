package Codegen.IR.Node.IRValue;

import Codegen.IR.Node.IRType.IRType;

public class Constant extends IRValue
{
    public int val;

    public Constant(IRType type, int val)
    {
        super(type);
        this.val = val;
    }

    @Override public String toString() 
    {
        return type.size != 0 ? Integer.toString(val) : "";
    }
}
