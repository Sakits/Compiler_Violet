package Semantic.AST.Node;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class ObjExprNode extends ExprNode
{
    public ExprNode obj;
    public String idt;

    public ObjExprNode(position pos, ExprNode obj, String idt)
    {
        super(pos);
        this.obj = obj;
        this.idt = idt;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
