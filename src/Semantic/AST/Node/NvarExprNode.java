package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class NvarExprNode extends ExprNode
{
    public String type;
    public int dim;

    public NvarExprNode(position pos, String type, int dim)
    {
        super(pos);
        this.type = type;
        this.dim = dim;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}