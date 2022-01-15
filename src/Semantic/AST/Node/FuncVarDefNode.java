package Semantic.AST.Node;

import Codegen.IR.Node.IRValue.Register;
import Semantic.AST.ASTVisitor;
import Utils.position;

public class FuncVarDefNode extends ASTNode
{
    public String type;
    public int dim;
    public OneVarDefNode one_var;
    public Register ptr, val;

    public FuncVarDefNode(position pos, String type, int dim, OneVarDefNode one_var)
    {
        super(pos);
        this.type = type;
        this.dim = dim;
        this.one_var = one_var;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
