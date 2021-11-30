## 思路

优化：

只要不 new 就不用 alloca 申请栈空间，而是直接使用虚拟寄存器，省去一大部分的 load 和 store，充分使用空闲寄存器

但是为了满足 SSA，需要利用 phi 正确合并控制流

经过思考得出了以下的虚拟寄存器分配算法：

### 虚拟寄存器分配

1. AST 里给每个 Scope 一个独一无二的编号 `scope_num`，使得每个变量 `var` 生成一个 `<scope_num, var_name>` 的二元组，唯一确定一个变量
2. 每个 basic block 开一个 `Map< <scope_num, var_name>, vector<reg_num_offset, lable_name> >`，保存每一个变量的**所有来源的** `<虚拟寄存器编号, basic block 名>` 二元组，使得若 vector 大小超过 1，则这个变量需要 phi，且之后的是所有需要 phi 到这个新增虚拟寄存器的 `<reg_name_offset, lable_name>` 二元组。

第一次 Pass：

1. 每个 basic block 用一个计数器记录当前 basic block 新增了多少个虚拟寄存器 (`offset`)
2. 每次赋值操作时，左边使用新的虚拟寄存器 (`offset += 1`)，右边使用 map 里查找到的寄存器编号，把被赋值的变量寄存器改成新的
3. 每次进行 jump 的时候，把当前 basic block 的 Map 深拷贝给要跳转到的 basic block 的 Map，`lable_name` 为当前 `basic block` 的 lable_name。如果某个位置已经有值，那么新建虚拟内存器(`new_reg++`)，把这个 `<reg_num, lable_name>` 二元组塞进 vector


第二次 Pass：

经过第一次 Pass 后，知道了每个 basic block 有哪些 var 是需要 phi 的

由此通过第二次 Pass 得到每个 basic block (label) 开头可用的虚拟寄存器编号

第三次 Pass：

得到 phi 的所有虚拟寄存器来源的真正虚拟寄存器编号，通过 label 的初始编号 + offset 得到

Scope 里记录每一个 var_name 使用的最后一个寄存器编号




## LLVM IR

### 命令
`clang -S -emit-llvm test.c` 生成 LLVM IR

`opt test.ll -S --O3` 或 `clang -S -emit-llvm -O3 test.c` O3 优化

`llc test.ll` LLVM IR 生成 Assembly Code

`clang main.ll -o main` LLVM IR 生成可执行文件

### IR 类型

##### 返回值
`ret i32 0`

##### 全局变量

`@global_variable = global i32 0`

##### 加法

寄存器以 `%` 开头

`%local_variable = add i32 1, 2`

##### 除法

`%2 = sdiv i8 -6, 2	; get (-6) / 2 = -3`

##### 栈空间

`%local_variable = alloca i32`

全局变量和栈上空间均为指针

##### 读取

`%1 = load i32, i32* @global_variable`

##### 储存

`store i32 1, i32* @global_variable`

##### 数组类型

`%a = alloca [4 x i32]`

##### 字符串

`@global_string = global [12 x i8] c"Hello world\00"`

##### 结构体

```
%MyStruct = type {
	i32,
	i8
}
```

##### 获取聚合类型中元素

`%1 = getelementptr [4 x %MyStruct], [4 x %MyStruct]* %my_structs, i64 2, i32 1 ; %1 is pointer to my_structs[2].y`

第三个参数不可省略

`%1 = getelementptr %MyStruct, %MyStruct* %my_struct, i64 0, i32 1 ; %1 is pointer to my_struct.y`

##### 获得 `my_structs[2].y[3]`

```
%MyStruct = type {
	i32,
	[5 x i32]
}
%my_structs = alloca [4 x %MyStruct]

%1 = getelementptr [4 x %MyStruct], [4 x %MyStruct]* %my_structs, i64 2, i32 1, i64 3
```

##### 获得聚合类型的值

`%2 = extractvalue %MyStruct %1, 1`


##### 修改聚合类型的值

`%3 = insertvalue %MyStruct %1, i32 233, 1`

然后`%3`就会是`%1`将第二个字段赋值为`233`后的值


##### 比较
`icmp` 比较指令 + `uge` 比较方案 + 两个比较参数，返回一个 `i1` 类型的数

`%comparison_result = icmp uge i32 %a, %b`

`eq` 相等 

`ne` 不相等

`sgt, sge, slt, sle` 大于、大于等于、小于、小于等于

##### 有条件跳转
`i1` 类型的值作为判断，`true` 跳转到 `label A`，否则跳转到 `lable B`

`br i1 %comparison_result, label %A, label %B`

##### 无条件跳转
跳转到 start

`br label %start`

##### basic block
一个 basic block 结尾是终止指令

终结指令，就是指改变执行顺序的指令，如跳转、返回等。

##### phi

根据其之前是哪个basic block来决定其返回值

`%x = phi i32 [1, %a], [2, %b], [3, %c]`

##### for
```
func:
	%i = 0
	br label %start
start:
	%i1 = phi i32 [ %i, %func ] [ %i2, %inc ]
	%cmp = icmp slt i32 %i1, 4 ; test if i < 4
	br i1 %cmp, label %body, label %out
body:
	; do something
	br label %inc
inc:
	%i2 = add i32 %i1, 1
	br label %start
out:
	; do something B
```

##### 函数

声明

```
define i32 @foo(i32 %a) {
	ret i32 0
}
```

调用

`%1 = call i32 @foo(i32 1)`

执行：

函数进入：

1. 把函数返回地址压栈
2. 跳转到相应函数的地址
   
函数返回：

1. 弹栈获得函数返回地址
2. 跳转到相应的返回地址

调用约定

所有参数按顺序放入指定寄存器，如果寄存器不够，剩余的则从右往左顺序压栈。

返回值按先后顺序放入寄存器或者放入调用者分配的空间中，如果只有一个返回值，那么就会放在rax里。

### 总表
##### Identifiers 标识符

    @ 全局
    % 局部

    后接字符串      命名量      @name %name
        无符号数字  未命名量    @42   %42


##### 类型系统

    void                                空类型
    <type> *                            指针类型
    <returntype> (<parameter list>)     函数类型
    < <# elements> x <elementtype> >    向量类型
    [<# elements> x <elementtype>]      数组类型
    { <type list> }                     普通结构体类型
    <{ <type list> }>                   打包结构体类型
    metadata                            元数据类型
    label                               标签类型
    token                               词元类型

##### 类型系统(例子)

    void                   空
    i32 *                  指针
    i32 (i32)              函数
    <5 x i32>              向量
    [5 x i32]              数组
    { i32, i32, i32 }      普通结构体
    <{ i32, i32, i32 }>    打包结构体

##### First Class Types 第一类型
    Single Value Types 单值类型
        只在寄存器里头有效

        Integer Type 整数类型

            iN ;N为比特数 (通用描述)

            i1 一个比特整数
            i32 32为整数

        Floating-Point Types 浮点类型

            half      - 16位浮点值
            float     - 32位浮点值
            double    - 64位浮点值
            fp128     - 128位浮点值
            x86_fp80  - 80位浮点值
            ppc_fp128 - 128位浮点值

##### 模块结构
    程序由模块组成，每个模块都是输入程序的翻译单元。
    Hello, world 模块

    ; 定义字符串常量作为全局常量
    @.str = private unnamed_addr constant [13 x i8] c"hello world\0A\00"

    ; 外部声明的 puts 函数
    declare i32 @puts(i8* nocapture) nounwind

    ; main 函数的定义
    define i32 @main() {   ; i32()*
      ; [13 x i8] 转换到 i8...
      %cast210 = getelementptr [13 x i8], [13 x i8]* @.str, i64 0, i64 0

      ; 调用 puts 函数，将字符串写入 stdout
      call i32 @puts(i8* %cast210)
      ret i32 0
    }

    ; 命名元信息
    !0 = !{i32 42, null, !"string"}
    !foo = !{!0}

#### 指令参考
##### Terminator Instructions
    指示当前块完成后，执行哪个块。
    终结指令典型的产生一个 void 值:他们影响控制流，而不是值。(invoke指令是一个例外)

    ret            返回
    br             二元条件分支/无条件转移
    switch         多条件分支
    indirectbr    
    invoke         普通/带异常调用
    resume         抛出异常?
    catchswitch    捕获异常
    catchret   
    cleanupret   
    unreachable    不可到达(无语义)

##### Binary Operations

    add    加
    fadd   浮点加
    sub    减
    fsub   浮点减
    mul    乘
    fmul   浮点乘
    udiv   无符号整数除
    sdib   带符号整数除
    fdiv   浮点除
    urem   无符号整数求余
    srem   带符号整数求余
    frem   浮点数求余

    运算\类型    无符号整数   带符号整数   浮点数
        +               add             fadd
        -               sub             fsub
        *               mul             fmul
        /       udiv        sdiv        fdiv
        %       urem        srem        frem

##### Bitwise Binary Operations

    shl    左移
    lshr   逻辑右移
    ashr   算数右移
    and    与
    or     或
    xor    异或

##### Vector Operations

    extractelement 取出元素
    insertelement  插入元素
    shufflevector 

##### Aggregate Operations

    extractvalue  取出值
    insertvalue   插入值

##### Memory Access and Addressing Operations

    alloca           分配内存
    load             从内存加载
    store            储存到内存
    fence
    cmpxchg
    atomicrmw        自动修改内存
    getelementptr    获取 aggregate(集合) 数据结构的子成员地址

##### Coversion Operations

    这个类型为转换指令(强制类型转换|铸造casting)
    都取一个单一运算对象和一个类型。
    对运算对象提供一系列位转换。

    trunc .. to            截断转换
    zext .. to             零扩展转换
    sext .. to             符号位扩展转换
    fptrunc .. to          浮点截断转换
    fpext .. to            浮点扩展
    fptoui .. to           浮点转无符号整数
    fptosi .. to           浮点转带符号整数
    uitofp .. to           无符号整数转浮点
    sitofp .. to           带符号整数转浮点
    ptrtoint .. to         指针转整数
    inttoptr .. to         整数转指针
    bitcast .. to          位模式转换(重新解释，不改变任何二进制位)
    addrepacecast .. to    地址空间转换
    
##### Other Operations

    icmp          整数比较
    fcmp          浮点数比较
    phi           φ 节点
    select        条件值选择
    call          简单函数调用
    va_arg        可变参数
    landingpad
    catchpad
    cleanuppad

### note

三地址

SSA

block