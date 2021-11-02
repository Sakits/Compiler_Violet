package Utils.error;

import Utils.position;

public abstract class Error extends RuntimeException
{
    position pos;

    public Error(position pos)
    {
        this.pos = pos;
    }

    public abstract String toString();
}
