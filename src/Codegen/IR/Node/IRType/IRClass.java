package Codegen.IR.Node.IRType;

import java.util.ArrayList;

public class IRClass extends IRType
{
    public ArrayList<IRType> vars = new ArrayList<>();

    public IRClass(String name)
    {
        super(name, 0);
    }

    @Override public int get_size()
    {
        int sum = 0;
        for (int i = 0; i < vars.size(); i++)
            sum += vars.get(i).get_size();
        return sum;
    }

    public String toString()
    {
        return "%" + name;
    }

    public String declare()
    {
        String s = toString();
        s += " = type { ";
        for (int i = 0; i < vars.size(); i++)
            s += vars.get(i).toString() + ((i != vars.size() - 1) ? ", " : "");
        s += " }";
        return s;
    }

    public int get_offset(int pos)
    {
        int ans = 0;
        for (int i = 0; i < pos; i++)
        {
            ans += vars.get(i).get_size();
        }
        return ans;
    }
}
