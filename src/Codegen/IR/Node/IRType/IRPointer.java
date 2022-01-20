package Codegen.IR.Node.IRType;

public class IRPointer extends IRType
{
    public int dim;
    public IRType basic_type;
    public String s;

    public IRPointer(int dim, IRType basic_type)
    {
        super("", 32);
        if (basic_type instanceof IRPointer)
        {
            this.dim = ((IRPointer)basic_type).dim + dim;
            this.basic_type = ((IRPointer)basic_type).basic_type;
        }
        else 
        {
            this.dim = dim;
            this.basic_type = basic_type;
        }
        s = this.basic_type.toString();
        for (int i = 0; i < this.dim; i++)
            s += "*";
    }

    @Override public String toString() 
    {
        return s;
    }
}
