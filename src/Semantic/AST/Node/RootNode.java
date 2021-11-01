package Semantic.AST.Node;

import java.util.ArrayList;
import Semantic.AST.ASTVisitor;
import Utils.position;

public class RootNode extends ASTNode
{
    public ArrayList<FuncDefNode> func = new ArrayList<>();
    public ArrayList<VarDefNode> var = new ArrayList<>();
    public ArrayList<ClassDefNode> cls = new ArrayList<>();

    public RootNode(position pos)
    {
        super(pos);
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
