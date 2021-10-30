package Semantic.AST;

import org.antlr.v4.runtime.ParserRuleContext;

import Semantic.AST.Node.*;
import Semantic.Grammar.MxstarBaseVisitor;
import Semantic.Grammar.MxstarParser;

public class ASTBuilder extends MxstarBaseVisitor<ASTNode>
{
    public int get_dim(String type)
    {
        int cnt = 0, ans = 0;
        for (int i = 0; i < type.length(); i++)
        {
            if (type.charAt(i) == '[')
                cnt++;
            if (type.charAt(i) == ']')
            {
                cnt--;
                if (cnt == 0)
                    ans++;
            }
        }
        return ans;
    }

    @Override public ASTNode visitProgram(MxstarParser.ProgramContext ctx)
    {
        if (ctx == null) return null;

        RootNode now = new RootNode(new position(ctx));

        ctx.functionDef().forEach(i -> now.func.add((FuncNode) visit(i)));
        ctx.classDef().forEach(i -> now.cls.add((ClassNode) visit(i)));
        for (var vardef : ctx.varDef())
            for (var onevar : vardef.oneVarDef())
            {
                VarNode v = (VarNode) visit(onevar);
                v.type = vardef.varType().basicType().toString();
                v.dim = get_dim(vardef.varType().toString());
                now.var.add(v);
            }

        return now;
    }

    @Override public ASTNode visitClassDef(MxstarParser.ClassDefContext ctx)
    {
        if (ctx == null) return null;

        ClassNode now = new ClassNode(new position(ctx), ctx.Identifier().toString());

        ctx.constructFuncDef().forEach(i -> now.func.put(i.Identifier().toString(), (FuncNode) visit(i)));
        ctx.functionDef().forEach(i -> now.func.put(i.Identifier().toString(), (FuncNode) visit(i)));
        for (var vardef : ctx.varDef())
            for (var onevar : vardef.oneVarDef())
            {
                VarNode v = (VarNode) visit(onevar);
                v.type = vardef.varType().basicType().toString();
                v.dim = get_dim(vardef.varType().toString());
                now.var.put(v.idt, v);
            }

        return now;
    }

    @Override public ASTNode visitFunctionDef(MxstarParser.FunctionDefContext ctx)
    {
        if (ctx == null) return null;

        FuncNode now = new FuncNode(new position(ctx), ctx.returnType().toString(), ctx.Identifier().toString());

        ctx.functionVarDef().funcVarDef().forEach(i -> now.var.add((VarNode) visit(i)));
        ctx.suite().statement().forEach(i -> now.st.add((StNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitConstructFuncDef(MxstarParser.ConstructFuncDefContext ctx)
    {
        if (ctx == null) return null;

        FuncNode now = new FuncNode(new position(ctx), new String("0"), ctx.Identifier().toString());

        ctx.functionVarDef().funcVarDef().forEach(i -> now.var.add((VarNode) visit(i)));
        ctx.suite().statement().forEach(i -> now.st.add((StNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitBlockTag(MxstarParser.BlockTagContext ctx)
    {
        if (ctx == null) return null;

        BlockNode now = new BlockNode(new position(ctx));

        ctx.suite().statement().forEach(i -> now.st.add((StNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitIfTag(MxstarParser.IfTagContext ctx)
    {
        if (ctx == null) return null;

        ExprNode cond = (ExprNode) visit(ctx.ifStat().expression());
        StNode trueSt = (StNode) visit(ctx.ifStat().trueSt);
        StNode falseSt = (StNode) visit(ctx.ifStat().falseSt);

        return new IfStNode(new position(ctx), cond, trueSt, falseSt);
    }    

    @Override public ASTNode visitReturnTag(MxstarParser.ReturnTagContext ctx)
    {
        if (ctx == null) return null;

        ExprNode expr = (ExprNode) visit(ctx.expression());

        return new ReturnNode(new position(ctx), expr);
    }

    @Override public ASTNode visitWhileTag(MxstarParser.WhileTagContext ctx)
    {
        if (ctx == null) return null;

        return (WhileNode) visit(ctx.whileStat());
    }

    @Override public ASTNode visitForTag(MxstarParser.ForTagContext ctx)
    {
        if (ctx == null) return null;

        return (ForNode) visit(ctx.forStat());
    }

    @Override public ASTNode visitBreakTag(MxstarParser.BreakTagContext ctx)
    {
        if (ctx == null) return null;
        return new BreakNode(new position(ctx));
    }

    @Override public ASTNode visitContinueTag(MxstarParser.ContinueTagContext ctx)
    {
        if (ctx == null) return null;
        return new ContinueNode(new position(ctx));
    }

    @Override public ASTNode visitVarTag(MxstarParser.VarTagContext ctx)
    {
        if (ctx == null) return null;

        return (VarDefNode) visit(ctx.varDef());
    }

    @Override public ASTNode visitExprTag(MxstarParser.ExprTagContext ctx)
    {
        if (ctx == null) return null;

        return new ExprNode(new position(ctx));
    }

    @Override public ASTNode visitSemiTag(MxstarParser.SemiTagContext ctx)
    {
        return null;
    }

    @Override public ASTNode visitFuncVarDef(MxstarParser.FuncVarDefContext ctx)
    {
        if (ctx == null) return null;

        VarNode now = new VarNode(new position(ctx), ctx.varType().basicType().toString(), ctx.oneVarDef().Identifier().toString(), get_dim(ctx.varType().toString()), null);
        return now;
    }

    @Override public ASTNode visitVarDef(MxstarParser.VarDefContext ctx)
    {
        if (ctx == null) return null;

        VarDefNode now = new VarDefNode(new position(ctx), ctx.varType().basicType().toString());
        for (ParserRuleContext vardef : ctx.oneVarDef())
        {
            VarNode v = (VarNode) visit(vardef);
            v.type = now.type;
            v.dim = get_dim(ctx.varType().toString());
            now.var.add(v);
        }
        return now;
    }

    @Override public ASTNode visitOneVarDef(MxstarParser.OneVarDefContext ctx)
    {
        if (ctx == null) return null;

        ExprNode val = (ExprNode) visit(ctx.expression());
        VarNode now = new VarNode(new position(ctx), null, ctx.Identifier().toString(), 0, val);

        return now;
    }

    @Override public ASTNode visitPrefixTag(MxstarParser.PrefixTagContext ctx)
    {
        if (ctx == null) return null;

        String op = ctx.op.toString();
        ExprNode obj = (ExprNode) visit(ctx.expression());
        PrefixNode now = new PrefixNode(new position(ctx), op, obj);

        return now;
    }

    @Override public ASTNode visitObjTag(MxstarParser.ObjTagContext ctx)
    {
        if (ctx == null) return null;

        ExprNode obj = (ExprNode) visit(ctx.expression(0));
        String idt = ctx.Identifier().toString();
        ExprNode para = (ExprNode) visit(ctx.expression(1));
        boolean isfunc = ctx.bra != null;

        ObjNode now = new ObjNode(new position(ctx), idt, obj, para, isfunc);

        return now;
    }    

    @Override public ASTNode visitNvarTag(MxstarParser.NvarTagContext ctx)
    {
        if (ctx == null) return null;

        VarNode now = new VarNode(new position(ctx), ctx.newType().basicType().toString(), null, get_dim(ctx.newType().toString()), null);

        return now;
    }

    @Override public ASTNode visitAssignTag(MxstarParser.AssignTagContext ctx)
    {
        if (ctx == null) return null;

        ExprNode dst = (ExprNode) visit(ctx.expression(0));
        ExprNode src = (ExprNode) visit(ctx.expression(1));
        AssignNode now = new AssignNode(new position(ctx), dst, src);

        return now;
    }

    @Override public ASTNode visitBinaryTag(MxstarParser.BinaryTagContext ctx)
    {
        if (ctx == null) return null;

        String op = ctx.op.toString();
        ExprNode lhs = (ExprNode) visit(ctx.expression(0));
        ExprNode rhs = (ExprNode) visit(ctx.expression(1));

        BinaryNode now = new BinaryNode(new position(ctx), op, lhs, rhs);

        return now;
    }

    @Override public ASTNode visitSuffixTag(MxstarParser.SuffixTagContext ctx)
    {
        if (ctx == null) return null;

        String op = ctx.op.toString();
        ExprNode obj = (ExprNode) visit(ctx.expression());
        SuffixNode now = new SuffixNode(new position(ctx), op, obj);

        return now;
    }

    @Override public ASTNode visitPrimaryTag(MxstarParser.PrimaryTagContext ctx)
    {
        if (ctx == null) return null;

        String type = ctx.primary().toString();
        PrimaryNode now = new PrimaryNode(new position(ctx), type, ctx.primary().toString());

        return now;
    }

    @Override public ASTNode visitExprinTag(MxstarParser.ExprinTagContext ctx)
    {
        if (ctx == null) return null;

        ExprinNode now = new ExprinNode(new position(ctx), (ExprNode) visit(ctx.expression()));

        return now;
    }

    @Override public ASTNode visitAddrTag(MxstarParser.AddrTagContext ctx)
    {
        if (ctx == null) return null;

        ExprNode ptr = (ExprNode) visit(ctx.expression(0));
        ExprNode offset = (ExprNode) visit(ctx.expression(1));
        AddrNode now = new AddrNode(new position(ctx), ptr, offset);

        return now;
    }

    @Override public ASTNode visitCallTag(MxstarParser.CallTagContext ctx) 
    {
        if (ctx == null) return null;
        
        ExprNode obj = (ExprNode) visit(ctx.expression(0));
        CallNode now = new CallNode(new position(ctx), obj);

        for (int i = 1; i < ctx.expression().size(); i++)
            now.para.add((ExprNode) visit(ctx.expression(i)));

        return now;
    }

    @Override public ASTNode visitIfStat(MxstarParser.IfStatContext ctx)
    {
        if (ctx == null) return null;

        ExprNode cond = (ExprNode) visit(ctx.expression());
        StNode trueSt = (StNode) visit(ctx.trueSt);
        StNode falseSt = (StNode) visit(ctx.falseSt);
        IfStNode now = new IfStNode(new position(ctx), cond, trueSt, falseSt);

        return now;
    }

    @Override public ASTNode visitWhileStat(MxstarParser.WhileStatContext ctx)
    {
        if (ctx == null) return null;

        ExprNode cond = (ExprNode) visit(ctx.expression());
        StNode st = (StNode) visit(ctx.statement());
        WhileNode now = new WhileNode(new position(ctx), cond, st);

        return now;
    }

    @Override public ASTNode visitForStat(MxstarParser.ForStatContext ctx)
    {
        if (ctx == null) return null;

        ExprNode init = (ExprNode) visit(ctx.init);
        ExprNode cond = (ExprNode) visit(ctx.cond);
        ExprNode next = (ExprNode) visit(ctx.next);
        StNode st = (StNode) visit(ctx.statement());

        ForNode now = new ForNode(new position(ctx), init, cond, next, st);

        for (ParserRuleContext vardef : ctx.varDef().oneVarDef())
        {
            VarNode v = (VarNode) visit(vardef);
            v.type = ctx.varDef().varType().basicType().toString();
            v.dim = get_dim(ctx.varDef().varType().toString());
            now.var.add(v);
        }

        return now;
    }

    
}