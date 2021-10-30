package Semantic.AST.Node;

import java.util.ArrayList;

public class VarDefNode extends StNode
{
    public String type;
    public ArrayList<VarNode> var = new ArrayList<>();

    public VarDefNode(position pos, String type)
    {
        super(pos);
        this.type = type;
    }
}
