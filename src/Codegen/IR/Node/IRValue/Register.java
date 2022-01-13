package Codegen.IR.Node.IRValue;

import Codegen.IR.Node.IRType.IRType;

public class Register extends IRValue
{
    public int num;
    public boolean is_global;
    public String idt;
    public Constant init_val = null;

    public Register(IRType type, boolean is_global, String idt, int num)
    {
        super(type);
        this.num = num;
        this.is_global = is_global;
        this.idt = idt;
    }

    @Override public String toString()
    {
        return  (is_global ? "@" : "%") + idt + "_"  + num;
    }

    public String declare()
    {
        assert init_val != null;
        String s = toString();
        s += " = global ";
        s += type.toString() + " " + init_val.toString();
        return s;
    }
}
