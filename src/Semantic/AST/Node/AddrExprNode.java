package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class AddrExprNode extends ExprNode
{
    public ExprNode obj, offset;

    public AddrExprNode(position pos, ExprNode obj, ExprNode offset)
    {
        super(pos);
        this.obj = obj;
        this.offset = offset;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
