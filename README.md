# Compiler_Violet


# Violet_IR

Violet IR is an IR simplified from LLVM IR without guaranteeing SSA in order to achieve some optimizations.

### Identifier
```
@ global
% local
```

### Type
```
void                                    
iN                                      
<type> *                                pointer
<type> (<parameter list>)               function
{<type list>}                           class
```


### Declare
```
@global_string = global i8* "Hello world\00"

define i32 @foo(i32 %a) 
{
	ret i32 0
}

%MyStruct = type 
{
	i32,
	i8
}
```

### Statement

#### alloca

`%local_variable = alloca i32`


#### global variable

`@global_variable = global i32 0`

#### binary
`%local_variable = add %a, %b`
```
add
sub
mul
div
rem
shl
shr
and
or
xor      
```

#### load

`%1 = load @global_variable`

#### store

`store 1, @global_variable`

#### get element
`%1 = get %my_structs, 1`

#### compare
Type of `%a, %b` must be `i32`.

Return an `i1` value.

`%comparison_result = cmp uge %a, %b`

`eq, ne, sgt, sge, slt, sle` 

#### branch
Type of `%comparison_result` must be `i1`.

`br %comparison_result, A, B`

#### jump

`jp start`

#### call

`%1 = call @foo(%variable, 1)`

#### move

`move %a %b`

#### return
`ret 0`