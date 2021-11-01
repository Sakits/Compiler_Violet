package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class ReturnNode extends StNode
{
    public ExprNode expr;

    public ReturnNode(position pos, ExprNode expr)
    {
        super(pos);
        this.expr = expr;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
