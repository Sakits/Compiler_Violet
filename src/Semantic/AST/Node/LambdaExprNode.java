package Semantic.AST.Node;

import java.util.ArrayList;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class LambdaExprNode extends ExprNode
{
    public ArrayList<FuncVarDefNode> var_list = new ArrayList<>();
    public ArrayList<ExprNode> para = new ArrayList<>();
    public StNode st;

    public LambdaExprNode (position pos, StNode st)
    {
        super(pos);
        this.st = st;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
