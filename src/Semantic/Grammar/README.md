- g4 中每一种语句都构造一个节点，代表一种不同的操作（语义），每个点都应该有一个独一无二的意义
- ASTBuilder 主要是遍历得到检查语法错误所需的树的结构和 Token 的信息，其他都不需要，能递归的就需要递归下去，主要是将文法从中去除方便 SemanticChecker 操作
- SemanticChecker 遍历 AST 来检验各种各样的语法错误
  - 为了处理各种错误，需要自行给各个 Node 添加一些需要维护的信息

需要处理的错误
- 变量作用域，变量 函数 结构体 重名 未定义
  - Scope 维护当前作用域的所有变量
  - Symbols 维护所有类和函数
- Type Match
  - ExprNode 记录 type, dim, is_left_val, is_class, is_func, tobe_left_val
  - OneVarNode 记录 type, dim
  - 两边 type 相同 dim 相同 左边是左值 右边不是 class 或者 func
- 函数有 return
  - 全局维护当前函数是否 return


