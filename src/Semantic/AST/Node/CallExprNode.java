package Semantic.AST.Node;

import java.util.ArrayList;
import Semantic.AST.SemanticChecker;

public class CallExprNode extends ExprNode
{
    public ExprNode obj;
    public ArrayList<ExprNode> para = new ArrayList<>();

    public CallExprNode(position pos, ExprNode obj)
    {
        super(pos);
        this.obj = obj;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
