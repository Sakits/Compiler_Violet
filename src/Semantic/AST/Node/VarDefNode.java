package Semantic.AST.Node;

import java.util.ArrayList;
import Semantic.AST.SemanticChecker;

public class VarDefNode extends StNode
{
    public String type;
    public int dim;
    public ArrayList<OneVarDefNode> var = new ArrayList<>();

    public VarDefNode(position pos, String type, int dim)
    {
        super(pos);
        this.type = type;
        this.dim = dim;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
