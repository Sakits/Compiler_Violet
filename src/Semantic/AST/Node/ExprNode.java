package Semantic.AST.Node;

public class ExprNode extends ASTNode
{
    public String type = null;
    public int dim = 0;
    public ExprNode(position pos)
    {
        super(pos);
    }
}
