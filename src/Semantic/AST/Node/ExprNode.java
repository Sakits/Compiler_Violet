package Semantic.AST.Node;

import Utils.position;

public abstract class ExprNode extends ASTNode
{
    public String type;
    public int dim;
    public boolean is_left_val, tobe_left_val;
    public FuncDefNode is_func;
    public ClassDefNode is_class;

    public ExprNode(position pos)
    {
        super(pos);
        this.type = null;
        this.dim = 0;
        this.is_left_val = false;
        this.is_func = null;
        this.is_class = null;
        this.tobe_left_val = false;
    }
}
