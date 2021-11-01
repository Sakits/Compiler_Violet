package Semantic.AST.Node;

import Semantic.AST.SemanticChecker;

public class OneVarDefNode extends ASTNode
{
    public String idt;
    public ExprNode init_val;

    public OneVarDefNode(position pos, String idt, ExprNode init_val)
    {
        super(pos);
        this.idt = idt;
        this.init_val = init_val;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
