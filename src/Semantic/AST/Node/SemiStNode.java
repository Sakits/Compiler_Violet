package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class SemiStNode extends StNode
{
    public SemiStNode(position pos)
    {
        super(pos);
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
