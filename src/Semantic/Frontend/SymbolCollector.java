package Semantic.Frontend;


import Semantic.AST.ASTVisitor;
import Semantic.AST.Node.*;
import Utils.position;

public class SymbolCollector extends ASTVisitor
{
    public Symbols symbols;
    public ClassDefNode now_class = null;

    public SymbolCollector(Symbols symbols)
    {
        this.symbols = symbols;
    }

    @Override
    public void visit(RootNode now)
    {
        ClassDefNode Int = new ClassDefNode(new position(), "int");
        Int.accept(this);
        ClassDefNode Bool = new ClassDefNode(new position(), "bool");
        Bool.accept(this);
        ClassDefNode Str = new ClassDefNode(new position(), "string");
        Str.accept(this);
        ClassDefNode Void = new ClassDefNode(new position(), "void");
        Void.accept(this);

        now.cls.forEach(i -> i.accept(this));
        now.func.forEach(i -> i.accept(this));   
    }

    @Override 
    public void visit(FuncDefNode now)
    {
        if (now_class == null)
        {
            if (symbols.func_is_used(now.idt))
                throw new SemanticError(now.pos, "symbol " + now.idt + " has been defined");
            symbols.add_func(now.idt, now);
        }
        else
        {
            if (now_class.funcs.containsKey(now.idt))
                throw new SemanticError(now.pos, "member function " + now.idt + " has been defined");
            now_class.funcs.put(now.idt, now);
        }
    }

    @Override
    public void visit(ClassDefNode now)
    {
        if (symbols.type_is_used(now.idt))
            throw new SemanticError(now.pos, "symbol " + now.idt + " has been defined");

        symbols.add_type(now.idt, now);
        now_class = now;
        now.var.forEach(i -> i.accept(this));
        now_class = null;
    }

    @Override
    public void visit(VarDefNode now)
    {
        for (var i : now.var)
        {
            if (now_class.vars.containsKey(i.idt))
                throw new SemanticError(now.pos, "member variable " + i.idt + " has been defined");
            i.type = now.type;
            i.dim = now.dim;
            now_class.vars.put(i.idt, i);
        }
    }
}
