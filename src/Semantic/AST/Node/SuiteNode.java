package Semantic.AST.Node;

import java.util.ArrayList;
import Semantic.AST.SemanticChecker;

public class SuiteNode extends StNode
{
    public ArrayList<StNode> st = new ArrayList<>();

    public SuiteNode(position pos)
    {
        super(pos);
    }

    public void accept(SemanticChecker sc)
    {
        sc.visit(this);
    }
}
