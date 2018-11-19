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
  ;

entryPointBlock
  : ENTRYPOINT LBRACE statement* RBRACE
  ;

expr
  : additiveExpr
  ;

additiveExpr
  : primary
  | left=additiveExpr op=PLUS right=primary
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
LBRACE: '{';
RBRACE: '}';
PLUS: '+';

StringLiteral
  : '"' StringChar* '"'
  ;

IntegerLiteral
  : [1-9][0-9]*
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

