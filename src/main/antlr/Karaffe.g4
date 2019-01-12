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
  : target=expr BIND initializer=expr
  ;

varDef
  : DEF binding (BIND initializer=expr)?
  ;

binding
  : Identifier typeName
  ;

typeName
  : Identifier
  ;

// Expr

expr
  : function=expr LPAREN args=exprList? RPAREN
  | left=expr right=opExpr+
  | target=expr DOT name=Identifier
  | LPAREN inExpr=expr RPAREN
  | lit=literal
  | t=THIS
  | id=Identifier
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

literal
  : StringLiteral
  | IntegerLiteral
  ;

ENTRYPOINT: 'entrypoint';
CLASS: 'class';
INIT: 'init';
THIS: 'this';
DEF: 'def';
BIND: ':=';
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
  | '='
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

