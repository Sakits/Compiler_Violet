package Semantic.AST.Node;

public class IfStNode extends StNode
{
    public ExprNode cond;
    public StNode trueSt, falseSt;

    public IfStNode(position pos, ExprNode cond, StNode trueSt, StNode falseSt)
    {
        super(pos);
        this.cond = cond;
        this.trueSt = trueSt;
        this.falseSt = falseSt;
    }
}
