package Semantic.AST.Node;

import Codegen.IR.IRValue.IRValue;
import Codegen.IR.IRValue.Register;
import Semantic.AST.ASTVisitor;
import Utils.position;

public class OneVarDefNode extends ASTNode
{
    public String idt, type;
    public int dim;
    public ExprNode init_val;
    public ClassDefNode belong = null;
    public Integer offset = 0;

    // IR
    public IRValue val = null;
    public Register ptr = null;

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
