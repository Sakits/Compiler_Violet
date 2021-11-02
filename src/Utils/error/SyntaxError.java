package Utils.error;

import Utils.position;

public class SyntaxError extends Error
{
    public String err;

    public SyntaxError(position pos, String err)
    {
        super(pos);
        this.err = "Syntax Error at " + pos.toString() + " : " + err;
    }

    public String toString()
    {
        return err;
    }
}
