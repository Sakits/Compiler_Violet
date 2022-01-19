package Semantic.Checker;

import java.util.HashMap;

import Semantic.AST.Node.OneVarDefNode;

public class Scope 
{
    public HashMap<String, OneVarDefNode> vars = new HashMap<>();
    public Scope fa_scope;

    public Scope(Scope fa_scope)
    {
        this.fa_scope = fa_scope;
    }

    public void add_var(String idt, OneVarDefNode var)
    {
        // System.out.println("insert : " + idt);
        vars.put(idt, var);
    }

    public OneVarDefNode get_var(String idt, boolean goup)
    {
        if (vars.containsKey(idt))
            return vars.get(idt);
        if (goup && fa_scope != null)
            return fa_scope.get_var(idt, goup);
        return null;
    }
}
