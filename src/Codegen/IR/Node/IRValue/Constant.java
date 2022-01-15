package Codegen.IR.Node.IRValue;

import Codegen.IR.Node.IRType.IRPointer;
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
        if (type instanceof IRPointer) return "null";
        if (type.name.equals("void")) return "";
        return Integer.toString(val);
    }
}
