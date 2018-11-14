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
  ;

entryPointBlock
  : ENTRYPOINT LBRACE RBRACE
  ;

ENTRYPOINT: 'entrypoint';
CLASS: 'class';
LBRACE: '{';
RBRACE: '}';

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

