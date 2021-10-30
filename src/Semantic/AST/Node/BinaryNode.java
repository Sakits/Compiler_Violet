package Semantic.AST.Node;

public class BinaryNode extends ExprNode
{
    public String op;
    public ExprNode lhs, rhs;

    public BinaryNode(position pos, String op, ExprNode lhs, ExprNode rhs)
    {
        super(pos);
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
