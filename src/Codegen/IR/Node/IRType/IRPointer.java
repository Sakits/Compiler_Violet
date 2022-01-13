package Codegen.IR.Node.IRType;

public class IRPointer extends IRType
{
    public int dim;
    public IRType basic_type;
    public String s;

    public IRPointer(int dim, IRType basic_type)
    {
        super("", 32);
        this.dim = dim;
        this.basic_type = basic_type;
        s = basic_type.toString();
        for (int i = 0; i < dim; i++)
            s += "*";
    }

    @Override public String toString() 
    {
        return s;
    }
}
