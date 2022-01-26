package Codegen.Assembly.ASMValue;

public class ASMAddr 
{
    public ASMReg base;
    public Immediate offset;

    public ASMAddr()
    {
        this.base = PhyReg.phy_regs.get("sp");
        this.offset = null;
    }
    
    public ASMAddr(ASMReg base, Immediate offset)
    {
        this.base = base;
        this.offset = offset;
    }

    public String toString()
    {
        // assert offset != null;
        if (offset == null)
            return "wait";
        return offset.toString() + "(" + base.toString() + ")";
    }
}
