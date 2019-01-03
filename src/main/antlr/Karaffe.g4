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
  | initBlock
  | varDef
  | expr
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
  : lit=literal
  | t=THIS
  | left=expr op=binaryOperator right=expr
  ;

binaryOperator
  : Identifier
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
INIT: 'init';
THIS: 'this';
DEF: 'def';
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
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
  | OperatorChar+
  ;

fragment
OperatorChar
  : '+'
  | '-'
  | '*'
  | '/'
  | '%'
  | '!'
  | '~'
  | '&'
  | '>'
  | '<'
  | '='
  | '^'
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

