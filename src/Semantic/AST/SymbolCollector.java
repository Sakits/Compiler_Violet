package Semantic.AST;

import Semantic.AST.Node.ClassNode;
import Semantic.AST.Node.FuncNode;
import Semantic.AST.Node.RootNode;
import Semantic.AST.Node.position;

public class SymbolCollector 
{
    public globalScope gScope;

    public SymbolCollector(globalScope gScope)
    {
        this.gScope = gScope;
    }

    public void visit(RootNode now)
    {
        now.cls.forEach(i -> visit(i));
        gScope.types.put("bool", new ClassNode(new position(), "bool"));
        gScope.types.put("int", new ClassNode(new position(), "int"));
        gScope.types.put("string", new ClassNode(new position(), "string"));

        now.func.forEach(i -> visit(i));
    }

    public void visit(ClassNode now)
    {
        if (now == null) return;
        gScope.addType(now.pos, now.idt, now);
    }

    public void visit(FuncNode now)
    {
        if (now == null) return;
        gScope.addFunc(now.pos, now.idt, now);
    }
}
