package Semantic.AST.Node;

import java.util.ArrayList;

public class FuncNode extends ASTNode
{
    public String returnType, idt;
    public ArrayList<VarNode> var = new ArrayList<>();
    public ArrayList<StNode> st = new ArrayList<>();
    public boolean isReturned = false;

    public FuncNode(position pos, String returnType, String idt)
    {
        super(pos);
        this.returnType = returnType;
        this.idt = idt;
    }
}
