package Semantic.AST;

import Semantic.AST.Node.position;

public class semanticError extends RuntimeException
{
    public position pos;   
    public String err;

    public semanticError(position pos, String err)
    {
        this.pos = pos;
        this.err = "Semantic Error : " + err;
    }
}
