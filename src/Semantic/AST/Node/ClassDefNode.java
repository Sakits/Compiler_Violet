package Semantic.AST.Node;

import java.util.ArrayList;

import Semantic.AST.SemanticChecker;

public class ClassDefNode extends ASTNode
{
    public String idt;
    public ArrayList<FuncDefNode> func = new ArrayList<>();
    public ArrayList<VarDefNode> var = new ArrayList<>();

    public ClassDefNode(position pos, String idt)
    {
        super(pos);
        this.idt = idt;
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
