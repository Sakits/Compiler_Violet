package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class AddrExprNode extends ExprNode
{
    public ExprNode ptr, offset;

    public AddrExprNode(position pos, ExprNode ptr, ExprNode offset)
    {
        super(pos);
        this.ptr = ptr;
        this.offset = offset;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
