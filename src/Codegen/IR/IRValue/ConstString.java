package Codegen.IR.IRValue;

import Codegen.Assembly.ASMValue.ASMHeapAddr;
import Codegen.IR.IRType.IRType;

public class ConstString extends IRValue
{
    public String s;
    public int num;

    // Codegen
    public ASMHeapAddr addr = null;

    public ConstString(String s, IRType type, int num)
    {
        super(type);
        this.s = s.substring(1, s.length() - 2) + '\0';
        this.num = num;
    }

    @Override public String toString() 
    {
        return "@str_" + num;
    }

    public String declare()
    {
        String ans = "@str_" + num + " = global i8* ";
        ans += "\"" + s.replace("\\", "\\5C").replace("\n", "\\0A").replace("\"", "\\22").replace("\0", "\\00")+ "\"";
        return ans;
    }
}
