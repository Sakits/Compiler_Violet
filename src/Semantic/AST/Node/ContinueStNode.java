package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class ContinueStNode extends StNode
{
    public ContinueStNode(position pos)
    {
        super(pos);
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
