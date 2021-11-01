package Semantic.Frontend;


import Semantic.AST.ASTVisitor;
import Semantic.AST.Node.*;

public class SymbolCollector extends ASTVisitor
{
    public Symbols symbols;
    public ClassDefNode nowClass = null;

    public SymbolCollector(Symbols symbols)
    {
        this.symbols = symbols;
    }

    @Override
    public void visit(RootNode now)
    {
        now.cls.forEach(i -> i.accept(this));
        now.func.forEach(i -> i.accept(this));   
    }

    @Override 
    public void visit(FuncDefNode now)
    {
        if (nowClass == null)
        {
            if (symbols.is_used(now.idt))
                throw new SemanticError(now.pos, "symbol " + now.idt + " has been defined");
            symbols.add_func(now.idt, now);
        }
        else
        {
            if (nowClass.funcs.containsKey(now.idt))
                throw new SemanticError(now.pos, "member function " + now.idt + " has been defined");
            nowClass.funcs.put(now.idt, now);
        }
    }

    @Override
    public void visit(ClassDefNode now)
    {
        if (symbols.is_used(now.idt))
            throw new SemanticError(now.pos, "symbol " + now.idt + " has been defined");

        symbols.add_class(now.idt, now);
        nowClass = now;
        now.var.forEach(i -> i.accept(this));
        nowClass = null;
    }

    @Override
    public void visit(VarDefNode now)
    {
        for (var i : now.var)
        {
            if (nowClass.vars.containsKey(i.idt))
                throw new SemanticError(now.pos, "member variable " + i.idt + " has been defined");
            i.type = now.type;
            i.dim = now.dim;
            nowClass.vars.put(i.idt, i);
        }
    }
}
