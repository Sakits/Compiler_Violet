package Semantic.AST.Node;

public class ReturnNode extends StNode
{
    public ExprNode expr;

    public ReturnNode(position pos, ExprNode expr)
    {
        super(pos);
        this.expr = expr;
    }
}
