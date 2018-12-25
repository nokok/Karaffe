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
  : 'print' '(' + body=expr? ')'
  ;

ENTRYPOINT: 'entrypoint';
CLASS: 'class';
DEF: 'def';
EQUAL: '=';
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

