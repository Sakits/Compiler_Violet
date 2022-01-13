package Semantic.AST;

import Semantic.AST.Node.*;
import Semantic.Grammar.MxstarBaseVisitor;
import Semantic.Grammar.MxstarParser;
import Utils.position;

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

    public String get_type(String type)
    {
        String ans = "";
        for (int i = 0; i < type.length(); i++)
        {
            if (type.charAt(i) == '[')
                break;

            ans += type.charAt(i);
        }

        return ans;
    }

    @Override public ASTNode visitProgram(MxstarParser.ProgramContext ctx)
    {
        RootNode now = new RootNode(new position(ctx));

        if (ctx.functionDef() != null)
            ctx.functionDef().forEach(i -> now.func.add((FuncDefNode) visit(i)));
        if (ctx.classDef() != null)
            ctx.classDef().forEach(i -> now.cls.add((ClassDefNode) visit(i)));
        if (ctx.varDef() != null)
            ctx.varDef().forEach(i -> now.var.add((VarDefNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitClassDef(MxstarParser.ClassDefContext ctx)
    {
        ClassDefNode now = new ClassDefNode(new position(ctx), ctx.Identifier().getText());

        if (ctx.constructFuncDef() != null)
        {
            for (var i : ctx.constructFuncDef())
            {
                FuncDefNode func = (FuncDefNode) visit(i);
                // func.return_type = ctx.Identifier().getText();
                now.func.add(func);
            }
        }
        else
        {
            SuiteNode st = new SuiteNode(new position(ctx));
            FuncDefNode func = new FuncDefNode(new position(ctx), 0, ctx.Identifier().getText(), ctx.Identifier().getText(), st);
            now.func.add(func);
        }
        if (ctx.functionDef() != null)
            ctx.functionDef().forEach(i -> now.func.add((FuncDefNode) visit(i)));
        if (ctx.varDef() != null)
            ctx.varDef().forEach(i -> now.var.add((VarDefNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitFunctionDef(MxstarParser.FunctionDefContext ctx)
    {
        StNode st = (StNode) visit(ctx.suite());
        String return_type = ctx.returnType().getText();
        FuncDefNode now = new FuncDefNode(new position(ctx), get_dim(return_type), get_type(return_type), ctx.Identifier().getText(), st);
        if (ctx.funcVarDef() != null)
            ctx.funcVarDef().forEach(i -> now.var.add((FuncVarDefNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitConstructFuncDef(MxstarParser.ConstructFuncDefContext ctx)
    {
        StNode st = (StNode) visit(ctx.suite());
        FuncDefNode now = new FuncDefNode(new position(ctx), 0, null, ctx.Identifier().getText(), st);
        if (ctx.funcVarDef() != null)
            ctx.funcVarDef().forEach(i -> now.var.add((FuncVarDefNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitSuite(MxstarParser.SuiteContext ctx)
    {
        SuiteNode now = new SuiteNode(new position(ctx));
        if (ctx.statement() != null)
            ctx.statement().forEach(i -> now.st.add((StNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitBlockTag(MxstarParser.BlockTagContext ctx)
    {
        return (SuiteNode) visit(ctx.suite());
    }

    @Override public ASTNode visitIfTag(MxstarParser.IfTagContext ctx)
    {
        return (IfStNode) visit(ctx.ifStat());
    }    

    @Override public ASTNode visitReturnTag(MxstarParser.ReturnTagContext ctx)
    {
        ExprNode expr = null;
        if (ctx.expression() != null)
            expr = (ExprNode) visit(ctx.expression());
        ReturnNode now = new ReturnNode(new position(ctx), expr);
        return now;
    }

    @Override public ASTNode visitWhileTag(MxstarParser.WhileTagContext ctx)
    {
        if (ctx == null) return null;
        return (WhileStNode) visit(ctx.whileStat());
    }

    @Override public ASTNode visitForTag(MxstarParser.ForTagContext ctx)
    {
        if (ctx == null) return null;
        return (ForStNode) visit(ctx.forStat());
    }

    @Override public ASTNode visitBreakTag(MxstarParser.BreakTagContext ctx)
    {
        return new BreakStNode(new position(ctx));
    }

    @Override public ASTNode visitContinueTag(MxstarParser.ContinueTagContext ctx)
    {
        return new ContinueStNode(new position(ctx));
    }

    @Override public ASTNode visitVarTag(MxstarParser.VarTagContext ctx)
    {
        return (VarDefNode) visit(ctx.varDef());
    }

    @Override public ASTNode visitExprTag(MxstarParser.ExprTagContext ctx)
    {
        return new ExprStNode(new position(ctx), (ExprNode) visit(ctx.expression()));
    }

    @Override public ASTNode visitSemiTag(MxstarParser.SemiTagContext ctx)
    {
        return new SemiStNode(new position(ctx));
    }

    @Override public ASTNode visitFuncVarDef(MxstarParser.FuncVarDefContext ctx)
    {
        String type = ctx.varType().getText();
        OneVarDefNode one_var = (OneVarDefNode) visit(ctx.oneVarDef());
        FuncVarDefNode now = new FuncVarDefNode(new position(ctx), get_type(type), get_dim(type), one_var);

        return now;
    }

    @Override public ASTNode visitVarDef(MxstarParser.VarDefContext ctx)
    {
        String type = ctx.varType().getText();
        VarDefNode now = new VarDefNode(new position(ctx), get_type(type), get_dim(type));
        if (ctx.oneVarDef() != null)
            ctx.oneVarDef().forEach(i -> now.var.add((OneVarDefNode) visit(i)));

        return now;
    }

    @Override public ASTNode visitOneVarDef(MxstarParser.OneVarDefContext ctx)
    {
        ExprNode val = null;
        if (ctx.expression() != null)
            val = (ExprNode) visit(ctx.expression());
        OneVarDefNode now = new OneVarDefNode(new position(ctx), ctx.Identifier().getText(), val);

        return now;
    }

    @Override public ASTNode visitPrefixTag(MxstarParser.PrefixTagContext ctx)
    {
        String op = ctx.op.getText();
        ExprNode obj = (ExprNode) visit(ctx.expression());
        PrefixExprNode now = new PrefixExprNode(new position(ctx), op, obj);

        return now;
    }

    @Override public ASTNode visitObjTag(MxstarParser.ObjTagContext ctx)
    {
        String idt = ctx.Identifier().getText();
        ExprNode obj = (ExprNode) visit(ctx.expression());
        ObjExprNode now = new ObjExprNode(new position(ctx), obj, idt);

        return now;
    }    

    @Override public ASTNode visitNvarTag(MxstarParser.NvarTagContext ctx)
    {
        String type = ctx.newType().getText();
        NvarExprNode now = new NvarExprNode(new position(ctx), get_type(type), get_dim(type));
        ctx.newType().expression().forEach(i -> now.expr.add((ExprNode) visit(i)));
        return now;
    }

    @Override public ASTNode visitAssignTag(MxstarParser.AssignTagContext ctx)
    {
        ExprNode lhs = (ExprNode) visit(ctx.expression(0));
        ExprNode rhs = (ExprNode) visit(ctx.expression(1));
        AssignExprNode now = new AssignExprNode(new position(ctx), lhs, rhs);

        return now;
    }

    @Override public ASTNode visitBinaryTag(MxstarParser.BinaryTagContext ctx)
    {
        String op = ctx.op.getText();
        ExprNode lhs = (ExprNode) visit(ctx.expression(0));
        ExprNode rhs = (ExprNode) visit(ctx.expression(1));

        BinaryExprNode now = new BinaryExprNode(new position(ctx), op, lhs, rhs);

        return now;
    }

    @Override public ASTNode visitSuffixTag(MxstarParser.SuffixTagContext ctx)
    {
        String op = ctx.op.getText();
        ExprNode obj = (ExprNode) visit(ctx.expression());
        SuffixExprNode now = new SuffixExprNode(new position(ctx), op, obj);

        return now;
    }

    @Override public ASTNode visitPrimaryTag(MxstarParser.PrimaryTagContext ctx)
    {
        int type = 0;
        if (ctx.primary().Number() != null)
            type = 1;
        else if (ctx.primary().Null() != null)
            type = 2;
        else if (ctx.primary().This() != null)
            type = 3;
        else if (ctx.primary().True() != null)
            type = 4;
        else if (ctx.primary().False() != null)
            type = 5;
        else if (ctx.primary().ConstString() != null)
            type = 6;

        PrimaryExprNode now = new PrimaryExprNode(new position(ctx), type, ctx.primary().getText());

        return now;
    }

    @Override public ASTNode visitExprinTag(MxstarParser.ExprinTagContext ctx)
    {
        BracketExprNode now = new BracketExprNode(new position(ctx), (ExprNode) visit(ctx.expression()));

        return now;
    }

    @Override public ASTNode visitAddrTag(MxstarParser.AddrTagContext ctx)
    {
        ExprNode ptr = (ExprNode) visit(ctx.expression(0));
        ExprNode offset = (ExprNode) visit(ctx.expression(1));
        AddrExprNode now = new AddrExprNode(new position(ctx), ptr, offset);

        return now;
    }

    @Override public ASTNode visitCallTag(MxstarParser.CallTagContext ctx) 
    {
        ExprNode obj = (ExprNode) visit(ctx.expression(0));
        CallExprNode now = new CallExprNode(new position(ctx), obj);

        for (int i = 1; i < ctx.expression().size(); i++)
            now.para.add((ExprNode) visit(ctx.expression(i)));

        return now;
    }

    @Override public ASTNode visitIfStat(MxstarParser.IfStatContext ctx)
    {
        ExprNode cond = (ExprNode) visit(ctx.expression());
        StNode true_st = (StNode) visit(ctx.trueSt);
        StNode false_st = null;
        if (ctx.falseSt != null)
            false_st = (StNode) visit(ctx.falseSt);
        IfStNode now = new IfStNode(new position(ctx), cond, true_st, false_st);

        return now;
    }

    @Override public ASTNode visitWhileStat(MxstarParser.WhileStatContext ctx)
    {
        ExprNode cond = (ExprNode) visit(ctx.expression());
        StNode st = (StNode) visit(ctx.statement());
        WhileStNode now = new WhileStNode(new position(ctx), cond, st);

        return now;
    }

    @Override public ASTNode visitForStat(MxstarParser.ForStatContext ctx)
    {
        VarDefNode var = null;
        ExprNode init = null, cond = null, next = null;
        if (ctx.varDef() != null)
            var = (VarDefNode) visit(ctx.varDef());
        if (ctx.init != null)
            init = (ExprNode) visit(ctx.init);
        if (ctx.cond != null)
            cond = (ExprNode) visit(ctx.cond);
        if (ctx.next != null)
            next = (ExprNode) visit(ctx.next);
        StNode st = (StNode) visit(ctx.statement());

        ForStNode now = new ForStNode(new position(ctx), var, init, cond, next, st);

        return now;
    }

    @Override public ASTNode visitLambdaFunc(MxstarParser.LambdaFuncContext ctx)
    {
        LambdaExprNode now = new LambdaExprNode(new position(ctx), (StNode) visit(ctx.suite()));
        ctx.funcVarDef().forEach(i -> now.var.add((FuncVarDefNode) visit(i)));
        ctx.expression().forEach(i -> now.para.add((ExprNode) visit(i)));

        return now;
    }
}