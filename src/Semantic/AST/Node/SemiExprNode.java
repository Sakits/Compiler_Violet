package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class SemiExprNode extends ExprNode
{
    public SemiExprNode(position pos)
    {
        super(pos);
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
