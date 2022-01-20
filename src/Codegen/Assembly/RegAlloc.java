package Codegen.Assembly;

import java.util.ArrayList;

import Codegen.Assembly.ASMBlock.ASMGlobal;
import Codegen.Assembly.ASMInst.ASMBinary;
import Codegen.Assembly.ASMInst.ASMInst;
import Codegen.Assembly.ASMInst.ASMLi;
import Codegen.Assembly.ASMInst.ASMLoad;
import Codegen.Assembly.ASMInst.ASMRet;
import Codegen.Assembly.ASMInst.ASMStore;
import Codegen.Assembly.ASMInst.ASMBinary.binary_op_type;
import Codegen.Assembly.ASMInst.ASMLoad.load_op_type;
import Codegen.Assembly.ASMInst.ASMStore.store_op_type;
import Codegen.Assembly.ASMValue.Immediate;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

public class RegAlloc 
{
    public RegAlloc(ASMGlobal global)
    {
        for (var func : global.funcs)
        {
            int offset = 0;
            for (var call : func.call)
            {
                offset = Integer.max(offset, call.size());
                for (int i = 0; i < call.size(); i++)
                    call.get(i).offset = new Immediate(i << 2);
            }

            offset <<= 2;

            for (var block : func.blocks)
            {
                for (var inst : block.asm_ins)
                {
                    
                    for (var x : inst.read_reg)
                    if (x instanceof VirReg)
                    {
                        VirReg reg = (VirReg) x;
                        if (reg.addr.offset == null)
                        {
                            reg.addr.offset = new Immediate(offset);
                            offset += 4;
                        }
                    }

                    for (var x : inst.write_reg)
                    if (x instanceof VirReg)
                    {
                        VirReg reg = (VirReg) x;
                        if (reg.addr.offset == null)
                        {
                            reg.addr.offset = new Immediate(offset);
                            offset += 4;
                        }
                    }
                }
            }

            for (int i = 0; i < func.alloca.size(); i++)
            {
                func.alloca.get(i).offset.val = offset;
                offset += 4;
            }

            for (int i = 0; i < func.para.size(); i++)
            {
                func.para.get(i).offset = new Immediate(offset + i * 4);
                System.out.println(offset + i * 4);
            }

            for (var block : func.blocks)
            {
                ArrayList<ASMInst> new_ins = new ArrayList<>();
                if (block == func.blocks.get(0))
                {
                    if (offset < 2048)
                        new_ins.add(new ASMBinary(binary_op_type.addi, new PhyReg("sp"), new PhyReg("sp"), new Immediate(-offset)));
                    else
                    {
                        new_ins.add(new ASMLi(new PhyReg("s0"), new Immediate(offset)));
                        new_ins.add(new ASMBinary(binary_op_type.sub, new PhyReg("sp"), new PhyReg("sp"), new PhyReg("s0")));
                    }
                }

                for (var inst : block.asm_ins)
                {
                    int phy_cnt = 0;
                    for (var x : inst.read_reg)
                    if (x instanceof VirReg)
                    {
                        VirReg vir_reg = (VirReg)x;
                        PhyReg phy_reg = new PhyReg("s" + phy_cnt);
                        phy_cnt++;
                        new_ins.add(new ASMLoad(load_op_type.lw, phy_reg, vir_reg.addr));
                        inst.change((VirReg)x, phy_reg);
                    }

                    if (inst instanceof ASMRet)
                    {
                        if (offset < 2048)
                            new_ins.add(new ASMBinary(binary_op_type.addi, new PhyReg("sp"), new PhyReg("sp"), new Immediate(offset)));
                        else
                        {
                            new_ins.add(new ASMLi(new PhyReg("s0"), new Immediate(offset)));
                            new_ins.add(new ASMBinary(binary_op_type.add, new PhyReg("sp"), new PhyReg("sp"), new PhyReg("s0")));
                        }
                    }
                    new_ins.add(inst);

                    for (var x : inst.write_reg)
                    if (x instanceof VirReg)
                    {
                        VirReg vir_reg = (VirReg)x;
                        PhyReg phy_reg = new PhyReg("s" + phy_cnt);
                        phy_cnt++;
                        new_ins.add(new ASMStore(store_op_type.sw, phy_reg, vir_reg.addr));
                        inst.change((VirReg)x, phy_reg);
                    }
                }

                block.asm_ins = new_ins;
            }            
        }
    }    
}
