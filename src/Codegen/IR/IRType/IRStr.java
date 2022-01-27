package Codegen.IR.IRType;

public class IRStr extends IRType
{
    public int len;

    public IRStr(int len)
    {
        super("", 32);
        this.len = len;
    }

    public String toString()
    {
        return "i8*";
    }
}
