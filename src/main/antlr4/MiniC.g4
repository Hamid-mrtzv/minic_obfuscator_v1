grammar MiniC;

program: (function | declaration)+ EOF;

function: type ID '(' params? ')' block;
params: param (',' param)*;
param: type ID;

declaration: type ID ';';

type: 'int' | 'char' | 'bool';

block: '{' statement* '}';
statement:
    block
    | declaration
    | assignment
    | ifStatement
    | whileStatement
    | forStatement
    | returnStatement
    | ioStatement
    ;

assignment: ID '=' expr ';';
ifStatement: 'if' '(' expr ')' statement ('else' statement)?;
whileStatement: 'while' '(' expr ')' statement;
forStatement: 'for' '(' assignment expr ';' assignment ')' statement;
returnStatement: 'return' expr? ';';
ioStatement: ('printf' | 'scanf') '(' STRING ',' ID ')' ';';

expr: expr op=('*'|'/') expr
    | expr op=('+'|'-') expr
    | ID
    | INT
    | '(' expr ')'
    ;

ID: [a-zA-Z_][a-zA-Z_0-9]*;
INT: [0-9]+;
STRING: '"' .*? '"';

WS: [ \t\r\n]+ -> skip;
