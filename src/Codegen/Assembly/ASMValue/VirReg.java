package Codegen.Assembly.ASMValue;

public class VirReg extends ASMReg
{
    public String name;
    public ASMAddr addr;

    // RegAlloc
    public PhyReg phy_reg = null;

    public VirReg(String name)
    {
        super();
        this.name = name;
        this.addr = new ASMAddr();
    }

    public String toString() 
    {
        return phy_reg != null ? phy_reg.toString() : "(vir)" + name;
    }
}
