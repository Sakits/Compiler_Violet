package Semantic.AST.Node;

import java.util.ArrayList;

public class ForNode extends StNode
{
    public ExprNode init, cond, next;
    public StNode st;
    public ArrayList<VarNode> var = new ArrayList<>();

    public ForNode(position pos, ExprNode init, ExprNode cond, ExprNode next, StNode st)
    {
        super(pos);
        this.init = init;
        this.cond = cond;
        this.next = next;
        this.st = st;
    }
}
