package Codegen.IR;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.misc.Pair;

import Codegen.IR.Node.BaseIR;

public class Block 
{
    public int phi_reg = 0, now_offset = 0, lable = 0;
    public HashMap<String, ArrayList< Pair<Integer, Integer> > > mp = new HashMap<>();
    public ArrayList<BaseIR> ir = new ArrayList<>();
}
