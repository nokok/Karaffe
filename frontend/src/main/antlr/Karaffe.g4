grammar Karaffe;

compilationUnit 
  : statement*
  ;

statement 
  : expr #exprStmt
  | 'println' '(' expr ')' #printExpr
  ;

expr
  : left=expr op=('*' | '/') right=expr #mulExpr
  | left=expr op=('+' | '-') right=expr #addExpr
  | value=literal #lit
  ;

literal
  : intLiteral
  ;

intLiteral
  : IntegerLiteral
  ;

PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
LPAREN: '(';
RPAREN: ')';

IntegerLiteral
  : NonZeroDigit Digit+
  | Digit
  ;

fragment
NonZeroDigit
  : [1-9]
  ;

fragment
Digit
  : '0'
  | NonZeroDigit
  ;

Whitespace : [ \t\r\n]+ -> skip ;
