package Semantic.AST.Node;

import Codegen.IR.Node.IRValue.Register;
import Semantic.AST.ASTVisitor;
import Utils.position;

public class OneVarDefNode extends ASTNode
{
    public String idt, type;
    public int dim;
    public ExprNode init_val;
    public Register val = null;
    public ClassDefNode belong = null;
    public Integer offset = 0;

    public OneVarDefNode(position pos, String idt, ExprNode init_val)
    {
        super(pos);
        this.idt = idt;
        this.init_val = init_val;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
