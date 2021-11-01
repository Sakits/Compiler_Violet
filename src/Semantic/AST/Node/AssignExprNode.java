package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class AssignExprNode extends ExprNode
{
    public ExprNode lhs, rhs;

    public AssignExprNode(position pos, ExprNode lhs, ExprNode rhs)
    {
        super(pos);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
