grammar expr;

//tokens
VAR    : 'var';
TRUE   : 'true';
FALSE  : 'false';
ASSIGN : '=';
SEMICOLON : ';';
LPAREN : '(';
RPAREN : ')';

ADD    : '+';
SUB    : '-';
MUL    : '*';
DIV    : '/';
LT     : '<';
GT     : '>';
LE     : '<=';
GE     : '>=';
EQ     : '==';
NEQ    : '!=';
AND    : '&&';
OR     : '||';
NOT    : '!';


ID     : [a-zA-Z][a-zA-Z0-9]*;
INT    : [0-9]+;
WS     : [ \t\r\n]+ -> skip;

program: (statement SEMICOLON)* EOF ;

statement
    : declaration
    | assignment
    | expression SEMICOLON
    ;

declaration : VAR ID;
assignment : ID ASSIGN expression;

expression
    : logicalOrExpression
    ;

logicalOrExpression
    : logicalAndExpression (OR logicalAndExpression)*
    ;

logicalAndExpression
    : equalityExpression (AND equalityExpression)*
    ;

equalityExpression
    : relationalExpression ((EQ| NEQ) relationalExpression)*
    ;

relationalExpression
    : additiveExpression ((LT | GT | LE | GE) additiveExpression)*
    ;

additiveExpression
    : multiplicativeExpression ((ADD|SUB) multiplicativeExpression)*
    ;

multiplicativeExpression
    : unaryExpression ((MUL | DIV) unaryExpression)*
    ;

unaryExpression
    : NOT unaryExpression
    | primaryExpression
    ;

primaryExpression
    : LPAREN expression RPAREN
    | ID
    | INT
    | TRUE
    | FALSE
    ;


