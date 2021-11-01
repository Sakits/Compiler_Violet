package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class SemiExprNode extends ExprNode
{
    public SemiExprNode(position pos)
    {
        super(pos);
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
