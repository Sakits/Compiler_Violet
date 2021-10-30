package Semantic.AST.Node;

public class PrefixNode extends ExprNode
{
    public String op;
    public ExprNode obj;

    public PrefixNode(position pos, String op, ExprNode obj)
    {
        super(pos);
        this.op = op;
        this.obj = obj;
    }
}
