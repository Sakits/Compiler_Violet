package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class BraExprNode extends ExprNode
{
    public ExprNode expr;

    public BraExprNode(position pos, ExprNode expr)
    {
        super(pos);
        this.expr = expr;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
