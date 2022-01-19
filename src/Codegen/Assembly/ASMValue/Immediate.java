package Codegen.Assembly.ASMValue;

public class Immediate extends ASMValue
{
    public int val;

    public Immediate(int val)
    {
        super();
        this.val = val;
    }

    public String toString()
    {
        return Integer.toString(val);
    }
}
