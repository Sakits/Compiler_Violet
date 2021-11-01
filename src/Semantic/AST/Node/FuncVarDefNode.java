package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class FuncVarDefNode extends ASTNode
{
    public String type;
    public int dim;
    public OneVarDefNode one_var;

    public FuncVarDefNode(position pos, String type, int dim, OneVarDefNode one_var)
    {
        super(pos);
        this.type = type;
        this.dim = dim;
        this.one_var = one_var;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}