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
  | assign
  | expr
  ;

entryPointBlock
  : ENTRYPOINT LBRACE statement* RBRACE
  ;

initBlock
  : INIT LBRACE statement* RBRACE
  ;

assign
  : target=expr ':=' initializer=expr
  ;

varDef
  : DEF Identifier
  ;

// Expr

expr
  : lit=literal
  | t=THIS
  | function=expr LPAREN args=exprList? RPAREN
  | left=expr right=opExpr+
  | target=expr DOT name=Identifier
  | LPAREN inExpr=expr RPAREN
  ;

opExpr
  : op=binaryOperator right=expr
  ;

binaryOperator
  : Identifier
  ;

exprList
  : expr (COMMA expr)*
  ;

atom
  : lit=literal
  | t=THIS
  | idt=Identifier DOT t=THIS
  | id=Identifier
  ;

literal
  : StringLiteral
  | IntegerLiteral
  ;

// ====

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
COMMA: ',';

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
  | '^'
  | '|'
  | '#'
  | '='
  | '@'
  | ':'
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

