package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class AssignExprNode extends ExprNode
{
    public ExprNode lhs, rhs;

    public AssignExprNode(position pos, ExprNode lhs, ExprNode rhs)
    {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
