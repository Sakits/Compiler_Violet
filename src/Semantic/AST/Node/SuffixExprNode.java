package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class SuffixExprNode extends ExprNode
{
    public String op;
    public ExprNode obj;

    public SuffixExprNode(position pos, String op, ExprNode obj)
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
