package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class ContinueStNode extends StNode
{
    public ContinueStNode(position pos)
    {
        super(pos);
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
