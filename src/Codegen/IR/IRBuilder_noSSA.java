package Codegen.IR;

import java.util.HashMap;
import java.util.Stack;

import Codegen.IR.Node.IRBlock.BasicBlock;
import Codegen.IR.Node.IRBlock.Function;
import Codegen.IR.Node.IRBlock.IRGlobal;
import Codegen.IR.Node.IRStat.IRAlloca;
import Codegen.IR.Node.IRStat.IRBinaryExpr;
import Codegen.IR.Node.IRStat.IRBitcast;
import Codegen.IR.Node.IRStat.IRBranch;
import Codegen.IR.Node.IRStat.IRCall;
import Codegen.IR.Node.IRStat.IRCmp;
import Codegen.IR.Node.IRStat.IRGetelementptr;
import Codegen.IR.Node.IRStat.IRJump;
import Codegen.IR.Node.IRStat.IRLoad;
import Codegen.IR.Node.IRStat.IRPhi;
import Codegen.IR.Node.IRStat.IRRet;
import Codegen.IR.Node.IRStat.IRStore;
import Codegen.IR.Node.IRStat.IRBinaryExpr.binary_op_type;
import Codegen.IR.Node.IRStat.IRCmp.cmp_op_type;
import Codegen.IR.Node.IRType.IRBool;
import Codegen.IR.Node.IRType.IRChar;
import Codegen.IR.Node.IRType.IRClass;
import Codegen.IR.Node.IRType.IRInt;
import Codegen.IR.Node.IRType.IRPointer;
import Codegen.IR.Node.IRType.IRStr;
import Codegen.IR.Node.IRType.IRType;
import Codegen.IR.Node.IRType.IRVoid;
import Codegen.IR.Node.IRValue.ConstString;
import Codegen.IR.Node.IRValue.Constant;
import Codegen.IR.Node.IRValue.IRValue;
import Codegen.IR.Node.IRValue.Register;
import Semantic.AST.ASTVisitor;
import Semantic.AST.Node.*;

public class IRBuilder_noSSA extends ASTVisitor
{
    public BasicBlock now_block = null;
    public Function now_func = null;
    public IRClass now_class = null;
    public HashMap<String, IRType> types = new HashMap<>();
    public HashMap<String, Function> funcs = new HashMap<>();
    public IRGlobal global;
    public HashMap<String, ConstString> const_string = new HashMap<>();
    public Stack<BasicBlock> break_to = new Stack<>();
    public Stack<BasicBlock> continue_to = new Stack<>();
    public int reg_cnt = 0, str_cnt = 0;
    
    public IRBuilder_noSSA()
    {
        this.global = new IRGlobal();
    }

    String get_func_name(FuncDefNode now)
    {
        String func_name = "";
        if (now.belong != null)
        {
            if (!now.belong.idt.equals("string"))
                func_name = now.belong.idt + ".";
            else
                func_name = "__builtin_";
        }
        func_name += now.idt;
        return func_name;
    }

    void get_builtin_func()
    {
        Function print = new Function(types.get("void"), "__builtin_print");
        print.para.add(new Register(types.get("string"), false, "s", 0));
        print.is_builtin = true;
        funcs.put("print", print);
        global.funcs.add(print);

        Function println = new Function(types.get("void"), "__builtin_println");
        println.para.add(new Register(types.get("string"), false, "s", 0));
        println.is_builtin = true;
        funcs.put("println", println);
        global.funcs.add(println);

        Function printInt = new Function(types.get("void"), "__builtin_printInt");
        printInt.para.add(new Register(types.get("int"), false, "int", 0));
        printInt.is_builtin = true;
        funcs.put("printInt", printInt);
        global.funcs.add(printInt);

        Function printlnInt = new Function(types.get("void"), "__builtin_printlnInt");
        printlnInt.para.add(new Register(types.get("int"), false, "int", 0));
        printlnInt.is_builtin = true;
        funcs.put("printlnInt", printlnInt);
        global.funcs.add(printlnInt);

        Function getInt = new Function(types.get("int"), "__builtin_getInt");
        getInt.is_builtin = true;
        funcs.put("getInt", getInt);
        global.funcs.add(getInt);

        Function getString = new Function(types.get("string"), "__builtin_getString");
        getString.is_builtin = true;
        funcs.put("getString", getString);
        global.funcs.add(getString);

        Function toString = new Function(types.get("string"), "__builtin_toString");
        toString.para.add(new Register(types.get("int"), false, "x", 0));
        toString.is_builtin = true;
        funcs.put("toString", toString);
        global.funcs.add(toString);
        
        Function malloc = new Function(types.get("string"), "__builtin_malloc");
        malloc.para.add(new Register(types.get("int"), false, "size", 0));
        malloc.is_builtin = true;
        funcs.put(malloc.name, malloc);
        global.funcs.add(malloc);
        
        Function str_add = new Function(types.get("string"), "__builtin_str_add");
        str_add.para.add(new Register(types.get("string"), false, "lhs", 0));
        str_add.para.add(new Register(types.get("string"), false, "rhs", 1));
        str_add.is_builtin = true;
        funcs.put(str_add.name, str_add);
        global.funcs.add(str_add);

        Function str_eq = new Function(types.get("bool"), "__builtin_str_eq");
        str_eq.para.add(new Register(types.get("string"), false, "lhs", 0));
        str_eq.para.add(new Register(types.get("string"), false, "rhs", 1));
        str_eq.is_builtin = true;
        funcs.put(str_eq.name, str_eq);
        global.funcs.add(str_eq);

        Function str_ne = new Function(types.get("bool"), "__builtin_str_ne");
        str_ne.para.add(new Register(types.get("string"), false, "lhs", 0));
        str_ne.para.add(new Register(types.get("string"), false, "rhs", 1));
        str_ne.is_builtin = true;
        funcs.put(str_ne.name, str_ne);
        global.funcs.add(str_ne);

        Function str_gt = new Function(types.get("bool"), "__builtin_str_gt");
        str_gt.para.add(new Register(types.get("string"), false, "lhs", 0));
        str_gt.para.add(new Register(types.get("string"), false, "rhs", 1));
        str_gt.is_builtin = true;
        funcs.put(str_gt.name, str_gt);
        global.funcs.add(str_gt);
        
        Function str_ge = new Function(types.get("bool"), "__builtin_str_ge");
        str_ge.para.add(new Register(types.get("string"), false, "lhs", 0));
        str_ge.para.add(new Register(types.get("string"), false, "rhs", 1));
        str_ge.is_builtin = true;
        funcs.put(str_ge.name, str_ge);
        global.funcs.add(str_ge);

        Function str_lt = new Function(types.get("bool"), "__builtin_str_lt");
        str_lt.para.add(new Register(types.get("string"), false, "lhs", 0));
        str_lt.para.add(new Register(types.get("string"), false, "rhs", 1));
        str_lt.is_builtin = true;
        funcs.put(str_lt.name, str_lt);
        global.funcs.add(str_lt);

        Function str_le = new Function(types.get("bool"), "__builtin_str_le");
        str_le.para.add(new Register(types.get("string"), false, "lhs", 0));
        str_le.para.add(new Register(types.get("string"), false, "rhs", 1));
        str_le.is_builtin = true;
        funcs.put(str_le.name, str_le);
        global.funcs.add(str_le);
        
        Function str_len = new Function(types.get("int"), "__builtin_length");
        str_len.para.add(new Register(types.get("string"), false, "this", 0));
        str_len.is_builtin = true;
        funcs.put(str_len.name, str_len);
        global.funcs.add(str_len);

        Function str_substring = new Function(types.get("string"), "__builtin_substring");
        str_substring.para.add(new Register(types.get("string"), false, "this", 0));
        str_substring.para.add(new Register(types.get("int"), false, "left", 1));
        str_substring.para.add(new Register(types.get("int"), false, "right", 2));
        str_substring.is_builtin = true;
        funcs.put(str_substring.name, str_substring);
        global.funcs.add(str_substring);

        Function str_parseInt = new Function(types.get("int"), "__builtin_parseInt");
        str_parseInt.para.add(new Register(types.get("string"), false, "this", 0));
        str_parseInt.is_builtin = true;
        funcs.put(str_parseInt.name, str_parseInt);
        global.funcs.add(str_parseInt);

        Function str_ord = new Function(types.get("int"), "__builtin_ord");
        str_ord.para.add(new Register(types.get("string"), false, "this", 0));
        str_ord.para.add(new Register(types.get("int"), false, "pos", 1));
        str_ord.is_builtin = true;
        funcs.put(str_ord.name, str_ord);
        global.funcs.add(str_ord);

        Function init_func = new Function(types.get("void"), "__builtin_init");
        funcs.put("__builtin_init", init_func);
        global.funcs.add(init_func);
    }

    public void visit(RootNode now)
    {
        types.put("void", new IRVoid());
        types.put("null", new IRPointer(1, new IRVoid()));
        types.put("bool", new IRBool());
        types.put("int", new IRInt());
        types.put("string", new IRPointer(1, new IRChar()));
        now.cls.forEach(i -> {
            IRClass new_class = new IRClass(i.idt);
            IRPointer class_ptr = new IRPointer(1, new_class);
            types.put(i.idt, class_ptr);
            global.classes.add(new_class);
        });

        now.cls.forEach(i -> {
            IRClass now_class = (IRClass) ((IRPointer) types.get(i.idt)).basic_type;
            i.var.forEach(j -> {
                IRType type = types.get(j.type);
                if (j.dim != 0)
                    type = new IRPointer(j.dim, type);
                now_class.vars.add(type);
            });
        });

        get_builtin_func();

        now.cls.forEach(i -> {
            i.func.forEach(j -> {
                IRType func_type = types.get(j.return_type);
                if (j.dim != 0)
                    func_type = new IRPointer(j.dim, func_type);
                String func_name = get_func_name(j);
                Function new_func = new Function(func_type, func_name);
                new_func.thisptr = new Register(types.get(i.idt), false, "this_ptr", reg_cnt++);
                funcs.put(func_name, new_func);
                global.funcs.add(new_func);
            });
        });

        now.func.forEach(i -> {
            IRType func_type = types.get(i.return_type);
            if (i.dim != 0)
                func_type = new IRPointer(i.dim, func_type);
            String func_name = get_func_name(i);
            Function new_func = new Function(func_type, func_name);
            funcs.put(func_name, new_func);
            global.funcs.add(new_func);
        });

        now_func = funcs.get("__builtin_init");
        now_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(now_block);
        
        now.var.forEach(i -> i.accept(this));
        
        now_func.init_block.irst.add(new IRJump(now_func.blocks.get(1)));
        now_block.irst.add(new IRRet(new Constant(now_func.return_type, 0)));
        
        now.cls.forEach(i -> i.accept(this));
        now.func.forEach(i -> i.accept(this));
    }

    public void visit(ClassDefNode now)
    {
        now_class = (IRClass) ((IRPointer) types.get(now.idt)).basic_type;
        now.func.forEach(i -> i.accept(this));
        now_class = null;
    }

    public void visit(FuncDefNode now)
    {
        now_func = funcs.get(get_func_name(now));
        now_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(now_block);

        if (now.idt.equals("main"))
        {
            now_block.irst.add(new IRCall(null, funcs.get("__builtin_init")));
        }

        if (now_class != null)
            now_func.para.add(now_func.thisptr);

        now.var.forEach(i -> {
            i.accept(this);
            now_func.para.add(i.val);
        });

        if (now.st != null)
            now.st.accept(this);
        
        if (now_block.is_returned == false)
            now_block.irst.add(new IRRet(new Constant(now_func.return_type, 0)));


        now_func.init_block.irst.add(new IRJump(now_func.blocks.get(1)));
        now_block = null;
        now_func = null;
    }

    public void visit(ReturnNode now)
    {
        now_block.is_returned = true;
        if (now.expr != null)
        {
            now.expr.accept(this);
            now.expr.val.type = now_func.return_type;
            now_block.irst.add(new IRRet(now.expr.val));
        }
        else
            now_block.irst.add(new IRRet(new Constant(types.get("void"), 0)));

        now_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(now_block);
    }

    public void visit(FuncVarDefNode now)
    {
        IRType type = types.get(now.one_var.type);
        if (now.dim != 0)
            type = new IRPointer(now.dim, type);

        now.val = new Register(type, false, now.one_var.idt + "_val", reg_cnt++);

        now.one_var.val = now.val;
    }

    public void visit(VarDefNode now)
    {
        now.var.forEach(i -> i.accept(this));
    }

    public void visit(OneVarDefNode now)
    {
        IRType now_type = types.get(now.type);
        if (now.dim != 0)
            now_type = new IRPointer(now.dim, now_type);

        IRValue init_val = null;
        if (now.init_val != null)
        {
            now.init_val.accept(this);
            init_val = now.init_val.val;
        }

        if (now_func.name.equals("__builtin_init"))
        {
            now.val = new Register(now_type, true, now.idt, reg_cnt++);
            if (init_val != null)
            {
                if (init_val instanceof Constant)
                    now.val.init_val = (Constant) init_val;
                else
                {
                    now.ptr.init_val = new Constant(now_type, 0);
                    now_block.irst.add(new IRStore(now.ptr, init_val));
                }
            }
            else
                now.ptr.init_val = new Constant(now_type, 0);

            global.vars.add(now.ptr);
        }
        else if (now_func != null)
        {
            now.ptr = new Register(new IRPointer(1, now_type), false, now.idt, reg_cnt++);
            now_func.init_block.irst.add(new IRAlloca(now.ptr, now_type));
            if (init_val != null)
                now_block.irst.add(new IRStore(now.ptr, init_val));
        }
    }

    public void visit(SuiteNode now)
    {
        now.st.forEach(i -> i.accept(this));
    }

    public void visit(AddrExprNode now)
    {
        now.obj.accept(this);
        now.offset.accept(this);

        IRType type = types.get(now.type);

        Register ptr = new Register(new IRPointer(now.dim + 1, type), false, "now_ptr", reg_cnt++);
        now_block.irst.add(new IRGetelementptr(ptr, now.obj.val, now.offset.val, false));

        if (now.dim == 0)
            now.val = new Register(type, false, "now_val_last", reg_cnt++);
        else
            now.val = new Register(new IRPointer(now.dim, type), false, "now_val_mid", reg_cnt++);
        now_block.irst.add(new IRLoad(now.val, ptr));
    }

    public void visit(AssignExprNode now)
    {
        now.lhs.accept(this);
        now.rhs.accept(this);
        now.lhs.val = now.rhs.val;
    }
    
    public void visit(BinaryExprNode now)
    {
        boolean flag = now.op.equals("||") || now.op.equals("&&");
        IRType type = types.get(now.type);
        if (now.dim != 0)
            type = new IRPointer(now.dim, type);
        
        if (!flag)
        {
            now.lhs.accept(this);
            now.rhs.accept(this);
            IRValue lval = now.lhs.val, rval = now.rhs.val;
            IRType str = types.get("string");
            // IRType null_type = types.get("null");
            binary_op_type op = null;
            cmp_op_type cop = null;
            
            switch (now.op)
            {
                case "+" : 
                    op = binary_op_type.add;

                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val + ((Constant)rval).val);
                    else if (type.equals(str))
                    {
                        Function str_add = funcs.get("__builtin_str_add");
                        now.val = new Register(type, false, "add_str", reg_cnt++);
                        IRCall call = new IRCall(now.val, str_add);
                        call.para.add(lval);
                        call.para.add(rval);
                        now_block.irst.add(call);
                    }
                    else
                    {
                        now.val = new Register(type, false, "add_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;

                case "-" :
                    op = binary_op_type.sub;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val - ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "sub_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;
                
                case "*" :
                    op = binary_op_type.mul;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val * ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "mul_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;

                case "/" :
                    op = binary_op_type.sdiv;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val / ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "div_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;

                case "%" :
                    op = binary_op_type.srem;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val % ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "rem_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;

                case "<<" :
                    op = binary_op_type.shl;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val << ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "shl_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;

                case ">>" :
                    op = binary_op_type.ashr;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val >> ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "shr_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;

                case "&":
                    op = binary_op_type.and;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val & ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "and_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;

                case "|" :
                    op = binary_op_type.or;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val | ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "or_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;

                case "^" :
                    op = binary_op_type.xor;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val ^ ((Constant)rval).val);
                    else
                    {
                        now.val = new Register(type, false, "xor_int", reg_cnt++);
                        now_block.irst.add(new IRBinaryExpr(now.val, op, lval, rval));
                    }
                    break;
                
                case "==" :
                    cop = cmp_op_type.eq;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val == ((Constant)rval).val ? 1 : 0);
                    else if (lval.type.equals(str))
                    {
                        Function str_eq = funcs.get("__builtin_str_eq");
                        now.val = new Register(type, false, "eq_str", reg_cnt++);
                        IRCall call = new IRCall(now.val, str_eq);
                        call.para.add(lval);
                        call.para.add(rval);
                        now_block.irst.add(call);
                    }
                    else
                    {
                        now.val = new Register(type, false, "eq_bool", reg_cnt++);
                        now_block.irst.add(new IRCmp(now.val, cop, lval, rval));
                    }
                    break;

                case "!=" :
                    cop = cmp_op_type.ne;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val != ((Constant)rval).val ? 1 : 0);
                    else if (lval.type.equals(str))
                    {
                        Function str_ne = funcs.get("__builtin_str_ne");
                        now.val = new Register(type, false, "ne_str", reg_cnt++);
                        IRCall call = new IRCall(now.val, str_ne);
                        call.para.add(lval);
                        call.para.add(rval);
                        now_block.irst.add(call);
                    }
                    else
                    {
                        now.val = new Register(type, false, "ne_bool", reg_cnt++);
                        now_block.irst.add(new IRCmp(now.val, cop, lval, rval));
                    }
                    break;

                case ">" :
                    cop = cmp_op_type.sgt;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val > ((Constant)rval).val ? 1 : 0);
                    else if (lval.type.equals(str))
                    {
                        Function str_gt = funcs.get("__builtin_str_gt");
                        now.val = new Register(type, false, "gt_str", reg_cnt++);
                        IRCall call = new IRCall(now.val, str_gt);
                        call.para.add(lval);
                        call.para.add(rval);
                        now_block.irst.add(call);
                    }
                    else
                    {
                        now.val = new Register(type, false, "gt_bool", reg_cnt++);
                        now_block.irst.add(new IRCmp(now.val, cop, lval, rval));
                    }
                    break;

                case ">=" :
                    cop = cmp_op_type.sge;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val >= ((Constant)rval).val ? 1 : 0);
                    else if (lval.type.equals(str))
                    {
                        Function str_ge = funcs.get("__builtin_str_ge");
                        now.val = new Register(type, false, "ge_str", reg_cnt++);
                        IRCall call = new IRCall(now.val, str_ge);
                        call.para.add(lval);
                        call.para.add(rval);
                        now_block.irst.add(call);
                    }
                    else
                    {
                        now.val = new Register(type, false, "ge_bool", reg_cnt++);
                        now_block.irst.add(new IRCmp(now.val, cop, lval, rval));
                    }
                    break;

                case "<" :
                    cop = cmp_op_type.slt;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val < ((Constant)rval).val ? 1 : 0);
                    else if (lval.type.equals(str))
                    {
                        Function str_lt = funcs.get("__builtin_str_lt");
                        now.val = new Register(type, false, "lt_str", reg_cnt++);
                        IRCall call = new IRCall(now.val, str_lt);
                        call.para.add(lval);
                        call.para.add(rval);
                        now_block.irst.add(call);
                    }
                    else
                    {
                        now.val = new Register(type, false, "lt_bool", reg_cnt++);
                        now_block.irst.add(new IRCmp(now.val, cop, lval, rval));
                    }
                    break;

                case "<=" :
                    cop = cmp_op_type.sle;
                    if (lval instanceof Constant && rval instanceof Constant)
                        now.val = new Constant(type, ((Constant)lval).val <= ((Constant)rval).val ? 1 : 0);
                    else if (lval.type.equals(str))
                    {
                        Function str_le = funcs.get("__builtin_str_le");
                        now.val = new Register(type, false, "le_str", reg_cnt++);
                        IRCall call = new IRCall(now.val, str_le);
                        call.para.add(lval);
                        call.para.add(rval);
                        now_block.irst.add(call);
                    }
                    else
                    {
                        now.val = new Register(type, false, "le_bool", reg_cnt++);
                        now_block.irst.add(new IRCmp(now.val, cop, lval, rval));
                    }
                    break;
            }
        }
        else
        {
            now.lhs.accept(this);
            BasicBlock lhs_block = now_block;
            BasicBlock rhs_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
            now_func.blocks.add(rhs_block);
            BasicBlock next_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
            now_func.blocks.add(next_block);
            IRValue lval = now.lhs.val;
            if (now.op.equals("&&"))
            {
                if (lval instanceof Constant)
                {
                    if (((Constant)lval).val == 1)
                    {
                        now_block.irst.add(new IRJump(rhs_block));
                        now_block = rhs_block;

                        now.rhs.accept(this);
                        IRValue rval = now.rhs.val;
                        now.val = rval;
                    }
                    else
                        now.val = lval;
                }
                else
                {
                    now_block.irst.add(new IRBranch(lval, rhs_block, next_block));
                    now_block = rhs_block;

                    now.rhs.accept(this);
                    IRValue rval = now.rhs.val;
                    now.val = new Register(type, false, "phi_and", reg_cnt++);
                    next_block.irst.add(new IRPhi(now.val, new Constant(type, 0), lhs_block, rval, rhs_block));
                }
            }
            else
            {
                if (lval instanceof Constant)
                {
                    if (((Constant)lval).val == 0)
                    {
                        now_block.irst.add(new IRJump(rhs_block));
                        now_block = rhs_block;

                        now.rhs.accept(this);
                        IRValue rval = now.rhs.val;
                        now.val = rval;
                    }
                    else
                        now.val = lval;
                }
                else
                {
                    now_block.irst.add(new IRBranch(lval, next_block, rhs_block));
                    now_block = rhs_block;

                    now.rhs.accept(this);
                    IRValue rval = now.rhs.val;
                    now.val = new Register(type, false, "phi_or", reg_cnt++);
                    next_block.irst.add(new IRPhi(now.val, new Constant(type, 1), lhs_block, rval, rhs_block));
                }
            }

            now_block.irst.add(new IRJump(next_block));
            now_block = next_block;
        }
    }

    public void visit(BracketExprNode now)
    {
        now.expr.accept(this);

        now.val = now.expr.val;
    }

    public void visit(CallExprNode now)
    {
        now.obj.accept(this);
        
        if (!now.func.idt.equals("__builtin_size"))
        {
            now.para.forEach(i -> i.accept(this));

            Function func = funcs.get(get_func_name(now.func));
            IRType type = types.get(now.type);
            if (now.dim != 0)
                type = new IRPointer(now.dim, type);

            now.val = new Register(type, false, "call_now_val", reg_cnt++);
            IRCall call = new IRCall(now.val, func);
            
            if (now.func.belong != null)
            {
                Register this_val;
                if (now.obj instanceof ObjExprNode)
                    this_val = (Register) ((ObjExprNode) now.obj).obj.val;
                else
                    this_val = now_func.thisptr;

                call.para.add(this_val);
            }
            
            now_block.irst.add(call);
            now.para.forEach(i -> call.para.add(i.val));
        }
        else
        {
            Register tmp_ptr = new Register(new IRPointer(1, new IRInt()), false, "tmp_ptr", reg_cnt++);
            now_block.irst.add(new IRBitcast(tmp_ptr, ((ObjExprNode) now.obj).obj.val));
            Register ptr = new Register(new IRPointer(1, new IRInt()), false, "len_ptr", reg_cnt++);
            now_block.irst.add(new IRGetelementptr(ptr, tmp_ptr, new Constant(new IRInt(), -1), false));

            now.val = new Register(new IRInt(), false, "len_val", reg_cnt++);
            now_block.irst.add(new IRLoad(now.val, ptr));
        }
    }

    Register get_new_array(int now_dim, NvarExprNode now)
    {
        IRType type = types.get(now.type);
        // now_func.init_block.irst.add(new IRAlloca(ans, ans.type));
        
        Register len = new Register(new IRInt(), false, "array_len", reg_cnt++);
        now_block.irst.add(new IRBinaryExpr(len, binary_op_type.mul, now.expr.get(now_dim).val, new Constant(new IRInt(), 8)));
        Register malloc_len = new Register(new IRInt(), false, "malloc_len", reg_cnt++);
        now_block.irst.add(new IRBinaryExpr(malloc_len, binary_op_type.add, len, new Constant(new IRInt(), 4)));
        
        Function malloc = funcs.get("__builtin_malloc");
        Register tmp = new Register(new IRPointer(1, new IRChar()), false, "malloc_tmp", reg_cnt++);
        IRCall call = new IRCall(tmp, malloc);
        call.para.add(malloc_len);
        now_block.irst.add(call);
        Register store_len = new Register(new IRPointer(1, new IRInt()), false, "store_len" + (now.dim - now_dim), reg_cnt++);
        now_block.irst.add(new IRBitcast(store_len, tmp));
        now_block.irst.add(new IRStore(store_len, now.expr.get(now_dim).val));

        Register ans = new Register(new IRPointer(1, new IRInt()), false, "ans_" + (now.dim - now_dim), reg_cnt++);
        now_block.irst.add(new IRGetelementptr(ans, store_len, new Constant(new IRInt(), 1), false));
        Register start = new Register(new IRPointer(now.dim - now_dim, type), false, "start", reg_cnt++);
        now_block.irst.add(new IRBitcast(start, ans));


        if (now_dim == now.expr.size() - 1)
            return start;
        
        Register now_offset = new Register(new IRInt(), false, "now_offset", reg_cnt++);
        now_block.irst.add(new IRBinaryExpr(now_offset, binary_op_type.add, new Constant(new IRInt(), 0), new Constant(new IRInt(), 0)));

        BasicBlock cond_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(cond_block);
        BasicBlock body_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(body_block);
        BasicBlock new_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(new_block);

        now_block.irst.add(new IRJump(body_block));
        now_block = body_block;

        Register now_pos = new Register(new IRPointer(now.dim - now_dim, type), false, "start", reg_cnt++);
        now_block.irst.add(new IRGetelementptr(now_pos, start, now_offset, false));
        now_block.irst.add(new IRStore(now_pos, get_new_array(now_dim + 1, now)));

        now_block.irst.add(new IRJump(cond_block));
        now_block = cond_block;

        now_block.irst.add(new IRBinaryExpr(now_offset, binary_op_type.add, now_offset, new Constant(new IRInt(), 1)));

        Register flag = new Register(new IRBool(), false, "flag", reg_cnt++);
        now_block.irst.add(new IRCmp(flag, cmp_op_type.eq, now_offset, now.expr.get(now_dim).val));
        now_block.irst.add(new IRBranch(flag, new_block, body_block));
        now_block = new_block;

        return start;
    }

    public void visit(NvarExprNode now)
    {
        now.expr.forEach(i -> i.accept(this));

        if (now.expr.size() > 0)
            now.val = get_new_array(0, now);
        else
        {
            IRType type = types.get(now.type);
            int size = ((IRPointer)type).basic_type.get_size();
            now.val = new Register(type, false, "class_val", reg_cnt++);

            Function malloc = funcs.get("__builtin_malloc");
            Register tmp = new Register(new IRPointer(1, new IRChar()), false, "malloc_tmp", reg_cnt++);
            IRCall call = new IRCall(tmp, malloc);
            call.para.add(new Constant(new IRInt(), size));
            now_block.irst.add(call);
            now_block.irst.add(new IRBitcast(now.val, tmp));

            if (now.cls.cons != null)
            {
                Function func = funcs.get(get_func_name(now.cls.cons));
                IRCall call_cons = new IRCall(null, func);
                call_cons.para.add(now.val);
                now_block.irst.add(call_cons);
            }
        }
    }

    public void visit(ObjExprNode now)
    {
        now.obj.accept(this);
        if (now.is_func != null) return;
        IRType type = types.get(now.type);
        if (now.dim != 0)
            type = new IRPointer(now.dim, type);

        Register ptr = new Register(new IRPointer(1, type), false, "obj_now_ptr", reg_cnt++);
        now_block.irst.add(new IRGetelementptr(ptr, now.obj.val, new Constant(new IRInt(), now.defnode.offset), true));

        now.val = new Register(type, false, "obj_now_val", reg_cnt++);
        now_block.irst.add(new IRLoad(now.val, ptr));
    }

    public void visit(PrefixExprNode now)
    {
        now.obj.accept(this);

        IRValue val = now.obj.val;
        IRType type = types.get(now.type);
        binary_op_type op;
        switch (now.op)
        {
            case "++" :
                if (val instanceof Constant)
                    now.val = new Constant(type, ((Constant)val).val + 1);
                else
                {
                    op = binary_op_type.add;
                    now.val = new Register(type, false, "pre_add", reg_cnt++);
                    now_block.irst.add(new IRBinaryExpr(now.val, op, val, new Constant(type, 1)));
                }
                now.val = now.obj.val;
                break;
            
            case "--" :
                if (val instanceof Constant)
                    now.val = new Constant(type, ((Constant)val).val - 1);
                else
                {
                    op = binary_op_type.sub;
                    now.val = new Register(type, false, "pre_sub", reg_cnt++);
                    now_block.irst.add(new IRBinaryExpr(now.val, op, val, new Constant(type, 1)));
                }
                now.val = now.obj.val;
                break;

            case "+" :
                now.val = val;
                break;

            case "-" :
                if (val instanceof Constant)
                    now.val = new Constant(type, -((Constant)val).val);
                else
                {
                    op = binary_op_type.sub;
                    now.val = new Register(type, false, "zero_sub", reg_cnt++);
                    now_block.irst.add(new IRBinaryExpr(now.val, op, new Constant(type, 0), val));
                }
                break;
                
            case "~" :
                if (val instanceof Constant)
                    now.val = new Constant(type, ~((Constant)val).val);
                else
                {
                    op = binary_op_type.xor;
                    now.val = new Register(type, false, "zero_sub", reg_cnt++);
                    now_block.irst.add(new IRBinaryExpr(now.val, op, new Constant(type, -1), val));
                }
                break;

            case "!" :
                if (val instanceof Constant)
                    now.val = new Constant(type, ((Constant)val).val != 0 ? 1 : 0);
                else
                {
                    op = binary_op_type.xor;
                    now.val = new Register(type, false, "not_bool", reg_cnt++);
                    now_block.irst.add(new IRBinaryExpr(now.val, op, new Constant(type, 1), val));
                }
                break;
        }
    }

    public void visit(SuffixExprNode now)
    {
        now.obj.accept(this);

        IRValue val = now.obj.val;
        IRType type = types.get(now.type);
        binary_op_type op;
        switch (now.op)
        {
            case "++" :
                if (val instanceof Constant)
                    now.val = new Constant(type, ((Constant)val).val + 1);
                else
                {
                    op = binary_op_type.add;
                    now.val = new Register(type, false, "pre_add", reg_cnt++);
                    now_block.irst.add(new IRBinaryExpr(now.val, op, val, new Constant(type, 1)));
                }
                now.obj.val = now.val;
                break;
            
            case "--" :
                if (val instanceof Constant)
                    now.val = new Constant(type, ((Constant)val).val - 1);
                else
                {
                    op = binary_op_type.sub;
                    now.val = new Register(type, false, "pre_sub", reg_cnt++);
                    now_block.irst.add(new IRBinaryExpr(now.val, op, val, new Constant(type, 1)));
                }
                now.obj.val = now.val; 
                break;
        }
    }

    public void visit(PrimaryExprNode now)
    {
        if (now.cate == 0)
        {
            if (now.is_func != null) return;

            OneVarDefNode def = now.defnode;
            IRType now_type = types.get(def.type);
            if (def.dim != 0)
                now_type = new IRPointer(def.dim, now_type);

            now.ptr = def.ptr;
            if (now.ptr == null)
            {
                IRValue _this = now_func.thisptr;
                
                now.ptr = new Register(new IRPointer(1, now_type), false, now.s + "_ptr", reg_cnt++);
                now_block.irst.add(new IRGetelementptr(now.ptr, _this, new Constant(new IRInt(), def.offset), true));
            }
                
            now.val = new Register(now_type, false, now.s + "_val", reg_cnt++);
            now_block.irst.add(new IRLoad(now.val, now.ptr));

        }
        else if (now.cate == 1)
            now.val = new Constant(types.get("int"), Integer.valueOf(now.s));
        else if (now.cate == 2)
            now.val = new Constant(types.get("null"), 0);
        else if (now.cate == 3)
            now.val = now_func.thisptr;
        else if (now.cate == 4)
            now.val = new Constant(types.get("bool"), 1);
        else if (now.cate == 5)
            now.val = new Constant(types.get("bool"), 0);
        else 
        {
            now.s = now.s.replace("\\\\", "\\");
            now.s = now.s.replace("\\n", "\n");
            now.s = now.s.replace("\\\"", "\"");
            now.s = now.s + "\0";

            ConstString str;
            if (const_string.containsKey(now.s))
                str = const_string.get(now.s);
            else
            {
                str = new ConstString(now.s, new IRPointer(1, new IRStr(now.s.length() - 2)), str_cnt++);
                global.strs.add(str);
                const_string.put(now.s, str);
            }

            now.val = new Register(new IRPointer(1, new IRChar()), false, "string_to_char", reg_cnt++);
            now_block.irst.add(new IRGetelementptr(now.val, str, new Constant(new IRInt(), 0), true));
        }
    }

    public void visit(SemiStNode now)
    {
    }

    public void visit(BreakStNode now)
    {
        now_block.irst.add(new IRJump(break_to.peek()));
        now_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(now_block);
    }

    public void visit(ContinueStNode now)
    {
        now_block.irst.add(new IRJump(continue_to.peek()));
        now_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(now_block);
    }

    public void visit(ExprStNode now)
    {
        now.expr.accept(this);
    }

    public void visit(ForStNode now)
    {
        BasicBlock body_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(body_block);
        BasicBlock cond_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(cond_block);
        BasicBlock next_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(next_block);
        BasicBlock new_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(new_block);

        break_to.push(new_block);
        continue_to.push(next_block);

        if (now.init != null)
            now.init.accept(this);
        now_block.irst.add(new IRJump(cond_block));
        now_block = body_block;

        if (now.st != null)
            now.st.accept(this);
        now_block.irst.add(new IRJump(next_block));
        now_block = cond_block;

        if (now.cond != null)
        {
            now.cond.accept(this);
            now_block.irst.add(new IRBranch(now.cond.val, body_block, new_block));
        }
        else
            now_block.irst.add(new IRJump(body_block));
        now_block = next_block;
        
        if (now.next != null)
            now.next.accept(this);
        now_block.irst.add(new IRJump(cond_block));
        now_block = new_block;

        break_to.pop();
        continue_to.pop();
    }

    public void visit(WhileStNode now)
    {
        BasicBlock cond_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(cond_block);
        BasicBlock body_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(body_block);
        BasicBlock new_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(new_block);

        break_to.push(new_block);
        continue_to.push(cond_block);

        now_block.irst.add(new IRJump(cond_block));
        now_block = cond_block;

        now.cond.accept(this);
        now_block.irst.add(new IRBranch(now.cond.val, body_block, new_block));
        now_block = body_block;

        now.st.accept(this);
        now_block.irst.add(new IRJump(cond_block));
        now_block = new_block;

        break_to.pop();
        continue_to.pop();
    }

    public void visit(IfStNode now)
    {
        BasicBlock true_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(true_block);
        BasicBlock false_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(false_block);
        BasicBlock new_block = new BasicBlock(now_func.name + "_" + now_func.blocks.size());
        now_func.blocks.add(new_block);

        now.cond.accept(this);
        now_block.irst.add(new IRBranch(now.cond.val, true_block, false_block));
        now_block = true_block;

        now.true_st.accept(this);
        now_block.irst.add(new IRJump(new_block));
        now_block = false_block;

        if (now.false_st != null)
            now.false_st.accept(this);
        now_block.irst.add(new IRJump(new_block));
        now_block = new_block;
    }
}
