package Codegen.IR;

import Codegen.IR.Node.IRBlock.BasicBlock;
import Codegen.IR.Node.IRBlock.Function;
import Codegen.IR.Node.IRBlock.IRGlobal;
import Codegen.IR.Node.IRStat.*;

public interface IRVisitor 
{
    public void visit(IRGlobal global);
    public void visit(Function func);
    public void visit(BasicBlock block);

    public void visit(IRStat now);
    public void visit(IRAlloca now);
    public void visit(IRBinaryExpr now);
    public void visit(IRBitcast now);
    public void visit(IRBranch now);
    public void visit(IRCall now);
    public void visit(IRCmp now);
    public void visit(IRGetelementptr now);
    public void visit(IRJump now);
    public void visit(IRLoad now);
    public void visit(IRMalloc now);
    public void visit(IRPhi now);
    public void visit(IRRet now);
    public void visit(IRStore now);
}