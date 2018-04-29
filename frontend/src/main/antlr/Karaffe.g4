grammar Karaffe;

compilationUnit 
  : statement*
  ;

statement 
  : classDef #classDefStmt
  | expr #exprStmt
  | 'println' '(' expr ')' #printExpr
  | 'main' LBRACE statement* RBRACE #mainStmt
  ;

classDef
  : simpleClassDef
  ;

classDefMember
  : statement
  ;

simpleClassDef
  : CLASS identifier classDefBody?
  ;

classDefBody
  : LBRACE classDefMember* RBRACE
  | classDefMember
  ;

expr
  : left=expr op=('*' | '/') right=expr #mulExpr
  | left=expr op=('+' | '-') right=expr #addExpr
  | value=literal #lit
  ;

literal
  : intLiteral
  ;

identifier
  : Identifier
  ;

intLiteral
  : IntegerLiteral
  ;

CLASS: 'class';
PLUS: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
LBRACE: '{';
RBRACE: '}';
LPAREN: '(';
RPAREN: ')';

IntegerLiteral
  : NonZeroDigit Digit+
  | Digit
  ;

Identifier
  : IdentifierHead IdentifierBody*
  ;

fragment IdentifierHead
  : [A-Za-z]
  ;

fragment IdentifierBody
  : IdentifierHead
  | '_'
  | [0-9]
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
