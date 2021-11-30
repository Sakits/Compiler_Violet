package Codegen.IR.Node;

public class Constant extends IRValue
{
    public int val;    

    public Constant (int val)
    {
        super();
        this.val = val;
    }
}
