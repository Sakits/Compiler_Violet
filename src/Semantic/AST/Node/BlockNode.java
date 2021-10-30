package Semantic.AST.Node;

import java.util.ArrayList;

public class BlockNode extends StNode
{
    public ArrayList<StNode> st = new ArrayList<>();

    public BlockNode(position pos)
    {
        super(pos);
    }
}
