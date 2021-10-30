package Semantic.AST.Node;

public class ExprinNode extends ExprNode
{
    public ExprNode expr;
    public ExprinNode(position pos, ExprNode expr)
    {
        super(pos);
        this.expr = expr;
    }
}
