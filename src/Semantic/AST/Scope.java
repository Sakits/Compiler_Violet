package Semantic.AST;

import java.util.HashMap;

import Semantic.AST.Node.VarNode;
import Semantic.AST.Node.position;

public class Scope 
{
    public HashMap<String, VarNode> members = new HashMap<>();
    public Scope faScope = null;

    public Scope(Scope faScope)
    {
        this.faScope = faScope;
    }

    public void defVar(position pos, String idt, VarNode node)
    {
        if (members.containsKey(idt))
            throw new semanticError(pos, "variable redefine");
        members.put(idt, node);
    }

    public boolean haveVar(String idt, boolean goup)
    {
        if (members.containsKey(idt))
            return true;
        return (goup && faScope != null) ? faScope.haveVar(idt, goup) : false;
    }

    public VarNode getNode(String idt, boolean goup)
    {
        if (members.containsKey(idt))
            return members.get(idt);
        return (goup && faScope != null) ? faScope.getNode(idt, goup) : null;
    }
}
