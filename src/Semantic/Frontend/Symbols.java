package Semantic.Frontend;

import java.util.HashMap;

import Semantic.AST.Node.ClassDefNode;
import Semantic.AST.Node.FuncDefNode;

public class Symbols 
{
    public HashMap<String, FuncDefNode> funcs = new HashMap<>();
    public HashMap<String, ClassDefNode> classes = new HashMap<>();

    public boolean is_used(String idt)
    {
        return funcs.containsKey(idt) || classes.containsKey(idt);
    }

    public void add_func(String idt, FuncDefNode func)
    {
        funcs.put(idt, func);
    }

    public FuncDefNode get_func(String idt)
    {
        return funcs.containsKey(idt) ? funcs.get(idt) : null;
    }

    public void add_class(String idt, ClassDefNode cls)
    {
        classes.put(idt, cls);
    }

    public ClassDefNode get_class(String idt)
    {
        return classes.containsKey(idt) ? classes.get(idt) : null;
    }
}
