package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class BinaryExprNode extends ExprNode
{
    public String op;
    public ExprNode lhs, rhs;

    public BinaryExprNode(position pos, String op, ExprNode lhs, ExprNode rhs)
    {
        super(pos);
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
