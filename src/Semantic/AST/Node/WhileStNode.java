package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class WhileStNode extends StNode
{
    public ExprNode cond;
    public StNode st;

    public WhileStNode(position pos, ExprNode cond, StNode st)
    {
        super(pos);
        this.cond = cond;
        this.st = st;
    }

    public void accept(ASTVisitor visitor) 
    {
        visitor.visit(this);
    }
}
