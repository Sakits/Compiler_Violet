package Codegen.Assembly.ASMValue;

public class ASMAddr 
{
    public ASMValue base;
    public Immediate offset;

    public ASMAddr()
    {
        this.base = new PhyReg("sp");
        this.offset = null;
    }
    
    public ASMAddr(ASMValue base, Immediate offset)
    {
        this.base = base;
        this.offset = offset;
    }

    public String toString()
    {
        assert offset != null;
        return offset.toString() + "(" + base.toString() + ")";
    }
}
