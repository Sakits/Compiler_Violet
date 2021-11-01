package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class AddrExprNode extends ExprNode
{
    public ExprNode ptr, offset;

    public AddrExprNode(position pos, ExprNode ptr, ExprNode offset)
    {
        super(pos);
        this.ptr = ptr;
        this.offset = offset;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
