package Semantic.AST.Node;

import java.util.ArrayList;
import Semantic.AST.ASTVisitor;
import Utils.position;

public class FuncDefNode extends ASTNode
{
    public String return_type, idt;
    public int dim;
    public ArrayList<FuncVarDefNode> var = new ArrayList<>();
    public StNode st;
    public boolean is_returned = false;

    public FuncDefNode(position pos, int dim, String return_type, String idt, StNode st)
    {
        super(pos);
        this.dim = dim;
        this.return_type = return_type;
        this.idt = idt;
        this.st = st;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
