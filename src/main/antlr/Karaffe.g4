grammar Karaffe;

sourceFile
  : classDef* EOF
  ;

classDef
  : CLASS Identifier typeDefBody?
  ;

typeDefBody
  : LBRACE statement* RBRACE
  ;

statement
  : entryPointBlock
  | printFunction
  | varDef
  ;

entryPointBlock
  : ENTRYPOINT LBRACE statement* RBRACE
  ;

varDef
  : DEF Identifier
  ;

expr
  : additiveExpr
  ;

additiveExpr
  : primary
  | left=additiveExpr op=(PLUS | MINUS) right=primary
  ;

primary
  : literal
  | printFunction
  ;

literal
  : StringLiteral
  | IntegerLiteral
  ;

printFunction
  : PRINT LPAREN body=expr? RPAREN
  ;

ENTRYPOINT: 'entrypoint';
CLASS: 'class';
PRINT: 'print';
DEF: 'def';
EQUAL: '=';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
PLUS: '+';
MINUS: '-';

StringLiteral
  : '"' StringChar* '"'
  ;

IntegerLiteral
  : [1-9][0-9]*
  | '0'
  ;

fragment
StringChar
  : ~["\\\r\n]
  ;

Identifier
  : Letter LetterOrDigit*
  ;

fragment
Letter
  : [a-zA-Z]
  ;

fragment
LetterOrDigit
  : [a-zA-Z]
  | [1-9][0-9]*
  ;

WS
  : [ \t\r\n]+ -> skip
  ;

