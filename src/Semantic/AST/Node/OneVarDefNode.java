package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class OneVarDefNode extends ASTNode
{
    public String idt, type;
    public int dim;
    public ExprNode init_val;

    public OneVarDefNode(position pos, String idt, ExprNode init_val)
    {
        super(pos);
        this.idt = idt;
        this.init_val = init_val;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
