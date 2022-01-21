package Semantic.AST.Node;

import java.util.ArrayList;
import java.util.HashMap;

import Semantic.AST.ASTVisitor;
import Utils.position;

public class ClassDefNode extends ASTNode
{
    public String idt;
    public ArrayList<FuncDefNode> func_list = new ArrayList<>();
    public ArrayList<VarDefNode> var_list = new ArrayList<>();
    public HashMap<String, FuncDefNode> funcs = new HashMap<>();
    public HashMap<String, OneVarDefNode> vars = new HashMap<>();
    public Integer now_offset = 0;
    public FuncDefNode cons = null;

    public ClassDefNode(position pos, String idt)
    {
        super(pos);
        this.idt = idt;
    }

    public void accept(ASTVisitor visitor)
    {
        visitor.visit(this);
    }
}
