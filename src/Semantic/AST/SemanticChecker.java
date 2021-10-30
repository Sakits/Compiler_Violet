package Semantic.AST;

import Semantic.AST.Node.AddrNode;
import Semantic.AST.Node.AssignNode;
import Semantic.AST.Node.BinaryNode;
import Semantic.AST.Node.BlockNode;
import Semantic.AST.Node.BreakNode;
import Semantic.AST.Node.CallNode;
import Semantic.AST.Node.ClassNode;
import Semantic.AST.Node.ContinueNode;
import Semantic.AST.Node.ExprNode;
import Semantic.AST.Node.ExprinNode;
import Semantic.AST.Node.ForNode;
import Semantic.AST.Node.FuncNode;
import Semantic.AST.Node.IfStNode;
import Semantic.AST.Node.ObjNode;
import Semantic.AST.Node.PrefixNode;
import Semantic.AST.Node.PrimaryNode;
import Semantic.AST.Node.ReturnNode;
import Semantic.AST.Node.RootNode;
import Semantic.AST.Node.StNode;
import Semantic.AST.Node.SuffixNode;
import Semantic.AST.Node.VarDefNode;
import Semantic.AST.Node.VarNode;
import Semantic.AST.Node.WhileNode;
import Semantic.AST.Node.position;
import Semantic.AST.Utils.Scope;
import Semantic.AST.Utils.globalScope;
import Semantic.AST.Utils.semanticError;

public class SemanticChecker 
{
    public globalScope gScope;
    public Scope nowScope;
    public ClassNode nowClass = null;

    public SemanticChecker(globalScope gScope)
    {
        nowScope = this.gScope = gScope;
    }

    public void visit(RootNode now)
    {
        boolean flag = false;
        for (var func : now.func)
        if (func.idt == "main")
        {
            if (flag == true)
                throw new semanticError(func.pos, "have more than one main()");
            flag = true;
            if (func.returnType != "int")
                throw new semanticError(func.pos, "Wrong main type");
            if (func.var.size() != 0)
                throw new semanticError(func.pos, "main() has para");
        }

        if (flag == false)
            throw new semanticError(now.pos, "lack of main()");

        now.var.forEach(i -> visit(i));
        now.cls.forEach(i -> visit(i));
        now.func.forEach(i -> visit(i));
    }

    public void visit(VarNode now)
    {
        if (now == null) return;

        if (nowClass != null)
        {
            if (nowClass.var.containsKey(now.idt))
                throw new semanticError(now.pos, "multiple definition of " + now.idt);
            nowClass.var.put(now.idt, now);
        }
        else
            nowScope.defVar(now.pos, now.idt, now);

        if (now.val != null)
        {
            visit(now.val);
            if (now.type != now.val.type || now.dim != now.val.dim)
                throw new semanticError(now.pos, "init type not match");
        }
    }

    public void visit(ClassNode now)
    {
        if (now == null) return;

        nowClass = now;
        for (var i : now.var.keySet())
            visit(now.var.get(i));

        for (var i : now.func.keySet())
            visit(now.func.get(i));

        nowClass = null;
    }

    public void visit(FuncNode now)
    {
        if (now == null) return;

        if (now.returnType != "void")
        {
            if (gScope.types.containsKey(now.returnType) == false)
                throw new semanticError(now.pos, "no such returnType " + now.returnType);
        }

        // gScope.getFunc(now.pos, now);

        nowScope = new Scope(nowScope);
        now.var.forEach(i -> visit(i));
        now.st.forEach(i -> visit(i, now));
        nowScope = nowScope.faScope;
    }

    public void visit(BlockNode now, FuncNode nowfunc)
    {
        if (now == null) return;

        // scope
        now.st.forEach(i -> visit(i, nowfunc));
    }

    public void visit(IfStNode now, FuncNode nowfunc)
    {
        if (now == null) return;

        visit(now.cond);
        visit(now.trueSt, nowfunc);
        visit(now.falseSt, nowfunc);
    }

    public void visit(ReturnNode now, FuncNode nowfunc)
    {
        if (now == null) return;

        visit(now.expr);
    }

    public void visit(WhileNode now, FuncNode nowfunc)
    {
        if (now == null) return;

        visit(now.cond);
        visit(now.st, nowfunc);
    }

    public void visit(ForNode now, FuncNode nowfunc)
    {
        if (now == null) return;

        now.var.forEach(i -> visit(i));

        visit(now.init);
        visit(now.cond);
        visit(now.next);

        visit(now.st);
    }

    public void visit(BreakNode now, FuncNode nowfunc) {}

    public void visit(ContinueNode now, FuncNode nowfunc) {}

    public void visit(VarDefNode now, FuncNode nowfunc)
    {
        now.var.forEach(i -> visit(i));
    }

    public void visit(PrimaryNode now) {}

    public void visit(ExprinNode now)
    {
        visit(now.expr);
    }

    public void visit(AddrNode now)
    {
        visit(now.ptr);
        visit(now.offset);
    }

    public void visit(CallNode now)
    {
        visit(now.obj);
        now.para.forEach(i -> visit(i));
    }

    public void visit(ObjNode now)
    {
        visit(now.obj);
        visit(now.para);
    }

    public void visit(PrefixNode now)
    {
        visit(now.obj);
    }

    public void visit(SuffixNode now)
    {
        visit(now.obj);
    }

    public void visit(BinaryNode now)
    {
        visit(now.lhs);
        visit(now.rhs);
    }

    public void visit(AssignNode now)
    {
        visit(now.dst);
        visit(now.src);
    }
}
