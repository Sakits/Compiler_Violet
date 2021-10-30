package Semantic.AST.Node;

public class AssignNode extends ExprNode
{
    public ExprNode dst, src;

    public AssignNode(position pos, ExprNode dst, ExprNode src)
    {
        super(pos);
        this.dst = dst;
        this.src = src;
    }
}
