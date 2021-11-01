package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class BraExprNode extends ExprNode
{
    public ExprNode expr;

    public BraExprNode(position pos, ExprNode expr)
    {
        super(pos);
        this.expr = expr;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
