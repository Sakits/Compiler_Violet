package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

abstract public class ASTNode
{
    public position pos;

    public ASTNode(position pos)
    {
        this.pos = pos;
    }

    abstract public void accept(ASTVisitor visitor);
}