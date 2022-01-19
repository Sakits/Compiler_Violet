package Semantic.Checker;


import Semantic.AST.ASTVisitor;
import Semantic.AST.Node.*;
import Utils.position;
import Utils.error.SemanticError;

public class SymbolCollector extends ASTVisitor
{
    public Symbols symbols;
    public ClassDefNode now_class = null;

    public SymbolCollector(Symbols symbols)
    {
        this.symbols = symbols;

        ClassDefNode Int = new ClassDefNode(new position(), "int");
        now_class = Int;
        Int.accept(this);
        now_class = null;

        ClassDefNode Bool = new ClassDefNode(new position(), "bool");
        now_class = Bool;
        Bool.accept(this);
        now_class = null;

        ClassDefNode Str = new ClassDefNode(new position(), "string");

        Str.func.add(new FuncDefNode(new position(), 0, "int", "length", null, true));

        FuncDefNode substring = new FuncDefNode(new position(), 0, "string", "substring", null, true);
        FuncVarDefNode left = new FuncVarDefNode(new position(), "int", 0, new OneVarDefNode(new position(), "left", null));
        FuncVarDefNode right = new FuncVarDefNode(new position(), "int", 0, new OneVarDefNode(new position(), "right", null));
        substring.var.add(left);
        substring.var.add(right);
        Str.func.add(substring);

        Str.func.add(new FuncDefNode(new position(), 0, "int", "parseInt", null, true));

        FuncDefNode ord = new FuncDefNode(new position(), 0, "int", "ord", null, true);
        FuncVarDefNode pos = new FuncVarDefNode(new position(), "int", 0, new OneVarDefNode(new position(), "pos", null));
        ord.var.add(pos);
        Str.func.add(ord);

        now_class = Str;
        Str.accept(this);
        now_class = null;
        

        ClassDefNode Void = new ClassDefNode(new position(), "void");
        now_class = Void;
        Void.accept(this);
        now_class = null;

        
        FuncVarDefNode printpara = new FuncVarDefNode(new position(), "string", 0, new OneVarDefNode(new position(), "str", null));
        FuncDefNode print = new FuncDefNode(new position(), 0, "void", "print", null);
        print.var.add(printpara);
        print.accept(this);

        FuncVarDefNode printlnpara = new FuncVarDefNode(new position(), "string", 0, new OneVarDefNode(new position(), "str", null));
        FuncDefNode println = new FuncDefNode(new position(), 0, "void", "println", null);
        println.var.add(printlnpara);
        println.accept(this);

        FuncVarDefNode printIntpara = new FuncVarDefNode(new position(), "int", 0, new OneVarDefNode(new position(), "n", null));
        FuncDefNode printInt = new FuncDefNode(new position(), 0, "void", "printInt", null);
        printInt.var.add(printIntpara);
        printInt.accept(this);

        FuncVarDefNode printlnIntpara = new FuncVarDefNode(new position(), "int", 0, new OneVarDefNode(new position(), "n", null));
        FuncDefNode printlnInt = new FuncDefNode(new position(), 0, "void", "printlnInt", null);
        printlnInt.var.add(printlnIntpara);
        printlnInt.accept(this);

        FuncDefNode getString = new FuncDefNode(new position(), 0, "string", "getString", null, true);
        getString.accept(this);

        FuncDefNode getInt = new FuncDefNode(new position(), 0, "int", "getInt", null, true);
        getInt.accept(this);

        FuncVarDefNode toStringpara = new FuncVarDefNode(new position(), "int", 0, new OneVarDefNode(new position(), "i", null));
        FuncDefNode toString = new FuncDefNode(new position(), 0, "string", "toString", null, true);
        toString.var.add(toStringpara);
        toString.accept(this);

        FuncDefNode size = new FuncDefNode(new position(), 0, "int", "__builtin_size", null);
        size.accept(this);
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
        if (now_class == null)
        {
            if (symbols.func_is_used(now.idt))
                throw new SemanticError(now.pos, "symbol " + now.idt + " has been defined");
            symbols.add_func(now.idt, now);
        }
        else
        {
            now.belong = now_class;
            
            if (now.idt.equals("main"))
                throw new SemanticError(now.pos, "Duplicated name for main");

            if (now_class.funcs.containsKey(now.idt))
                throw new SemanticError(now.pos, "member function " + now.idt + " has been defined");
            if (now.idt.equals(now_class.idt))
            {
                if (now.return_type != null)
                    throw new SemanticError(now.pos, "Constructor should not have type");
                else
                    now.return_type = now_class.idt;
            }
            now_class.funcs.put(now.idt, now);
        }
    }

    @Override
    public void visit(ClassDefNode now)
    {
        if (now.idt.equals("main"))
            throw new SemanticError(now.pos, "Duplicated name for main");
        if (symbols.type_is_used(now.idt))
            throw new SemanticError(now.pos, "symbol " + now.idt + " has been defined");

        symbols.add_type(now.idt, now);
        now_class = now;
        now.var.forEach(i -> i.accept(this));
        now.func.forEach(i -> i.accept(this));
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
