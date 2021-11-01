package Semantic.Frontend;

import java.util.HashMap;

import Semantic.AST.Node.ClassDefNode;
import Semantic.AST.Node.FuncDefNode;

public class Symbols 
{
    public HashMap<String, FuncDefNode> funcs = new HashMap<>();
    public HashMap<String, ClassDefNode> types = new HashMap<>();

    public boolean is_used(String idt)
    {
        return funcs.containsKey(idt) || types.containsKey(idt);
    }

    public boolean func_is_used(String idt)
    {
        return funcs.containsKey(idt);
    }

    public boolean type_is_used(String idt)
    {
        return types.containsKey(idt);
    }

    public void add_func(String idt, FuncDefNode func)
    {
        funcs.put(idt, func);
    }

    public FuncDefNode get_func(String idt)
    {
        return funcs.containsKey(idt) ? funcs.get(idt) : null;
    }

    public void add_type(String idt, ClassDefNode cls)
    {
        types.put(idt, cls);
    }

    public ClassDefNode get_type(String idt)
    {
        return types.containsKey(idt) ? types.get(idt) : null;
    }
}
