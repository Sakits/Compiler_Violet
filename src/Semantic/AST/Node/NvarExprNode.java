package Semantic.AST.Node;

import java.util.ArrayList;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class NvarExprNode extends ExprNode
{
    public ArrayList<ExprNode> expr = new ArrayList<>();
    public ClassDefNode cls = null;

    public NvarExprNode(position pos, String type, int dim)
    {
        super(pos);
        this.type = type;
        this.dim = dim;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
