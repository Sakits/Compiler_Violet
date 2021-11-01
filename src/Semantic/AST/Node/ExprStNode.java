package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class ExprStNode extends StNode
{
    public ExprNode expr;

    public ExprStNode(position pos, ExprNode expr)
    {
        super(pos);
        this.expr = expr;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
