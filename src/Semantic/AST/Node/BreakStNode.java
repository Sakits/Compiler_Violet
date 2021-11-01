package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class BreakStNode extends StNode
{
    public BreakStNode(position pos)
    {
        super(pos);
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
