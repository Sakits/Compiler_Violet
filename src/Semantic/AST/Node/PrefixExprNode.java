package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

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

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
