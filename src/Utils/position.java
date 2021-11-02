package Utils;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ParserRuleContext;


public class position 
{
    public int row, col;

    public position()
    {
        this.row = 0;
        this.col = 0;
    }

    public position(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    public position(Token token)
    {
        this.row = token.getLine();
        this.col = token.getCharPositionInLine();
    }

    public position(ParserRuleContext ctx)
    {
        this(ctx.getStart());
    }

    public String toString()
    {
        return "(" + row + ", " + col + ")";
    }
}
