package Semantic.Frontend;

import java.util.HashMap;

import Semantic.AST.Node.VarDefNode;

public class Scope 
{
    public HashMap<String, VarDefNode> vars = new HashMap<>();
    public Scope fa_scope;

    public Scope(Scope fa_scope)
    {
        this.fa_scope = fa_scope;
    }

    public void add_var(String idt, VarDefNode var)
    {
        vars.put(idt, var);
    }

    public VarDefNode get_var(String idt)
    {
        return vars.containsKey(idt) ? vars.get(idt) : null;
    }
}
