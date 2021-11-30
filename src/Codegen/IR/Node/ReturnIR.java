package Codegen.IR.Node;

public class ReturnIR extends BaseIR
{
    public IRValue val;
    
    public ReturnIR(IRValue val)
    {
        super();
        this.val = val;
    }
}
