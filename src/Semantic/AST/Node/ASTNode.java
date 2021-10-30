package Semantic.AST.Node;

abstract public class ASTNode
{
    public position pos;

    public ASTNode(position pos)
    {
        this.pos = pos;
    }
}