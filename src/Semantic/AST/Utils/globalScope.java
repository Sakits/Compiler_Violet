package Semantic.AST.Utils;

import java.util.HashMap;

import Semantic.AST.Node.ClassNode;
import Semantic.AST.Node.FuncNode;
import Semantic.AST.Node.position;

public class globalScope extends Scope
{
    public HashMap<String, ClassNode> types = new HashMap<>();
    public HashMap<String, FuncNode> funcs = new HashMap<>();

    public globalScope(Scope faScope)
    {
        super(faScope);
    }

    public void addType(position pos, String type, ClassNode node)
    {
        if (types.containsKey(type))
            throw new semanticError(pos, "multiple definition of " + type);
        types.put(type, node);
    }

    public ClassNode getType(position pos, String type)
    {
        if (types.containsKey(type))
            return types.get(type);
        throw new semanticError(pos, "no such type " + type);
    }

    public void addFunc(position pos, String func, FuncNode node)
    {
        if (funcs.containsKey(func))
            throw new semanticError(pos, "multiple functions of " + func);
        funcs.put(func, node);
    }

    public FuncNode getFunc(position pos, String func)
    {
        if (funcs.containsKey(func))
            return funcs.get(func);
        throw new semanticError(pos, "no function definition of " + func);
    }
}
