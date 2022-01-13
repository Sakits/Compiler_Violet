package Codegen.IR.Node.IRType;

abstract public class IRType 
{
    public String name;
    public int size;
    
    public IRType (String name, int size)
    {
        this.name = name;
        this.size = size;
    }

    public String toString()
    {
        return size != 0 ? "i" + size : name;
    }

    public int get_size() 
    {
        return (size + 7) >> 3;
    }
}
