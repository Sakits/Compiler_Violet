package Codegen.IR.IRValue;

import Codegen.Assembly.ASMValue.ASMHeapAddr;
import Codegen.IR.IRType.IRPointer;
import Codegen.IR.IRType.IRType;

public class Register extends IRValue
{
    public int num;
    public boolean is_global;
    public String idt;
    public Constant init_val = null;

    // Codegen
    public ASMHeapAddr addr;

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
        IRPointer pre_type = (IRPointer)type;
        pre_type = new IRPointer(pre_type.dim - 1, pre_type.basic_type);
        s += pre_type.toString() + " " + init_val.toString();
        return s;
    }
}
