package Semantic.AST.Node;

public class ObjNode extends ExprNode
{
    public String idt;
    public ExprNode obj, para;
    public boolean isfunc;

    public ObjNode(position pos, String idt, ExprNode obj, ExprNode para, boolean isfunc)
    {
        super(pos);
        this.idt = idt;
        this.obj = obj;
        this.para = para;
        this.isfunc = isfunc;
    }
}
