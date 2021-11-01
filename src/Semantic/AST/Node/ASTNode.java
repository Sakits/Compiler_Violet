package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

abstract public class ASTNode
{
    public position pos;

    public ASTNode(position pos)
    {
        this.pos = pos;
    }

    abstract public void accept(SemanticChecker sc);
}