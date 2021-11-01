package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

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

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
