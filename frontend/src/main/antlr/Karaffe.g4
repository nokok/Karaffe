grammar Karaffe;

compilationUnit 
  : topLevelStatement*
  ;

topLevelStatement
  : classDef
  ;

classDef
  : simpleClassDef
  ;

topLevelExpr
  : expr
  ;

simpleClassDef
  : CLASS identifier classDefBody?
  ;

classDefBody
  : LBRACE classDefMember* RBRACE
  | classDefMember
  ;

classDefMember
  : mainMethodDef
  ;

mainMethodDef
  : MAIN LBRACE statement* RBRACE
  ;

statement
  : expr
  ;

expr
  : additiveExpr
  ;

additiveExpr
  : multiplicativeExpr
  | left=additiveExpr op=(PLUS | MINUS) right=multiplicativeExpr
  ;

multiplicativeExpr
  : primary
  | left=multiplicativeExpr op=(MUL|DIV) right=primary
  ;

primary
  : literal
  | LPAREN expr RPAREN
  | methodInvocation
  ;

methodInvocation
  : identifier LPAREN argumentList? RPAREN
  ;

argumentList
  : expr (COMMA expr)*
  ;

block
  : LBRACE expr RBRACE
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
MAIN: 'main';
COMMA: ',';
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
