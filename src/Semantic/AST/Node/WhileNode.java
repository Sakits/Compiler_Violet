package Semantic.AST.Node;

public class WhileNode extends StNode
{
    public ExprNode cond;
    public StNode st;

    public WhileNode(position pos, ExprNode cond, StNode st)
    {
        super(pos);
        this.cond = cond;
        this.st = st;
    }
}
