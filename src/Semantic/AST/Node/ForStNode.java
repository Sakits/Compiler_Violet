package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class ForStNode extends StNode
{
    public VarDefNode var;
    public ExprNode init, cond, next;
    public StNode st;

    public ForStNode(position pos, VarDefNode var, ExprNode init, ExprNode cond, ExprNode next, StNode st)
    {
        super(pos);
        this.var = var;
        this.init = init;
        this.cond = cond;
        this.next = next;
        this.st = st;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
