package Codegen.Assembly.ASMValue;

public class VirReg extends ASMReg
{
    public String name;
    public ASMAddr addr;


    public VirReg(String name)
    {
        super();
        this.name = name;
        this.addr = new ASMAddr();
    }

    public String toString() 
    {
        return "(vir)" + name;
    }
}
