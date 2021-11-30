package Codegen.IR;

import Semantic.AST.ASTVisitor;
import Semantic.AST.Node.*;

public class IRBuilder extends ASTVisitor
{
    public Block now_block = null;

    public void visit(RootNode now)
    {
        now.func.forEach(i -> i.accept(this));
    }

    public void visit(ClassDefNode now)
    {

    }

    public void visit(FuncDefNode now)
    {

    }

    public void visit(ReturnNode now)
    {

    }

    public void visit(FuncVarDefNode now)
    {
    }

    public void visit(VarDefNode now)
    {
    }

    public void visit(OneVarDefNode now)
    {
    }

    public void visit(SuiteNode now)
    {
    }

    public void visit(AddrExprNode now)
    {
    }

    public void visit(AssignExprNode now)
    {
    }

    public void visit(BinaryExprNode now)
    {
    }

    public void visit(BraExprNode now)
    {
    }

    public void visit(CallExprNode now)
    {
    }

    public void visit(NvarExprNode now)
    {
    }

    public void visit(ObjExprNode now)
    {

    }

    public void visit(PrefixExprNode now)
    {
    }

    public void visit(SuffixExprNode now)
    {
    }

    public void visit(PrimaryExprNode now)
    {
    }

    public void visit(SemiStNode now)
    {
    }

    public void visit(BreakStNode now)
    {
    }

    public void visit(ContinueStNode now)
    {
    }

    public void visit(ExprStNode now)
    {
    }

    public void visit(ForStNode now)
    {
    }

    public void visit(WhileStNode now)
    {
    }

    public void visit(IfStNode now)
    {
    }
}
