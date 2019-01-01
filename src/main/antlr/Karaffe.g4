grammar Karaffe;

compilationUnit
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
  | initBlock
  | varDef
  ;

entryPointBlock
  : ENTRYPOINT LBRACE statement* RBRACE
  ;

initBlock
  : INIT LBRACE statement* RBRACE
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
  | THIS
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
INIT: 'init';
THIS: 'this';
DEF: 'def';
EQUAL: '=';
LBRACE: '{';
RBRACE: '}';
PLUS: '+';
MINUS: '-';
DOT: '.';

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

