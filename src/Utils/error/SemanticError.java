package Utils.error;

import Utils.position;

public class SemanticError extends Error
{
    public String err;

    public SemanticError(position pos, String err)
    {
        super(pos);
        this.err = "Semantic Error at " + pos.toString() + " : " + err;
    }

    public String toString()
    {
        return err;
    }
}
