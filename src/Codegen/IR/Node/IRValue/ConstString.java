package Codegen.IR.Node.IRValue;

import Codegen.IR.Node.IRType.IRType;

public class ConstString extends IRValue
{
    public String s;
    public int len;

    public ConstString(String s, int len, IRType type)
    {
        super(type);
        this.s = s;
        this.len = len;
    }

    @Override public String toString() 
    {
        return s;
    }

    public String declare()
    {
        String ans = toString() + " = private unnamed_addr constant ";
        ans += "[" + (len + 1) + " x i8]* ";
        ans += "c\"" + s + "\\00\"";
        return ans;
    }
}
