package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class IfStNode extends StNode
{
    public ExprNode cond;
    public StNode true_st, false_st = null;

    public IfStNode(position pos, ExprNode cond, StNode true_st, StNode false_st)
    {
        super(pos);
        this.cond = cond;
        this.true_st = true_st;
        this.false_st = false_st;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
