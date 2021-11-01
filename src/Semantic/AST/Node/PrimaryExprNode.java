package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

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

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
