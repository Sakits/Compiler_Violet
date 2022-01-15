package Semantic.Frontend;

import java.util.ArrayList;

import Semantic.AST.ASTVisitor;
import Semantic.AST.Node.*;
import Utils.position;
import Utils.error.SemanticError;

public class SemanticChecker extends ASTVisitor
{
    public Symbols symbols;
    public Scope now_scope;
    public ClassDefNode now_class = null;
    public ArrayList<FuncDefNode> now_func = new ArrayList<>();
    public int is_in_loop = 0;

    public SemanticChecker(Symbols symbols)
    {
        this.symbols = symbols;
        this.now_scope = new Scope(null);
    }

    public FuncDefNode now_func()
    {
        return now_func.get(now_func.size() - 1);
    }

    public void visit(RootNode now)
    {
        now.var.forEach(i -> i.accept(this));
        now.cls.forEach(i -> i.accept(this));

        boolean flag = false;
        for (var func : now.func)
        {
            if (func.idt.equals("main"))
            {
                if (flag == true)
                    throw new SemanticError(func.pos, "have more than one main()");
                flag = true;
                if (!func.return_type.equals("int"))
                    throw new SemanticError(func.pos, "Wrong main type");
                if (func.var.size() != 0)
                    throw new SemanticError(func.pos, "main() has para");
            }
        }
        if (flag == false)
            throw new SemanticError(now.pos, "lack of main()");

        now.func.forEach(i -> i.accept(this));
    }

    public void visit(ClassDefNode now)
    {
        now_class = now;
        now_scope = new Scope(now_scope);
        now.var.forEach(i -> i.accept(this));
        now.func.forEach(i -> i.accept(this));
        now_scope = now_scope.fa_scope;
        now_class = null;
    }

    public void visit(FuncDefNode now)
    {
        if (symbols.get_type(now.return_type) == null)
            throw new SemanticError(now.pos, "no such return type " + now.return_type);

        now_scope = new Scope(now_scope);
        now_func.add(now);
        now.var.forEach(i -> i.accept(this));  
        if (now.st != null) 
            now.st.accept(this);

        if (!now.return_type.equals("void") && !now.idt.equals("main") && now.is_returned == false)
        {
            if (now_class == null || !now_class.idt.equals(now_func().idt))
                throw new SemanticError(now.pos, "function " + now.idt + " has no return");
        }

        // IR
        now.is_returned = false;
        now.belong = now_class;
        if (now_class != null && now_class.idt.equals(now_func().idt))
            now_class.cons = now;

        now_func.remove(now_func.size() - 1);
        now_scope = now_scope.fa_scope;
    }

    public void visit(ReturnNode now)
    {
        if (now.expr != null)
        {
            now.expr.accept(this);
            if (now_func().return_type != null)
            {
                if (now_class != null && now_func().idt.equals(now_class.idt))
                    throw new SemanticError(now.pos, "cannot return any value in a constructor");
                // now.expr.accept(this);
                if (!now.expr.type.equals("null") && !now.expr.type.equals(now_func().return_type))
                    throw new SemanticError(now.pos, "return type not match");
                if (!now.expr.type.equals("null") && now.expr.dim != now_func().dim)
                    throw new SemanticError(now.pos, "return dimension not match");
            }
            else
            {
                now_func().return_type = now.expr.type;
                now_func().dim = now.expr.dim;
            }
        }
        else
        {
            if (now_func().return_type != null)
            {
                if (!now_func().return_type.equals("void"))
                {
                    if (now_class == null || !now_class.idt.equals(now_func().idt))
                        throw new SemanticError(now.pos, "lack of return expression");
                }
            }
            else
                now_func().return_type = "null";
        }

        now_func().is_returned = true;
    }

    public void visit(FuncVarDefNode now)
    {
        if (symbols.get_type(now.type) == null)
            throw new SemanticError(now.pos, "no such type " + now.type);
        now.one_var.accept(this);
        now.one_var.type = now.type;
        now.one_var.dim = now.dim;
    }

    public void visit(VarDefNode now)
    {
        if (symbols.get_type(now.type) == null)
            throw new SemanticError(now.pos, "no such type " + now.type);

        for (var i : now.var)
        {
            i.accept(this);
            i.type = now.type;
            i.dim = now.dim;
        }
    }

    public void visit(OneVarDefNode now)
    {
        if (symbols.type_is_used(now.idt))
            throw new SemanticError(now.pos, "multiple defines of " + now.idt);
        if (now_scope.get_var(now.idt, false) != null)
            throw new SemanticError(now.pos, "multiple defines of " + now.idt);

        if (now.init_val != null)
            now.init_val.accept(this);
        
        now_scope.add_var(now.idt, now);
        if (now_class != null)
        {
            now.belong = now_class;
            now.offset = now_class.now_offset++;
        }
    }

    public void visit(SuiteNode now)
    {
        now_scope = new Scope(now_scope);
        now.st.forEach(i -> i.accept(this));
        now_scope = now_scope.fa_scope;
    }

    public void visit(AddrExprNode now)
    {
        now.obj.accept(this);
        if (now.obj.is_left_val == false)
            if (now.obj.is_class != null || (now.obj.is_left_val == false && now.obj.is_func != null))
              throw new SemanticError(now.obj.pos, "func or class can't use []");
        if (now.obj.tobe_left_val)
            throw new SemanticError(now.obj.pos, "new can't use []");
        if (now.obj.dim == 0)
            throw new SemanticError(now.obj.pos, "ptr have no dimension");

        now.offset.accept(this);
        if (!now.offset.type.equals("int") || now.offset.dim != 0)
            throw new SemanticError(now.obj.pos, "Wrong offset type");

        now.type = now.obj.type;
        now.dim = now.obj.dim - 1;
        now.is_left_val = now.obj.is_left_val;
    }

    public void visit(AssignExprNode now)
    {
        now.lhs.accept(this);
        if (now.lhs.is_left_val == false)
            throw new SemanticError(now.lhs.pos, "lhs is not a left val");

        now.rhs.accept(this);
        if (now.rhs.is_class != null)
            throw new SemanticError(now.rhs.pos, "rhs is class");
        if (now.rhs.is_left_val == false && now.rhs.is_func != null)
            throw new SemanticError(now.rhs.pos, "rhs is func");

        if (!now.rhs.type.equals("null") && !now.lhs.type.equals(now.rhs.type))
            throw new SemanticError(now.pos, "assign lhs and rhs type not match");
        if (!now.rhs.type.equals("null") && now.lhs.dim != now.rhs.dim)
            throw new SemanticError(now.pos, "assign lhs and rhs dimension not match");

        if (now.lhs.dim == 0)
        {
            if (now.lhs.type.equals("int") && now.rhs.type.equals("null"))
                throw new SemanticError(now.pos, "int can't be assigned with null");
            if (now.lhs.type.equals("bool") && now.rhs.type.equals("null"))
                throw new SemanticError(now.pos, "bool can't be assigned with null");
            if (now.lhs.type.equals("string") && now.rhs.type.equals("null"))
                throw new SemanticError(now.pos, "string can't be assigned with null");
            if (now.lhs.type.equals("void") && now.rhs.type.equals("null"))
                throw new SemanticError(now.pos, "void can't be assigned with null");
        }

        now.type = now.lhs.type;
        now.dim = now.lhs.dim;
        now.is_left_val = true;
    }

    public void visit(BinaryExprNode now)
    {
        now.lhs.accept(this);
        if (now.lhs.is_class != null)
            throw new SemanticError(now.lhs.pos, "lhs is class");
        if (now.lhs.is_left_val == false && now.lhs.is_func != null)
            throw new SemanticError(now.lhs.pos, "lhs is func");

        now.rhs.accept(this);
        if (now.rhs.is_class != null)
            throw new SemanticError(now.rhs.pos, "rhs is class");
        if (now.rhs.is_left_val == false && now.rhs.is_func != null)
            throw new SemanticError(now.rhs.pos, "rhs is func");

        if (now.op.equals("==") || now.op.equals("!="))
        {
            if (!now.lhs.type.equals(now.rhs.type) && !now.rhs.type.equals("null"))
                throw new SemanticError(now.pos, "lhs and rhs type not match");
            if (now.lhs.dim != now.rhs.dim && !now.rhs.type.equals("null"))
                throw new SemanticError(now.pos, "lhs and rhs dimension not match");

            now.type = "bool";
        }
        else
        {
            if (!now.lhs.type.equals(now.rhs.type))
                throw new SemanticError(now.pos, "lhs and rhs type not match");
            if (now.lhs.dim != now.rhs.dim)
                throw new SemanticError(now.pos, "lhs and rhs dimension not match");
            if (now.lhs.type.equals("bool"))
            {
                if (now.op.equals("<=") || now.op.equals(">=") || now.op.equals("<") || now.op.equals(">"))    
                    throw new SemanticError(now.pos, "bool can't use operator " + now.op);
            }
            else if (now.lhs.type.equals("string"))
            {
                if (!now.op.equals("+") && !now.op.equals("<=") && !now.op.equals(">=") && !now.op.equals("<") && !now.op.equals(">"))
                    throw new SemanticError(now.pos, "string can't use op " + now.op);
            }
            else if (!now.lhs.type.equals("int"))
                throw new SemanticError(now.pos, "operator " + now.op + " cannot applied to " + now.lhs.type);

            if (now.op.equals("<=") || now.op.equals(">=") || now.op.equals("<") || now.op.equals(">"))    
                now.type = "bool";
            else
                now.type = now.lhs.type;
            now.dim = now.lhs.dim;
        }
    }

    public void visit(BracketExprNode now)
    {
        now.expr.accept(this);

        now.type = now.expr.type;
        now.dim = now.expr.dim;
        now.is_left_val = now.expr.is_left_val;
        if (now.expr.tobe_left_val == true)
            now.is_left_val = true;
        else
        {
            if (now.expr.is_class != null)
                throw new SemanticError(now.pos, "class lack of consturctor");
            if (now.expr.is_left_val == false && now.expr.is_func != null)
                throw new SemanticError(now.pos, "func lack of call");
        }
    }

    public void visit(CallExprNode now)
    {
        now.obj.accept(this);
        if (now.obj.is_class == null && now.obj.is_func == null)
            throw new SemanticError(now.pos, "obj is neither class nor function");
        
        FuncDefNode func = null;
        if (now.obj.is_class != null)
            func = now.obj.is_class.func.get(0);
        else
            func = now.obj.is_func;
        if (func.var.size() != now.para.size())
            throw new SemanticError(now.pos, "para number not match");

        now.func = func;

        now.para.forEach(i -> i.accept(this));
        for (var i = 0; i < func.var.size(); i++)
        {
            if (now.para.get(i).is_class != null)
                throw new SemanticError(now.para.get(i).pos, "para " + i + " is class");
            if (now.para.get(i).is_left_val == false && now.para.get(i).is_func != null)
                throw new SemanticError(now.para.get(i).pos, "para " + i + " is func");

            if (!now.para.get(i).type.equals("null") && !func.var.get(i).type.equals(now.para.get(i).type))
                throw new SemanticError(now.pos, "para " + i + " type not match");
            if (!now.para.get(i).type.equals("null") && func.var.get(i).dim != now.para.get(i).dim)
                throw new SemanticError(now.pos, "para " + i + " dimension not match");
        }

        if (now.obj.is_class != null)
        {
            now.type = now.obj.is_class.idt;
            now.is_left_val = true;
        }
        else
        {
            now.type = now.obj.is_func.return_type;
            now.dim = now.obj.is_func.dim;
        }
    }

    public void visit(NvarExprNode now)
    {
        now.cls = symbols.get_type(now.type);
        if (now.cls == null)
            throw new SemanticError(now.pos, "no such type " + now.type);

        for (var i : now.expr)
        {
            i.accept(this);
            if ((i.is_left_val == false && i.is_func != null) || i.is_class != null || i.dim != 0 || !i.type.equals("int"))
                throw new SemanticError(i.pos, "The size of array should be of int type");
        }
        
        now.tobe_left_val = true;
    }

    public void visit(ObjExprNode now)
    {
        now.obj.accept(this);
        if (now.obj.is_class != null || (now.obj.is_left_val == false && now.obj.is_func != null))
            throw new SemanticError(now.pos, "class or func can't use .");

        ClassDefNode cls = symbols.get_type(now.obj.type);

        if (now.obj.dim == 0)
        {
            if (cls.vars.containsKey(now.idt))
            {
                now.defnode = cls.vars.get(now.idt);
                now.type = now.defnode.type;
                now.dim = now.defnode.dim;
                now.is_left_val = true;
            }
            else if (cls.funcs.containsKey(now.idt))
            {
                now.is_func = cls.funcs.get(now.idt);
                now.type = now.is_func.return_type;
                now.dim = now.is_func.dim;
            }
            else
                throw new SemanticError(now.pos, "obj has no such idt");
        }
        else if (now.idt.equals("size"))
        {
            now.is_func = symbols.get_func("__builtin_size");
            now.type = "int";
        }
        else
            throw new SemanticError(now.pos, "Array only have size() method");
    }

    public void visit(PrefixExprNode now)
    {
        now.obj.accept(this);

        if (!now.obj.type.equals("int") && !now.obj.type.equals("bool"))
            throw new SemanticError(now.pos, "prefixexpr Wrong type");
        if (!now.op.equals("!") && !now.obj.type.equals("int"))
            throw new SemanticError(now.pos, "prefixexpr Wrong type");
        if (now.op.equals("!") && !now.obj.type.equals("bool"))
            throw new SemanticError(now.pos, "prefixexpr Wrong type");

        if (now.op.equals("++") || now.op.equals("--"))
        {
            if (now.obj.is_left_val == false)
                throw new SemanticError(now.pos, "obj is not left val");
            now.is_left_val = true;
        }
        now.type = now.obj.type;
        now.dim = now.obj.dim;
    }

    public void visit(SuffixExprNode now)
    {
        now.obj.accept(this);
        if (now.obj.is_left_val == false)
            throw new SemanticError(now.pos, "obj is not left val");
        if (!now.obj.type.equals("int"))
            throw new SemanticError(now.pos, "suffixexpr Wrong type");
        
        now.type = "int";
        now.dim = now.obj.dim;
    }

    boolean cmp(position a, position b)
    {
        return a.row != b.row ? a.row < b.row : a.col < b.col;
    }

    public void visit(PrimaryExprNode now)
    {
        if (now.cate == 0)
        {
            OneVarDefNode var = now_scope.get_var(now.s, true);

            if (var != null && cmp(now.pos, var.pos))
                var = null;

            if (now_class != null)
            {
                OneVarDefNode var2 = now_class.vars.containsKey(now.s) ? now_class.vars.get(now.s) : null;

                if (var == null)
                    var = var2;
                else if (var2 != null && cmp(var.pos, now_class.pos))
                    var = var2;
            }

            now.defnode = var;
            
            FuncDefNode func = symbols.get_func(now.s);
            if (now_class != null && now_class.funcs.containsKey(now.s))
                func = now_class.funcs.get(now.s);

            ClassDefNode cls = symbols.get_type(now.s);

            if (var != null && func != null)
            {
                now.type = var.type;
                now.dim = var.dim;
                now.is_left_val = true;
                now.is_func = func;
            }
            else if (var != null)
            {
                now.type = var.type;
                now.dim = var.dim;
                now.is_left_val = true;
            }
            else if (cls != null)
            {
                now.type = cls.idt;
                now.tobe_left_val = true;
                now.is_class = cls;
            }
            else if (func != null)
            {
                now.type = func.return_type;
                now.is_func = func;
            }
            else 
                throw new SemanticError(now.pos, "no such Identifier " + now.s);
        }
        else if (now.cate == 1)
            now.type = "int";
        else if (now.cate == 2)
            now.type = "null";
        else if (now.cate == 3)
        {
            if (now_class == null)
                throw new SemanticError(now.pos, "unable to use This outside a class");
            now.type = now_class.idt;
        }
        else if (now.cate == 4 || now.cate == 5)
            now.type = "bool";
        else if (now.cate == 6)
            now.type = "string";
    }

    public void visit(SemiStNode now)
    {
    }

    public void visit(BreakStNode now)
    {
        if (is_in_loop == 0)
            throw new SemanticError(now.pos, "break outside the loop");
    }

    public void visit(ContinueStNode now)
    {
        if (is_in_loop == 0)
            throw new SemanticError(now.pos, "continue outside the loop");
    }

    public void visit(ExprStNode now)
    {
        now.expr.accept(this);
    }

    public void visit(ForStNode now)
    {
        now_scope = new Scope(now_scope);
        is_in_loop ++;

        if (now.var != null)
            now.var.accept(this);   
        if (now.init != null)
            now.init.accept(this);
        if (now.cond != null)
        {
            now.cond.accept(this);
            if (!now.cond.type.equals("bool"))
                throw new SemanticError(now.pos, "Condition should be bool");
        }
        if (now.next != null)
            now.next.accept(this);

        now.st.accept(this);

        is_in_loop --;
        now_scope = now_scope.fa_scope;
    }

    public void visit(WhileStNode now)
    {
        now_scope = new Scope(now_scope);
        is_in_loop ++;

        now.cond.accept(this);
        now.st.accept(this);

        is_in_loop --;
        now_scope = now_scope.fa_scope;
    }

    public void visit(IfStNode now)
    {
        now.cond.accept(this);
        if (!now.cond.type.equals("bool"))
            throw new SemanticError(now.cond.pos, "If condition should be of boolean type");
        now_scope = new Scope(now_scope);
        now.true_st.accept(this);
        now_scope = now_scope.fa_scope;
        if (now.false_st != null)
        {
            now_scope = new Scope(now_scope);
            now.false_st.accept(this);
            now_scope = now_scope.fa_scope;
        }
    }

    public void visit(LambdaExprNode now)
    {
        if (now.var.size() != now.para.size())
            throw new SemanticError(now.pos, "para number not match");

        now_scope = new Scope(now_scope);

        now.var.forEach(i -> i.accept(this));
        now.para.forEach(i -> i.accept(this));
     
        for (var i = 0; i < now.var.size(); i++)
        {
            if (now.para.get(i).is_class != null)
                throw new SemanticError(now.para.get(i).pos, "para " + i + " is class");
            if (now.para.get(i).is_left_val == false && now.para.get(i).is_func != null)
                throw new SemanticError(now.para.get(i).pos, "para " + i + " is func");

            if (!now.para.get(i).type.equals("null") && !now.var.get(i).type.equals(now.para.get(i).type))
                throw new SemanticError(now.pos, "para " + i + " type not match");
            if (!now.para.get(i).type.equals("null") && now.var.get(i).dim != now.para.get(i).dim)
                throw new SemanticError(now.pos, "para " + i + " dimension not match");
        }

        FuncDefNode lambda = new FuncDefNode(now.pos, 0, null, "__lambda_" + now.pos.toString(), null);
        now_func.add(lambda);
        now.st.accept(this);

        if (lambda.is_returned == false)
            throw new SemanticError(now.pos, "lambda function has no return");

        now.type = lambda.return_type;
        now.dim = lambda.dim;
        now_func.remove(now_func.size() - 1);

        now_scope = now_scope.fa_scope;
    }
}