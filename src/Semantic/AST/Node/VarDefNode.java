package Semantic.AST.Node;

import java.util.ArrayList;
import Semantic.AST.ASTVisitor;
import Utils.position;

public class VarDefNode extends StNode
{
    public String type;
    public int dim;
    public ArrayList<OneVarDefNode> var_list = new ArrayList<>();

    public VarDefNode(position pos, String type, int dim)
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
