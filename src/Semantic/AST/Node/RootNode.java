package Semantic.AST.Node;

import java.util.ArrayList;
import Semantic.AST.ASTVisitor;
import Utils.position;

public class RootNode extends ASTNode
{
    public ArrayList<FuncDefNode> func_list = new ArrayList<>();
    public ArrayList<VarDefNode> var_list = new ArrayList<>();
    public ArrayList<ClassDefNode> cls_list = new ArrayList<>();

    public RootNode(position pos)
    {
        super(pos);
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
