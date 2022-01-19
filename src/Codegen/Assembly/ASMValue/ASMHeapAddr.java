package Codegen.Assembly.ASMValue;

public class ASMHeapAddr extends ASMValue
{
    public enum var_type {glo_bool, glo_int, glo_string, glo_void}

    public String name, s;
    public var_type type;
    public int val = 0;

    public ASMHeapAddr(String name)
    {
        super();
        this.name = name;
        this.type = var_type.glo_void;
    }

    public String toString() 
    {
        switch (type) {
            case glo_bool -> { return ".byte\t" + val + " ".repeat(23) + "# " + (val == 1); }
            case glo_int -> 
            {
                return ".word\t" + Integer.toUnsignedLong(val)
                        + " ".repeat(24 - Integer.toUnsignedString(val).length()) 
                        + "# " + val;
            }
            case glo_string -> 
            {
                return ".asciz\t\"" + s.replace("\\", "\\\\")
                        .replace("\n", "\\n")
                        .replace("\"", "\\\"") + "\"";
            }
            case glo_void ->
            {
                return null;
            }
        }

        return null;
    }
}
