package Semantic.AST.Node;

public class SuffixNode extends ExprNode
{
    public String op;
    public ExprNode obj;

    public SuffixNode(position pos, String op, ExprNode obj)
    {
        super(pos);
        this.op = op;
        this.obj = obj;
    }
}
