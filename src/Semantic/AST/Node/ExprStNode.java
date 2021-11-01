package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class ExprStNode extends StNode
{
    public ExprNode expr;

    public ExprStNode(position pos, ExprNode expr)
    {
        super(pos);
        this.expr = expr;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
