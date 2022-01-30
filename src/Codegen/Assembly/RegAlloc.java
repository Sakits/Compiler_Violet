package Codegen.Assembly;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import org.antlr.v4.runtime.misc.Pair;

import Codegen.Assembly.ASMBlock.ASMBlock;
import Codegen.Assembly.ASMBlock.ASMFunc;
import Codegen.Assembly.ASMBlock.ASMGlobal;
import Codegen.Assembly.ASMInst.ASMBinary;
import Codegen.Assembly.ASMInst.ASMInst;
import Codegen.Assembly.ASMInst.ASMLi;
import Codegen.Assembly.ASMInst.ASMLoad;
import Codegen.Assembly.ASMInst.ASMMv;
import Codegen.Assembly.ASMInst.ASMRet;
import Codegen.Assembly.ASMInst.ASMStore;
import Codegen.Assembly.ASMInst.ASMBinary.binary_op_type;
import Codegen.Assembly.ASMInst.ASMLoad.load_op_type;
import Codegen.Assembly.ASMInst.ASMStore.store_op_type;
import Codegen.Assembly.ASMValue.ASMAddr;
import Codegen.Assembly.ASMValue.ASMReg;
import Codegen.Assembly.ASMValue.Immediate;
import Codegen.Assembly.ASMValue.PhyReg;
import Codegen.Assembly.ASMValue.VirReg;

public class RegAlloc 
{
    static final int K = 30;
    HashMap<ASMBlock, HashSet<ASMReg> > live_in_mp, live_out_mp, def_mp;
    void liveness_analysis(ASMFunc func)
    {
        def_mp = new HashMap<>();
        live_in_mp = new HashMap<>();
        live_out_mp = new HashMap<>();

        for (var block : func.blocks)
        {
            HashSet<ASMReg> def = new HashSet<>();
            HashSet<ASMReg> use = new HashSet<>();

            for (var inst : block.asm_ins)
            {
                for (var reg : inst.use)
                    if (!def.contains(reg))
                        use.add(reg);

                inst.def.forEach(i -> def.add(i));
            }

            def_mp.put(block, def);
            live_in_mp.put(block, use);
            live_out_mp.put(block, new HashSet<>());

            // System.out.println(block + ":");
            // def.forEach(i -> System.out.println(i));
            // System.out.println("==================");
        }

        while (true)
        {
            boolean flag = false;
            for (int i = func.blocks.size() - 1; i >= 0; i--)
            {
                ASMBlock block = func.blocks.get(i);
                HashSet<ASMReg> live_in = live_in_mp.get(block);
                HashSet<ASMReg> live_out = live_out_mp.get(block);
                int live_in_size = live_in.size();
                int live_out_size = live_out.size();

                live_out.removeAll(def_mp.get(block));
                live_in.addAll(live_out);
                block.succ.forEach(j -> live_out.addAll(live_in_mp.get(j)));

                assert live_out.size() == live_out_mp.get(block).size();

                flag |= live_in_size != live_in.size() || live_out_size != live_out.size();
            }

            if (!flag) break;
        }
    }

    HashSet<ASMReg> precolored_nodes = new HashSet<>();
    HashSet<ASMReg> initial_nodes = new HashSet<>();
    HashSet<ASMReg> simplify_nodes = new HashSet<>();
    HashSet<ASMReg> freeze_nodes = new HashSet<>();
    HashSet<ASMReg> spill_nodes = new HashSet<>();
    HashSet<ASMReg> spilled_nodes = new HashSet<>();
    HashSet<ASMReg> coalesced_nodes = new HashSet<>();
    HashSet<ASMReg> colored_nodes = new HashSet<>();
    Stack<ASMReg> select_nodes = new Stack<>();

    HashSet<ASMMv> coalesced_mvs = new HashSet<>();
    HashSet<ASMMv> constrained_mvs = new HashSet<>();
    HashSet<ASMMv> frozen_mvs = new HashSet<>();
    HashSet<ASMMv> worklist_mvs = new HashSet<>();
    HashSet<ASMMv> active_mvs = new HashSet<>();

    HashSet<Pair<ASMReg, ASMReg>> adj_set = new HashSet<>();
    HashMap<ASMReg, HashSet<ASMReg>> adj_list = new HashMap<>();
    HashMap<ASMReg, Integer> degree = new HashMap<>();
    HashMap<ASMReg, HashSet<ASMMv>> move_list = new HashMap<>();
    HashMap<ASMReg, ASMReg> alias = new HashMap<>();
    HashMap<ASMReg, Integer> color = new HashMap<>();

    HashMap<ASMReg, Integer> val_mp = new HashMap<>();

    void build(ASMFunc func)
    {
        // 通过 block 的 live_out 反推 block 中所有 inst 的 live_in / live_out
        for (var block : func.blocks)
        {
            HashSet<ASMReg> live_out = live_out_mp.get(block);
            for (int i = block.asm_ins.size() - 1; i >= 0; i--)
            {
                // 计算 spill 的代价
                ASMInst inst = block.asm_ins.get(i);
                inst.def.forEach(j -> val_mp.replace(j, val_mp.get(j) + (precolored_nodes.contains(j) ? 0 : 1)));
                inst.use.forEach(j -> val_mp.replace(j, val_mp.get(j) + (precolored_nodes.contains(j) ? 0 : 1)));

                if (inst instanceof ASMMv)
                {
                    // 若 t <- s，那么 (t, s) 可以不连冲突边
                    live_out.removeAll(inst.use);

                    // 维护 move_list, worklist_mvs
                    inst.def.forEach(j -> move_list.get(j).add((ASMMv) inst));
                    inst.use.forEach(j -> move_list.get(j).add((ASMMv) inst));
                    worklist_mvs.add((ASMMv) inst);
                }

                
                // 建立冲突图，def 往 live_out 连边
                live_out.addAll(inst.def);

                // System.out.println("inst");
                // System.out.println(inst);
                // live_out.forEach(j -> System.out.println(j));

                inst.def.forEach(j -> live_out.forEach(k -> add_edge(j, k)));

                // 更新 live_out
                live_out.removeAll(inst.def);  
                live_out.addAll(inst.use);
            }
        }

        // degree.keySet().forEach(i -> System.out.println("degree: " + i + ": " + degree.get(i)));
    }

    // 冲突图连边
    void add_edge(ASMReg u, ASMReg v)
    {
        if (!adj_set.contains(new Pair<>(u, v)) && u != v)
        {
            for (int i = 0; i < 32; i++)
                if (PhyReg.type_list.get(i) == 2)
                {
                    if (u == PhyReg.phy_regs.get(PhyReg.name_list.get(i)))
                        return;
                    if (v == PhyReg.phy_regs.get(PhyReg.name_list.get(i)))
                        return;
                }
            
            // System.out.println("add_edge: " + u + ", " + v);
            adj_set.add(new Pair<>(u, v));
            adj_set.add(new Pair<>(v, u));
            if (!precolored_nodes.contains(u))
            {
                adj_list.get(u).add(v);
                degree.replace(u, degree.get(u) + 1);
            }
            if (!precolored_nodes.contains(v))
            {
                adj_list.get(v).add(u);
                degree.replace(v, degree.get(v) + 1);
            }
        }
    }

    // 维护 worklist
    void make_worklist()
    {
        for (var reg : initial_nodes)
        {
            if (degree.get(reg) >= K)
                spill_nodes.add(reg);
            else if (move_related(reg))
                freeze_nodes.add(reg);
            else
            {
                assert reg != null;
                simplify_nodes.add(reg);
            }
        }
    }

    // 得到仍活跃的相邻点
    HashSet<ASMReg> adjacent(ASMReg reg)
    {
        HashSet<ASMReg> ans = new HashSet<>(adj_list.get(reg));
        ans.removeAll(select_nodes);
        ans.removeAll(coalesced_nodes);
        return ans;
    }

    // 得到仍活跃的 Move 指令
    HashSet<ASMMv> node_moves(ASMReg reg)
    {
        HashSet<ASMMv> ans = new HashSet<>(active_mvs);
        ans.addAll(worklist_mvs);
        ans.retainAll(move_list.get(reg));
        return ans;
    }

    // 属于某条仍活跃的 Move 指令
    boolean move_related(ASMReg reg)
    {
        return !node_moves(reg).isEmpty();
    }

    // 简化
    void simplify()
    {
        ASMReg reg = simplify_nodes.iterator().next();
        // System.out.println("simplify:" + reg);
        simplify_nodes.remove(reg);
        select_nodes.push(reg);
        // 邻结点度数 -1
        adjacent(reg).forEach(i -> decrease_degree(i));
    }

    // 减少度数
    void decrease_degree(ASMReg reg)
    {
        int deg = degree.get(reg);
        degree.replace(reg, deg - 1);

        // 度数从 K 变为 K - 1，从 spill_nodes 中弹出
        // 根据塞入是否传送有关 freeze 或者 simplify
        if (deg == K)
        {
            HashSet<ASMReg> regs = new HashSet<>(adjacent(reg));
            regs.add(reg);
            enable_moves(regs);
            spill_nodes.remove(reg);
            if (move_related(reg))
                freeze_nodes.add(reg);
            else
            {
                assert reg != null;
                // System.out.println("add:" + reg);
                simplify_nodes.add(reg);
            }
        }
    }

    // 相关的传送指令将可能合并
    void enable_moves(HashSet<ASMReg> regs)
    {
        for (var reg : regs)
        {
            for (var mv : node_moves(reg))
            if (active_mvs.contains(mv))
            {
                active_mvs.remove(mv);
                worklist_mvs.add(mv);
            }
        }
    }

    // 尝试合并
    void coalesce()
    {
        ASMMv mv = worklist_mvs.iterator().next();
        ASMReg x = get_alias(mv.rd);
        ASMReg y = get_alias(mv.rs);

        // 若有预染色节点，保证 u 是预染色节点
        ASMReg u, v;
        if (precolored_nodes.contains(y))
        {
            u = y;
            v = x;
        }
        else
        {
            u = x;
            v = y;
        }

        // if (cnt == 1)
        // System.out.println("coalesce:" + u + ", " + v);

        worklist_mvs.remove(mv);
        if (u == v)
        {
            // u, v 相同直接合并
            coalesced_mvs.add(mv);
            add_worklist(u);
        }
        else if (precolored_nodes.contains(v) || adj_set.contains(new Pair<>(u, v)) || u == PhyReg.phy_regs.get("zero") || v == PhyReg.phy_regs.get("zero"))
        {
            // u, v 冲突，不能合并，因此受抑制
            constrained_mvs.add(mv);
            add_worklist(u);
            add_worklist(v);
        }
        else
        {
            // 若存在预染色节点，则使用 George 合并测试
            boolean flag = true;
            for (var t : adjacent(v))
            {
                // System.out.println("t: " + t + ", u: " + u + ", " + flag + ", " + (degree.get(t) < K || precolored_nodes.contains(t) || adj_set.contains(new Pair<>(t, u))));
                flag &= OK(t, u);
            }

            // 若都为普通节点，则使用 Briggs 合并测试
            if (!precolored_nodes.contains(u))
            {
                HashSet<ASMReg> adj = adjacent(u);
                adj.addAll(adjacent(v));
                flag = conservative(adj);
            }
            
            if (flag)
            {
                combine(u, v);
                add_worklist(u);
            }
            else
                active_mvs.add(mv);
        }
    }

    // 可以简化则加入简化 worklist
    void add_worklist(ASMReg reg)
    {
        if (!precolored_nodes.contains(reg) && !move_related(reg) && degree.get(reg) < K)
        {
            freeze_nodes.remove(reg);
            assert reg != null;
            // System.out.println("add:" + reg);
            simplify_nodes.add(reg);
        }
    }

    // George 合并测试
    boolean OK(ASMReg t, ASMReg r)
    {
        return degree.get(t) < K || precolored_nodes.contains(t) || adj_set.contains(new Pair<>(t, r));
    }

    // 高度数邻节点数 < K
    boolean conservative(HashSet<ASMReg> regs)
    {
        int k = 0;
        for (var reg : regs)
            k += degree.get(reg) >= K ? 1 : 0;
        return k < K;
    }

    // 找到合并到的最终节点（可以用并查集优化，但没必要？
    ASMReg get_alias(ASMReg reg)
    {
        return coalesced_nodes.contains(reg) ? get_alias(alias.get(reg)) : reg;
    }

    // 合并
    void combine(ASMReg u, ASMReg v)
    {
        // if (cnt == 1)
        // System.out.println("combine: " + u + ", " + v);

        if (freeze_nodes.contains(v))
            freeze_nodes.remove(v);
        else
            spill_nodes.remove(v);

        coalesced_nodes.add(v);
        alias.replace(v, u);

        // v 传送指令变成 u 的传送指令
        move_list.get(u).addAll(move_list.get(v));
        // 若存在一个结点 x 与 u, v 都冲突，合并后度数减少，可能导致可以合并
        // 若 x 本身是传送相关的，那么与 x 本身关联的传送指令也加入 worklist
        HashSet<ASMReg> v_regs = new HashSet<>(){{add(v);}};
        enable_moves(v_regs);
        for (var t : adjacent(v))
        {
            add_edge(t, u);
            decrease_degree(t);
        }

        if (degree.get(u) >= K && freeze_nodes.contains(u))
        {
            freeze_nodes.remove(u);
            spill_nodes.add(u);
        }
    }

    // 冻结
    void freeze()
    {
        ASMReg reg = freeze_nodes.iterator().next();
        freeze_nodes.remove(reg);
        assert reg != null;
        // System.out.println("add:" + reg);
        simplify_nodes.add(reg);
        freeze_moves(reg);
    }

    void freeze_moves(ASMReg reg)
    {
        for (var mv : node_moves(reg))
        {
            // 找到传送指令中 reg 之外的一个结点 v
            ASMReg v;
            if (get_alias(mv.rs) == get_alias(reg))
                v = get_alias(mv.rd);
            else
                v = get_alias(mv.rs);

            active_mvs.remove(mv);
            frozen_mvs.add(mv);

            // 若 v 不再传送相关且低度数，则加入 simplify
            if (node_moves(v).isEmpty() && degree.get(v) < K)
            {
                freeze_nodes.remove(v);
                assert reg != null;
                // System.out.println("add:" + v);
                simplify_nodes.add(v);
            }
        }
    }

    // 选择溢出结点
    void select_spill()
    {
        // 权重：(use + def) / degree
        double min = Double.POSITIVE_INFINITY;
        ASMReg spill = null;
        for (var reg : spill_nodes)
        {
            if (degree.get(reg) != 0)
            {
                double val = 1.0 * val_mp.get(reg) / degree.get(reg);
                // System.out.println(reg + ": " + val + ", " + degree.get(reg)); 
                if (val < min)
                {
                    min = val;
                    spill = reg;
                }
            }
        }
        // System.out.println("spill:" + spill);
        spill_nodes.remove(spill);
        assert spill != null;
        // System.out.println("spill:" + spill);
        simplify_nodes.add(spill);
        freeze_moves(spill);
    }

    // 分配颜色
    void assign_color()
    {
        // System.out.println("==================");
        while (!select_nodes.empty())
        {
            ASMReg reg = select_nodes.pop();
            ArrayList<Integer> ok_colors = new ArrayList<>();
            for (int i = 0; i < 32; i++)
                if (PhyReg.type_list.get(i) != 2)
                    ok_colors.add(i);

            // 删除邻结点颜色
            for (var w : adj_list.get(reg))
            {
                // System.out.println("del:" + reg + ", " + w + ", " + get_alias(w));
                if (colored_nodes.contains(get_alias(w)) || precolored_nodes.contains(get_alias(w)))
                {
                    // System.out.println("del:" + reg + ", " + w + ", " + get_alias(w) + ": " + color.get(get_alias(w)));
                    ok_colors.remove(color.get(get_alias(w)));
                }
            }

            if (ok_colors.isEmpty())
            {
                // System.out.println("spilled:" + reg);
                spilled_nodes.add(reg);
            }
            else
            {
                colored_nodes.add(reg);
                int col = -1;
                for (var i : ok_colors)
                    if (PhyReg.type_list.get(i) == 0)
                    {
                        col = i;
                        break;
                    }
                if (col == -1)
                    col = ok_colors.iterator().next();
                
                // ok_colors.forEach(i -> System.out.println("ok:" + i));
                // System.out.println(reg + ": " + Integer.toString(col));
                color.put(reg, col);
            }
        }

        // coalesced_nodes.forEach(reg -> System.out.println(reg + ", " + get_alias(reg)));
        coalesced_nodes.forEach(reg -> color.put(reg, color.get(get_alias(reg))));
        // color.keySet().forEach(i -> System.out.println("color: " + i + ": " + PhyReg.phy_regs.get(PhyReg.name_list.get(color.get(i)))));
    }

    void rewrite_program(ASMFunc func)
    {
        for (var n : spilled_nodes)
        {
            assert n instanceof VirReg;
            VirReg reg = (VirReg)n;
            if (reg.addr.offset == null)
            {
                reg.addr.offset = new Immediate(offset);
                offset += 4;
            }
        }

        // System.out.println("offset:" + Integer.toString(offset));

        for (int i = 0; i < func.para.size(); i++)
        {
            func.para.get(i).offset = new Immediate(offset + i * 4);
        }

        for (var block : func.blocks)
        {
            ArrayList<ASMInst> new_ins = new ArrayList<>();

            for (var inst : block.asm_ins)
            {
                ArrayList<ASMReg> use = new ArrayList<>(inst.use);
                ArrayList<ASMReg> def = new ArrayList<>(inst.def);

                for (var x : use)
                if (spilled_nodes.contains(x))
                {
                    VirReg reg = (VirReg)x;
                    VirReg temp1 = new VirReg(reg.name + "_spilled_1");
                    VirReg temp2 = new VirReg(reg.name + "_spilled_2");
                    VirReg temp3 = new VirReg(reg.name + "_spilled_3");
                    inst.change(reg, temp1);

                    if (reg.addr.offset.val < 2048)
                        new_ins.add(new ASMLoad(load_op_type.lw, temp1, reg.addr));
                    else
                    {
                        new_ins.add(new ASMLi(temp2, reg.addr.offset));
                        new_ins.add(new ASMBinary(binary_op_type.add, temp3, reg.addr.base, temp2));
                        new_ins.add(new ASMLoad(load_op_type.lw, temp1, new ASMAddr(temp3, new Immediate(0))));
                    }
                }
                
                new_ins.add(inst);

                for (var x : def)
                if (spilled_nodes.contains(x))
                {
                    VirReg reg = (VirReg)x;
                    VirReg temp1 = new VirReg(reg.name + "_spilled_1");
                    VirReg temp2 = new VirReg(reg.name + "_spilled_2");
                    VirReg temp3 = new VirReg(reg.name + "_spilled_3");
                    inst.change(reg, temp1);

                    if (reg.addr.offset.val < 2048)
                        new_ins.add(new ASMStore(store_op_type.sw, temp1, reg.addr));
                    else
                    {
                        new_ins.add(new ASMLi(temp2, reg.addr.offset));
                        new_ins.add(new ASMBinary(binary_op_type.add, temp3, reg.addr.base, temp2));
                        new_ins.add(new ASMStore(store_op_type.sw, temp1, new ASMAddr(temp3, new Immediate(0))));
                    }
                }
            }

            block.asm_ins = new_ins;
        }      
    }

    void work(ASMFunc func)
    {
        // System.out.println("============" + func + "============");

        precolored_nodes = new HashSet<>();
        initial_nodes = new HashSet<>();
        simplify_nodes = new HashSet<>();
        freeze_nodes = new HashSet<>();
        spill_nodes = new HashSet<>();
        spilled_nodes = new HashSet<>();
        coalesced_nodes = new HashSet<>();
        colored_nodes = new HashSet<>();
        select_nodes = new Stack<>();

        coalesced_mvs = new HashSet<>();
        constrained_mvs = new HashSet<>();
        frozen_mvs = new HashSet<>();
        worklist_mvs = new HashSet<>();
        active_mvs = new HashSet<>();

        adj_set = new HashSet<>();
        adj_list = new HashMap<>();
        degree = new HashMap<>();
        move_list = new HashMap<>();
        alias = new HashMap<>();
        color = new HashMap<>();

        val_mp = new HashMap<>();

        for (var block : func.blocks)
        {
            for (var inst : block.asm_ins)
            {
                initial_nodes.addAll(inst.def);
                initial_nodes.addAll(inst.use);
            }
        }

        for (int i = 0; i < 32; i++)
        {
            String name = PhyReg.name_list.get(i);
            precolored_nodes.add(PhyReg.phy_regs.get(name));
            color.put(PhyReg.phy_regs.get(name), i);
        }

        for (var reg : initial_nodes)
        {
            adj_list.put(reg, new HashSet<>());
            move_list.put(reg, new HashSet<>());
            degree.put(reg, 0);
            alias.put(reg, null);

            if (precolored_nodes.contains(reg))
            {
                val_mp.put(reg, Integer.MAX_VALUE);
                degree.replace(reg, Integer.MAX_VALUE);
            }
            else
                val_mp.put(reg, 0);
        }
        initial_nodes.removeAll(precolored_nodes);

        liveness_analysis(func);
        build(func);

        // val_mp.keySet().forEach(i -> System.out.println(i + ":" + Double.toString(val_mp.get(i))));

        make_worklist();
        while (true)
        {
            if (!simplify_nodes.isEmpty())
                simplify();
            else if (!worklist_mvs.isEmpty())
                coalesce();
            else if (!freeze_nodes.isEmpty())
                freeze();
            else if (!spill_nodes.isEmpty())
                select_spill();
            else
                break;
        }

        assign_color();
        
        if (!spilled_nodes.isEmpty() && cnt < 1)
        {
            // cnt++;
            // System.out.println("========================================" + func);
            rewrite_program(func);
            work(func);
        }
    }

    int cnt;
    int offset = 0;
    public RegAlloc(ASMGlobal global)
    {
        for (var func : global.funcs)
        {
            cnt = 0;
            offset = 0;

            for (var call : func.call)
            {
                offset = Integer.max(offset, call.size());
                for (int i = 0; i < call.size(); i++)
                    call.get(i).offset = new Immediate(i << 2);
            }

            offset <<= 2;

            work(func);

            for (var block : func.blocks)
            {
                ArrayList<ASMInst> new_ins = new ArrayList<>();
                if (block == func.blocks.get(0))
                {
                    if (offset <= 2048)
                        new_ins.add(new ASMBinary(binary_op_type.addi, PhyReg.phy_regs.get("sp"), PhyReg.phy_regs.get("sp"), new Immediate(-offset)));
                    else
                    {
                        new_ins.add(new ASMLi(PhyReg.phy_regs.get("t0"), new Immediate(offset)));
                        new_ins.add(new ASMBinary(binary_op_type.sub, PhyReg.phy_regs.get("sp"), PhyReg.phy_regs.get("sp"), PhyReg.phy_regs.get("t0")));
                    }
                }

                for (var inst : block.asm_ins)
                {
                    ArrayList<ASMReg> use = new ArrayList<>(inst.use);
                    ArrayList<ASMReg> def = new ArrayList<>(inst.def);
                    for (var x : use)
                    if (x instanceof VirReg)
                    {
                        VirReg reg = (VirReg)x;
                        if (color.containsKey(reg) && color.get(reg) != null)
                        inst.change((VirReg)x, PhyReg.phy_regs.get(PhyReg.name_list.get(color.get(reg))));
                    }

                    for (var x : def)
                    if (x instanceof VirReg)
                    {
                        VirReg reg = (VirReg)x;
                        if (color.containsKey(reg) && color.get(reg) != null)
                        inst.change((VirReg)x, PhyReg.phy_regs.get(PhyReg.name_list.get(color.get(reg))));
                    }

                    if (inst instanceof ASMRet)
                    {
                        if (offset < 2048)
                            new_ins.add(new ASMBinary(binary_op_type.addi, PhyReg.phy_regs.get("sp"), PhyReg.phy_regs.get("sp"), new Immediate(offset)));
                        else
                        {
                            new_ins.add(new ASMLi(PhyReg.phy_regs.get("t0"), new Immediate(offset)));
                            new_ins.add(new ASMBinary(binary_op_type.add, PhyReg.phy_regs.get("sp"), PhyReg.phy_regs.get("sp"), PhyReg.phy_regs.get("t0")));
                        }
                    }

                    if (inst instanceof ASMMv)
                    {
                        ASMMv mv = (ASMMv) inst;
                        if (color.get(mv.rd) == color.get(mv.rs))
                            continue;
                    }
                    new_ins.add(inst);
                }

                block.asm_ins = new_ins;
            }            
        }
    }
}
