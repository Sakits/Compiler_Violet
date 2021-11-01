package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class PrimaryExprNode extends ExprNode
{
    public int cate;
    public String s;

    public PrimaryExprNode(position pos, int cate, String s)
    {
        super(pos);
        this.cate = cate;
        this.s = s;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
