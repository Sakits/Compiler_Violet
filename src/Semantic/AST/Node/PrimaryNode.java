package Semantic.AST.Node;

public class PrimaryNode extends ExprNode
{
    public String type, s;

    public PrimaryNode(position pos, String type, String s)
    {
        super(pos);
        this.type = type;
        this.s = s;
    }
}
