package Codegen.Assembly;

import java.util.ArrayList;

import Codegen.Assembly.ASMBlock.ASMBlock;
import Codegen.Assembly.ASMBlock.ASMFunc;
import Codegen.Assembly.ASMBlock.ASMGlobal;
import Codegen.Assembly.ASMInst.ASMBinary;
import Codegen.Assembly.ASMInst.ASMBranch;
import Codegen.Assembly.ASMInst.ASMCall;
import Codegen.Assembly.ASMInst.ASMJudge;
import Codegen.Assembly.ASMInst.ASMJump;
import Codegen.Assembly.ASMInst.ASMLa;
import Codegen.Assembly.ASMInst.ASMLi;
import Codegen.Assembly.ASMInst.ASMLoad;
import Codegen.Assembly.ASMInst.ASMMv;
import Codegen.Assembly.ASMInst.ASMRet;
import Codegen.Assembly.ASMInst.ASMStore;
import Codegen.Assembly.ASMInst.ASMBinary.binary_op_type;
import Codegen.Assembly.ASMInst.ASMBranch.branch_op_type;
import Codegen.Assembly.ASMInst.ASMJudge.judge_op_type;
import Codegen.Assembly.ASMInst.ASMLoad.load_op_type;
import Codegen.Assembly.ASMInst.ASMStore.store_op_type;
import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMHeapAddr;
import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.Immediate;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;
import Codegen.Assembly.ASMValue.ASMHeapAddr.var_type;
import Codegen.IR.IRBlock.IRBlock;
import Codegen.IR.IRBlock.IRFunc;
import Codegen.IR.IRBlock.IRGlobal;
import Codegen.IR.IRInst.*;
import Codegen.IR.IRType.IRBool;
import Codegen.IR.IRType.IRClass;
import Codegen.IR.IRType.IRInt;
import Codegen.IR.IRType.IRPointer;
import Codegen.IR.IRValue.ConstString;
import Codegen.IR.IRValue.Constant;
import Codegen.IR.IRValue.IRValue;
import Codegen.IR.IRValue.Register;

public class ASMBuilder 
{
    public ASMFunc now_func;
    public ASMBlock now_block;
    public ASMGlobal global;

    public ASMBuilder()
    {
        this.global = new ASMGlobal();
    }

    public ASMReg get_reg(IRValue irval)
    {
        if (irval.val != null) return irval.val;

        ASMReg ans = PhyReg.phy_regs.get("zero");
        if (irval instanceof Constant)
        {
            int val = ((Constant)irval).val;
            if (irval.type instanceof IRInt)
            {
                if (val != 0)
                {
                    ans = new VirReg("const_int");
                    Immediate imm = new Immediate(val);
                    if (-2048 <= val && val < 2048)
                        now_block.asm_ins.add(new ASMBinary(binary_op_type.addi, ans, PhyReg.phy_regs.get("zero"), imm));
                    else
                        now_block.asm_ins.add(new ASMLi(ans, imm));
                }
                else
                    ans = PhyReg.phy_regs.get("zero");
            }

            if (irval.type instanceof IRBool)
            {
                if (val != 0)
                {
                    ans = new VirReg("const_bool");
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.addi, ans, PhyReg.phy_regs.get("zero"), new Immediate(1)));
                }
                else
                    ans = PhyReg.phy_regs.get("zero");
            }
        }
        else
            ans = new VirReg(((Register)irval).idt);

        return irval.val = ans;
    }

    public void visit(IRGlobal now) 
    {
        now.strs.forEach(i -> {
            ASMHeapAddr addr = new ASMHeapAddr("str_" + i.num);
            global.vars.add(addr);
            i.addr = addr;

            addr.type = var_type.glo_string;
            addr.s = i.s;
        });

        now.vars.forEach(i -> {
            ASMHeapAddr addr = new ASMHeapAddr(i.idt);
            global.vars.add(addr);
            i.addr = addr;
            
            IRPointer type = (IRPointer) i.type;

            if (type.dim == 1)
            {
                if (type.basic_type instanceof IRInt)
                    addr.type = var_type.glo_int;
                else
                    addr.type = var_type.glo_bool;  
            }
            else
                addr.type = var_type.glo_int;
            addr.val = i.init_val.val;
        });

        now.funcs.forEach(i -> i.func = new ASMFunc(i.name));
        now.funcs.forEach(i -> {
            if (!i.is_builtin)
            {
                global.funcs.add(i.func);
                i.accept(this);
            }
        });
    }

    public void visit(IRFunc now) 
    {
        now_func = now.func;
        now.blocks.forEach(i -> {
            i.block = new ASMBlock("." + i.tag);
            now_func.blocks.add(i.block);
        });
        now_block = now.blocks.get(0).block;

        VirReg ra_store = new VirReg("ra_store");
        now_func.ra = ra_store;
        now_block.asm_ins.add(new ASMMv(ra_store, PhyReg.phy_regs.get("ra")));

        for (var i = 0; i < 32; i++)
            if (PhyReg.type_list.get(i) == 1)
            {
                VirReg callee_store = new VirReg(PhyReg.name_list.get(i) + "_save");
                now_func.callees.add(callee_store);
                now_block.asm_ins.add(new ASMMv(callee_store, PhyReg.phy_regs.get(PhyReg.name_list.get(i))));
            }
            else
                now_func.callees.add(null);

        for (int i = 0; i < Integer.min(8, now.para.size()); i++)
            now_block.asm_ins.add(new ASMMv(get_reg(now.para.get(i)), PhyReg.phy_regs.get("a" + i)));
        
        for (int i = 8; i < now.para.size(); i++)
        {
            VirReg reg = (VirReg)get_reg(now.para.get(i));
            now_func.para.add(reg.addr);
            now_block.asm_ins.add(new ASMLoad(load_op_type.lw, reg, reg.addr));
        }

        now.blocks.forEach(i -> i.accept(this));
    }

    public void visit(IRBlock now) 
    {
        now_block = now.block;
        now.irst.forEach(i -> i.accept(this));
        now_block = null;
    }

    void const_binary(binary_op_type op, binary_op_type opi, ASMReg rd, ASMReg rs1, int val)
    {
        if (-2048 <= val && val < 2048)
            now_block.asm_ins.add(new ASMBinary(opi, rd, rs1, new Immediate((val))));
        else
        {
            now_block.asm_ins.add(new ASMLi(rd, new Immediate(val)));
            now_block.asm_ins.add(new ASMBinary(op, rd, rd, rs1));
        }
    }

    public void visit(IRBinaryExpr now) 
    {
        ASMReg lhs = get_reg(now.lhs);
        ASMReg ans = get_reg(now.dest);

        switch (now.op)
        {
            case add ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    const_binary(binary_op_type.add, binary_op_type.addi, ans, lhs, val);
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.add, ans, lhs, get_reg(now.rhs)));
            }

            case sub ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    const_binary(binary_op_type.add, binary_op_type.addi, ans, lhs, -val);
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.sub, ans, lhs, get_reg(now.rhs)));
            }

            case mul ->
            {
                now_block.asm_ins.add(new ASMBinary(binary_op_type.mul, ans, lhs, get_reg(now.rhs)));
            }

            case sdiv ->
            {
                assert !get_reg(now.rhs).equals(PhyReg.phy_regs.get("zero"));
                now_block.asm_ins.add(new ASMBinary(binary_op_type.div, ans, lhs, get_reg(now.rhs)));
            }

            case srem ->
            {
                assert !get_reg(now.rhs).equals(PhyReg.phy_regs.get("zero"));
                now_block.asm_ins.add(new ASMBinary(binary_op_type.rem, ans, lhs, get_reg(now.rhs)));
            }

            case shl ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.slli, ans, lhs, new Immediate((val))));
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.sll, ans, lhs, get_reg(now.rhs)));
            }

            case ashr ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.srai, ans, lhs, new Immediate((val))));
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.sra, ans, lhs, get_reg(now.rhs)));
            }

            case and ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    const_binary(binary_op_type.and, binary_op_type.andi, ans, lhs, val);
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.and, ans, lhs, get_reg(now.rhs)));
            }

            case or ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    const_binary(binary_op_type.or, binary_op_type.ori, ans, lhs, val);
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.or, ans, lhs, get_reg(now.rhs)));
            }

            case xor ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    const_binary(binary_op_type.xor, binary_op_type.xori, ans, lhs, val);
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.xor, ans, lhs, get_reg(now.rhs)));
            }
        }
    }

    public void visit(IRBranch now) 
    {
        now_block.asm_ins.add(new ASMBranch(branch_op_type.beqz, get_reg(now.cond), null, now.false_block.block));
        now_block.asm_ins.add(new ASMJump(now.true_block.block));
        now_block.succ.add(now.false_block.block);
        now_block.succ.add(now.true_block.block);
    }

    public void visit(IRCall now) 
    {
        for (int i = 0; i < Integer.min(8, now.para.size()); i++)
            now_block.asm_ins.add(new ASMMv(PhyReg.phy_regs.get("a" + i), get_reg(now.para.get(i))));

        ArrayList<ASMAddr> call = new ArrayList<>();
        for (int i = 8; i < now.para.size(); i++)
        {
            ASMAddr call_addr = new ASMAddr();
            call.add(call_addr);
            now_block.asm_ins.add(new ASMStore(store_op_type.sw, get_reg(now.para.get(i)), call_addr));
        }
        now_func.call.add(call);

        now_block.asm_ins.add(new ASMCall(now.func.func));

        if (now.dest != null)
            now_block.asm_ins.add(new ASMMv(get_reg(now.dest), PhyReg.phy_regs.get("a0")));
    }

    public void visit(IRCmp now) 
    {
        ASMReg lhs = get_reg(now.lhs);
        ASMReg ans = get_reg(now.dest);
        VirReg tmp = new VirReg("cmp_tmp");

        switch (now.op)
        {
            case eq ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    const_binary(binary_op_type.add, binary_op_type.addi, tmp, lhs, -val);
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.sub, tmp, lhs, get_reg(now.rhs)));

                now_block.asm_ins.add(new ASMJudge(judge_op_type.seqz, ans, tmp));
            }

            case ne ->
            {
                if (now.rhs instanceof Constant)
                {
                    int val = ((Constant) now.rhs).val;
                    const_binary(binary_op_type.add, binary_op_type.addi, tmp, lhs, -val);
                }
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.sub, tmp, lhs, get_reg(now.rhs)));

                    now_block.asm_ins.add(new ASMJudge(judge_op_type.snez, ans, tmp));
            }

            case sge ->
            {
                ASMReg rhs = get_reg(now.rhs);
                now_block.asm_ins.add(new ASMBinary(binary_op_type.slt, tmp, lhs, rhs));
                now_block.asm_ins.add(new ASMBinary(binary_op_type.xori, ans, tmp, new Immediate(1)));
            }

            case sgt ->
            {
                ASMReg rhs = get_reg(now.rhs);
                now_block.asm_ins.add(new ASMBinary(binary_op_type.slt, ans, rhs, lhs));
            }

            case sle ->
            {
                ASMReg rhs = get_reg(now.rhs);
                now_block.asm_ins.add(new ASMBinary(binary_op_type.slt, tmp, rhs, lhs));
                now_block.asm_ins.add(new ASMBinary(binary_op_type.xori, ans, tmp, new Immediate(1)));
            }

            case slt ->
            {
                ASMReg rhs = get_reg(now.rhs);
                now_block.asm_ins.add(new ASMBinary(binary_op_type.slt, ans, lhs, rhs));
            }
        }
    }

    public void visit(IRGet now) 
    {
        ASMReg ans = get_reg(now.dest);

        if (now.ptr instanceof Register && ((Register)now.ptr).is_global)
        {
            now_block.asm_ins.add(new ASMLa(ans, ((Register)now.ptr).addr));
            return;
        }

        if (now.ptr instanceof ConstString)
        {
            now_block.asm_ins.add(new ASMLa(ans, ((ConstString)now.ptr).addr));
            return;
        }

        ASMReg ptr = get_reg(now.ptr);
        if (!now.is_class)
        {
            if (now.offset instanceof Constant)
            {
                int val = 4 * ((Constant)now.offset).val;
                if (-2048 <= val && val < 2048)
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.addi, ans, ptr, new Immediate(val)));
                else
                    now_block.asm_ins.add(new ASMBinary(binary_op_type.add, ans, ptr, get_reg(new Constant(new IRInt(), val))));
            }
            else
            {
                ASMReg tmp = new VirReg("get_ele_tmp");
                now_block.asm_ins.add(new ASMBinary(binary_op_type.slli, tmp, get_reg(now.offset), new Immediate(2)));
                now_block.asm_ins.add(new ASMBinary(binary_op_type.add, ans, ptr, tmp));
            }
        }
        else
        {
            int offset = ((Constant)now.offset).val;
            int val = ((IRClass)((IRPointer)now.ptr.type).basic_type).get_offset(offset);
            if (-2048 <= val && val < 2048)
                now_block.asm_ins.add(new ASMBinary(binary_op_type.addi, ans, ptr, new Immediate(val)));
            else
                now_block.asm_ins.add(new ASMBinary(binary_op_type.add, ans, ptr, get_reg(new Constant(new IRInt(), val))));
        }
    }

    public void visit(IRJump now) 
    {
        now_block.asm_ins.add(new ASMJump(now.dest_block.block));
        now_block.succ.add(now.dest_block.block);
    }

    public void visit(IRLoad now) 
    {
        ASMReg ans = get_reg(now.dest);
        ASMReg addr;
        if (now.ptr instanceof Register && ((Register)now.ptr).is_global)
        {
            addr = new VirReg("global_addr");
            now_block.asm_ins.add(new ASMLa(addr, ((Register)now.ptr).addr));
        }
        else
            addr = get_reg(now.ptr);

        if (now.type.name.equals("bool"))
            now_block.asm_ins.add(new ASMLoad(load_op_type.lb, ans, new ASMAddr(addr, new Immediate(0))));
        else
            now_block.asm_ins.add(new ASMLoad(load_op_type.lw, ans, new ASMAddr(addr, new Immediate(0))));
    }

    public void visit(IRStore now) 
    {
        ASMReg val = get_reg(now.val);
        ASMReg addr;
        if (now.ptr instanceof Register && ((Register)now.ptr).is_global)
        {
            addr = new VirReg("global_addr");
            now_block.asm_ins.add(new ASMLa(addr, ((Register)now.ptr).addr));
        }
        else
            addr = get_reg(now.ptr);
            
        if (now.val.type.name.equals("bool"))
            now_block.asm_ins.add(new ASMStore(store_op_type.sb, val, new ASMAddr(addr, new Immediate(0))));
        else
            now_block.asm_ins.add(new ASMStore(store_op_type.sw, val, new ASMAddr(addr, new Immediate(0))));
    }

    public void visit(IRMove now)
    {
        now_block.asm_ins.add(new ASMMv(get_reg(now.dest), get_reg(now.src)));
    }

    public void visit(IRRet now) 
    {
        if (!now.val.type.toString().equals("void"))
            now_block.asm_ins.add(new ASMMv(PhyReg.phy_regs.get("a0"), get_reg(now.val)));

        now_block.asm_ins.add(new ASMMv(PhyReg.phy_regs.get("ra"), now_func.ra));
        for (var i = 0; i < 32; i++)
        if (PhyReg.type_list.get(i) == 1)
            now_block.asm_ins.add(new ASMMv(PhyReg.phy_regs.get(PhyReg.name_list.get(i)), now_func.callees.get(i)));

        now_block.asm_ins.add(new ASMRet());
    }

    public void visit(IRInst now) {}
}
