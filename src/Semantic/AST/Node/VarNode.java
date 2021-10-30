package Semantic.AST.Node;

import java.util.ArrayList;

public class VarNode extends ASTNode
{
    public int dim;
    public String type, idt;
    public ExprNode val;
    public ArrayList<ExprNode> size = new ArrayList<>();
    
    public VarNode(position pos, String type, String idt, int dim, ExprNode val)
    {
        super(pos);
        this.type = type;
        this.idt = idt;
        this.dim = dim;
        this.val = val;
    }
}