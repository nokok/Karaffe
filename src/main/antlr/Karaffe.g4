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
  | expr
  ;

entryPointBlock
  : ENTRYPOINT LBRACE statement* RBRACE
  ;

varDef
  : DEF Identifier
  ;

expr
  : literal
  | THIS
  | left=expr op=binaryOperator right=expr
  ;

binaryOperator
  : PLUS
  | MINUS
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
THIS: 'this';
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

