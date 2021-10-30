package Semantic.AST.Node;

public class AddrNode extends ExprNode
{
    public ExprNode ptr, offset;

    public AddrNode(position pos, ExprNode ptr, ExprNode offset)
    {
        super(pos);
        this.ptr = ptr;
        this.offset = offset;
    }
}
