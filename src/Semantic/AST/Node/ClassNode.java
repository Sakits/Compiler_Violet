package Semantic.AST.Node;

import java.util.HashMap;

public class ClassNode extends ASTNode
{
    public String idt;
    public HashMap<String, FuncNode> func = new HashMap<>();
    public HashMap<String, VarNode> var = new HashMap<>();

    public ClassNode(position pos, String idt)
    {
        super(pos);
        this.idt = idt;
    }
}
