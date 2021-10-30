package Semantic.AST.Node;

import java.util.ArrayList;

public class RootNode extends ASTNode
{
    public ArrayList<FuncNode> func = new ArrayList<>();
    public ArrayList<VarNode> var = new ArrayList<>();
    public ArrayList<ClassNode> cls = new ArrayList<>();

    public RootNode(position pos)
    {
        super(pos);
    }
}
