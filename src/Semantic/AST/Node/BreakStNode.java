package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class BreakStNode extends StNode
{
    public BreakStNode(position pos)
    {
        super(pos);
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
