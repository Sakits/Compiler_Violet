grammar Mxstar;

program : (functionDef | varDef ';' | classDef)*;

classDef :
    Class Identifier 
    '{'
        (functionDef | constructFuncDef | varDef ';')*
    '}' ';'
    ;

functionDef : returnType Identifier '(' (funcVarDef (',' funcVarDef)*)? ')' suite;
lambdaFunc : '[' '&' ']' ('(' (funcVarDef (',' funcVarDef)*)? ')')? '->' suite '(' (expression (',' expression)*)? ')';
constructFuncDef : Identifier '(' (funcVarDef (',' funcVarDef)*)? ')' suite;

suite : '{' statement* '}';

statement
    : suite                                                         # blockTag
    | ifStat                                                        # ifTag
    | Return expression? ';'                                        # returnTag
    | whileStat                                                     # whileTag
    | forStat                                                       # forTag
    | Break ';'                                                     # breakTag
    | Continue ';'                                                  # continueTag
    | varDef ';'                                                    # varTag
    | expression ';'                                                # exprTag
    | ';'                                                           # semiTag
    ; 

funcVarDef : varType oneVarDef;
varDef : varType oneVarDef (',' oneVarDef)* ;
oneVarDef : Identifier ('=' expression )?;

expression
    : primary                                                       # primaryTag
    | '(' expression ')'                                            # exprinTag
    | New newType ('(' expression? ')')?                            # nvarTag
    | expression '[' expression ']'                                 # addrTag
    | expression '(' (expression (',' expression)*)? ')'            # callTag
    | expression '.' Identifier                                     # objTag
    | op = '!' expression                                           # prefixTag
    | op = '~' expression                                           # prefixTag
    | <assoc=right> expression op = ('++' | '--')                   # suffixTag
    | <assoc=right> op = ('++' | '--') expression                   # prefixTag
    | op = '-' expression                                           # prefixTag
    | op = '+' expression                                           # prefixTag
    | expression op = ('*' | '/' | '%') expression                  # binaryTag
    | expression op = ('+' | '-') expression                        # binaryTag
    | expression op = ('<<' | '>>') expression                      # binaryTag
    | expression op = ('<' | '<=' | '>' | '>=') expression          # binaryTag
    | expression op = ('==' | '!=') expression                      # binaryTag
    | expression op = '&' expression                                # binaryTag
    | expression op = '^' expression                                # binaryTag
    | expression op = '|' expression                                # binaryTag
    | expression op = '&&' expression                               # binaryTag
    | expression op = '||' expression                               # binaryTag
    | <assoc=right> expression '=' expression                       # assignTag
    | lambdaFunc                                                    # lambdaTag
    ;

ifStat :
    If '(' expression ')' 
        trueSt = statement
    (Else
        falseSt = statement)?
    ;

whileStat : While '(' expression ')' statement;
forStat : For '(' (varDef | init = expression)? ';' cond = expression? ';' next = expression? ')' statement;

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
// getText : 'getText';

// Length : 'length';
// Substring : 'substring';
// ParseInt : 'parseInt';
// Ord : 'ord';

ConstString : '"' ('\\"' | '\\''\\'  | .)*? '"';
Identifier : [a-zA-Z][a-zA-Z0-9_]*;
Number : [1-9][0-9]* | '0';

Indent : [ \t]+ -> skip;
Newline : ('\r' '\n'? | '\n') -> skip; 
Comment : ('//' ~[\r\n]* | '/*' .*? '*/') -> skip;
