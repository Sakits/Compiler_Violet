package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class PrefixExprNode extends ExprNode
{
    public String op;
    public ExprNode obj;

    public PrefixExprNode(position pos, String op, ExprNode obj)
    {
        super(pos);
        this.op = op;
        this.obj = obj;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
