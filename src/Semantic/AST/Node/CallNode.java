package Semantic.AST.Node;

import java.util.ArrayList;

public class CallNode extends ExprNode
{
    public ExprNode obj;
    public ArrayList<ExprNode> para = new ArrayList<>();

    public CallNode(position pos, ExprNode obj)
    {
        super(pos);
        this.obj = obj;
    }
}
