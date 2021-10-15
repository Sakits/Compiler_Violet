grammar Mxstar;

program : (functionDef | varDef ';' | classDef)*;

classDef :
    Class Identifier 
    '{'
        (functionDef | constructFuncDef | varDef ';')*
    '}' ';'
    ;

functionDef : returnType Identifier '(' functionVarDef ')' suite;
functionVarDef : (varDef (',' varDef)*)?;
lambdaFunc : '[' '&' ']' ('(' functionVarDef ')')? '->' suite '(' expression? ')';
constructFuncDef : Identifier '(' functionVarDef ')' suite;

suite : '{' statement* '}';

statement
    : suite                                                         # block
    | ifStat                                                        # if
    | Return (expression | newVar)? ';'                             # return
    | loopStat                                                      # loop
    | Break ';'                                                     # break
    | Continue ';'                                                  # continue
    | varDef ';'                                                    # var
    | newVar ';'                                                    # nvar
    | expression ';'                                                # expr
    | ';'                                                           # semi
    ; 

varDef : varType oneVarDef (',' oneVarDef)* ;
newVar : New newType ('(' expression? ')')?;
oneVarDef : Identifier ('=' (newVar | expression) )?;

expression
    : primary
    | '(' expression ')'
    | '(' newVar ')'
    | expression '[' expression ']'
    | expression '(' (expression (',' expression)*)? ')'
    | expression '.' Identifier ('(' expression? ')')?
    | '!' expression
    | ('++' | '--') expression 
    | '-' expression
    | '+' expression
    | expression ('++' | '--')
    | expression ('*' | '/' | '%') expression
    | expression ('+' | '-') expression
    | expression ('<<' | '>>') expression
    | expression ('<' | '<=' | '>' | '>=') expression
    | expression ('==' | '!=') expression
    | expression '&' expression
    | expression '^' expression
    | expression '|' expression
    | expression '&&' expression
    | expression '||' expression
    | expression '=' newVar
    | <assoc=right> expression '=' expression
    ;

ifStat :
    If '(' expression ')' 
        statement
    (Else
        statement)?
    ;

loopStat : whileStat | forState;
whileStat : While '(' expression ')' statement;
forState : For '(' (varDef | expression)? ';' expression? ';' expression? ')' statement;

returnType : Void | varType;
varType : basicType ('[' ']')*;
newType : basicType ('[' expression ']')* ('[' ']')*;
basicType
    : Identifier
    | Bool
    | Int
    | String
    ;

primary
    : Identifier
    | Number 
    | Null
    | This
    | True
    | False
    | ConstString
    ;

Int : 'int';
Bool : 'bool';
String : 'string';
Null : 'null';
Void : 'void';
True : 'true';
False : 'false';
If : 'if';
Else : 'else';
For : 'for';
While : 'while';
Break : 'break';
Continue : 'continue';
Return : 'return';
New : 'new';
Class : 'class';
This : 'this';

// Print : 'print';
// Println : 'println';
// PrintInt : 'printInt';
// PrintlnInt : 'printlnInt';
// GetString : 'getString';
// GetInt : 'getInt';
// ToString : 'toString';

// Length : 'length';
// Substring : 'substring';
// ParseInt : 'parseInt';
// Ord : 'ord';

ConstString : '"' ('\\"' | .)*? '"';
Identifier : [a-zA-Z_][a-zA-Z0-9_]*;
Number : [1-9][0-9]* | '0';

Indent : [ \t]+ -> skip;
Newline : ('\r' '\n'? | '\n') -> skip; 
Comment : ('//' ~[\r\n]* | '/*' .*? '*/') -> skip;
